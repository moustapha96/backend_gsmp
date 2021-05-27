package com.ut.sn.Modeles;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;


@Entity
@Table(name = "patient")
public class Patient implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPatient;
	private String statut_social;
	private String prenom;
	private String profession;
	private String adresse;
	private String genre;
	@OneToOne
	private UserModel user;
	private String nom;
	private String tel;
	private int taille;
	private int age;
	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false)
	private Date creatAt;
	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "patient", fetch= FetchType.LAZY)
	private List<Notification> notifications = new ArrayList<>();
	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "patient", fetch= FetchType.LAZY)
	private List<RendezVous> rendezVous = new ArrayList<>();
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "patient", fetch = FetchType.LAZY)
	private Dossier_medical dossierMedical;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "patient", fetch= FetchType.LAZY)
	private List<DemandeRV> demandeRV = new ArrayList<>();
	
	

	
	public Integer getId() {
		return idPatient;
	}

	public void setId(Integer id) {
		this.idPatient = id;
	}

	public String getStatut_social() {
		return statut_social;
	}

	public void setStatut_social(String statut_social) {
		this.statut_social = statut_social;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public UserModel getuser() {
		return user;
	}

	public void setuser(UserModel user) {
		this.user = user;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public int getTaille() {
		return taille;
	}

	public void setTaille(int taille) {
		this.taille = taille;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getCreatAt() {
		return creatAt;
	}

	public void setCreatAt(Date creatAt) {
		this.creatAt = creatAt;
	}

	@Override
	public String toString() {
		return "Patient [id=" + idPatient + ", statut_social=" + statut_social + ", prenom=" + prenom + ", profession="
				+ profession + ", adresse=" + adresse + ", genre=" + genre + ", user=" + user + ", nom=" + nom
				+ ", tel=" + tel + ", taille=" + taille + ", age=" + age + ", creatAt=" + creatAt + "]";
	}

	public Patient(Integer id, String statut_social, String prenom, String profession, String adresse, String genre,
			UserModel user, String nom, String tel, int taille, int age, Date creatAt) {
		super();
		this.idPatient = id;
		this.statut_social = statut_social;
		this.prenom = prenom;
		this.profession = profession;
		this.adresse = adresse;
		this.genre = genre;
		this.user = user;
		this.nom = nom;
		this.tel = tel;
		this.taille = taille;
		this.age = age;
		this.creatAt = creatAt;
	}

	public Patient() {
		super();
		// TODO Auto-generated constructor stub
	}

}
