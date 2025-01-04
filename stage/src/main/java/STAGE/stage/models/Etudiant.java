package STAGE.stage.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



import jakarta.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Etudiant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEtu;
    private String nom;
    private String prenom;
    private String tel;
    private String email;
    private String motDePasse;
    private String codeEtu;
    @Lob
    private byte[] PhotoProfil;
    @Lob
    private byte[] PhotoCouverture;
    private String statutEtudiant;

    @ManyToOne
    private Ecole ecole;

    @ManyToOne
    @JoinColumn(name = "filiere_id") // Clé étrangère dans la table Etudiant
    private Filiere filiere;

    @OneToMany(mappedBy = "etudiant", cascade = CascadeType.ALL)
    private List<Postulation> postulations;

    @OneToMany(mappedBy = "etudiant", cascade = CascadeType.ALL)
    private List<Entretien> entretiens; // Un étudiant peut passer plusieurs entretiens


    @OneToMany(mappedBy = "etudiant", cascade = CascadeType.ALL)
    private List<Stage> stages;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id") // Explicitly mapping foreign key
    private User user;



}
