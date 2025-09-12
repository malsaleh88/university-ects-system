package be.ulb.ects.universityectssystem.service;

import be.ulb.ects.universityectssystem.dto.*;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BulletinService {

    private final ExternalApiService externalApiService;

    public BulletinService(ExternalApiService externalApiService) {
        this.externalApiService = externalApiService;
    }

    public List<BulletinDto> generateBulletins() {
        var inscriptions = Optional.ofNullable(externalApiService.getInscriptions()).orElse(List.of());
        var cours = Optional.ofNullable(externalApiService.getCours()).orElse(List.of());
        var notes = Optional.ofNullable(externalApiService.getNotes()).orElse(List.of());

        // ✅ Index courses by mnemonique
        var coursMap = cours.stream()
                .collect(Collectors.toMap(CourDto::getMnemonique, c -> c, (c1, c2) -> c1));

        // ✅ Index notes by (matricule → mnemonique → NoteDto)
        var notesMap = notes.stream()
                .collect(Collectors.groupingBy(
                        NoteDto::getMatricule,
                        Collectors.toMap(NoteDto::getMnemonique, n -> n, (n1, n2) -> n1)
                ));

        List<BulletinDto> bulletins = new ArrayList<>();

        for (InscriptionDto insc : inscriptions) {
            var coursInscrits = parseCoursJson(insc.getCoursJson());
            var details = new ArrayList<CourDetailDto>();

            int ectsTotal = 0;
            int ectsObtenus = 0;
            int totalCreditsNotes = 0;
            int totalWeightedNotes = 0;

            // ✅ Lookup notes in O(1) instead of scanning
            var notesForStudent = notesMap.getOrDefault(insc.getMatricule(), Map.of());

            for (String mnemo : coursInscrits) {
                var cour = coursMap.get(mnemo);
                if (cour == null) continue; // skip unknown courses

                ectsTotal += cour.getCredit();

                var noteDto = notesForStudent.get(mnemo);
                Integer note = (noteDto != null) ? noteDto.getNote() : null;

                if (note != null) {
                    totalCreditsNotes += cour.getCredit();
                    totalWeightedNotes += note * cour.getCredit();
                    if (note >= 10) ectsObtenus += cour.getCredit();
                }

                details.add(new CourDetailDto(
                        cour.getMnemonique(),
                        cour.getIntitule(),
                        cour.getCredit(),
                        cour.getTitulaire(),
                        note
                ));
            }

            double moyenne = totalCreditsNotes > 0
                    ? (double) totalWeightedNotes / totalCreditsNotes
                    : 0.0;

            boolean reussite = (ectsObtenus >= 60) ||
                    (details.stream().allMatch(d -> d.getNote() != null) && moyenne >= 10);

            bulletins.add(new BulletinDto(
                    insc.getMatricule(),
                    insc.getNom(),
                    insc.getPrenom(),
                    insc.getAnneeEtude(),
                    ectsTotal,
                    ectsObtenus,
                    moyenne,
                    reussite,
                    details.stream()
                            .sorted(Comparator.comparing(CourDetailDto::getMnemonique))
                            .toList()
            ));
        }

        return bulletins;
    }

    private List<String> parseCoursJson(String coursJson) {
        if (coursJson == null || coursJson.isBlank()) return List.of();
        return Arrays.stream(coursJson.replace("[", "")
                        .replace("]", "")
                        .replace("\"", "")
                        .split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .toList();
    }
}
