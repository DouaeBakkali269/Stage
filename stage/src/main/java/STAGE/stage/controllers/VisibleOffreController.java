package STAGE.stage.controllers;

import STAGE.stage.models.Offre;
import STAGE.stage.services.FiliereService;
import STAGE.stage.services.OffreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/visible-offres")
@RequiredArgsConstructor
public class VisibleOffreController {
    private final FiliereService filiereService;


    @PostMapping("/filiere/{filiereId}/offre/{offreId}/visibility")
    public void setOffreVisibility(@PathVariable Long filiereId,
                                   @PathVariable Long offreId,
                                   @RequestParam Boolean visible) {
        // in tha front visible in post get always value true
        filiereService.setOffreVisibility(filiereId, offreId, visible);
    }

    @GetMapping("/{filiereId}/visible-offres")
    public List<Offre> getVisibleOffresByFiliere(@PathVariable Long filiereId) {
        return filiereService.getVisibleOffresByFiliere(filiereId);
    }

}