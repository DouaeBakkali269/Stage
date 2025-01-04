package STAGE.stage.models;

import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Offre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOffre;
    private String objetOffre;
    private String descriptionOffre;
    private Date dateLancement;
    private Date dateLimite;
    private String posteOffre;
    private String dureeStage;
    private String modeOffre;
    private String remuneration;
    private String typeStageOffre;
    private String niveauRequisOffre;

    @ManyToOne
    @JoinColumn(name = "rh_id")
    private RH rh;

    @ManyToOne
    @JoinColumn(name = "entreprise_id")
    private Entreprise entreprise;

    @OneToMany(mappedBy = "offre")
    private List<Entretien> entretiens;

    @OneToMany(mappedBy = "offre", cascade = CascadeType.ALL)
    private List<Postulation> postulations;

    @OneToMany(mappedBy = "offre", cascade = CascadeType.ALL)
    private List<Stage> stages;
}


