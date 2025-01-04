package STAGE.stage.services.implementation;

import STAGE.stage.models.User;
import STAGE.stage.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import STAGE.stage.dtos.AdminDTO;
import STAGE.stage.mappers.EntityMapper;
import STAGE.stage.models.Admin;
import STAGE.stage.repositories.AdminRepository;
import STAGE.stage.services.AdminService;

import org.springframework.security.crypto.password.PasswordEncoder;



import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private  AdminRepository adminRepository;

    @Autowired
    private  EntityMapper entityMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public AdminDTO save(AdminDTO adminDTO) {
        User user = new User();
        user.setEmail(adminDTO.getEmailAd());
        String encodedPassword = passwordEncoder.encode(adminDTO.getMotDePasse());
        user.setPassword(encodedPassword); // Hash the password before saving
        user.setRole("ADMIN");// 2. Create the Admin entity
        Admin admin = new Admin();
        admin.setNomAd(adminDTO.getNomAd());
        admin.setPrenomAd(adminDTO.getPrenomAd());
        admin.setTelephone(adminDTO.getTelephone());
        admin.setUser(user); // Link User to Admin
        userRepository.save(user);
        adminRepository.save(admin);

        return entityMapper.toDto(admin);
    }


    @Override
    public Optional<AdminDTO> findById(Long id) {
        return adminRepository.findById(id)
                .map(entityMapper::toDto);
    }

    @Override
    public List<AdminDTO> findAll() {
        return adminRepository.findAll().stream()
                .map(entityMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        adminRepository.deleteById(id);
    }



}
