package STAGE.stage.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import STAGE.stage.dtos.PostulationDTO;
import STAGE.stage.services.PostulationService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/postulations")
@RequiredArgsConstructor
public class PostulationController {
    private final PostulationService postulationService;

    @PostMapping("/postuler")
    public ResponseEntity<PostulationDTO> postuler(@RequestBody PostulationDTO dto) {
        PostulationDTO postulation = postulationService.postuler(dto);
        return new ResponseEntity<>(postulation, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PostulationDTO>> getAllPostulations() {
        List<PostulationDTO> postulations = postulationService.getAllPostulations();
        return new ResponseEntity<>(postulations, HttpStatus.OK);
    }

    @PatchMapping("/{postulationId}/etat")
    public ResponseEntity<Void> updateEtatPostulation(@PathVariable Long postulationId, @RequestParam String etat) {
        postulationService.updateEtatPostulation(postulationId, etat);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/offre/{offreId}")
    public ResponseEntity<List<PostulationDTO>> getPostulationsByOffreId(@PathVariable Long offreId) {
        List<PostulationDTO> postulations = postulationService.getPostulationsByOffreId(offreId);
        return new ResponseEntity<>(postulations, HttpStatus.OK);
    }

    @GetMapping("/etudiant/{etudiantId}")
    public ResponseEntity<List<PostulationDTO>> getPostulationsByEtudiantId(@PathVariable Long etudiantId) {
        List<PostulationDTO> postulations = postulationService.getPostulationsByEtudiantId(etudiantId);
        return new ResponseEntity<>(postulations, HttpStatus.OK);
    }
    @PostMapping("/upload")
    public ResponseEntity<PostulationDTO> createPostulationWithFiles(
            @RequestParam("offreId") Long offreId,
            @RequestParam("etudiantId") Long etudiantId,
            @RequestParam("etatPostulation") String etatPostulation,
            @RequestParam("Cv") MultipartFile Cv,
            @RequestParam("LettreMotivation") MultipartFile LettreMotivation) {
        try {
            PostulationDTO postulationDTO = new PostulationDTO();
            postulationDTO.setOffreId(offreId);
            postulationDTO.setEtudiantId(etudiantId);
            postulationDTO.setEtatPostulation(etatPostulation);
            postulationDTO.setCv(Cv.getBytes());
            postulationDTO.setLettreMotivation(LettreMotivation.getBytes());

            PostulationDTO createdPostulation = postulationService.postuler(postulationDTO);
            return new ResponseEntity<>(createdPostulation, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Delete an Offer by its ID.
     *
     * @param id the ID of the offer to delete
     * @return ResponseEntity indicating the result
     */
    @DeleteMapping("/postulations/{id}")
    public ResponseEntity<String> deletePostulation(@PathVariable Long id) {
        try {
            postulationService.deletePostulationById(id);
            return ResponseEntity.ok("Postulation with ID " + id + " has been deleted successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
