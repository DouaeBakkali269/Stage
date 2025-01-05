package STAGE.stage.controllers;

import STAGE.stage.dtos.ChefDeFiliereDTO;
import STAGE.stage.services.ChefDeFiliereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chefs-de-filiere")
public class ChefDeFiliereController {

    @Autowired
    private ChefDeFiliereService chefDeFiliereService;

    /**
     * Create a new ChefDeFiliere
     */
    @PostMapping
    public ChefDeFiliereDTO createChefDeFiliere(@RequestBody ChefDeFiliereDTO dto) {
        return chefDeFiliereService.createChefDeFiliere(dto);
    }

    /**
     * Update an existing ChefDeFiliere by ID
     */
    @PutMapping("/{id}")
    public ChefDeFiliereDTO updateChefDeFiliere(@PathVariable Long id, @RequestBody ChefDeFiliereDTO dto) {
        return chefDeFiliereService.updateChefDeFiliere(id, dto);
    }

    /**
     * Get a ChefDeFiliere by ID
     */
    @GetMapping("/{id}")
    public ChefDeFiliereDTO getChefDeFiliereById(@PathVariable Long id) {
        return chefDeFiliereService.getChefDeFiliereById(id);
    }

    /**
     * Get all chefs de filière
     */
    @GetMapping
    public List<ChefDeFiliereDTO> getAllChefs() {
        return chefDeFiliereService.getAllChefs();
    }

    /**
     * Delete a ChefDeFiliere by ID
     */
    @DeleteMapping("/{id}")
    public void deleteChefDeFiliere(@PathVariable Long id) {
        chefDeFiliereService.deleteChefDeFiliere(id);
    }

    /**
     * Get chefs de filière by ecoleId
     */
    @GetMapping("/by-ecole/{ecoleId}")
    public List<ChefDeFiliereDTO> getChefsByEcoleId(@PathVariable Long ecoleId) {
        return chefDeFiliereService.getChefsByEcoleId(ecoleId);
    }

    /**
     * Get chefs de filière by filiereId
     */
    @GetMapping("/by-filiere/{filiereId}")
    public List<ChefDeFiliereDTO> getChefsByFiliereId(@PathVariable Long filiereId) {
        return chefDeFiliereService.getChefsByFiliereId(filiereId);
    }
}