package STAGE.stage.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import STAGE.stage.dtos.RHDTO;
import STAGE.stage.models.RH;
import STAGE.stage.services.RHService;

import java.util.List;

@RestController
@RequestMapping("/api/rh")
public class RHController {
    @Autowired
    private RHService rhService;

    @PostMapping
    public RHDTO createRH(@RequestBody RHDTO rhDTO) {
        return rhService.createRH(rhDTO);
    }

    // Récupérer un RH par son ID
    @GetMapping("/{id}")
    public RHDTO getRHById(@PathVariable Long id) {
        return rhService.getRHById(id);
    }

    // Récupérer tous les RH
    @GetMapping
    public List<RHDTO> getAllRHs() {
        return rhService.getAllRHs();
    }

    // Mettre à jour un RH existant
    @PutMapping("/{id}")
    public RHDTO updateRH(@PathVariable Long id, @RequestBody RHDTO rhDTO) {
        return rhService.updateRH(id, rhDTO);
    }

    // Supprimer un RH par son ID
    @DeleteMapping("/{id}")
    public void deleteRH(@PathVariable Long id) {
        rhService.deleteRH(id);
    }
    @GetMapping("/entreprise/{entrepriseId}")
    public List<RH> getRHByEntreprise(@PathVariable Long entrepriseId) {
        return rhService.getRHByEntreprise(entrepriseId);
    }
}
