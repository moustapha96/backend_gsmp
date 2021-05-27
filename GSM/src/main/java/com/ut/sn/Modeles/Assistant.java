package com.ut.sn.Modeles;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "assistant")

public class Assistant {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idAssistant;
	private String prenom;
	private String adresse;
	private String genre;
	@OneToOne
	private UserModel user;
	private String nom;
	private String tel;
	private int age;
	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false)
	private Date creatAt;

	@ManyToOne(fetch = FetchType.EAGER)
	private ServiceMedical serviceMedical;

	public long getId() {
		return idAssistant;
	}

	public void setId(Integer id) {
		this.idAssistant = id;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
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
		return "Assistant [id=" + idAssistant + ", prenom=" + prenom + ", adresse=" + adresse + ", genre=" + genre
				+ ", user=" + user + ", nom=" + nom + ", tel=" + tel + ", age=" + age + ", creatAt=" + creatAt + "]";
	}

	public ServiceMedical getServiceMedical() {
		return serviceMedical;
	}

	public Assistant(String prenom, String adresse, String genre, UserModel user, String nom, String tel, int age,
			Date creatAt, ServiceMedical serviceMedical) {
		super();
		this.prenom = prenom;
		this.adresse = adresse;
		this.genre = genre;
		this.user = user;
		this.nom = nom;
		this.tel = tel;
		this.age = age;
		this.creatAt = creatAt;
		this.serviceMedical = serviceMedical;
	}

	public Assistant() {
		super();
		// TODO Auto-generated constructor stub
	}

}
