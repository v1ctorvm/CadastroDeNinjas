package dev.v1ctorvm.cadastrodeninjas.Missoes;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("missoes")
public class MissaoController {


    private final  MissaoService service;

    public MissaoController(MissaoService service) {
        this.service = service;
    }

    // Get- manda uma requisicao para mostrar as missoes
    @GetMapping("/listar")
    public ResponseEntity<List<MissaoDTO>> listarMissao() {
        List<MissaoDTO> missoes = service.listarMissoes();

        return ResponseEntity.ok(missoes);
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarMissaoPorId(@PathVariable Long id) {
        MissaoDTO missao = service.listarPorId(id);

        if (missao != null){
            return  ResponseEntity.ok(missao);
        } else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Missao com id nao existe");
    }


    // post - manda requisicao para criar a missao
    @PostMapping("/criar")
    public ResponseEntity<String> criarMissao(@RequestBody MissaoDTO dto){
        MissaoDTO missaoDTO = service.criarMissao(dto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Missao criada com sucesso: " + missaoDTO.getNome() + " | ID: "+ missaoDTO.getId());
    }

    // put - manda requisicao para alterar a missao
    @PutMapping("/alterar/{id}")
    public ResponseEntity<?> alterarMissao(@PathVariable Long id, @RequestBody MissaoDTO atualizado){
        MissaoDTO missao = service.atualizarMissao(id, atualizado);

        if (missao != null){
            return  ResponseEntity.ok(missao);
        } else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Missao com o ID: " + id + " nao encontrada");
    }

    // Delete - manda requisicao para deletar a missao
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarMissao(@PathVariable Long id){

        if (service.listarPorId(id) != null){
            service.deletarMissaoPorId(id);
            return ResponseEntity.ok("Missao com ID" + id + " deletada");
        } else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Missao com id nao existe");
    }










}
