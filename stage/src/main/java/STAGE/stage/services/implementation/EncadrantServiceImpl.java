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
}
