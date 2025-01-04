package STAGE.stage.services.implementation;

import STAGE.stage.models.User;
import STAGE.stage.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import STAGE.stage.dtos.EtudiantDTO;
import STAGE.stage.mappers.EntityMapper;
import STAGE.stage.models.Ecole;
import STAGE.stage.models.Etudiant;
import STAGE.stage.models.Filiere;
import STAGE.stage.repositories.EcoleRepository;
import STAGE.stage.repositories.EtudiantRepository;
import STAGE.stage.repositories.FiliereRepository;
import STAGE.stage.services.EtudiantService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EtudiantServiceImpl implements EtudiantService {
    private final EtudiantRepository etudiantRepository;
    private final EcoleRepository ecoleRepository;  // Repository pour l'école
    private final FiliereRepository filiereRepository;  // Repository pour la filière
    private final EntityMapper mapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userrepository;

    @Override
    public EtudiantDTO createEtudiant(EtudiantDTO dto) {
        if (dto.getMotDePasse() == null || dto.getMotDePasse().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty.");
        }
        // Vérification de l'existence de l'école et de la filière
        Ecole ecole = ecoleRepository.findById(dto.getEcoleId())
                .orElseThrow(() -> new RuntimeException("École introuvable"));
        Filiere filiere = filiereRepository.findById(dto.getFiliereId())
                .orElseThrow(() -> new RuntimeException("Filière introuvable"));

        // Créer un étudiant
        User user = new User();
        user.setRole("ETUDIANT");
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getMotDePasse()));
        userrepository.save(user);

        Etudiant etudiant = new Etudiant();
        etudiant.setNom(dto.getNom());
        etudiant.setPrenom(dto.getPrenom());
        etudiant.setEmail(dto.getEmail());
        etudiant.setTel(dto.getTel());
        etudiant.setPhotoProfil(dto.getPhotoProfil());
        etudiant.setPhotoCouverture(dto.getPhotoCouverture());
        etudiant.setEcole(ecole);  // Associer l'école
        etudiant.setFiliere(filiere);
        etudiant.setMotDePasse(passwordEncoder.encode(dto.getMotDePasse()));
        etudiant.setStatutEtudiant(dto.getStatutEtudiant());
        etudiant.setCodeEtu(dto.getCodeEtu());
        etudiant.setUser(user);// Associer la filière

        // Sauvegarder l'étudiant et le retourner
        return mapper.toDto(etudiantRepository.save(etudiant));
    }

    @Override
    public EtudiantDTO getEtudiantById(Long etudiantId) {
        Etudiant etudiant = etudiantRepository.findById(etudiantId)
                .orElseThrow(() -> new RuntimeException("Étudiant introuvable"));
        return mapper.toDto(etudiant);
    }

    @Override
    public List<EtudiantDTO> getEtudiantsByEcole(Long ecoleId) {
        List<Etudiant> etudiants = etudiantRepository.findByEcoleIdEcole(ecoleId);
        return etudiants.stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<EtudiantDTO> getEtudiantsByFiliere(Long filiereId) {
        List<Etudiant> etudiants = etudiantRepository.findByFiliereIdFiliere(filiereId);
        return etudiants.stream().map(mapper::toDto).collect(Collectors.toList());
    }
}


