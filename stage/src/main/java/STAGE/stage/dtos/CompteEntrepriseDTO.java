package STAGE.stage.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CompteEntrepriseDTO {
    private Long idCompte;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private Long entrepriseId;
}
