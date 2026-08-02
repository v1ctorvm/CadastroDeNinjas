package dev.v1ctorvm.cadastrodeninjas.Ninjas;

import dev.v1ctorvm.cadastrodeninjas.Missoes.MissaoModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NinjaDTO {

    private Long id;
    private String nome;
    private String email;
    private int idade;
    private String imgUrl;
    private String rank;
    private MissaoModel missoes;


}
