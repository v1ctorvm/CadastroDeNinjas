package dev.v1ctorvm.cadastrodeninjas.Missoes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.v1ctorvm.cadastrodeninjas.Ninjas.NinjaModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tb_missoes")

@NoArgsConstructor
@AllArgsConstructor
@Data

public class MissaoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String dificuldade;

    @OneToMany(mappedBy = "missoes") // uma missao pode ter varios ninjas
    @JsonIgnore
    private List<NinjaModel> ninjas;
}
