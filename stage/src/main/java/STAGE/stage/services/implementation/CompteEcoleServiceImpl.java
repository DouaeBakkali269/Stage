package STAGE.stage.services.implementation;

import lombok.RequiredArgsConstructor;
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

    @Override
    public CompteEcoleDTO createCompteEcole(CompteEcoleDTO dto) {
        Ecole ecole = ecoleRepository.findById(dto.getEcoleId())
                .orElseThrow(() -> new RuntimeException("École introuvable"));

        CompteEcole compte = mapper.toEntity(dto);
        compte.setEcole(ecole);

        return mapper.toDto(compteRepository.save(compte));
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
}

