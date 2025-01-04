package STAGE.stage.controllers;

import STAGE.stage.dtos.CompteEcoleDTO;
import STAGE.stage.services.CompteEcoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/compte-ecoles")
public class CompteEcoleController {

    @Autowired
    private CompteEcoleService compteEcoleService;

    /**
     * Create a new CompteEcole
     */
    @PostMapping
    public CompteEcoleDTO createCompteEcole(@RequestBody CompteEcoleDTO dto) {
        return compteEcoleService.createCompteEcole(dto);
    }

    /**
     * Update an existing CompteEcole by ID
     */
    @PutMapping("/{id}")
    public CompteEcoleDTO updateCompteEcole(@PathVariable Long id, @RequestBody CompteEcoleDTO dto) {
        return compteEcoleService.updateCompteEcole(id, dto);
    }

    /**
     * Get a CompteEcole by ID
     */
    @GetMapping("/{id}")
    public CompteEcoleDTO getCompteEcoleById(@PathVariable Long id) {
        return compteEcoleService.getCompteEcoleById(id);
    }

    /**
     * Get all CompteEcoles
     */
    @GetMapping
    public List<CompteEcoleDTO> getAllComptesEcole() {
        return compteEcoleService.getAllComptesEcole();
    }

    /**
     * Delete a CompteEcole by ID
     */
    @DeleteMapping("/{id}")
    public void deleteCompteEcole(@PathVariable Long id) {
        compteEcoleService.deleteCompteEcole(id);
    }

    @GetMapping("/by-ecole/{ecoleId}")
    public CompteEcoleDTO getCompteEcoleByEcoleId(@PathVariable Long ecoleId) {
        return compteEcoleService.getCompteEcoleByEcoleId(ecoleId);
    }
}