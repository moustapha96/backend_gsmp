package com.ut.sn.Modeles;

import java.time.LocalDateTime;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "user_model")
@NamedQueries({ @NamedQuery(name = "User.findAll", query = "SELECT u FROM UserModel u"),
		@NamedQuery(name = "User.findByIduser", query = "SELECT u FROM UserModel u WHERE u.iduser = :iduser"),
		@NamedQuery(name = "User.findByEmail", query = "SELECT u FROM UserModel u WHERE u.email = :email"),
		@NamedQuery(name = "User.findByPassword", query = "SELECT u FROM UserModel u WHERE u.password = :password") })
public class UserModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer iduser;
	private String email;
	private String password;
	private String role;
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
	private Assistant assistant;
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
	private Medecin medecin;
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
	private Patient patient;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
	private Admin admin;

	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime creatAt;

	public LocalDateTime getCreatAt() {
		return creatAt;
	}
	public void setCreatAt(LocalDateTime creatAt) {
		this.creatAt = creatAt;
	}
	
	public UserModel() {
	}

	public UserModel(Integer iduser) {
		this.iduser = iduser;
	}

	public UserModel(Integer iduser, String email, String password) {
		this.iduser = iduser;
		this.email = email;
		this.password = password;
	}

	public Integer getIduser() {
		return iduser;
	}

	public void setIduser(Integer iduser) {
		this.iduser = iduser;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (iduser != null ? iduser.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof UserModel)) {
			return false;
		}
		UserModel other = (UserModel) object;
		if ((this.iduser == null && other.iduser != null)
				|| (this.iduser != null && !this.iduser.equals(other.iduser))) {
			return false;
		}
		return true;
	}
}
