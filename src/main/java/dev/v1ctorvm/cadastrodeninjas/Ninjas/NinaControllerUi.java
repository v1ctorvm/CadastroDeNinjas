package dev.v1ctorvm.cadastrodeninjas.Ninjas;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/ninjas/ui")
public class NinaControllerUi {

    private final NinjaService ninjaService;

    public NinaControllerUi(NinjaService service, NinjaService ninjaService) {
        this.ninjaService = ninjaService;

    }

    @GetMapping("/listar")
    public String listarNinjas(Model model){
        List<NinjaDTO> ninjas =  ninjaService.listarNinjas();

        model.addAttribute("ninjas",ninjas);

        return "listarNinjas";
    }


}
