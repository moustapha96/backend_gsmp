package com.ut.sn.Modeles;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;



@Entity
@Table(name = "medecin")

public class Medecin implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idMedecin;
	private String specialisation;
	private String initial;
	private String prenom;
	private String num_licence;
	private String adresse;
	 @OneToOne
	private UserModel user;
	private String genre;
	private String nom;
	private String tel;
	private int taille;
	private int age;
	
	@CreationTimestamp
	@Column(name="created_at",nullable=false,updatable=false)
	private Date creatAt;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "medecin")
	private List<Notification> notifications = new ArrayList<>();
	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "medecin")
	private List<RendezVous> rendezVous = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "medecin")
	private List<Dossier_medical> dossierMedical = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "medecin")
	private List<Memos> memos = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL , mappedBy = "medecin")
	private List<DemandeRV> deamndeRv = new ArrayList<>();
	
	
	@OneToOne
	private ServiceMedical serviceMedical;

	public Medecin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Medecin(String specialisation, String initial, String prenom, String num_licence, String adresse,
			UserModel user, String genre,String nom, String tel, int taille, int age, Date creatAt,
			List<Notification> notifications, List<RendezVous> rendezVous, List<Dossier_medical> dossierMedical,
			List<Memos> memos, ServiceMedical serviceMedical) {
		super();
		this.specialisation = specialisation;
		this.initial = initial;
		this.prenom = prenom;
		this.num_licence = num_licence;
		this.adresse = adresse;
		this.user = user;
		this.genre = genre;
		
		this.nom = nom;
		this.tel = tel;
		this.taille = taille;
		this.age = age;
		this.creatAt = creatAt;
		this.notifications = notifications;
		this.rendezVous = rendezVous;
		this.dossierMedical = dossierMedical;
		this.memos = memos;
		this.serviceMedical = serviceMedical;
	}

	public Integer getIdMedecin() {
		return idMedecin;
	}

	public void setIdMedecin(Integer idMedecin) {
		this.idMedecin = idMedecin;
	}

	public String getSpecialisation() {
		return specialisation;
	}

	public void setSpecialisation(String specialisation) {
		this.specialisation = specialisation;
	}

	public String getInitial() {
		return initial;
	}

	public void setInitial(String initial) {
		this.initial = initial;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNum_licence() {
		return num_licence;
	}

	public void setNum_licence(String num_licence) {
		this.num_licence = num_licence;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
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

//	public List<Notification> getNotifications() {
//		return notifications;
//	}
//
//	public void setNotifications(List<Notification> notifications) {
//		this.notifications = notifications;
//	}
//
//	public List<RendezVous> getRendezVous() {
//		return rendezVous;
//	}
//
//	public void setRendezVous(List<RendezVous> rendezVous) {
//		this.rendezVous = rendezVous;
//	}
//
//	public List<Dossier_medical> getDossierMedical() {
//		return dossierMedical;
//	}
//
//	public void setDossierMedical(List<Dossier_medical> dossierMedical) {
//		this.dossierMedical = dossierMedical;
//	}
//
//	public List<Memos> getMemos() {
//		return memos;
//	}
//
//	public void setMemos(List<Memos> memos) {
//		this.memos = memos;
//	}
//
//	public ServiceMedical getServiceMedical() {
//		return serviceMedical;
//	}
//
//	public void setServiceMedical(ServiceMedical serviceMedical) {
//		this.serviceMedical = serviceMedical;
//	}
//	
	
}
