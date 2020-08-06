package hasa.hafia.controller;

import hasa.hafia.entites.Demande;
import hasa.hafia.service.DemandeService;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/demandes")
//@PreAuthorize("hasAnyRole('ROLE_RECRUTEUR')")
public class DemandeController {
    private final DemandeService service;
    private static final String DEFAULT_VIEW = "views/demande";
    private static final String DEFAULT_REDIRECTION = "redirect:/demandes";

    public DemandeController(DemandeService service) {
        this.service = service;
    }

    @GetMapping
    public String index () {
        //model.addAttribute("liste_candidat", service.findAll());
        //model.addAttribute("demande", new Demande());
        return DEFAULT_VIEW.concat("/index");
    }
    @PreAuthorize("hasAnyRole('ROLE_DEMANDEUR')")
    @GetMapping("/list")
    public String listeDemande(Model model){
        model.addAttribute("liste_candidat", service.findAll());

        return DEFAULT_VIEW.concat("/list");

    }

    @PreAuthorize("hasAnyRole('ROLE_DEMANDEUR')")
    @GetMapping("/add")
    public String addDemande(Model model){
        model.addAttribute("demande", new Demande());
        return DEFAULT_VIEW.concat("/add");
    }

    @PostMapping
    public String add(@ModelAttribute("demande") Demande demande) {
        demande.setOffres(null);
        service.create(demande);
        return DEFAULT_REDIRECTION.concat("/list");
    }

  
    @GetMapping("/edit")
    public String update(HttpServletRequest request, Model model){
        final Long id = Long.valueOf(request.getParameter("id"));
        Demande demande = service.findById(id);
        model.addAttribute("demande", demande);
        return DEFAULT_REDIRECTION.concat("/list");
    }

    @GetMapping("/delete")
    public String delete (long id, Model model) {
        service.delete(id);
        model.addAttribute("liste_candidat", service.findAll());//Pour la liste
        model.addAttribute("demande", new Demande());//Pour le formulaire
        return DEFAULT_REDIRECTION.concat("/list");
    }
}
