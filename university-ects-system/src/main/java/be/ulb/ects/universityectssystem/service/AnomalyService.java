package be.ulb.ects.universityectssystem.service;

import be.ulb.ects.universityectssystem.dto.*;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AnomalyService {


    private final ExternalApiService externalApiService;

    public AnomalyService(ExternalApiService externalApiService) {
        this.externalApiService = externalApiService;
    }

    public List<AnomalyDto> generateAnomalies() {
        var inscriptions = Optional.ofNullable(externalApiService.getInscriptions()).orElse(List.of());
        var cours = Optional.ofNullable(externalApiService.getCours()).orElse(List.of());
        var notes = Optional.ofNullable(externalApiService.getNotes()).orElse(List.of());

        // index courses by mnemonique
        var coursMap = cours.stream()
                .filter(c -> c.getMnemonique() != null)
                .collect(Collectors.toMap(CourDto::getMnemonique, c -> c, (c1, c2) -> c1));

        // ✅ Pre-index inscriptions: matricule -> (InscriptionDto, Set of cours inscrits)
        var inscriptionMap = inscriptions.stream()
                .collect(Collectors.toMap(
                        InscriptionDto::getMatricule,
                        i -> Map.entry(i, new HashSet<>(parseCoursJson(i.getCoursJson()))),
                        (i1, i2) -> i1
                ));

        List<AnomalyDto> anomalies = new ArrayList<>();

        // --- INSCRIPTION_SANS_COURS + COURS_INCONNU (handled in one pass over inscriptions)
        for (var entry : inscriptionMap.values()) {
            var insc = entry.getKey();
            var coursSet = entry.getValue();

            if (coursSet.isEmpty()) {
                anomalies.add(anomaly("INSCRIPTION_SANS_COURS", insc.getMatricule(), insc.getAnneeEtude(),
                        "Aucune inscription de cours trouvée."));
            }

            for (String mnemo : coursSet) {
                if (!coursMap.containsKey(mnemo)) {
                    anomalies.add(anomaly("COURS_INCONNU", insc.getMatricule(), insc.getAnneeEtude(),
                            "Cours " + mnemo + " non présent dans la liste des cours."));
                }
            }
        }

        // --- NOTE_SANS_INSCRIPTION + NOTE_SANS_CREDIT (one pass over notes)
        for (NoteDto note : notes) {
            var inscEntry = inscriptionMap.get(note.getMatricule());
            if (inscEntry == null) {
                anomalies.add(anomaly("NOTE_SANS_INSCRIPTION", note.getMatricule(), -1,
                        "Note pour cours " + note.getMnemonique() + " sans inscription."));
            } else {
                var insc = inscEntry.getKey();
                var coursSet = inscEntry.getValue();
                if (!coursSet.contains(note.getMnemonique())) {
                    anomalies.add(anomaly("NOTE_SANS_INSCRIPTION", insc.getMatricule(), insc.getAnneeEtude(),
                            "Note trouvée pour " + note.getMnemonique() + " mais non inscrit."));
                }
            }

            var cour = coursMap.get(note.getMnemonique());
            if (cour != null && cour.getCredit() <= 0) {
                anomalies.add(anomaly("NOTE_SANS_CREDIT", note.getMatricule(), -1,
                        "Cours " + note.getMnemonique() + " noté mais crédit manquant ou <= 0."));
            }
        }

        // --- DUPLICATA_NOTE (one grouping pass)
        notes.stream()
                .collect(Collectors.groupingBy(n -> n.getMatricule() + "-" + n.getMnemonique()))
                .forEach((key, group) -> {
                    if (group.size() > 1) {
                        anomalies.add(anomaly("DUPLICATA_NOTE", group.get(0).getMatricule(), -1,
                                "Plusieurs notes trouvées pour " + key));
                    }
                });

        return anomalies;
    }

    private AnomalyDto anomaly(String type, String matricule, int annee, String detail) {
        return new AnomalyDto(type, matricule, annee, detail);
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

