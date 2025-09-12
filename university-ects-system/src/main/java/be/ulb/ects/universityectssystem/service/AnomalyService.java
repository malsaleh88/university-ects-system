package be.ulb.ects.universityectssystem.service;

import be.ulb.ects.universityectssystem.dto.*;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AnomalyService {

    private final ExternalApiService externalApiService;

    public AnomalyService(ExternalApiService externalApiService) {
        this.externalApiService = externalApiService;
    }

    public List<AnomalyDto> generateAnomalies() {
        List<InscriptionDto> inscriptions = Optional.ofNullable(externalApiService.getInscriptions())
                .orElse(Collections.emptyList());
        List<CourDto> cours = Optional.ofNullable(externalApiService.getCours())
                .orElse(Collections.emptyList());
        List<NoteDto> notes = Optional.ofNullable(externalApiService.getNotes())
                .orElse(Collections.emptyList());

        Map<String, CourDto> coursMap = cours.stream()
                .filter(c -> c.getMnemonique() != null)
                .collect(Collectors.toMap(CourDto::getMnemonique, c -> c, (c1, c2) -> c1));

        List<AnomalyDto> anomalies = new ArrayList<>();

        // --- INSCRIPTION_SANS_COURS
        for (InscriptionDto insc : inscriptions) {
            if (insc.getCoursJson() == null || insc.getCoursJson().trim().isEmpty()) {
                anomalies.add(new AnomalyDto(
                        "INSCRIPTION_SANS_COURS",
                        insc.getMatricule(),
                        insc.getAnneeEtude(),
                        "Aucune inscription de cours trouvée."
                ));
            }
        }

        // --- NOTE_SANS_INSCRIPTION
        for (NoteDto note : notes) {
            Optional<InscriptionDto> inscOpt = inscriptions.stream()
                    .filter(i -> i.getMatricule().equals(note.getMatricule()))
                    .findFirst();
            if (inscOpt.isEmpty()) {
                anomalies.add(new AnomalyDto(
                        "NOTE_SANS_INSCRIPTION",
                        note.getMatricule(),
                        -1,
                        "Note pour cours " + note.getMnemonique() + " sans inscription."
                ));
            } else {
                InscriptionDto insc = inscOpt.get();
                List<String> coursInscrits = parseCoursJson(insc.getCoursJson());
                if (!coursInscrits.contains(note.getMnemonique())) {
                    anomalies.add(new AnomalyDto(
                            "NOTE_SANS_INSCRIPTION",
                            insc.getMatricule(),
                            insc.getAnneeEtude(),
                            "Note trouvée pour " + note.getMnemonique() + " mais non inscrit."
                    ));
                }
            }
        }

        // --- COURS_INCONNU
        for (InscriptionDto insc : inscriptions) {
            List<String> coursInscrits = parseCoursJson(insc.getCoursJson());
            for (String mnemo : coursInscrits) {
                if (!coursMap.containsKey(mnemo)) {
                    anomalies.add(new AnomalyDto(
                            "COURS_INCONNU",
                            insc.getMatricule(),
                            insc.getAnneeEtude(),
                            "Cours " + mnemo + " non présent dans la liste des cours."
                    ));
                }
            }
        }

        // --- DUPLICATA_NOTE
        Map<String, List<NoteDto>> notesGrouped = notes.stream()
                .collect(Collectors.groupingBy(n -> n.getMatricule() + "-" + n.getMnemonique()));
        for (var entry : notesGrouped.entrySet()) {
            if (entry.getValue().size() > 1) {
                anomalies.add(new AnomalyDto(
                        "DUPLICATA_NOTE",
                        entry.getValue().get(0).getMatricule(),
                        -1,
                        "Plusieurs notes trouvées pour " + entry.getKey()
                ));
            }
        }

        // --- NOTE_SANS_CREDIT
        for (NoteDto note : notes) {
            CourDto cour = coursMap.get(note.getMnemonique());
            if (cour != null && cour.getCredit() <= 0) {
                anomalies.add(new AnomalyDto(
                        "NOTE_SANS_CREDIT",
                        note.getMatricule(),
                        -1,
                        "Cours " + note.getMnemonique() + " noté mais crédit manquant ou <= 0."
                ));
            }
        }

        return anomalies;
    }

    // helper: parse cours_json into list
    private List<String> parseCoursJson(String coursJson) {
        if (coursJson == null || coursJson.isBlank()) {
            return Collections.emptyList();
        }
        return Arrays.stream(coursJson.replace("[", "")
                        .replace("]", "")
                        .replace("\"", "")
                        .split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .toList();
    }
}
