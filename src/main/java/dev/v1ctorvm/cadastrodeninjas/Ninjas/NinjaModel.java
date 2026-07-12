package dev.v1ctorvm.cadastrodeninjas.Ninjas;

import dev.v1ctorvm.cadastrodeninjas.Missoes.MissoesModel;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_cadastro")

@NoArgsConstructor
@AllArgsConstructor
@Data

public class NinjaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Column(unique = true)
    private String email;
    private int idade;

    @Column(name = "img_url")
    private String imgUrl;

    @ManyToOne // um ninja tem uma unica missão
    @JoinColumn(name = "missoes_id") // Foreign Key - Chave estrangeira
    private MissoesModel missoes;

}
