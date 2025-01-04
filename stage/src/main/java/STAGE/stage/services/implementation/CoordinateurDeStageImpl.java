package STAGE.stage.services.implementation;

import STAGE.stage.dtos.CoordinateurDeStageDTO;
import STAGE.stage.mappers.EntityMapper;
import STAGE.stage.models.CoordinateurDeStage;
import STAGE.stage.models.User;
import STAGE.stage.repositories.CoordinateurDeStageRepository;
import STAGE.stage.repositories.UserRepository;
import STAGE.stage.services.CoordinateurDeDStageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CoordinateurDeStageImpl implements CoordinateurDeDStageService {

    private final CoordinateurDeStageRepository coordinateurDeStageRepository;
    private final EntityMapper mapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userrepository;
    @Override
    public CoordinateurDeStageDTO createCoordinateurDeStage(CoordinateurDeStageDTO dto) {
        if (dto.getMotDePasse() == null || dto.getMotDePasse().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty.");
        }

        User user = new User();
        user.setRole("COORDINATEUR_DE_STAGE");
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getMotDePasse()));
        userrepository.save(user);

        CoordinateurDeStage coordinateur = new CoordinateurDeStage();
        coordinateur.setNom(dto.getNom());
        coordinateur.setPrenom(dto.getPrenom());
        coordinateur.setEmail(dto.getEmail());
        coordinateur.setTelephone(dto.getTelephone());
        coordinateur.setMotDePasse(passwordEncoder.encode(dto.getMotDePasse()));
        coordinateur.setUser(user); // Associate User

        return mapper.toDto(coordinateurDeStageRepository.save(coordinateur));
    }
}
