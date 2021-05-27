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

import com.ut.sn.Modeles.Patient;
import com.ut.sn.Modeles.UserModel;
import com.ut.sn.Repository.PatientRepository;
import com.ut.sn.Repository.UserRepository;
import com.ut.sn.exception.ResourceNotFoundException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class PatientController {

	@Autowired
	PatientRepository repository;

	@Autowired
	UserRepository userRepo;

	@GetMapping("/patients")
	public List<Patient> getAllpatient() {
		System.out.println("Get all patients...");

		List<Patient> patients = new ArrayList<>();
		repository.findAll().forEach(patients::add);

		return patients;
	}

	@PostMapping("/patients")
	public Patient ajouterPatient(@RequestBody Patient pd) {
		Patient newPr = repository.save(pd);
		return newPr;
	}

	@GetMapping("/patients/{id}")
	public ResponseEntity<Patient> getPatientById(@PathVariable(value = "id") Integer Id)
			throws ResourceNotFoundException {
		Patient patient = repository.findById(Id)
				.orElseThrow(() -> new ResourceNotFoundException("patient non trouvé  :: " + Id));
		return ResponseEntity.ok().body(patient);
	}

	@GetMapping("/patients/user/{email}")
	public ResponseEntity<Patient> getPatientByUser(@PathVariable(value = "email") String email)
			throws ResourceNotFoundException {
		System.out.println("user " + email);
		Optional<UserModel> user = userRepo.findByEmail(email);

		Patient patient = repository.findByUser(user)
				.orElseThrow(() -> new ResourceNotFoundException("patient non trouvé  :: " + user.toString()));
		return ResponseEntity.ok().body(patient);
	}

//	@GetMapping("/patients/user/{email}")
//	public ResponseEntity<Patient> getPatientByUser(@PathVariable(value="email")  String email)
//			throws ResourceNotFoundException {
//		UserModel user = userRepo.findOneByEmail(email);
//		Patient patient = user.getOnePatient();
//		return ResponseEntity.ok().body(patient);
//	}

	@DeleteMapping("/patients/{id}")
	public Map<String, Boolean> deletePatient(@PathVariable(value = "id") Integer PatientId)
			throws ResourceNotFoundException {
		Patient patient = repository.findByIdPatient(PatientId)
				.orElseThrow(() -> new ResourceNotFoundException("Patient non trouvé :: " + PatientId));
		repository.delete(patient);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	@DeleteMapping("/patients/delete")
	public ResponseEntity<String> deleteAllpatients() {
		System.out.println("Delete All patients...");
		repository.deleteAll();
		return new ResponseEntity<>("tout les patients ont été supprimer de la base ", HttpStatus.OK);
	}

	@PutMapping("/patients/{id}")
	public ResponseEntity<Patient> updatePatient(@PathVariable("id") Integer id, @RequestBody Patient Patient) {
		System.out.println("Update Article with ID = " + id + "...");
		Optional<Patient> patientInfo = repository.findByIdPatient(id);
		if (patientInfo.isPresent()) {
			Patient patient = patientInfo.get();
			patient.setAdresse(patient.getAdresse());
			patient.setAge(patient.getAge());
			patient.setGenre(patient.getGenre());
			patient.setNom(patient.getNom());
			patient.setPrenom(patient.getPrenom());
			patient.setProfession(patient.getProfession());
			patient.setStatut_social(patient.getStatut_social());
			patient.setTaille(patient.getTaille());
			patient.setTel(patient.getTel());

			return new ResponseEntity<>(repository.save(patient), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
