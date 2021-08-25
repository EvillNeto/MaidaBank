package br.com.evilasionetodev.maidabank.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.evilasionetodev.maidabank.controllers.dtos.TokenDto;
import br.com.evilasionetodev.maidabank.controllers.forms.LoginForm;
import br.com.evilasionetodev.maidabank.security.TokenService;
import br.com.evilasionetodev.maidabank.service.UserService;

@RestController
@RequestMapping(value = "/auth")
public class AuthenticationController {
	
	@Autowired
	private UserService service;

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<TokenDto> autentication(@RequestBody @Valid LoginForm form){
		UsernamePasswordAuthenticationToken loginData = form.converter();
		try {
			Authentication authentication = authManager.authenticate(loginData);
			String token = tokenService.generateToken(authentication);
			return ResponseEntity.ok(new TokenDto(service.findByEmail(form.getEmail()), token));
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
}
