package STAGE.stage.services.implementation;

import STAGE.stage.repositories.*;
import STAGE.stage.services.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {

    // Repositories Declaration
    private final EntrepriseRepository entrepriseRepository;
    private final EcoleRepository ecoleRepository;
    private final EtudiantRepository etudiantRepository;
    private final OffreRepository offreRepository;
    private final EntretienRepository entretienRepository;
    private final StageRepository stageRepository;
    private final RHRepository rhRepository;
    private final EncadrantRepository encadrantRepository;
    private final PostulationRepository postulationRepository;

    // Admin Statistics
    @Override
    public long countEntreprises() {
        return entrepriseRepository.count();
    }

    @Override
    public long countEcoles() {
        return ecoleRepository.count();
    }

    @Override
    public long countEtudiants() {
        return etudiantRepository.count();
    }

    @Override
    public long countTotalOffers() {
        return offreRepository.count();
    }

    @Override
    public long countEntretiens() {
        return entretienRepository.count();
    }

    @Override
    public long countOngoingStages(LocalDate currentDate) {
        return stageRepository.countByDateFinBefore(currentDate);
    }

    // Ecole Statistics
    @Override
    public long countStudentsByEcole(Long idEcole) {
        return etudiantRepository.countByEcole_IdEcole(idEcole);
    }

    @Override
    public long countStudentsWithoutInternship(Long idEcole) {
        return etudiantRepository.countByEcole_IdEcoleAndStagesIsEmpty(idEcole);
    }

    @Override
    public long countStudentsWithInternshipByFiliere(Long filiereId, Long idEcole) {
        return etudiantRepository.countByFiliere_IdFiliereAndEcole_IdEcoleAndStagesIsNotEmpty(filiereId, idEcole);
    }

    @Override
    public long countTotalStudentsByFiliere(Long filiereId) {
        return etudiantRepository.countByFiliere_IdFiliere(filiereId);
    }




    // Entreprise Statistics
    //Offre repo
    @Override
    public long countTotalOffersByCompanyId(Long companyId) {
        return offreRepository.countOffersByCompanyId(companyId);
    }

    @Override
    public long countOpenOffersByCompanyId(Long companyId) {
        return offreRepository.countOpenOffersByCompanyId(companyId);
    }

    @Override
    public long countVisibleOffersByFiliere(Long filiereId) {
        return offreRepository.countVisibleOffersByFiliere(filiereId);
    }

    @Override
    public long countTotalInterviewsByCompanyId(Long companyId) {
        return entretienRepository.countByEntrepriseIdEntreprise(companyId);
    }

    @Override
    public long countTotalInternshipsByCompanyId(Long companyId) {
        return stageRepository.countByEntrepriseIdEntreprise(companyId);
    }

    @Override
    public long countRhByCompanyId(Long companyId) {
        return rhRepository.countByEntrepriseIdEntreprise(companyId);
    }

    @Override
    public long countSupervisorsByCompanyId(Long companyId) {
        return encadrantRepository.countByEntrepriseIdEntreprise(companyId);
    }

    @Override
    public long countApplicantsPerOffer(Long offerId) {
        return postulationRepository.countByOffreIdOffre(offerId);
    }

    // Encadrant Statistics
    @Override
    public long countOngoingInternshipsBySupervisor(Long supervisorId) {
        return stageRepository.countByEncadrantIdEncadrantAndDateFinBefore(supervisorId, LocalDate.now());
    }

    @Override
    public long countTotalInternshipsBySupervisor(Long supervisorId) {
        return stageRepository.countByEncadrantIdEncadrant(supervisorId);
    }
}