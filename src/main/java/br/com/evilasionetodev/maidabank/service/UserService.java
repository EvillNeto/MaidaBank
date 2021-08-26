package br.com.evilasionetodev.maidabank.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.evilasionetodev.maidabank.controllers.forms.UserForm;
import br.com.evilasionetodev.maidabank.models.User;
import br.com.evilasionetodev.maidabank.repositories.RoleRepository;
import br.com.evilasionetodev.maidabank.repositories.UserRepository;
import br.com.evilasionetodev.maidabank.service.exceptions.BadRequestException;
import br.com.evilasionetodev.maidabank.service.exceptions.NotFoundException;

@Service
public class UserService {

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository repository;
	
	public void saveUser(UserForm form) {
		checkEmail(form.getEmail());
		User user = form.converter();
		user.addRoles(roleRepository.findByRole("ROLE_USER").get());
		repository.save(user);
	}
	
	public void checkEmail(String email) {
		Optional<User> op = repository.findByEmail(email);
		if(op.isPresent()) {
			throw new BadRequestException("Email:" + email + " já foi registrado");
		}
	}
	
	public User findByEmail(String email) {
		Optional<User> op = repository.findByEmail(email);
		if(op.isEmpty()) {
			throw new NotFoundException("Email incorreto");
		}
		return op.get();
	}
}
