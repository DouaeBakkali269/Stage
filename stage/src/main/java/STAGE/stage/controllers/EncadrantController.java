package STAGE.stage.controllers;

import STAGE.stage.dtos.EncadrantDTO;
import STAGE.stage.services.EncadrantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/encadrants")
@RequiredArgsConstructor
public class EncadrantController {

    private final EncadrantService encadrantService;

    // Create a new Encadrant
    @PostMapping
    public ResponseEntity<EncadrantDTO> createEncadrant(@RequestBody EncadrantDTO dto) {
        EncadrantDTO created = encadrantService.createEncadrant(dto);
        return ResponseEntity.ok(created);
    }

    // Update an existing Encadrant
    @PutMapping("/{id}")
    public ResponseEntity<EncadrantDTO> updateEncadrant(
            @PathVariable Long id,
            @RequestBody EncadrantDTO dto) {
        EncadrantDTO updated = encadrantService.updateEncadrant(id, dto);
        return ResponseEntity.ok(updated);
    }

    // Get Encadrant by ID
    @GetMapping("/{id}")
    public ResponseEntity<EncadrantDTO> getEncadrantById(@PathVariable Long id) {
        EncadrantDTO encadrant = encadrantService.getEncadrantById(id);
        return ResponseEntity.ok(encadrant);
    }

    // Get All Encadrants
    @GetMapping
    public ResponseEntity<List<EncadrantDTO>> getAllEncadrants() {
        List<EncadrantDTO> encadrants = encadrantService.getAllEncadrants();
        return ResponseEntity.ok(encadrants);
    }

    // Delete Encadrant by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEncadrant(@PathVariable Long id) {
        encadrantService.deleteEncadrant(id);
        return ResponseEntity.noContent().build();
    }
}