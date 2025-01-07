package STAGE.stage.services.implementation;

import STAGE.stage.dtos.StageDTO;
import STAGE.stage.mappers.EntityMapper;
import STAGE.stage.models.*;
import STAGE.stage.repositories.*;
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
    @Autowired
    private ChefDeFiliereRepository chefDeFiliereRepository;
    @Autowired
    private FiliereRepository filiereRepository;
    @Autowired
    private EntityMapper mapper;

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

    @Override
    public List<StageDTO> getStagesByCF(Long idCf) {
        // Fetch the ChefDeFiliere entity by idCf
        ChefDeFiliere chefDeFiliere = chefDeFiliereRepository.findById(idCf)
                .orElseThrow(() -> new RuntimeException("ChefDeFiliere not found with id: " + idCf));

        // Access the associated Filiere
        Filiere filiere = chefDeFiliere.getFiliere();

        // Check if Filiere is not null to avoid NullPointerException

            Long filiereId = filiere.getIdFiliere();


        // Step 2: Get Etudiant IDs by Filiere ID
            List<Long> etudiantIds = etudiantRepository.findByFiliereIdFiliere(filiereId)
                    .stream()
                    .map(Etudiant::getIdEtu)
                    .toList();

            // Step 3: Get Stages by Etudiant IDs with status "To validate"
            List<Stage> stages = stageRepository.findByEtudiantIdEtuInAndStatut(etudiantIds, "To validate");

            // Step 4: Map entities to DTOs and return result
            return stages.stream()
                    .map(mapper::toDto)
                    .toList();
        }

        @Override
        public List<StageDTO> getValidatedStagesByEcoleId(Long ecoleId) {
            // Fetch validated stages via repository
            List<Stage> stages = stageRepository.findValidatedStagesByEcoleId(ecoleId);

            // Map list of Stage entities to StageDTO
            return stages.stream()
                    .map(stage -> new StageDTO(
                            stage.getIdStage(), stage.getTitre(), stage.getDescription(),
                            stage.getDateDebut(), stage.getDateFin(), stage.getDuree(),
                            stage.getLocalisation(), stage.getMontantRemuneration(),
                            stage.getStatut(), stage.getType(),
                            stage.getEtudiant().getIdEtu(),
                            stage.getOffre() != null ? stage.getOffre().getIdOffre() : null,
                            stage.getEncadrant() != null ? stage.getEncadrant().getIdEncadrant() : null))
                    .toList();
        }

    @Override
    public List<StageDTO> getAValiderStagesByEcoleId(Long ecoleId) {
        // Fetch validated stages via repository
        List<Stage> stages = stageRepository.findAValiderStagesByEcoleId(ecoleId);

        // Map list of Stage entities to StageDTO
        return stages.stream()
                .map(stage -> new StageDTO(
                        stage.getIdStage(), stage.getTitre(), stage.getDescription(),
                        stage.getDateDebut(), stage.getDateFin(), stage.getDuree(),
                        stage.getLocalisation(), stage.getMontantRemuneration(),
                        stage.getStatut(), stage.getType(),
                        stage.getEtudiant().getIdEtu(),
                        stage.getOffre() != null ? stage.getOffre().getIdOffre() : null,
                        stage.getEncadrant() != null ? stage.getEncadrant().getIdEncadrant() : null))
                .toList();
    }


        @Override
        public void setStatusAndDeleteRest(Long etudiantId, Long stageId) {
            // Step 1: Get all stages associated with the student
            List<Stage> stages = stageRepository.findByEtudiantIdEtu(etudiantId);

            // Step 2: Find the stage with the given stageId and update its status
            stages.stream()
                    .filter(stage -> stage.getIdStage().equals(stageId))
                    .findFirst()
                    .ifPresent(stage -> {
                        stage.setStatut("a valider");
                        stageRepository.save(stage); // Save the status update
                    });

            // Step 3: Delete all other stages for the same student
            stages.stream()
                    .filter(stage -> !stage.getIdStage().equals(stageId))
                    .forEach(stageRepository::delete);

    }
    @Override
    public List<StageDTO> getStagesByEtudiantId(Long etudiantId) {
        // Fetch stages by Etudiant ID
        return stageRepository.findByEtudiantIdEtu(etudiantId).stream()
                .map(mapper::toDto) // Convert entities to DTOs using EntityMapper
                .collect(Collectors.toList());
    }
}
