package STAGE.stage.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Ecole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEcole;

    private String nomEcole;
    private String villeEcole;
    private String adresseEcole;
    @Lob
    private byte[] logo;
    private String description;
    private String telephoneFix;
    private String typeEcole;
    private String domaineEcole;

    @OneToOne(mappedBy = "ecole", cascade = CascadeType.ALL)
    private CompteEcole compteEcole; // Relation avec le compte utilisateur

    @OneToMany(mappedBy = "ecole")
    private List<Etudiant> etudiants;

    @OneToMany(mappedBy = "ecole")
    private List<CoordinateurDeStage> coordinateurs;

    @OneToMany(mappedBy = "ecole")
    private List<ChefDeFiliere> chefDeFilieres;

    @OneToMany(mappedBy = "ecole", cascade = CascadeType.ALL)
    private List<Filiere> filieres; // Updated association
}