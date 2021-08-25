package br.com.evilasionetodev.maidabank.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.evilasionetodev.maidabank.models.Role;
import br.com.evilasionetodev.maidabank.repositories.RoleRepository;

@Configuration
public class BootConfig implements CommandLineRunner{

	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public void run(String... args) throws Exception {
		Role r = new Role("ROLE_USER");
		roleRepository.save(r);
		
	}
	
	

}
