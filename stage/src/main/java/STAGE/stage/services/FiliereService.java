package STAGE.stage.services;


import STAGE.stage.dtos.FiliereDTO;

import java.util.List;

public interface FiliereService {
    FiliereDTO createFiliere(FiliereDTO filiereDTO);
    FiliereDTO getFiliereById(Long id);
    List<FiliereDTO> getAllFilieres();
    FiliereDTO updateFiliere(Long id, FiliereDTO filiereDTO);
    void deleteFiliere(Long id);
}


