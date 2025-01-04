package STAGE.stage.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import STAGE.stage.dtos.EcoleDTO;
import STAGE.stage.models.Filiere;
import STAGE.stage.services.EcoleService;

import java.util.List;

@RestController
@RequestMapping("/api/ecoles")
@RequiredArgsConstructor
public class EcoleController {

    private final EcoleService ecoleService;

    @PostMapping
    public ResponseEntity<EcoleDTO> createEcole(@RequestBody EcoleDTO ecoleDTO) {
        EcoleDTO createdEcole = ecoleService.createEcole(ecoleDTO);
        return new ResponseEntity<>(createdEcole, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EcoleDTO> updateEcole(@PathVariable Long id, @RequestBody EcoleDTO ecoleDTO) {
        EcoleDTO updatedEcole = ecoleService.updateEcole(id, ecoleDTO);
        return new ResponseEntity<>(updatedEcole, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EcoleDTO> getEcoleById(@PathVariable Long id) {
        EcoleDTO ecoleDTO = ecoleService.getEcoleById(id);
        return new ResponseEntity<>(ecoleDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<EcoleDTO>> getAllEcoles() {
        List<EcoleDTO> ecoles = ecoleService.getAllEcoles();
        return new ResponseEntity<>(ecoles, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEcole(@PathVariable Long id) {
        ecoleService.deleteEcole(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{ecoleId}/filieres/{filiereId}")
    public ResponseEntity<EcoleDTO> addFiliereToEcole(@PathVariable Long ecoleId, @PathVariable Long filiereId) {
        EcoleDTO updatedEcole = ecoleService.addFiliereToEcole(ecoleId, filiereId);
        return new ResponseEntity<>(updatedEcole, HttpStatus.OK);
    }

    // Supprimer une filière d'une école
    @DeleteMapping("/{ecoleId}/filieres/{filiereId}")
    public ResponseEntity<EcoleDTO> removeFiliereFromEcole(@PathVariable Long ecoleId, @PathVariable Long filiereId) {
        EcoleDTO updatedEcole = ecoleService.removeFiliereFromEcole(ecoleId, filiereId);
        return new ResponseEntity<>(updatedEcole, HttpStatus.OK);
    }

    // Mettre à jour une filière d'une école
    @PutMapping("/{ecoleId}/filieres/{filiereId}/update")
    public ResponseEntity<EcoleDTO> updateFiliereInEcole(@PathVariable Long ecoleId, @PathVariable Long filiereId, @RequestBody Filiere newFiliere) {
        EcoleDTO updatedEcole = ecoleService.updateFiliereInEcole(ecoleId, filiereId, newFiliere);
        return new ResponseEntity<>(updatedEcole, HttpStatus.OK);
    }
}
