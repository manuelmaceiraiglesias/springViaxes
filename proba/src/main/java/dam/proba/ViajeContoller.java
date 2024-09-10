package dam.proba;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("")
public class ViajeContoller {
    private final ViaxeService viaxeService;

    public ViajeContoller(ViaxeService viaxeService) {
        this.viaxeService = viaxeService;
    }

    @GetMapping("/viajes")
    public String listViajes(Model model) {
        model.addAttribute("viajes", viaxeService.verTodos()); //mete a lista de viaxes nun model
        return "viajes";
    }

    @GetMapping("/viajes/new")
    public String createViajeForm(Model model) {
        Viaje viaje = new Viaje();//thymeleaf necesita un modelo vacio co que vincular o form
        model.addAttribute("viaje", viaje);
        return "crear_viaje";
    }

    @PostMapping("/viajes")
    public String saveViaje(@Valid Viaje viaje, Errors errors,Model model) { //requestbody dalgun xeto da problemas en modelos mvc
        if (null != errors && errors.getErrorCount() > 0) {//se errors existe e ten erros volve o menu crear_viaje
            return "crear_viaje";
        }
        model.addAttribute("viaje",viaje);
        viaxeService.gardarUn(viaje);
        return "redirect:/viajes";
    }

    @GetMapping("viajes/edit/{fecha}")
    public String editViajeForm(@PathVariable LocalDate fecha, Model model) {
        model.addAttribute("viaje", viaxeService.verUn(fecha));
        return "editar_viaje";
    }

    @PostMapping("/viajes/{fecha}")
    public String updateViaje(@PathVariable LocalDate fecha,
                              @Valid @ModelAttribute("viaje") Viaje viaje,Errors errors,
                              Model model) {//@Valid fai que valide o form contra as restriccions da clase
        if (null != errors && errors.getErrorCount() > 0) {
            return "editar_viaje";//se errors existe e ten erros volve o menu editar_viaje
        }
        Viaje existingViaje = viaxeService.verUn(fecha);
        existingViaje.setFecha(fecha);
        existingViaje.setDistancia(viaje.getDistancia());
        existingViaje.setDesnivel(viaje.getDesnivel());
        existingViaje.setComentario(viaje.getComentario());

        viaxeService.modificarUn(existingViaje);
        return "redirect:/viajes";
    }

    @GetMapping("/viajes/{fecha}")
    public String deleteStudent(@PathVariable LocalDate fecha) {
        viaxeService.borrarUn(fecha);
        return "redirect:/viajes";
    }


}
