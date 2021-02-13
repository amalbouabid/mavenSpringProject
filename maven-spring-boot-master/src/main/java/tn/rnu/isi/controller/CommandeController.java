package tn.rnu.isi.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import tn.rnu.isi.model.Commande;
import tn.rnu.isi.service.CommandeService;
import tn.rnu.isi.service.ClientService;
import tn.rnu.isi.service.ProduitService;

 
@Controller("commandeController")
public class CommandeController {
	
	private final Logger logger = LoggerFactory.getLogger(CommandeController.class);

 
	@Autowired
	CommandeService commandeService;

	@Autowired
	ClientService clientService;

	@Autowired
	ProduitService produitService;
 

@RequestMapping(value = "/commande/listAll", method = RequestMethod.GET)

	protected ModelAndView showAllCommandes() throws Exception {
		/*
		 * Lancement du Service et recupeation donnees en base
		 */
		List<Commande> listeCommandes = commandeService.getAll();

		/*
		 * Envoi Vue + Modele MVC pour Affichage donnees vue
		 */
		return new ModelAndView("/commande/showAllCommandes", "commandes", listeCommandes);
	}

	 	@RequestMapping(value = "/commande/list", method = RequestMethod.GET)
	    public String list(Model model) throws Exception {
	        model.addAttribute("commandes", commandeService.getAll());
	        return "/commande/showAllCommandes"; // Afficher la page showAllCommandes.html qui se trouve sous /commande
	        
	    }

	    @RequestMapping(value = "/commande/get/{id}" , method = RequestMethod.GET)
	    public String get(@PathVariable Long id, Model model) throws Exception {
	        model.addAttribute("commandeToShow", commandeService.getByIdCommande(id));
	        return "/commande/showCommande"; // Afficher la page showCommande.html qui se trouve sous /commande
	    }
	    
	    
	    @RequestMapping(value = "/commande/save", method = RequestMethod.POST)
	    public String saveOrUpdate(@ModelAttribute("commandeForm") Commande commande, Model model, final RedirectAttributes redirectAttributes) throws Exception {
	    	try {
				if (commande.getIdCommande() != null) {
					commandeService.save(commande);
					redirectAttributes.addFlashAttribute("typeAlert", "update");
					redirectAttributes.addFlashAttribute("msgAlert", "Commande dont ID : " + commande.getIdCommande() + " a été mis à jour.");
				} else {
					Long idCommande = commandeService.save(commande);
					redirectAttributes.addFlashAttribute("typeAlert", "new");
					redirectAttributes.addFlashAttribute("msgAlert", "Nouvelle commande a été enregsitrée avec ID : " + idCommande);
				}
	    	
	    	} catch (Exception e) {
				e.printStackTrace();
			}
	        return "redirect:/commande/listAll";
	    }
	    

 
	    @RequestMapping("/commande/update/{id}")
	    public String update(@PathVariable Long id, Model model, final RedirectAttributes redirectAttributes) throws Exception {
	        Commande commande = commandeService.getByIdCommande(id);
	        model.addAttribute("commandeForm", commande);
			model.addAttribute("clients", clientService.getAll());
			model.addAttribute("produits", produitService.getAll());
	        return "/commande/addUpdateCommande";
	    }
	    
	    @RequestMapping(value = "/commande/delete/{id}")
	    public String delete(@PathVariable Long id, final RedirectAttributes redirectAttributes) throws Exception {
	        commandeService.deleteCommande(id);
			redirectAttributes.addFlashAttribute("typeAlert", "delete");
			redirectAttributes.addFlashAttribute("msgAlert", "Commande dont ID : "+id+" a été supprimé.");
	        return "redirect:/commande/listAll";
	    }
 
}
