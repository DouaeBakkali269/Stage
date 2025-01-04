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
public class Encadrant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idEncadrant;
    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;
    private String telephone;

    @ManyToOne
    @JoinColumn(name="entreprise_id")
    private Entreprise entreprise;


    @OneToMany(mappedBy = "encadrant", cascade = CascadeType.ALL)
    private List<Stage> stages;
}
