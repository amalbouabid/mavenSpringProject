package tn.rnu.isi.model;


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "COMMANDE")
public class Commande implements java.io.Serializable {

	private Long idCommande;
	private Client client;
	private Produit produit;
	private Long qteCommande;
	private Date dateCommande;
	private Long etatCommande;

	public Commande() {
	}

	public Commande(Long idCommande, Client client, Produit produit) {
		this.idCommande = idCommande;
		this.client = client;
		this.produit = produit;
	}

	public Commande(Long idCommande, Client client, Produit produit, Long qteCommande, Date dateCommande,
			Long etatCommande) {
		this.idCommande = idCommande;
		this.client = client;
		this.produit = produit;
		this.qteCommande = qteCommande;
		this.dateCommande = dateCommande;
		this.etatCommande = etatCommande;
	}
	
	@Id
	@Column(name = "ID_COMMANDE")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="COMMANDE_gen_seq")
    @SequenceGenerator(name="COMMANDE_gen_seq", sequenceName="COMMANDE_SEQ", allocationSize=1)

	public Long getIdCommande() {
		return this.idCommande;
	}

	public void setIdCommande(Long idCommande) {
		this.idCommande = idCommande;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_CLIENT", nullable = false)
	public Client getClient() {
		return this.client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PRODUIT",  nullable = false)
	public Produit getProduit() {
		return this.produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	@Column(name = "QTE_COMMANDE", precision = 22, scale = 0)
	public Long getQteCommande() {
		return this.qteCommande;
	}

	public void setQteCommande(Long qteCommande) {
		this.qteCommande = qteCommande;
	}

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	@Column(name = "DATE_COMMANDE", length = 10)
	public Date getDateCommande() {
		return this.dateCommande;
	}

	public void setDateCommande(Date dateCommande) {
		this.dateCommande = dateCommande;
	}

	@Column(name = "ETAT_COMMANDE", precision = 22, scale = 0)
	public Long getEtatCommande() {
		return this.etatCommande;
	}

	public void setEtatCommande(Long etatCommande) {
		this.etatCommande = etatCommande;
	}

}
