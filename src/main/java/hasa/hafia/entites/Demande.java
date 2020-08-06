package hasa.hafia.entites;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Demande {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nom;
	private String prenom;
	private String motivation;
	private String niveauEtude;
	private int experience;
	@ManyToOne
	private Offres offres = new Offres();
	public Demande() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Demande(Long id, String nom, String prenom, String motivation, String niveauEtude, int experience,
				   Offres offres) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.motivation = motivation;
		this.niveauEtude = niveauEtude;
		this.experience = experience;
		this.offres = offres;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getMotivation() {
		return motivation;
	}
	public void setMotivation(String motivation) {
		this.motivation = motivation;
	}
	public String getNiveauEtude() {
		return niveauEtude;
	}
	public void setNiveauEtude(String niveauEtude) {
		this.niveauEtude = niveauEtude;
	}
	public int getExperience() {
		return experience;
	}
	public void setExperience(int experience) {
		this.experience = experience;
	}
	public Offres getOffres() {
		return offres;
	}
	public void setOffres(Offres offres) {
		this.offres = offres;
	}



}
