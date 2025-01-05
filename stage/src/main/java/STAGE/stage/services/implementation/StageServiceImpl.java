package STAGE.stage.services.implementation;

import STAGE.stage.dtos.StageDTO;
import STAGE.stage.models.Etudiant;
import STAGE.stage.models.Offre;
import STAGE.stage.models.Stage;
import STAGE.stage.models.Encadrant;
import STAGE.stage.repositories.EtudiantRepository;
import STAGE.stage.repositories.OffreRepository;
import STAGE.stage.repositories.StageRepository;
import STAGE.stage.repositories.EncadrantRepository;
import STAGE.stage.services.StageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StageServiceImpl implements StageService {

    @Autowired
    private StageRepository stageRepository;

    @Autowired
    private OffreRepository offreRepository;

    @Autowired
    private EtudiantRepository etudiantRepository;

    @Autowired
    private EncadrantRepository encadrantRepository;

    @Override
    public StageDTO createStage(StageDTO stageDTO) {
        Stage stage = new Stage();
        stage.setTitre(stageDTO.getTitre());
        stage.setDescription(stageDTO.getDescription());
        stage.setDateDebut(stageDTO.getDateDebut());
        stage.setDateFin(stageDTO.getDateFin());
        stage.setDuree(stageDTO.getDuree());
        stage.setLocalisation(stageDTO.getLocalisation());
        stage.setMontantRemuneration(stageDTO.getMontantRemuneration());
        stage.setStatut(stageDTO.getStatut());
        stage.setType(stageDTO.getType());

        Offre offre = offreRepository.findById(stageDTO.getOffreId())
                .orElseThrow(() -> new RuntimeException("Offre introuvable"));
        Etudiant etudiant = etudiantRepository.findById(stageDTO.getEtudiantId())
                .orElseThrow(() -> new RuntimeException("Etudiant introuvable"));
        Encadrant encadrant = encadrantRepository.findById(stageDTO.getEncadrantId())
                .orElseThrow(() -> new RuntimeException("Encadrant introuvable"));

        stage.setOffre(offre);
        stage.setEtudiant(etudiant);
        stage.setEncadrant(encadrant);

        Stage savedStage = stageRepository.save(stage);
        stageDTO.setIdStage(savedStage.getIdStage());
        return stageDTO;
    }

    @Override
    public StageDTO updateStage(Long id, StageDTO stageDTO) {
        Stage stage = stageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stage introuvable"));

        stage.setTitre(stageDTO.getTitre());
        stage.setDescription(stageDTO.getDescription());
        stage.setDateDebut(stageDTO.getDateDebut());
        stage.setDateFin(stageDTO.getDateFin());
        stage.setDuree(stageDTO.getDuree());
        stage.setLocalisation(stageDTO.getLocalisation());
        stage.setMontantRemuneration(stageDTO.getMontantRemuneration());
        stage.setStatut(stageDTO.getStatut());
        stage.setType(stageDTO.getType());

        Offre offre = offreRepository.findById(stageDTO.getOffreId())
                .orElseThrow(() -> new RuntimeException("Offre introuvable"));
        Etudiant etudiant = etudiantRepository.findById(stageDTO.getEtudiantId())
                .orElseThrow(() -> new RuntimeException("Etudiant introuvable"));
        Encadrant encadrant = encadrantRepository.findById(stageDTO.getEncadrantId())
                .orElseThrow(() -> new RuntimeException("Encadrant introuvable"));

        stage.setOffre(offre);
        stage.setEtudiant(etudiant);
        stage.setEncadrant(encadrant);

        Stage updatedStage = stageRepository.save(stage);
        stageDTO.setIdStage(updatedStage.getIdStage());
        return stageDTO;
    }

    @Override
    public StageDTO getStageById(Long id) {
        Stage stage = stageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stage introuvable"));

        return mapToStageDTO(stage);
    }

    @Override
    public List<StageDTO> getStagesByEntrepriseId(Long entrepriseId) {
        List<Stage> stages = stageRepository.findByOffre_EntrepriseId(entrepriseId);

        return stages.stream().map(this::mapToStageDTO).collect(Collectors.toList());
    }

    @Override
    public List<StageDTO> getStagesByStatus(String status) {
        List<Stage> stages = stageRepository.findByStatut(status);

        return stages.stream().map(this::mapToStageDTO).collect(Collectors.toList());
    }

    @Override
    public StageDTO updateStageStatus(Long id, String newStatus) {
        Stage stage = stageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stage introuvable"));

        stage.setStatut(newStatus);
        Stage updatedStage = stageRepository.save(stage);
        return mapToStageDTO(updatedStage);
    }

    @Override
    public List<StageDTO> getAllStages() {
        return stageRepository.findAll().stream()
                .map(this::mapToStageDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteStage(Long id) {
        if (!stageRepository.existsById(id)) {
            throw new RuntimeException("Stage non trouvé");
        }
        stageRepository.deleteById(id);
    }

    private StageDTO mapToStageDTO(Stage stage) {
        StageDTO dto = new StageDTO();
        dto.setIdStage(stage.getIdStage());
        dto.setTitre(stage.getTitre());
        dto.setDescription(stage.getDescription());
        dto.setDateDebut(stage.getDateDebut());
        dto.setDateFin(stage.getDateFin());
        dto.setDuree(stage.getDuree());
        dto.setLocalisation(stage.getLocalisation());
        dto.setMontantRemuneration(stage.getMontantRemuneration());
        dto.setStatut(stage.getStatut());
        dto.setType(stage.getType());
        dto.setOffreId(stage.getOffre().getIdOffre());
        dto.setEtudiantId(stage.getEtudiant().getIdEtu());
        dto.setEncadrantId(stage.getEncadrant().getIdEncadrant());
        return dto;
    }
}