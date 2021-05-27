package com.ut.sn.Modeles;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.CascadeType;
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
@Table(name = "rendezvous")
public class RendezVous implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idRendezVous;
	private Date date_rv;
	private String heure;
	
	@ManyToOne(fetch= FetchType.EAGER)
	private Medecin medecin;
	
	@ManyToOne(fetch= FetchType.EAGER)
	private Patient patient;
	
	
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "rendezVous")
	private Notification notification;
	
	@CreationTimestamp
	@Column(name="created_at",nullable=false,updatable=false)
	private Date creatAt;
	

//	public Notification getNotification() {
//		return notification;
//	}
//
//	public void setNotification(Notification notification) {
//		this.notification = notification;
//	}
	
	public RendezVous() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public RendezVous(Date date_rv, String heure, Medecin medecin, Patient patient, Notification notification,
			Date creatAt) {
		super();
		this.date_rv = date_rv;
		this.heure = heure;
		this.medecin = medecin;
		this.patient = patient;
		this.notification = notification;
		this.creatAt = creatAt;
	}

	public Integer getIdRendezVous() {
		return idRendezVous;
	}

	public void setIdRendezVous(Integer idRendezVous) {
		this.idRendezVous = idRendezVous;
	}

	public Date getDate_rv() {
		return date_rv;
	}

	public void setDate_rv(Date date_rv) {
		this.date_rv = date_rv;
	}

	public String getHeure() {
		return heure;
	}

	public void setHeure(String heure) {
		this.heure = heure;
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

	

	public Date getCreatAt() {
		return creatAt;
	}

	public void setCreatAt(Date creatAt) {
		this.creatAt = creatAt;
	}
	
	
}
