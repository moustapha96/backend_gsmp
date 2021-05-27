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
import com.ut.sn.Modeles.Patient;
import com.ut.sn.Modeles.RendezVous;
import com.ut.sn.Repository.MedecinRepository;
import com.ut.sn.Repository.PatientRepository;
import com.ut.sn.Repository.RendezVousRepository;
import com.ut.sn.exception.ResourceNotFoundException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class RendezVousController {

	@Autowired
	RendezVousRepository repository;

	@Autowired 
	MedecinRepository repoMedecin;
	
	@Autowired PatientRepository repoPatient;
	
	@GetMapping("/RendezVous")
	public List<RendezVous> getAllrendezVous() {
		System.out.println("Get all RendezVous...");

		List<RendezVous> rendezVous = new ArrayList<>();
		repository.findAll().forEach(rendezVous::add);

		return rendezVous;
	}

	@PostMapping("/RendezVous")
	public RendezVous ajouterRendezVous(@RequestBody RendezVous pd) {
		RendezVous newPr = repository.save(pd);
		return newPr;
	}

	@GetMapping("RendezVous/medecin/{id}")
	public ResponseEntity<List<RendezVous>> getRvMedecin(@PathVariable(value="id")Integer id)
			throws ResourceNotFoundException{
			Optional<Medecin> medecin = repoMedecin.findById(id);
			List<RendezVous> rendezVous = repository.findByMedecin(medecin);
//					.orElseThrow(() -> new ResourceNotFoundException("ce medecin n'a pas de rendezVous " + medecin.prenom));
			return ResponseEntity.ok().body(rendezVous);
	}
	@GetMapping("/RendezVous/{id}")
	public ResponseEntity<RendezVous> getRendezVousById(@PathVariable(value = "id") Integer Id)
			throws ResourceNotFoundException {
		RendezVous rendezVous = repository.findById(Id)
				.orElseThrow(() -> new ResourceNotFoundException("rendezVous non trouvé  :: " + Id));
		return ResponseEntity.ok().body(rendezVous);
	}

	@DeleteMapping("/RendezVous/{id}")
	public Map<String, Boolean> deleteRendezVous(@PathVariable(value = "id") Integer RendezVousId)
			throws ResourceNotFoundException {
		RendezVous rendezVous = repository.findById(RendezVousId)
				.orElseThrow(() -> new ResourceNotFoundException("RendezVous non trouvé :: " + RendezVousId));
		repository.delete(rendezVous);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	@DeleteMapping("/RendezVous/delete")
	public ResponseEntity<String> deleteAllRendezVous() {
		System.out.println("Delete All RendezVous...");
		repository.deleteAll();
		return new ResponseEntity<>("tout les RendezVous ont été supprimer de la base ", HttpStatus.OK);
	}

	@PutMapping("/RendezVous/{id}")
	public ResponseEntity<RendezVous> updateRendezVous(@PathVariable("id") Integer id,
			@RequestBody RendezVous RendezVous) {
		System.out.println("Update Article with ID = " + id + "...");
		Optional<RendezVous> rendezVousInfo = repository.findById(id);
		if (rendezVousInfo.isPresent()) {
			RendezVous rendezVous = rendezVousInfo.get();
			rendezVous.setDate_rv(rendezVous.getDate_rv());
			rendezVous.setHeure(rendezVous.getHeure());

			return new ResponseEntity<>(repository.save(rendezVous), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("RendezVous/patient/{id}")
	public ResponseEntity<List<RendezVous>> getRvPatient(@PathVariable(value="id")Integer id)
			throws ResourceNotFoundException{
			Optional<Patient> patient = repoPatient.findById(id);
			List<RendezVous> rendezVous = repository.findByPatient(patient);
//					.orElseThrow(() -> new ResourceNotFoundException("ce medecin n'a pas de rendezVous " + medecin.prenom));
			return ResponseEntity.ok().body(rendezVous);
	}
}
