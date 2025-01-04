package STAGE.stage.services.implementation;

import STAGE.stage.models.User;
import STAGE.stage.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import STAGE.stage.dtos.CompteEcoleDTO;
import STAGE.stage.mappers.EntityMapper;
import STAGE.stage.models.CompteEcole;
import STAGE.stage.models.Ecole;
import STAGE.stage.repositories.CompteEcoleRepository;
import STAGE.stage.repositories.EcoleRepository;
import STAGE.stage.services.CompteEcoleService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompteEcoleServiceImpl implements CompteEcoleService {
    private final CompteEcoleRepository compteRepository;
    private final EcoleRepository ecoleRepository;
    private final EntityMapper mapper;


    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userrepository;

    @Override
    public CompteEcoleDTO createCompteEcole(CompteEcoleDTO dto) {
        if (dto.getMotDePasse() == null || dto.getMotDePasse().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty.");
        }

        User user = new User();
        user.setRole("ECOLE");
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getMotDePasse()));
        userrepository.save(user);

        CompteEcole compteEcole = new CompteEcole();
        compteEcole.setNom(dto.getNom());
        compteEcole.setEmail(dto.getEmail());
        compteEcole.setTelephone(dto.getTelephone());
        compteEcole.setMotDePasse(passwordEncoder.encode(dto.getMotDePasse()));
        compteEcole.setEcole(ecoleRepository.findById(dto.getEcoleId())
                .orElseThrow(() -> new RuntimeException("École introuvable"))); // Associate Ecole
        compteEcole.setUser(user); // Associate User

        return mapper.toDto(compteRepository.save(compteEcole));
    }


    @Override
    public CompteEcoleDTO updateCompteEcole(Long id, CompteEcoleDTO dto) {
        CompteEcole compte = compteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compte introuvable"));

        Ecole ecole = ecoleRepository.findById(dto.getEcoleId())
                .orElseThrow(() -> new RuntimeException("École introuvable"));

        compte.setNom(dto.getNom());
        compte.setPrenom(dto.getPrenom());
        compte.setEmail(dto.getEmail());
        compte.setTelephone(dto.getTelephone());
        compte.setEcole(ecole);

        return mapper.toDto(compteRepository.save(compte));
    }

    @Override
    public CompteEcoleDTO getCompteEcoleById(Long id) {
        return compteRepository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("Compte introuvable"));
    }

    @Override
    public List<CompteEcoleDTO> getAllComptesEcole() {
        return compteRepository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCompteEcole(Long id) {
        compteRepository.deleteById(id);
    }
    @Override
    public CompteEcoleDTO getCompteEcoleByEcoleId(Long ecoleId) {
        CompteEcole compteEcole = compteRepository.findByEcole_IdEcole(ecoleId)
                .orElseThrow(() -> new RuntimeException("CompteEcole introuvable pour cet ID d'École."));

        return mapper.toDto(compteEcole);
    }
}

