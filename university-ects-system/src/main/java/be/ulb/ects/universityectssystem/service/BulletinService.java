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
        List<InscriptionDto> inscriptions = externalApiService.getInscriptions();
        List<CourDto> cours = externalApiService.getCours();
        List<NoteDto> notes = externalApiService.getNotes();

        // Create map for quick lookup
        Map<String, CourDto> coursMap = cours.stream()
                .collect(Collectors.toMap(CourDto::getMnemonique, c -> c));

        List<BulletinDto> bulletins = new ArrayList<>();

        for (InscriptionDto insc : inscriptions) {
            List<String> coursInscrits = parseCoursJson(insc.getCoursJson());
            List<CourDetailDto> details = new ArrayList<>();

            int ectsTotal = 0;
            int ectsObtenus = 0;
            int totalCreditsNotes = 0;
            int totalWeightedNotes = 0;

            for (String mnemo : coursInscrits) {
                CourDto cour = coursMap.get(mnemo);
                if (cour == null) continue; // unknown course (could also be flagged as anomaly)

                ectsTotal += cour.getCredit();

                // Find note
                Optional<NoteDto> noteOpt = notes.stream()
                        .filter(n -> n.getMatricule().equals(insc.getMatricule()) &&
                                n.getMnemonique().equals(mnemo))
                        .findFirst();

                Integer note = noteOpt.map(NoteDto::getNote).orElse(null);

                if (note != null) {
                    totalCreditsNotes += cour.getCredit();
                    totalWeightedNotes += note * cour.getCredit();

                    if (note >= 10) {
                        ectsObtenus += cour.getCredit();
                    }
                }

                details.add(new CourDetailDto(
                        cour.getMnemonique(),
                        cour.getIntitule(),
                        cour.getCredit(),
                        cour.getTitulaire(),
                        note
                ));
            }

            double moyenne = totalCreditsNotes > 0 ?
                    (double) totalWeightedNotes / totalCreditsNotes : 0.0;

            boolean reussite = (ectsObtenus >= 60) ||
                    (details.stream().allMatch(d -> d.getNote() != null) && moyenne >= 10);

            BulletinDto bulletin = new BulletinDto(
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
                            .collect(Collectors.toList())
            );

            bulletins.add(bulletin);
        }

        return bulletins;
    }

    private List<String> parseCoursJson(String coursJson) {
        // coursJson looks like ["MAT101","INF120"]
        coursJson = coursJson.replace("[", "")
                .replace("]", "")
                .replace("\"", "");
        if (coursJson.trim().isEmpty()) return Collections.emptyList();
        return Arrays.asList(coursJson.split(","));
    }


}
