package STAGE.stage.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class CoordinateurDeStage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idCs;
    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;
    private String telephone;
    private String role;

    @ManyToOne
    private Ecole ecole;
}