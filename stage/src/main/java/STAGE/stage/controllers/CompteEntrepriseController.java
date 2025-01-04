package STAGE.stage.controllers;

import STAGE.stage.dtos.CompteEntrepriseDTO;
import STAGE.stage.services.CompteEntrepriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/compte-entreprises")
public class CompteEntrepriseController {

    @Autowired
    private CompteEntrepriseService compteEntrepriseService;

    @PostMapping
    public CompteEntrepriseDTO createCompteEntreprise(@RequestBody CompteEntrepriseDTO dto) {
        return compteEntrepriseService.createCompteEntreprise(dto);
    }

    @PutMapping("/{id}")
    public CompteEntrepriseDTO updateCompteEntreprise(@PathVariable Long id, @RequestBody CompteEntrepriseDTO dto) {
        return compteEntrepriseService.updateCompteEntreprise(id, dto);
    }

    @GetMapping("/{id}")
    public CompteEntrepriseDTO getCompteEntrepriseById(@PathVariable Long id) {
        return compteEntrepriseService.getCompteEntrepriseById(id);
    }

    @GetMapping
    public List<CompteEntrepriseDTO> getAllComptesEntreprise() {
        return compteEntrepriseService.getAllComptesEntreprise();
    }

    @DeleteMapping("/{id}")
    public void deleteCompteEntreprise(@PathVariable Long id) {
        compteEntrepriseService.deleteCompteEntreprise(id);
    }

    @GetMapping("/by-entreprise/{entrepriseId}")
    public CompteEntrepriseDTO getCompteEntrepriseByEntrepriseId(@PathVariable Long entrepriseId) {
        return compteEntrepriseService.getCompteEntrepriseByEntrepriseId(entrepriseId);
    }
}