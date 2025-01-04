package STAGE.stage.services.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import STAGE.stage.dtos.RHDTO;
import STAGE.stage.mappers.EntityMapper;
import STAGE.stage.models.Entreprise;
import STAGE.stage.models.RH;
import STAGE.stage.repositories.EntrepriseRepository;
import STAGE.stage.repositories.RHRepository;
import STAGE.stage.services.RHService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RHServiceImpl implements RHService {

    private final RHRepository rhRepository;
    private final EntrepriseRepository entrepriseRepository;
    private final EntityMapper entityMapper;

    @Override
    public RHDTO createRH(RHDTO rhDTO) {
        RH rh = new RH();
        rh.setNom(rhDTO.getNom());
        rh.setPrenom(rhDTO.getPrenom());
        rh.setEmail(rhDTO.getEmail());
        rh.setMotDePasse(rhDTO.getMotDePasse());
        rh.setTelephone(rhDTO.getTelephone());

        // Associer l'entreprise (si applicable)
        Entreprise entreprise = entrepriseRepository.findById(rhDTO.getEntrepriseId())
                .orElseThrow(() -> new RuntimeException("Entreprise introuvable"));
        rh.setEntreprise(entreprise);

        RH savedRH = rhRepository.save(rh);
        return entityMapper.toDto(savedRH);
    }

    @Override
    public RHDTO getRHById(Long id) {
        RH rh = rhRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("RH not found with id: " + id));
        return entityMapper.toDto(rh);
    }

    @Override
    public List<RHDTO> getAllRHs() {
        return rhRepository.findAll()
                .stream()
                .map(entityMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public RHDTO updateRH(Long id, RHDTO rhDTO) {
        RH rh = rhRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("RH not found with id: " + id));

        rh.setNom(rhDTO.getNom());
        rh.setPrenom(rhDTO.getPrenom());
        rh.setEmail(rhDTO.getEmail());
        rh.setMotDePasse(rhDTO.getMotDePasse());
        rh.setTelephone(rhDTO.getTelephone());

        Entreprise entreprise = entrepriseRepository.findById(rhDTO.getEntrepriseId())
                .orElseThrow(() -> new RuntimeException("Entreprise introuvable"));
        rh.setEntreprise(entreprise);

        RH updatedRH = rhRepository.save(rh);
        return entityMapper.toDto(updatedRH);
    }

    @Override
    public void deleteRH(Long id) {
        if (!rhRepository.existsById(id)) {
            throw new RuntimeException("RH not found with id: " + id);
        }
        rhRepository.deleteById(id);
    }
    @Override
    public List<RH> getRHByEntreprise(Long entrepriseId) {
        return rhRepository.findByEntrepriseIdEntreprise(entrepriseId); // Assurez-vous que la méthode existe dans le repository
    }
}
