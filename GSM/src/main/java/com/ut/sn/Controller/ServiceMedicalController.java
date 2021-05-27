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

import com.ut.sn.Modeles.ServiceMedical;
import com.ut.sn.Repository.ServiceMedicalRepository;
import com.ut.sn.exception.ResourceNotFoundException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ServiceMedicalController {

	@Autowired
	ServiceMedicalRepository repository;

	@GetMapping("/ServiceMedical")
	public List<ServiceMedical> getAllserviceMedical() {
		System.out.println("Get all ServiceMedical...");

		List<ServiceMedical> serviceMedical = new ArrayList<>();
		repository.findAll().forEach(serviceMedical::add);

		return serviceMedical;
	}

	@PostMapping("/ServiceMedical")
	public ServiceMedical ajouterServiceMedical(@RequestBody ServiceMedical pd) {
		ServiceMedical newPr = repository.save(pd);
		return newPr;
	}

	@GetMapping("/ServiceMedical/{id}")
	public ResponseEntity<ServiceMedical> getServiceMedicalById(@PathVariable(value = "id") Integer Id)
			throws ResourceNotFoundException {
		ServiceMedical serviceMedical = repository.findById(Id)
				.orElseThrow(() -> new ResourceNotFoundException("serviceMedical non trouvé  :: " + Id));
		return ResponseEntity.ok().body(serviceMedical);
	}

	@DeleteMapping("/ServiceMedical/{id}")
	public Map<String, Boolean> deleteServiceMedical(@PathVariable(value = "id") Integer ServiceMedicalId)
			throws ResourceNotFoundException {
		ServiceMedical serviceMedical = repository.findById(ServiceMedicalId)
				.orElseThrow(() -> new ResourceNotFoundException("ServiceMedical non trouvé :: " + ServiceMedicalId));
		repository.delete(serviceMedical);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	@DeleteMapping("/ServiceMedical/delete")
	public ResponseEntity<String> deleteAllServiceMedical() {
		System.out.println("Delete All ServiceMedical...");
		repository.deleteAll();
		return new ResponseEntity<>("tout les ServiceMedical ont été supprimer de la base ", HttpStatus.OK);
	}

	@PutMapping("/ServiceMedical/{id}")
	public ResponseEntity<ServiceMedical> updateServiceMedical(@PathVariable("id") Integer id,
			@RequestBody ServiceMedical ServiceMedical) {
		System.out.println("Update Article with ID = " + id + "...");
		Optional<ServiceMedical> serviceMedicalInfo = repository.findById(id);
		if (serviceMedicalInfo.isPresent()) {
			ServiceMedical serviceMedical = serviceMedicalInfo.get();
			serviceMedical.setNom(serviceMedical.getNom());
			return new ResponseEntity<>(repository.save(serviceMedical), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
