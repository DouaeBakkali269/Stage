package STAGE.stage.controllers;

import STAGE.stage.dtos.StageDTO;
import STAGE.stage.services.StageService;
import org.springframework.beans.factory.annotation.Autowired;
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
}