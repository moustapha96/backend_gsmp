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

import com.ut.sn.Modeles.Medecin;
import com.ut.sn.Modeles.UserModel;
import com.ut.sn.Repository.MedecinRepository;
import com.ut.sn.Repository.UserRepository;
import com.ut.sn.exception.ResourceNotFoundException;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class MedecinController {

	@Autowired
	MedecinRepository repository;
	@Autowired
	UserRepository userRepo;

	
	@GetMapping("/medecins")
	public List<Medecin> getAllmedecin() {
		System.out.println("Get all medecins...");

		List<Medecin> medecins = new ArrayList<>();
		repository.findAll().forEach(medecins::add);

		return medecins;
	}

	@PostMapping("/medecins")
	public Medecin ajouterMedecin(@RequestBody Medecin pd){
		Medecin newPr = repository.save(pd) ; 
		return newPr;
	}
	@GetMapping("/medecins/{id}")
	public ResponseEntity<Medecin> getMedecinById(@PathVariable(value = "id") Integer Id)
			throws ResourceNotFoundException {
		Medecin medecin = repository.findById(Id)
				.orElseThrow(() -> new ResourceNotFoundException("medecin non trouvé  :: " + Id));
		return ResponseEntity.ok().body(medecin);
	}

	@DeleteMapping("/medecins/{id}")
	public Map<String, Boolean> deleteMedecin(@PathVariable(value = "id") Integer MedecinId)
			throws ResourceNotFoundException {
		Medecin medecin = repository.findById(MedecinId)
				.orElseThrow(() -> new ResourceNotFoundException("Medecin non trouvé :: " + MedecinId));
		repository.delete(medecin);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	@DeleteMapping("/medecins/delete")
	public ResponseEntity<String> deleteAllmedecins() {
		System.out.println("Delete All medecins...");
		repository.deleteAll();
		return new ResponseEntity<>("tout les medecins ont été supprimer de la base ", HttpStatus.OK);
	}

	@PutMapping("/medecins/{id}")
	public ResponseEntity<Medecin> updateMedecin(@PathVariable("id") Integer id, @RequestBody Medecin Medecin) {
		System.out.println("Update Article with ID = " + id + "...");
		Optional<Medecin> medecinInfo = repository.findById(id);
		if (medecinInfo.isPresent()) {
			Medecin medecin = medecinInfo.get();
			medecin.setAdresse(medecin.getAdresse());
			medecin.setAge(medecin.getAge());
			medecin.setGenre(medecin.getGenre());
			medecin.setNom(medecin.getNom());
			medecin.setPrenom(medecin.getPrenom());
			medecin.setTel(medecin.getTel());
			medecin.setInitial(medecin.getInitial());
			medecin.setNum_licence(medecin.getNum_licence());
			medecin.setSpecialisation(medecin.getSpecialisation());
			medecin.setTaille(medecin.getTaille());
			
			return new ResponseEntity<>(repository.save(medecin), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@GetMapping("/medecins/user/{email}")
	public ResponseEntity<Medecin> getPatientByUser(@PathVariable(value = "email") String email)
			throws ResourceNotFoundException {
		System.out.println("user " + email);
		Optional<UserModel> user = userRepo.findByEmail(email);

		Medecin medecin = repository.findByUser(user)
				.orElseThrow(() -> new ResourceNotFoundException("medecin non trouvé  :: " + user.toString()));
		return ResponseEntity.ok().body(medecin);
	}

}
