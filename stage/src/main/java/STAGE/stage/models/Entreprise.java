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
public class Entreprise {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idEntreprise;
    private String nomEntreprise;
    private String description;
    private byte[] logo;
    private String villeEntreprise;
    private String adresseEntreprise;
    private String telephoneFix;
    private String domaineEntreprise;

    @OneToMany(mappedBy = "entreprise", cascade = CascadeType.ALL)
    private List<RH> rhs;

    @OneToMany(mappedBy = "entreprise", cascade = CascadeType.ALL)
    private List<Encadrant> encadrants;

    @OneToMany(mappedBy = "entreprise", cascade = CascadeType.ALL)
    private List<Offre> offres;

    @OneToOne(mappedBy = "entreprise", cascade = CascadeType.ALL)
    private CompteEntreprise compteEntreprise; // Relation avec le compte utilisateur

}
