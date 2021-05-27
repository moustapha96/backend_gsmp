package com.ut.sn.DTO;

public class UserDTO {
	private String password;
	private String role;
	private String email;
	private Integer iduser;
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEamil(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getId() {
		return iduser;
	}
	public void setId(Integer id) {
		this.iduser = id;
	}
}
