package br.com.evilasionetodev.maidabank.controllers;

import java.net.URI;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.evilasionetodev.maidabank.controllers.dtos.UserDto;
import br.com.evilasionetodev.maidabank.controllers.forms.UserForm;
import br.com.evilasionetodev.maidabank.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@PostMapping
	@Transactional
	public ResponseEntity<UserDto> registeruser(@RequestBody @Valid UserForm form, UriComponentsBuilder uriBuilder){
		service.saveUser(form);
		URI uri = uriBuilder.path("/users").build().toUri();
		return ResponseEntity.created(uri).body(new UserDto(form));
	}
}
