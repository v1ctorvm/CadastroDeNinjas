package dev.v1ctorvm.cadastrodeninjas.Ninjas;

import org.springframework.stereotype.Component;

@Component
public class NinjaMapper {

    public NinjaModel map(NinjaDTO ninjaDTO){
        NinjaModel ninjaModel = new NinjaModel();

        ninjaModel.setId(ninjaDTO.getId());
        ninjaModel.setNome(ninjaDTO.getNome());
        ninjaModel.setEmail(ninjaDTO.getEmail());
        ninjaModel.setIdade(ninjaDTO.getIdade());
        ninjaModel.setImgUrl(ninjaDTO.getImgUrl());
        ninjaModel.setRank(ninjaDTO.getRank());
        ninjaModel.setMissoes(ninjaDTO.getMissoes());

        return ninjaModel;
    }

    public NinjaDTO map(NinjaModel model){
        NinjaDTO ninjaDTO = new NinjaDTO();

        ninjaDTO.setId(model.getId());
        ninjaDTO.setNome(model.getNome());
        ninjaDTO.setEmail(model.getEmail());
        ninjaDTO.setIdade(model.getIdade());
        ninjaDTO.setImgUrl(model.getImgUrl());
        ninjaDTO.setRank(model.getRank());
        ninjaDTO.setMissoes(model.getMissoes());

        return ninjaDTO;
    }
}
