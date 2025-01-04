package STAGE.stage.services;

import STAGE.stage.dtos.OffreDTO;
import STAGE.stage.models.Offre;

import java.util.List;

public interface OffreService {
    OffreDTO createOffre(OffreDTO dto);

    List<OffreDTO> getAllOffres();

    OffreDTO updateOffre(Long offreId, OffreDTO offreDTO);

    List<OffreDTO> getOffresByRH(Long rhId);

    List<Offre> getOffresByEntreprise(Long entrepriseId);
}
