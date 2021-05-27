package com.ut.sn.Controller;

import java.util.ArrayList;
import java.util.Collection;
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

import com.ut.sn.Modeles.Consultation;
import com.ut.sn.Modeles.Dossier_medical;
import com.ut.sn.Modeles.Patient;
import com.ut.sn.Modeles.UserModel;
import com.ut.sn.Repository.DossierMedicalRepository;
import com.ut.sn.Repository.PatientRepository;
import com.ut.sn.Repository.UserRepository;
import com.ut.sn.exception.ResourceNotFoundException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")

public class DossierMedicalController {

	@Autowired
	DossierMedicalRepository repository;

	@Autowired
	UserRepository userRepo;


	@Autowired
	PatientRepository patientRepo;

	@GetMapping("/dms")
	public List<Dossier_medical> getAlldossier_medical() {
		System.out.println("Get all dms...");

		List<Dossier_medical> dossier_medical = new ArrayList<>();
		repository.findAll().forEach(dossier_medical::add);

		return dossier_medical;
	}

	@PostMapping("/dms")
	public Dossier_medical ajouterDossier_medical(@RequestBody Dossier_medical pd) {
		Dossier_medical newPr = repository.save(pd);
		return newPr;
	}

	@GetMapping("/dms/{id}")
	public ResponseEntity<Dossier_medical> getDossier_medicalById(@PathVariable(value = "id") Integer Id)
			throws ResourceNotFoundException {
		Dossier_medical dossier_medical = repository.findById(Id)
				.orElseThrow(() -> new ResourceNotFoundException("dossier_medical non trouvé  :: " + Id));
		return ResponseEntity.ok().body(dossier_medical);
	}

	@DeleteMapping("/dms/{id}")
	public Map<String, Boolean> deleteDossier_medical(@PathVariable(value = "id") Integer Dossier_medicalId)
			throws ResourceNotFoundException {
		Dossier_medical dossier_medical = repository.findById(Dossier_medicalId)
				.orElseThrow(() -> new ResourceNotFoundException("Dossier_medical non trouvé :: " + Dossier_medicalId));
		repository.delete(dossier_medical);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	@DeleteMapping("/dms/delete")
	public ResponseEntity<String> deleteAlldms() {
		System.out.println("Delete All dms...");
		repository.deleteAll();
		return new ResponseEntity<>("tout les dms ont été supprimer de la base ", HttpStatus.OK);
	}

	@PutMapping("/dms/{id}")
	public ResponseEntity<Dossier_medical> updateDossier_medical(@PathVariable("id") Integer id,
			@RequestBody Dossier_medical Dossier_medical) {
		System.out.println("Update Article with ID = " + id + "...");
		System.out.println("consultation " + Dossier_medical.getConsultations());
		Optional<Dossier_medical> dossier_medicalInfo = repository.findById(id);
		if (dossier_medicalInfo.isPresent()) {
			Dossier_medical dossier_medical = dossier_medicalInfo.get();
			dossier_medical.setDate_creation(Dossier_medical.getDate_creation());
			dossier_medical.setConsultations(Dossier_medical.getConsultations());

			return new ResponseEntity<>(repository.save(dossier_medical), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/dms/patient/{email}")
	public ResponseEntity<Dossier_medical> findDossierByPatient(@PathVariable("email") @RequestBody String email)
			throws ResourceNotFoundException {
		System.out.println("patient " + email);

		Optional<UserModel> user = userRepo.findByEmail(email);

		Patient patient = patientRepo.findByUser(user)
				.orElseThrow(() -> new ResourceNotFoundException("patient non trouvé  :: " + user.toString()));

		Dossier_medical dossier_medical = repository.findByPatient(patient)

				.orElseThrow(() -> new ResourceNotFoundException("dossier_medical non trouvé  :: "));
		if (dossier_medical != null) {
			return new ResponseEntity<>(dossier_medical, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
//		return ResponseEntity.ok().body(dossier_medical);

	}

	@PutMapping("/dms/addConsultation/{id}")
	public ResponseEntity<Dossier_medical> addConultationDm(@PathVariable("id") Integer id,
			@RequestBody Consultation consultation) {
		System.out.println("Update Article with ID = " + id + "...");
		Optional<Dossier_medical> dossier_medicalInfo = repository.findById(id);
		if (dossier_medicalInfo.isPresent()) {
			Dossier_medical dossier_medical = dossier_medicalInfo.get();

			Collection<Consultation> collection = dossier_medical.getConsultations();
			collection.add(consultation);

			dossier_medical.setConsultations(collection);

			System.out.println(dossier_medical);
			return new ResponseEntity<>(repository.save(dossier_medical), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/dms/patient/dm/{id}")
	public ResponseEntity<List<Dossier_medical>> getDmPatient(@PathVariable("id") Integer id)
			throws ResourceNotFoundException {
		Optional<Patient> patient = patientRepo.findById(id);
		List<Dossier_medical> dms = repository.findByPatient(patient);
		
		return ResponseEntity.ok().body(dms);
	}
	
	
	
	
	
	
	
	
	
}
