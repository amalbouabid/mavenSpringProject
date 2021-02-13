package tn.rnu.isi.model;


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "CATEGORIE")
public class Categorie implements java.io.Serializable {

	private Long  idCateg;
	private String codeCateg;
	private String libelleCateg;
	private Set<Produit> produits = new HashSet<Produit>(0);

	public Categorie() {
	}

	public Categorie(Long  idCateg) {
		this.idCateg = idCateg;
	}

	public Categorie(Long  idCateg, String codeCateg, String libelleCateg, Set<Produit> produits) {
		this.idCateg = idCateg;
		this.codeCateg = codeCateg;
		this.libelleCateg = libelleCateg;
		this.produits = produits;
	}

	@Id

	@Column(name = "ID_CATEG")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="CATEGORIE_gen_seq")
    @SequenceGenerator(name="CATEGORIE_gen_seq", sequenceName="CATEGORIE_SEQ", allocationSize=1)

	public Long  getIdCateg() {
		return this.idCateg;
	}

	public void setIdCateg(Long  idCateg) {
		this.idCateg = idCateg;
	}

	@Column(name = "CODE_CATEG", length = 10)
	public String getCodeCateg() {
		return this.codeCateg;
	}

	public void setCodeCateg(String codeCateg) {
		this.codeCateg = codeCateg;
	}

	@Column(name = "LIBELLE_CATEG", length = 50)
	public String getLibelleCateg() {
		return this.libelleCateg;
	}

	public void setLibelleCateg(String libelleCateg) {
		this.libelleCateg = libelleCateg;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "categorie", orphanRemoval = true)
	public Set<Produit> getProduits() {
		return this.produits;
	}

	public void setProduits(Set<Produit> produits) {
		this.produits = produits;
	}

}
