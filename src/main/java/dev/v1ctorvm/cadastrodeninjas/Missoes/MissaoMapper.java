package dev.v1ctorvm.cadastrodeninjas.Missoes;

import org.springframework.stereotype.Component;

@Component
public class MissaoMapper {

    public MissaoModel map(MissaoDTO dto) {
        MissaoModel model = new MissaoModel();

        model.setId(dto.getId());
        model.setDificuldade(dto.getDificuldade());
        model.setNinjas(dto.getNinjas());
        model.setNome(dto.getNome());

        return model;
    }

    public MissaoDTO map(MissaoModel model) {

        MissaoDTO dto = new MissaoDTO();

        dto.setId(model.getId());
        dto.setDificuldade(model.getDificuldade());
        dto.setNinjas(model.getNinjas());
        dto.setNome(model.getNome());

        return dto;
    }
}
