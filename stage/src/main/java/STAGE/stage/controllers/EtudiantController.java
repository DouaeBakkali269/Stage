package STAGE.stage.controllers;

import STAGE.stage.repositories.VisibleOffreRepository;
import STAGE.stage.services.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import STAGE.stage.dtos.EtudiantDTO;
import STAGE.stage.services.EtudiantService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/api/etudiants")
public class EtudiantController {

    @Autowired
    private  EtudiantService etudiantService;

    @Autowired
    private StatisticsService statisticsService;

    @Autowired
    private VisibleOffreRepository visibleOffreRepository;

    // Créer un étudiant
    @PostMapping
    public ResponseEntity<EtudiantDTO> createEtudiant(@RequestBody EtudiantDTO etudiantDTO) {
        EtudiantDTO createdEtudiant = etudiantService.createEtudiant(etudiantDTO);
        return new ResponseEntity<>(createdEtudiant, HttpStatus.CREATED);
    }

    // Récupérer un étudiant par ID
    @GetMapping("/{id}")
    public ResponseEntity<EtudiantDTO> getEtudiantById(@PathVariable Long id) {
        EtudiantDTO etudiantDTO = etudiantService.getEtudiantById(id);
        return new ResponseEntity<>(etudiantDTO, HttpStatus.OK);
    }

    // Récupérer les étudiants par école
    @GetMapping("/ecole/{ecoleId}")
    public ResponseEntity<List<EtudiantDTO>> getEtudiantsByEcole(@PathVariable Long ecoleId) {
        List<EtudiantDTO> etudiants = etudiantService.getEtudiantsByEcole(ecoleId);
        return new ResponseEntity<>(etudiants, HttpStatus.OK);
    }

    // Récupérer les étudiants par filière
    @GetMapping("/filiere/{filiereId}")
    public ResponseEntity<List<EtudiantDTO>> getEtudiantsByFiliere(@PathVariable Long filiereId) {
        List<EtudiantDTO> etudiants = etudiantService.getEtudiantsByFiliere(filiereId);
        return new ResponseEntity<>(etudiants, HttpStatus.OK);
    }

    // Créer un étudiant avec image
    @PostMapping("/upload")
    public ResponseEntity<EtudiantDTO> createEtudiantWithImage(
            @RequestParam("nom") String nom,
            @RequestParam("prenom") String prenom,
            @RequestParam("tel") String tel,
            @RequestParam("email") String email,
            @RequestParam("motDePasse") String motDePasse,
            @RequestParam("codeEtu") String codeEtu,
            @RequestParam("photoProfil") MultipartFile photoProfil,
            @RequestParam("photoCouverture") MultipartFile photoCouverture,
            @RequestParam("statutEtudiant") String statutEtudiant,
            @RequestParam("ecoleId") Long ecoleId,
            @RequestParam("filiereId") Long filiereId) {
        try {
            EtudiantDTO etudiantDTO = new EtudiantDTO();
            etudiantDTO.setNom(nom);
            etudiantDTO.setPrenom(prenom);
            etudiantDTO.setTel(tel);
            etudiantDTO.setEmail(email);
            etudiantDTO.setMotDePasse(motDePasse);
            etudiantDTO.setCodeEtu(codeEtu);
            etudiantDTO.setPhotoProfil(photoProfil.getBytes());
            etudiantDTO.setPhotoCouverture(photoCouverture.getBytes());
            etudiantDTO.setStatutEtudiant(statutEtudiant);
            etudiantDTO.setEcoleId(ecoleId);
            etudiantDTO.setFiliereId(filiereId);

            EtudiantDTO createdEtudiant = etudiantService.createEtudiant(etudiantDTO);
            return new ResponseEntity<>(createdEtudiant, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEtudiant(@PathVariable Long id) {
        try {
            etudiantService.deleteEtudiantById(id);
            return ResponseEntity.ok("Etudiant with ID " + id + " has been deleted successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Long> getEtudiantIdByUserId(@PathVariable Long userId) {
        try {
            Long etudiantId = etudiantService.getEtudiantIdByUserId(userId);
            return ResponseEntity.ok(etudiantId);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    // Update an existing student by ID
    @PutMapping("/{id}")
    public ResponseEntity<EtudiantDTO> updateEtudiant(
            @PathVariable Long id,
            @RequestBody EtudiantDTO studentData) {
        try {
            EtudiantDTO updatedEtudiant = etudiantService.updateEtudiant(id, studentData);
            return ResponseEntity.ok(updatedEtudiant);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/student/{studentId}/count-applications")
    public long countApplicationsByStudent(@PathVariable Long studentId) {
        return statisticsService.countApplicationsByStudentId(studentId);
    }
    @GetMapping("/student/{studentId}/count-interviews")
    public long countInterviewsByStudent(@PathVariable Long studentId) {
        return statisticsService.countInterviewsByStudentId(studentId);
    }

    // Endpoint to count postulations by student ID
    @GetMapping("/{idEtu}/postulations/count")
    public long countPostulationsByEtudiantId(@PathVariable Long idEtu) {
        return statisticsService.countPostulationsByEtudiantId(idEtu);
    }

    // Endpoint to count internships by student ID
    @GetMapping("/{idEtu}/internships/count")
    public long countInternshipsByEtudiantId(@PathVariable Long idEtu) {
        return statisticsService.countInternshipsByEtudiantId(idEtu);
    }

    @GetMapping("/{idFiliere}/visible/count")
    public long countVisibleOffersByFiliere(@PathVariable Long idFiliere) {
        return visibleOffreRepository.countVisibleOffersByFiliere(idFiliere);
    }

}


