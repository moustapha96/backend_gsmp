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

import com.ut.sn.Modeles.Consultation;
import com.ut.sn.Repository.ConsultationRepository;
import com.ut.sn.exception.ResourceNotFoundException;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ConsultationController {

	@Autowired
	ConsultationRepository repository;

	@GetMapping("/Consultation")
	public List<Consultation> getAllconsultation() {
		System.out.println("Get all Consultation...");

		List<Consultation> consultation = new ArrayList<>();
		repository.findAll().forEach(consultation::add);

		return consultation;
	}

	@PostMapping("/Consultation")
	public Consultation ajouterConsultation(@RequestBody Consultation pd){
		Consultation newPr = repository.save(pd) ; 
		return newPr;
	}
	
	@GetMapping("/Consultation/{id}")
	public ResponseEntity<Consultation> getConsultationById(@PathVariable(value = "id") Integer Id)
			throws ResourceNotFoundException {
		Consultation consultation = repository.findById(Id)
				.orElseThrow(() -> new ResourceNotFoundException("consultation non trouvé  :: " + Id));
		return ResponseEntity.ok().body(consultation);
	}

	@DeleteMapping("/Consultation/{id}")
	public Map<String, Boolean> deleteConsultation(@PathVariable(value = "id") Integer ConsultationId)
			throws ResourceNotFoundException {
		Consultation consultation = repository.findById(ConsultationId)
				.orElseThrow(() -> new ResourceNotFoundException("Consultation non trouvé :: " + ConsultationId));
		repository.delete(consultation);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	@DeleteMapping("/Consultation/delete")
	public ResponseEntity<String> deleteAllConsultation() {
		System.out.println("Delete All Consultation...");
		repository.deleteAll();
		return new ResponseEntity<>("tout les Consultation ont été supprimer de la base ", HttpStatus.OK);
	}

	@PutMapping("/Consultation/{id}")
	public ResponseEntity<Consultation> updateConsultation(@PathVariable("id") Integer id, @RequestBody Consultation Consultation) {
		System.out.println("Update Article with ID = " + id + "...");
		Optional<Consultation> consultationInfo = repository.findById(id);
		if (consultationInfo.isPresent()) {
			Consultation consultation = consultationInfo.get();
			consultation.setDate_consultation(consultation.getDate_consultation());
			consultation.setDiagnostic(consultation.getDiagnostic());
			consultation.setTraitement(consultation.getTraitement());
			
			return new ResponseEntity<>(repository.save(consultation), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
