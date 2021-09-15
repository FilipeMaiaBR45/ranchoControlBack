package tads.eaj.filipe.ranchocontrol.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String nome;
    int idade;
    float peso;

    Date deleted = null;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="idBaia")
    Baia animal_idBaia;

}
