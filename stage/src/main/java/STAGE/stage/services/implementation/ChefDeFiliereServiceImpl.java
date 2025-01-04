package STAGE.stage.services.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import STAGE.stage.dtos.ChefDeFiliereDTO;
import STAGE.stage.mappers.EntityMapper;
import STAGE.stage.models.ChefDeFiliere;
import STAGE.stage.models.Ecole;
import STAGE.stage.models.Filiere;
import STAGE.stage.repositories.ChefDeFiliereRepository;
import STAGE.stage.repositories.EcoleRepository;
import STAGE.stage.repositories.FiliereRepository;
import STAGE.stage.services.ChefDeFiliereService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChefDeFiliereServiceImpl implements ChefDeFiliereService {
    private final ChefDeFiliereRepository chefRepository;
    private final FiliereRepository filiereRepository;
    private final EcoleRepository ecoleRepository;
    private final EntityMapper mapper;

    @Override
    public ChefDeFiliereDTO createChefDeFiliere(ChefDeFiliereDTO dto) {
        // Récupérer la filière et l'école depuis leurs IDs
        Filiere filiere = filiereRepository.findById(dto.getFiliereId())
                .orElseThrow(() -> new RuntimeException("Filière introuvable"));
        Ecole ecole = ecoleRepository.findById(dto.getEcoleId())
                .orElseThrow(() -> new RuntimeException("École introuvable"));

        // Mapper le DTO en entité
        ChefDeFiliere chef = mapper.toEntity(dto);
        chef.setFiliere(filiere);
        chef.setEcole(ecole);

        // Sauvegarder l'entité
        ChefDeFiliere savedChef = chefRepository.save(chef);

        // Retourner le DTO
        return mapper.toDto(savedChef);
    }

    @Override
    public ChefDeFiliereDTO updateChefDeFiliere(Long id, ChefDeFiliereDTO dto) {
        ChefDeFiliere chef = chefRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chef de filière introuvable"));

        Filiere filiere = filiereRepository.findById(dto.getFiliereId())
                .orElseThrow(() -> new RuntimeException("Filière introuvable"));
        Ecole ecole = ecoleRepository.findById(dto.getEcoleId())
                .orElseThrow(() -> new RuntimeException("École introuvable"));

        chef.setNom(dto.getNom());
        chef.setPrenom(dto.getPrenom());
        chef.setEmail(dto.getEmail());
        chef.setTelephone(dto.getTelephone());
        chef.setMotDePasse(dto.getMotDePasse());
        chef.setFiliere(filiere);
        chef.setEcole(ecole);

        return mapper.toDto(chefRepository.save(chef));
    }

    @Override
    public ChefDeFiliereDTO getChefDeFiliereById(Long id) {
        ChefDeFiliere chef = chefRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chef de filière introuvable"));
        return mapper.toDto(chef);
    }

    @Override
    public List<ChefDeFiliereDTO> getAllChefs() {
        return chefRepository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteChefDeFiliere(Long id) {
        chefRepository.deleteById(id);
    }
}
