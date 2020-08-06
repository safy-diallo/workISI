package hasa.hafia.controller;

import hasa.hafia.entites.Demande;
import hasa.hafia.entites.Offres;
import hasa.hafia.repository.OffreRepository;
import hasa.hafia.service.OffreService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/offres")
//@PreAuthorize("hasAnyRole('ROLE_RECRUTEUR')")
public class OffreController {
   
	private final OffreService service;
    private static final String DEFAULT_VIEW = "views/offres";
    private static final String DEFAULT_REDIRECTION = "redirect:/offres";

    public OffreController(OffreService service) {
        this.service = service;
    }

    @GetMapping
    public String index () {
        //model.addAttribute("liste_offres", service.findAll());
        //model.addAttribute("offre", new Offres());
        return DEFAULT_VIEW.concat("/index");
    }
   
    @GetMapping("/listeOffre")
    public String listeDemande(Model model){
    	model.addAttribute("liste_offres", service.findAll());        
         return DEFAULT_VIEW.concat("/listeOffre");
    }
   
    @GetMapping("/ajoutOffre")
    public String addDemande(Model model){
    	model.addAttribute("offre", new Offres());
         return DEFAULT_VIEW.concat("/add");
    }
    @PreAuthorize("hasAnyRole('ROLE_RECRUTEUR')")
    @PostMapping
    public String add(@ModelAttribute("offre") Offres offre) {
        offre.setDemandes(null);
        service.create(offre);
        return DEFAULT_REDIRECTION;
    }
  
    @GetMapping("/edit")
    public String update(HttpServletRequest request, Model model){
        final Long id = Long.valueOf(request.getParameter("id"));
        Offres offres = service.findById(id);
        model.addAttribute("offre", offres);
        return DEFAULT_VIEW;
    }
  
    @GetMapping("/delete")
    public String delete (long id, Model model) {
        service.delete(id);
        model.addAttribute("liste_offre", service.findAll());//Pour la liste
        model.addAttribute("offre", new Offres());//Pour le formulaire
        return DEFAULT_REDIRECTION;
    }
    
    

}
