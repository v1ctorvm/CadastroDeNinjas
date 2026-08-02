package dev.v1ctorvm.cadastrodeninjas.Missoes;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MissaoService {

    private final MissaoRepository repository;
    private final MissaoMapper mapper;

    public MissaoService(MissaoRepository repository, MissaoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<MissaoDTO> listarMissoes(){
        List<MissaoModel> missoes = repository.findAll();

        return missoes.stream()
                .map(mapper::map)
                .collect(Collectors.toList());
    }

    public MissaoDTO listarPorId(Long id){
        Optional<MissaoModel> model = repository.findById(id);
        return model.map(mapper::map).orElse(null);
    }

    public MissaoDTO criarMissao(MissaoDTO dto){
        MissaoModel model = new MissaoMapper().map(dto);
        model = repository.save(model);
        return mapper.map(model);
    }

    public void deletarMissaoPorId(Long id){
        repository.deleteById(id);
    }

    public MissaoDTO atualizarMissao(Long id, MissaoDTO dto){
        Optional<MissaoModel> missaoExistente = repository.findById(id);
        if (missaoExistente.isPresent()){}
        MissaoModel atualizado = mapper.map(dto);
        atualizado.setId(id);
        MissaoModel missaoSalva = repository.save(atualizado);
        return mapper.map(missaoSalva);
    }
}
