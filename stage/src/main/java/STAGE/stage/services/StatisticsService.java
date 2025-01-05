package STAGE.stage.services;

import java.time.LocalDate;

public interface StatisticsService {

    // Admin Statistics
    long countEntreprises();
    long countEcoles();
    long countEtudiants();
    long countTotalOffers();
    long countEntretiens();
    long countOngoingStages(LocalDate currentDate);

    // Ecole Statistics
    long countStudentsByEcole(Long idEcole);
    long countStudentsWithoutInternship(Long idEcole);
    long countStudentsWithInternshipByFiliere(Long filiereId, Long idEcole);
    long countTotalStudentsByFiliere(Long filiereId);
    long countVisibleOffersByFiliere(Long filiereId);

    // Entreprise Statistics
    long countOpenOffersByCompanyId(Long companyId);
    long countTotalOffersByCompanyId(Long companyId);
    long countTotalInterviewsByCompanyId(Long companyId);
    long countTotalInternshipsByCompanyId(Long companyId);
    long countRhByCompanyId(Long companyId);
    long countSupervisorsByCompanyId(Long companyId);
    long countApplicantsPerOffer(Long offerId);

    // Encadrant Statistics
    long countOngoingInternshipsBySupervisor(Long supervisorId);
    long countTotalInternshipsBySupervisor(Long supervisorId);
}