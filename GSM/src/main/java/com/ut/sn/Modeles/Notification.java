package com.ut.sn.Modeles;

import java.io.Serializable;
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
@Table(name = "notification")
public class Notification implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idNotification;
	private String titre;
	private String message;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Patient patient;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Medecin medecin;
	
	@OneToOne
	private RendezVous rendezVous;
	
	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false)
	private Date date_creer;

	
	public RendezVous getRendezVous() {
		return rendezVous;
	}
	
	public Patient getPatient() {
		return patient;
	}

	
	public Medecin getMedecin() {
		return medecin;
	}
	
	

	
	
	public Notification(String titre, String message, Patient patient, Medecin medecin, Date date_creer,
			RendezVous rendezVous) {
		super();

		this.titre = titre;
		this.message = message;
		this.patient = patient;
		this.medecin = medecin;
		this.patient = patient;
		this.date_creer = date_creer;
		this.rendezVous = rendezVous;
	}

	public Notification() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getIdNotification() {
		return idNotification;
	}

	public void setIdNotification(Integer idNotification) {
		this.idNotification = idNotification;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	public Date getDate_creer() {
		return date_creer;
	}

	public void setDate_creer(Date date_creer) {
		this.date_creer = date_creer;
	}



}
