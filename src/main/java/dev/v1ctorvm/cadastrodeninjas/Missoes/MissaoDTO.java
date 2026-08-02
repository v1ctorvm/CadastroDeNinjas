package dev.v1ctorvm.cadastrodeninjas.Missoes;

import dev.v1ctorvm.cadastrodeninjas.Ninjas.NinjaModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MissaoDTO {

    private Long id;
    private String nome;
    private String dificuldade;
    private List<NinjaModel> ninjas;
}
