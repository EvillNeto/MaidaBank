package br.com.evilasionetodev.maidabank.controllers.forms;

import javax.validation.constraints.NotEmpty;

import br.com.evilasionetodev.maidabank.models.User;

public class UserForm {
	
	@NotEmpty
	private String email;
	
	@NotEmpty
	private String password;
	
	@NotEmpty
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	
	public User converter() {
		User user = new User(name, email, password);
		return user;
	}
}
