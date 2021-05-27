package com.ut.sn.Modeles;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "consultation")
public class Consultation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idConsultation;
	
	private String diagnostic;
	private String traitement;
	
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Dossier_medical dossierMedical;
	
	@CreationTimestamp
	@Column(name="date_consultation",nullable=false,updatable=false)
	private Date date_consultation;

	public Consultation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Consultation(String diagnostic, String traitement, Date date_consultation, Dossier_medical dossier_medical) {
		super();
		this.diagnostic = diagnostic;
		this.traitement = traitement;
		this.date_consultation = date_consultation;
		this.dossierMedical = dossier_medical;
	}

	public Integer getIdConsultation() {
		return idConsultation;
	}

	public void setIdConsultation(Integer idConsultation) {
		this.idConsultation = idConsultation;
	}

	public String getDiagnostic() {
		return diagnostic;
	}

	public void setDiagnostic(String diagnostic) {
		this.diagnostic = diagnostic;
	}

	public String getTraitement() {
		return traitement;
	}

	public void setTraitement(String traitement) {
		this.traitement = traitement;
	}

	public Date getDate_consultation() {
		return date_consultation;
	}

	public void setDate_consultation(Date date_consultation) {
		this.date_consultation = date_consultation;
	}
	
	public void setDossierMedical(Dossier_medical dossierMedical) {
		this.dossierMedical = dossierMedical;
	}
//	public Dossier_medical getDossierMedical() {
//		return dossierMedical;
//	}
}
