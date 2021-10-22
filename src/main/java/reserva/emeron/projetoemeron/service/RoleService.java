package reserva.emeron.projetoemeron.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reserva.emeron.projetoemeron.model.Role;
import reserva.emeron.projetoemeron.repository.RoleRepository;

@Service
public class RoleService {
	
	
	@Autowired
	private RoleRepository roleRepository;

	
	public List<Role> buscarTodosPerfil(){
		return roleRepository.findAll();
	}
	
	
	public Integer countPerfil() {
		 return (int) roleRepository.count();
		 
		}
}
