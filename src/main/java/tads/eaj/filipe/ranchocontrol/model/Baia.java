package tads.eaj.filipe.ranchocontrol.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class Baia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String nome;
    String descricaoBaia;
    Date deleted = null;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="funcionarioResponsavelId")
    Funcionario funcionarioResponsavelId;




}
