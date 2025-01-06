package STAGE.stage.services.implementation;

import STAGE.stage.dtos.EncadrantDTO;
import STAGE.stage.mappers.EntityMapper;
import STAGE.stage.repositories.EncadrantRepository;
import STAGE.stage.repositories.UserRepository;
import STAGE.stage.services.EncadrantService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import STAGE.stage.models.Encadrant;
import STAGE.stage.models.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor

public class EncadrantServiceImpl implements EncadrantService {
    @Autowired
    private EncadrantRepository encadrantRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userrepository;
    private final EntityMapper mapper;

    @Override
    public EncadrantDTO createEncadrant(EncadrantDTO dto) {
        if (dto.getMotDePasse() == null || dto.getMotDePasse().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty.");
        }

        User user = new User();
        user.setRole("ENCADRANT");
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getMotDePasse()));
        userrepository.save(user);

        Encadrant encadrant = new Encadrant();
        encadrant.setNom(dto.getNom());
        encadrant.setPrenom(dto.getPrenom());
        encadrant.setEmail(dto.getEmail());
        encadrant.setTelephone(dto.getTelephone());
        encadrant.setMotDePasse(passwordEncoder.encode(dto.getMotDePasse()));
        encadrant.setUser(user); // Associate User

        return mapper.toDto(encadrantRepository.save(encadrant));
    }
    @Override
    public EncadrantDTO updateEncadrant(Long id, EncadrantDTO dto) {
        Encadrant existing = encadrantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Encadrant not found"));

        existing.setNom(dto.getNom());
        existing.setPrenom(dto.getPrenom());
        existing.setEmail(dto.getEmail());
        existing.setTelephone(dto.getTelephone());
        // Update User (email and password) if needed
        if (dto.getUserId() != null) {
            User user = userrepository.findById(dto.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found with id: " + dto.getUserId()));
            user.setEmail(dto.getEmail()); // Update email
            if (dto.getMotDePasse() != null && !dto.getMotDePasse().isEmpty()) {
                user.setPassword(passwordEncoder.encode(dto.getMotDePasse())); // Update password
            }
            userrepository.save(user); // Save updated User
        }
        if (dto.getMotDePasse() != null && !dto.getMotDePasse().isEmpty()) {
            existing.setMotDePasse(passwordEncoder.encode(dto.getMotDePasse()));
        }

        return mapper.toDto(encadrantRepository.save(existing));
    }

    @Override
    public EncadrantDTO getEncadrantById(Long id) {
        Encadrant encadrant = encadrantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Encadrant not found"));
        return mapper.toDto(encadrant);
    }

    @Override
    public List<EncadrantDTO> getAllEncadrants() {
        return encadrantRepository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteEncadrant(Long id) {
        if (!encadrantRepository.existsById(id)) {
            throw new RuntimeException("Encadrant not found");
        }
        encadrantRepository.deleteById(id);
    }
    @Override
    public Long getEncadrantIdByUserId(Long userId) {
        Optional<Encadrant> encadrantOptional = encadrantRepository.findByUserId(userId);
        if (encadrantOptional.isPresent()) {
            return encadrantOptional.get().getIdEncadrant();
        } else {
            throw new IllegalArgumentException("Encadrant with userId " + userId + " does not exist.");
        }
    }
}
