package dev.v1ctorvm.cadastrodeninjas.Missoes;

import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;

@RestController
@RequestMapping("missoes")
public class MissoesController {

    public MissoesController(DataSource dataSource) {
    }

    // Get- manda uma requisicao para mostrar as missoes
    @GetMapping("/listar")
    public String listarMissao(){
        return "Missao Listada";
    }


    // post - manda requisicao para criar a missao
    @PostMapping("/criar")
    public String criarMissao(){
        return "Criando missao";
    }

    // put - manda requisicao para alterar a missao
    @PutMapping("/alterar")
    public String alterarMissao(){
        return "Missao alterada";
    }

    // Delete - manda requisicao para deletar a missao
    @DeleteMapping("/deletar")
    public String deletarMissao(){
        return "Missao deletada";
    }










}
