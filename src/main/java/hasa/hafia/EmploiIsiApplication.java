package hasa.hafia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import hasa.hafia.entites.Roles;
import hasa.hafia.entites.Users;
import hasa.hafia.repository.RoleRepository;
import hasa.hafia.service.UserService;

@SpringBootApplication
public class EmploiIsiApplication implements CommandLineRunner {

	@Autowired private UserService userService;
	@Autowired private RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(EmploiIsiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Users bbtanou = new Users();
		bbtanou.setNom("Diallo Boubacar");
		bbtanou.setUsername("bbtanou");
		bbtanou.setPassword("tanou");
		Roles role = new Roles();
		role.setLibelle("ROLE_DEMANDEUR");
		role.setUsers(bbtanou);
		bbtanou.getRoles().add(role);
		userService.create(bbtanou);

		Users safi = new Users();
		safi.setNom("Safiatou Diallo");
		safi.setUsername("safi");
		safi.setPassword("safi");
		Roles recruteur = new Roles();
		recruteur.setLibelle("ROLE_RECRUTEUR");
		recruteur.setUsers(safi);
		safi.getRoles().add(recruteur);
		userService.create(safi);

	}
	}
