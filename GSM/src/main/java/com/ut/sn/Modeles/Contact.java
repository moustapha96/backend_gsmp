package com.ut.sn.Modeles;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "contact")

public class Contact implements Serializable{
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer id;
		
		private String nom;
		private String email;
		private String subject;
		private String message;
	
		@CreationTimestamp
		@Column(name="date_contact",nullable=false,updatable=false)
		private Date date_contact;

		public Contact(String nom, String email, String subject, String message, Date date_contact) {
			super();
			this.nom = nom;
			this.email = email;
			this.subject = subject;
			this.message = message;
			this.date_contact = date_contact;
		}

		public Contact() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getNom() {
			return nom;
		}

		public void setNom(String nom) {
			this.nom = nom;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getSubject() {
			return subject;
		}

		public void setSubject(String subject) {
			this.subject = subject;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public Date getDate_contact() {
			return date_contact;
		}

		public void setDate_contact(Date date_contact) {
			this.date_contact = date_contact;
		}


		
}
