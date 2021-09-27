package Esercitazioni.es1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class HomeController
{

    private final UserService userService;

    @Autowired
    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registrazione")
    public String registration(Model model) {
        model.addAttribute( "libro", new LibroDTO());
        return "registrazione";
    }

    @PostMapping("/registrazione")
    public String registrationSuccess(
            @ModelAttribute("libro") @Valid LibroDTO libro,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes ra)
    {
        if (bindingResult.hasErrors())
        {
            for(ObjectError temp :bindingResult.getAllErrors())
            {
                System.out.println("Errore trovato: nome " + temp.getObjectName() +
                        ";codice " + temp.getCode() + "; messaggio " + temp.getDefaultMessage());
            }
            return "registrazione";
        }

        this.userService.saveBook(libro);
        ra.addFlashAttribute("libro", libro);
        return "redirect:/RegistrazioneAvvenuta";
    }

    @GetMapping("/RegistrazioneAvvenuta")
    public String risultato(
            @ModelAttribute("libro") LibroDTO libro,
            Model model) {

        model.addAttribute("libro",libro);

        return "RegistrazioneAvvenuta";
    }

    @GetMapping("/ElencoLibri")
    public String fooresult(
            @ModelAttribute("libro") LibroDTO libro,
            Model model) {

        model.addAttribute("myList",this.userService.getAllBooks());

        return "ElencoLibri";
    }







}