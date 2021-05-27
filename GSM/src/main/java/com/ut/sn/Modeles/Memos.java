package com.ut.sn.Modeles;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "memos")
public class Memos implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idMemos;
	
	
	private String titre;
	private String message;
	

	@ManyToOne
	private Medecin medecin;
	
	@CreationTimestamp
	@Column(name="created_at",nullable=false,updatable=false)
	private Date date_creer;

	public Memos() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Memos(String titre, String message, Medecin medecin) {
		super();
		this.titre = titre;
		this.message = message;
		this.medecin = medecin;
	}

	public Integer getIdMemos() {
		return idMemos;
	}

	public void setIdMemos(Integer idMemos) {
		this.idMemos = idMemos;
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

	public Medecin getMedecin() {
		return medecin;
	}

	public void setMedecin(Medecin medecin) {
		this.medecin = medecin;
	}

	public Date getDate_creer() {
		return date_creer;
	}

	public void setDate_creer(Date date_creer) {
		this.date_creer = date_creer;
	}


	
}
