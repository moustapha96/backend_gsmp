package com.ut.sn.Modeles;

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
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "service_medical")
public class ServiceMedical {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idServiceMedical;
	
	@OneToMany(cascade= CascadeType.ALL , mappedBy = "serviceMedical")
	private List<Medecin> medecins = new ArrayList<>();
	
	
	@OneToMany(cascade= CascadeType.ALL , mappedBy = "serviceMedical" )
	private List<Assistant> assistants = new ArrayList<>();
	
	
	private String nom;
	
	@CreationTimestamp
	@Column(name="created_at",nullable=false,updatable=false)
	private Date creatAt;

	public ServiceMedical(List<Medecin> medecins, List<Assistant> assistants, String nom, Date creatAt) {
		super();
		this.medecins = medecins;
		this.assistants = assistants;
		this.nom = nom;
		this.creatAt = creatAt;
	}

	public ServiceMedical() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getIdServiceMedical() {
		return idServiceMedical;
	}

	public void setIdServiceMedical(Integer idServiceMedical) {
		this.idServiceMedical = idServiceMedical;
	}

//	public List<Medecin> getMedecins() {
//		return medecins;
//	}
//
//	public void setMedecins(List<Medecin> medecins) {
//		this.medecins = medecins;
//	}
//
//	public List<Assistant> getAssistants() {
//		return assistants;
//	}
//
//	public void setAssistants(List<Assistant> assistants) {
//		this.assistants = assistants;
//	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Date getCreatAt() {
		return creatAt;
	}

	public void setCreatAt(Date creatAt) {
		this.creatAt = creatAt;
	}
	
	
}
