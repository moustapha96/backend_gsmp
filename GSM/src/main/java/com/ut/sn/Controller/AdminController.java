package com.ut.sn.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ut.sn.Modeles.Admin;
import com.ut.sn.Repository.AdminRepository;
import com.ut.sn.Repository.UserRepository;
import com.ut.sn.exception.ResourceNotFoundException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class AdminController {

	@Autowired
	AdminRepository repository;

	@Autowired
	UserRepository userRepo;

	@GetMapping("/admins")
	public List<Admin> getAllAdmin() {
		System.out.println("Get all admins...");

		List<Admin> admins = new ArrayList<>();
		repository.findAll().forEach(admins::add);

		return admins;
	}

	@PostMapping("/admins")
	public Admin ajouterAdmin(@RequestBody Admin pd) {
		Admin newPr = repository.save(pd);
		return newPr;
	}

	@GetMapping("/admins/{id}")
	public ResponseEntity<Admin> getAdminById(@PathVariable(value = "id") Integer Id) throws ResourceNotFoundException {
		Admin admin = repository.findById(Id)
				.orElseThrow(() -> new ResourceNotFoundException("Admin non trouvé  :: " + Id));
		return ResponseEntity.ok().body(admin);
	}

//	@GetMapping("/admins/user/{email}")
//	public ResponseEntity<Admin> getAdminByUser(@PathVariable(value = "email") String email)
//			throws ResourceNotFoundException {
//		UserModel user = userRepo.findOneByEmail(email);
//		Admin admin = user.getAdmin();
//		return ResponseEntity.ok().body(admin);
//	}

	@DeleteMapping("/admins/{id}")
	public Map<String, Boolean> deleteAdmin(@PathVariable(value = "id") Integer AdminId)
			throws ResourceNotFoundException {
		Admin admin = repository.findById(AdminId)
				.orElseThrow(() -> new ResourceNotFoundException("Admin non trouvé :: " + AdminId));
		repository.delete(admin);

		Map<String, Boolean> response = new HashMap<>();
		response.put("admin supprimé", Boolean.TRUE);
		return response;
	}

	@DeleteMapping("/admins/delete")
	public ResponseEntity<String> deleteAlladmins() {
		System.out.println("Delete All admins...");
		repository.deleteAll();
		return new ResponseEntity<>("tout les admins ont été supprimer de la base ", HttpStatus.OK);
	}

	@PutMapping("/admins/{id}")
	public ResponseEntity<Admin> updateAdmin(@PathVariable("id") Integer id, @RequestBody Admin Admin) {
		System.out.println("Update Article with ID = " + id + "...");
		Optional<Admin> adminInfo = repository.findById(id);
		if (adminInfo.isPresent()) {
			Admin admin = adminInfo.get();
			admin.setNom(admin.getNom());
			admin.setPrenom(admin.getPrenom());
			admin.setProfession(admin.getProfession());
			admin.setTel(admin.getTel());

			return new ResponseEntity<>(repository.save(admin), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
