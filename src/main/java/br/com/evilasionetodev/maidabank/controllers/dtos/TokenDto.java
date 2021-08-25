package br.com.evilasionetodev.maidabank.controllers.dtos;

import br.com.evilasionetodev.maidabank.models.User;

public class TokenDto {

	private String name;
	private String email;
	private String token;

	public TokenDto(User user, String token) {
		this.name = user.getName();
		this.email = user.getEmail();
		this.token = token;
	}
	
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

	public void setToken(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

}
