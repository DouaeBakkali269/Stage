package STAGE.stage.mappers;

import STAGE.stage.dtos.*;
import STAGE.stage.models.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EntityMapper {
    // Admin Mapping
    AdminDTO toDto(Admin admin);
    Admin toEntity(AdminDTO adminDTO);

    // Filiere Mapping
    FiliereDTO toDto(Filiere filiere);
    Filiere toEntity(FiliereDTO filiereDTO);

    // Ecole Mapping
    EcoleDTO toDto(Ecole ecole);
    Ecole toEntity(EcoleDTO ecoleDTO);

    // ChefDeFiliere Mapping
    @Mapping(source = "ecole.idEcole", target = "ecoleId")
    @Mapping(source = "filiere.idFiliere", target = "filiereId")
    ChefDeFiliereDTO toDto(ChefDeFiliere chefDeFiliere);
    @Mapping(source = "ecoleId", target = "ecole.idEcole")
    @Mapping(source = "filiereId", target = "filiere.idFiliere")
    ChefDeFiliere toEntity(ChefDeFiliereDTO chefDeFiliereDTO);

    // Etudiant Mapping
    @Mapping(source = "ecole.idEcole", target = "ecoleId")
    @Mapping(source = "filiere.idFiliere", target = "filiereId")
    @Mapping(source = "user.id", target = "userId")
    EtudiantDTO toDto(Etudiant etudiant);
    @Mapping(source = "ecoleId", target = "ecole.idEcole")
    @Mapping(source = "filiereId", target = "filiere.idFiliere")
    @Mapping(source = "userId", target = "user.id")
    Etudiant toEntity(EtudiantDTO etudiantDTO);

    // Stage Mapping
    @Mapping(source = "etudiant.idEtu", target = "etudiantId")
    @Mapping(source = "offre.idOffre", target = "offreId")
    @Mapping(source = "encadrant.idEncadrant", target = "encadrantId")
    StageDTO toDto(Stage stage);
    @Mapping(source = "etudiantId", target = "etudiant.idEtu")
    @Mapping(source = "offreId", target = "offre.idOffre")
    @Mapping(source = "encadrantId", target = "encadrant.idEncadrant")
    Stage toEntity(StageDTO stageDTO);

    // Offre Mapping
    @Mapping(source = "entreprise.idEntreprise", target = "entrepriseId")
    @Mapping(source = "rh.idRh", target = "rhId")
    OffreDTO toDto(Offre offre);
    @Mapping(source = "entrepriseId", target = "entreprise.idEntreprise")
    @Mapping(source = "rhId", target = "rh.idRh")
    Offre toEntity(OffreDTO offreDTO);

    // Postulation Mapping
    @Mapping(source = "etudiant.idEtu", target = "etudiantId")
    @Mapping(source = "offre.idOffre", target = "offreId")
    PostulationDTO toDto(Postulation postulation);
    @Mapping(source = "etudiantId", target = "etudiant.idEtu")
    @Mapping(source = "offreId", target = "offre.idOffre")
    Postulation toEntity(PostulationDTO postulationDTO);

    // RH Mapping
    @Mapping(source = "entreprise.idEntreprise", target = "entrepriseId")
    RHDTO toDto(RH rh);
    @Mapping(source = "entrepriseId", target = "entreprise.idEntreprise")
    RH toEntity(RHDTO rhDTO);

    // CoordinateurDeStage Mapping
    @Mapping(source = "ecole.idEcole", target = "ecoleId")
    CoordinateurDeStageDTO toDto(CoordinateurDeStage coordinateurDeStage);
    @Mapping(source = "ecoleId", target = "ecole.idEcole")
    CoordinateurDeStage toEntity(CoordinateurDeStageDTO coordinateurDeStageDTO);

    // Encadrant Mapping
    @Mapping(source = "entreprise.idEntreprise", target = "entrepriseId")
    EncadrantDTO toDto(Encadrant encadrant);
    @Mapping(source = "entrepriseId", target = "entreprise.idEntreprise")
    Encadrant toEntity(EncadrantDTO encadrantDTO);

    // Entreprise Mapping
    EntrepriseDTO toDto(Entreprise entreprise);
    Entreprise toEntity(EntrepriseDTO entrepriseDTO);

    // CompteEcole Mapping
    @Mapping(source = "ecole.idEcole", target = "ecoleId")
    CompteEcoleDTO toDto(CompteEcole compteEcole);
    @Mapping(source = "ecoleId", target = "ecole.idEcole")
    CompteEcole toEntity(CompteEcoleDTO compteEcoleDTO);

    // CompteEntreprise Mapping
    @Mapping(source = "entreprise.idEntreprise", target = "entrepriseId")
    CompteEntrepriseDTO toDto(CompteEntreprise compteEntreprise);
    @Mapping(source = "entrepriseId", target = "entreprise.idEntreprise")
    CompteEntreprise toEntity(CompteEntrepriseDTO compteEntrepriseDTO);

    // Entretien Mapping
    @Mapping(source = "offre.idOffre", target = "offreId")
    @Mapping(source = "etudiant.idEtu", target = "etudiantId")
    EntretienDTO toDto(Entretien entretien);
    @Mapping(source = "offreId", target = "offre.idOffre")
    @Mapping(source = "etudiantId", target = "etudiant.idEtu")
    Entretien toEntity(EntretienDTO entretienDTO);
}
