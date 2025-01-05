package STAGE.stage.controllers;

import STAGE.stage.dtos.EvaluationDTO;
import STAGE.stage.services.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/evaluations")
public class EvaluationController {

    @Autowired
    private EvaluationService evaluationService;

    @PostMapping
    public EvaluationDTO createEvaluation(@RequestBody EvaluationDTO evaluationDTO) {
        return evaluationService.createEvaluation(evaluationDTO);
    }

    @PutMapping("/{id}")
    public EvaluationDTO updateEvaluation(@PathVariable Long id, @RequestBody EvaluationDTO evaluationDTO) {
        return evaluationService.updateEvaluation(id, evaluationDTO);
    }

    @GetMapping("/{id}")
    public EvaluationDTO getEvaluationById(@PathVariable Long id) {
        return evaluationService.getEvaluationById(id);
    }

    @GetMapping
    public List<EvaluationDTO> getAllEvaluations() {
        return evaluationService.getAllEvaluations();
    }

    @DeleteMapping("/{id}")
    public void deleteEvaluation(@PathVariable Long id) {
        evaluationService.deleteEvaluation(id);
    }

    @GetMapping("/by-encadrant/{encadrantId}")
    public List<EvaluationDTO> getEvaluationsByEncadrantId(@PathVariable Long encadrantId) {
        return evaluationService.getEvaluationsByEncadrantId(encadrantId);
    }
}