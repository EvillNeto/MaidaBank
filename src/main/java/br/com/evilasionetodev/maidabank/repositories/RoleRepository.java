package br.com.evilasionetodev.maidabank.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.evilasionetodev.maidabank.models.Role;

public interface RoleRepository  extends JpaRepository<Role, Long> {

	public Optional<Role> findByRole(String role);
}
