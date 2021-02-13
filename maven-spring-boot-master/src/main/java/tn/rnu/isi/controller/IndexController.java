package tn.rnu.isi.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import tn.rnu.isi.model.Produit;
import tn.rnu.isi.model.Client;
import tn.rnu.isi.model.Categorie;
import tn.rnu.isi.model.Commande;
import tn.rnu.isi.service.ProduitService;
import tn.rnu.isi.service.ClientService;
import tn.rnu.isi.service.CategorieService;
import tn.rnu.isi.service.CommandeService;


 
 
@Controller 
@RequestMapping("/") //make all URL's through this controller relative to /index
public class IndexController {
	
	private final Logger logger = LoggerFactory.getLogger(IndexController.class);

	@Autowired
	ProduitService produitService;

	@Autowired
	ClientService clientService;

	@Autowired
	CategorieService categorieService;

    @Autowired
    CommandeService commandeService;
	
	@RequestMapping(value="/", method= RequestMethod.GET)
	public String index(Map<String, Object> model) throws Exception {
	 
    return "index";
	}   
	
 
	/**************************************
	 * Gestion produit
	 * **Nouvelle : /produit/new
     * **Rechercher : /produit/search
     * **Liser Tous : /produit/listAll
	 ***************************************/	
	 
	// show new Produit form
		@RequestMapping(value = "/produit/new", method = RequestMethod.GET)
		public String showNewProduit(Model model) {

			logger.debug(":::showNewProduit:::");

			Produit produit = new Produit();
			model.addAttribute("categories", categorieService.getAll());
			model.addAttribute("produitForm", produit);

	 
			 return "/produit/addUpdateProduit";// C'est le nom de la page JSP à rediriger (newProduit.jsp)

		}

		@RequestMapping(value = "/client/new", method = RequestMethod.GET)
		public String showNewClient(Model model) {

			logger.debug(":::showNewClient:::");

			Client client = new Client();

			model.addAttribute("clientForm", client);


			return "/client/addUpdateClient";// C'est le nom de la page JSP à rediriger (newClient.jsp)

		}

		@RequestMapping(value = "/categorie/new", method = RequestMethod.GET)
		public String showNewCategorie(Model model) {

			logger.debug(":::showNewCategorie:::");

			Categorie categorie = new Categorie();

			model.addAttribute("categorieForm", categorie);


			return "/categorie/addUpdateCategorie";// C'est le nom de la page JSP à rediriger (newCategorie.jsp)

		}

        @RequestMapping(value = "/commande/new", method = RequestMethod.GET)
        public String showNewCommande(Model model) {

            logger.debug(":::showNewCommande:::");

            Commande commande = new Commande();

            model.addAttribute("commandeForm", commande);
			model.addAttribute("clients", clientService.getAll());
			model.addAttribute("produits", produitService.getAll());
            return "/commande/addUpdateCommande";// C'est le nom de la page JSP à rediriger (newCommande.jsp)

        }
	
				
	 // show list of All Produit
		@RequestMapping({"/produit/listAll","produitList"})
		protected ModelAndView lisAllProduits(HttpServletRequest request,
				HttpServletResponse response) throws Exception {
			/*
			 * Lancement du Service et récupération données en base
			 */
			List<Produit> listeProduits = produitService.getAll();

			/*
			 * Envoi Vue + Modèle MVC pour Affichage données vue
			 */
			return new ModelAndView("/produit/showAllProduits", "produits", listeProduits);
		}

		@RequestMapping({"/client/listAll","clientList"})
		protected ModelAndView lisAllClients(HttpServletRequest request,
											  HttpServletResponse response) throws Exception {
			/*
			 * Lancement du Service et récupération données en base
			 */
			List<Client> listeClients = clientService.getAll();

			/*
			 * Envoi Vue + Modèle MVC pour Affichage données vue
			 */
			return new ModelAndView("/client/showAllClients", "clients", listeClients);
		}

		@RequestMapping({"/categorie/listAll","categorieList"})
		protected ModelAndView lisAllCategories(HttpServletRequest request,
											 HttpServletResponse response) throws Exception {
			/*
			 * Lancement du Service et récupération données en base
			 */
			List<Categorie> listeCategories = categorieService.getAll();

			/*
			 * Envoi Vue + Modèle MVC pour Affichage données vue
			 */
			return new ModelAndView("/categorie/showAllCategories", "categories", listeCategories);
		}

        @RequestMapping({"/commande/listAll","commandeList"})
        protected ModelAndView lisAllCommandes(HttpServletRequest request,
                                             HttpServletResponse response) throws Exception {
            /*
             * Lancement du Service et récupération données en base
             */
            List<Commande> listeCommandes = commandeService.getAll();

            /*
             * Envoi Vue + Modèle MVC pour Affichage données vue
             */
            return new ModelAndView("/commande/showAllCommandes", "commandes", listeCommandes);
        }

}
