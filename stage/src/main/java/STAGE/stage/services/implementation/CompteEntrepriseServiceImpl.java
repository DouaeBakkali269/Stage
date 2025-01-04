package STAGE.stage.services.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import STAGE.stage.dtos.CompteEntrepriseDTO;
import STAGE.stage.mappers.EntityMapper;
import STAGE.stage.models.CompteEntreprise;
import STAGE.stage.models.Entreprise;
import STAGE.stage.repositories.CompteEntrepriseRepository;
import STAGE.stage.repositories.EntrepriseRepository;
import STAGE.stage.services.CompteEntrepriseService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompteEntrepriseServiceImpl implements CompteEntrepriseService {
    private final CompteEntrepriseRepository compteRepository;
    private final EntrepriseRepository entrepriseRepository;
    private final EntityMapper mapper;

    @Override
    public CompteEntrepriseDTO createCompteEntreprise(CompteEntrepriseDTO dto) {
        Entreprise entreprise = entrepriseRepository.findById(dto.getIdCompte())
                .orElseThrow(() -> new RuntimeException("Entreprise introuvable"));

        CompteEntreprise compte = mapper.toEntity(dto);
        compte.setEntreprise(entreprise);

        return mapper.toDto(compteRepository.save(compte));
    }

    @Override
    public CompteEntrepriseDTO updateCompteEntreprise(Long id, CompteEntrepriseDTO dto) {
        CompteEntreprise compte = compteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compte introuvable"));

        Entreprise entreprise = entrepriseRepository.findById(dto.getIdCompte())
                .orElseThrow(() -> new RuntimeException("Entreprise introuvable"));

        compte.setNom(dto.getNom());
        compte.setPrenom(dto.getPrenom());
        compte.setEmail(dto.getEmail());
        compte.setTelephone(dto.getTelephone());
        compte.setEntreprise(entreprise);

        return mapper.toDto(compteRepository.save(compte));
    }

    @Override
    public CompteEntrepriseDTO getCompteEntrepriseById(Long id) {
        return compteRepository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new RuntimeException("Compte introuvable"));
    }

    @Override
    public List<CompteEntrepriseDTO> getAllComptesEntreprise() {
        return compteRepository.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCompteEntreprise(Long id) {
        compteRepository.deleteById(id);
    }
}
