package STAGE.stage.controllers;

import STAGE.stage.dtos.StageDTO;
import STAGE.stage.services.StageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stages")
public class StageController {

    @Autowired
    private StageService stageService;

    @PostMapping
    public StageDTO createStage(@RequestBody StageDTO stageDTO) {
        return stageService.createStage(stageDTO);
    }

    @PutMapping("/{id}")
    public StageDTO updateStage(@PathVariable Long id, @RequestBody StageDTO stageDTO) {
        return stageService.updateStage(id, stageDTO);
    }

    @PutMapping("/{id}/status")
    public StageDTO updateStageStatus(@PathVariable Long id, @RequestParam String newStatus) {
        return stageService.updateStageStatus(id, newStatus);
    }

    @GetMapping
    public List<StageDTO> getAllStages() {
        return stageService.getAllStages();
    }

    @GetMapping("/{id}")
    public StageDTO getStageById(@PathVariable Long id) {
        return stageService.getStageById(id);
    }

    @GetMapping("/by-entreprise/{entrepriseId}")
    public List<StageDTO> getStagesByEntrepriseId(@PathVariable Long entrepriseId) {
        return stageService.getStagesByEntrepriseId(entrepriseId);
    }

    @GetMapping("/status/{status}")
    public List<StageDTO> getStagesByStatus(@PathVariable String status) {
        return stageService.getStagesByStatus(status);
    }

    @DeleteMapping("/{id}")
    public void deleteStage(@PathVariable Long id) {
        stageService.deleteStage(id);
    }

    @GetMapping("/cf/{idCF}/to-validate ")
    public List<StageDTO> getStagesByCF(@PathVariable Long idCF) {
        return stageService.getStagesByCF(idCF);
    }

    @GetMapping("/validated/ecole/{ecoleId}")
    public ResponseEntity<List<StageDTO>> getValidatedStagesByEcoleId(@PathVariable Long ecoleId) {
        List<StageDTO> validatedStages = stageService.getValidatedStagesByEcoleId(ecoleId);
        return ResponseEntity.ok(validatedStages);
    }

    @GetMapping("/AValider/ecole/{ecoleId}")
    public ResponseEntity<List<StageDTO>> getAValidertagesByEcoleId(@PathVariable Long ecoleId) {
        List<StageDTO> validatedStages = stageService.getAValiderStagesByEcoleId(ecoleId);
        return ResponseEntity.ok(validatedStages);
    }

    @PutMapping("/set-status-delete-others/{etudiantId}/{stageId}")
    public ResponseEntity<String> setStatusAndDeleteRest(@PathVariable Long etudiantId, @PathVariable Long stageId) {
        stageService.setStatusAndDeleteRest(etudiantId, stageId);
        return ResponseEntity.ok("Stage status updated and unrelated stages deleted.");
    }

    @GetMapping("/by-etudiant/{etudiantId}")
    public ResponseEntity<List<StageDTO>> getStagesByEtudiantId(@PathVariable Long etudiantId) {
        List<StageDTO> stages = stageService.getStagesByEtudiantId(etudiantId);
        return ResponseEntity.ok(stages);
    }

}