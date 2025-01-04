package STAGE.stage.services.implementation;

import STAGE.stage.dtos.ChefDeFiliereDTO;
import STAGE.stage.models.ChefDeFiliere;
import STAGE.stage.models.Ecole;
import STAGE.stage.models.Etudiant;
import STAGE.stage.repositories.ChefDeFiliereRepository;
import STAGE.stage.repositories.EcoleRepository;
import STAGE.stage.repositories.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import STAGE.stage.dtos.FiliereDTO;
import STAGE.stage.mappers.EntityMapper;
import STAGE.stage.models.Filiere;
import STAGE.stage.repositories.FiliereRepository;
import STAGE.stage.services.FiliereService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FiliereServiceImpl implements FiliereService {

    @Autowired
    private FiliereRepository filiereRepository;

    @Autowired
    private ChefDeFiliereRepository chefDeFiliereRepository;
    private EtudiantRepository etudiantRepository;
    private EcoleRepository ecoleRepository;
    @Autowired
    private EntityMapper entityMapper;

    @Override
    public FiliereDTO createFiliere(FiliereDTO filiereDTO) {
        Filiere filiere = entityMapper.toEntity(filiereDTO);
        Filiere savedFiliere = filiereRepository.save(filiere);
        return entityMapper.toDto(savedFiliere);
    }

    @Override
    public FiliereDTO getFiliereById(Long id) {
        Filiere filiere = filiereRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Filiere not found with id: " + id));
        return entityMapper.toDto(filiere);
    }

    @Override
    public List<FiliereDTO> getAllFilieres() {
        return filiereRepository.findAll()
                .stream()
                .map(entityMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public FiliereDTO updateFiliere(Long id, FiliereDTO filiereDTO) {
        Filiere filiere = filiereRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Filiere not found with id: " + id));

        filiere.setNomFiliere(filiereDTO.getNomFiliere());
        filiere.setAbrvFiliere(filiereDTO.getAbrvFiliere());

        if (filiereDTO.getChefDeFiliereId() != null) {
            ChefDeFiliere chefDeFiliere = chefDeFiliereRepository.findById(filiereDTO.getChefDeFiliereId())
                    .orElseThrow(() -> new RuntimeException("Chef de filiere not found with id: " + filiereDTO.getChefDeFiliereId()));
            filiere.setChefDeFiliere(chefDeFiliere);
        }

        if (filiereDTO.getEtudiantIds() != null) {
            List<Etudiant> etudiants = filiereDTO.getEtudiantIds()
                    .stream()
                    .map(etudiantId -> etudiantRepository.findById(etudiantId)
                            .orElseThrow(() -> new RuntimeException("Etudiant not found with id: " + etudiantId)))
                    .collect(Collectors.toList());
            filiere.setEtudiants(etudiants);
        }

        if (filiereDTO.getEcoleId() != null) {
            Ecole ecole = ecoleRepository.findById(filiereDTO.getEcoleId())
                            .orElseThrow(() -> new RuntimeException("Ecole not found with id: " + filiereDTO.getEcoleId()));
            filiere.setEcole(ecole);
        }

        Filiere updatedFiliere = filiereRepository.save(filiere);
        return entityMapper.toDto(updatedFiliere);
    }

    @Override
    public void deleteFiliere(Long id) {
        if (!filiereRepository.existsById(id)) {
            throw new RuntimeException("Filiere not found with id: " + id);
        }
        filiereRepository.deleteById(id);
    }
}
