package com.ut.sn.Modeles;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "dossier_medical")
public class Dossier_medical implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idDossierMedical;
	
	@ManyToOne(optional = false,fetch = FetchType.EAGER)
	private Medecin medecin;
	
	@OneToOne
	private Patient patient;
	
	@OneToMany(cascade = CascadeType.ALL , mappedBy = "dossierMedical" ,fetch = FetchType.LAZY)
    private Collection<Consultation> consultations ;
	
	@CreationTimestamp
	@Column(name="date_creation",nullable=false,updatable=false)
	private Date date_creation;

	public Dossier_medical() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Dossier_medical(Medecin medecin, Patient patient,
			Date date_creation) {
		super();
		this.medecin = medecin;
		this.patient = patient;
		this.date_creation = date_creation;
	}



	public Integer getIdDossierMedical() {
		return idDossierMedical;
	}

	public void setIdDossierMedical(Integer idDossierMedical) {
		this.idDossierMedical = idDossierMedical;
	}

	public Medecin getMedecin() {
		return medecin;
	}

	public void setMedecin(Medecin medecin) {
		this.medecin = medecin;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	
	public Date getDate_creation() {
		return date_creation;
	}

	public void setDate_creation(Date date_creation) {
		this.date_creation = date_creation;
	}

	public Collection<Consultation> getConsultations() {
		return consultations;
	}
	public void setConsultations(Collection<Consultation> consultations) {
		this.consultations = consultations;
	}
}
