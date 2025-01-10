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
public class RH {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idRh;
    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;
    private String telephone;

    @ManyToOne
    @JoinColumn(name = "entreprise_id")
    private Entreprise entreprise;

    @OneToMany(mappedBy = "rh", cascade = CascadeType.ALL)
    private List<Offre> offres;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id") // Explicitly mapping foreign key
    private Utilisateur user;
}
