package com.ut.sn.Modeles;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "admin")
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idAdmin;
	private String prenom;
	private String nom;
	 @OneToOne
	private UserModel user;
	private String profession;
	private String tel;
	
	@CreationTimestamp
	@Column(name="created_at",nullable=false,updatable=false)
	private Date creatAt;

	public Integer getId() {
		return idAdmin;
	}

	public void setId(Integer id) {
		this.idAdmin = id;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Date getCreatAt() {
		return creatAt;
	}

	public void setCreatAt(Date creatAt) {
		this.creatAt = creatAt;
	}

	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Admin(String prenom, String nom, UserModel user, String profession, String tel, Date creatAt) {
		super();
		this.prenom = prenom;
		this.nom = nom;
		this.user = user;
		this.profession = profession;
		this.tel = tel;
		this.creatAt = creatAt;
	}
	
}
