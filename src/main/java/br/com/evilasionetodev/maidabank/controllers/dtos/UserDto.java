package br.com.evilasionetodev.maidabank.controllers.dtos;

import br.com.evilasionetodev.maidabank.controllers.forms.UserForm;
import br.com.evilasionetodev.maidabank.models.User;

public class UserDto {

	private String email;
	private String name;
	
	public UserDto(User user) {
		this.email = user.getEmail();
		this.name = user.getName();
	}
	public UserDto(UserForm form) {
		this.email = form.getEmail().toLowerCase();
		this.name = form.getName();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
