package br.com.evilasionetodev.maidabank.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.evilasionetodev.maidabank.controllers.forms.UserForm;
import br.com.evilasionetodev.maidabank.models.User;
import br.com.evilasionetodev.maidabank.repositories.UserRepository;
import br.com.evilasionetodev.maidabank.service.exceptions.AlreadyExistsException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public void saveUser(UserForm form) {
		checkEmail(form.getEmail());
		repository.save(form.converter());
	}
	
	public void checkEmail(String email) {
		Optional<User> op = repository.findByEmail(email.toLowerCase());
		if(op.isPresent()) {
			throw new AlreadyExistsException("Email:" + email + " já foi registrado");
		}
	}
}
