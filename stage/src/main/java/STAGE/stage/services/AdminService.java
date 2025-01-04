package STAGE.stage.services;

import STAGE.stage.dtos.AdminDTO;

import java.util.List;
import java.util.Optional;

public interface AdminService {
    AdminDTO save(AdminDTO adminDTO);
    Optional<AdminDTO> findById(Long id);
    List<AdminDTO> findAll();
    void deleteById(Long id);
}


