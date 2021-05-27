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

import com.ut.sn.Modeles.Assistant;
import com.ut.sn.Modeles.UserModel;
import com.ut.sn.Repository.AssistantRepository;
import com.ut.sn.Repository.UserRepository;
import com.ut.sn.exception.ResourceNotFoundException;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class AssistantController {

	@Autowired AssistantRepository repository;
	
	@Autowired UserRepository userRepo;
	
	@GetMapping("/assistants")
	public List<Assistant> getAllassistant() {
		System.out.println("Get all assistants...");

		List<Assistant> assistants = new ArrayList<>();
		repository.findAll().forEach(assistants::add);
		
		return assistants;
	}

	@PostMapping("/assistants")
	public Assistant ajouterAssistant(@RequestBody Assistant pd){
		Assistant newPr = repository.save(pd) ; 
		return newPr;
	}
	
	@GetMapping("/assistants/{id}")
	public ResponseEntity<Assistant> getAssistantById(@PathVariable(value = "id") Integer Id)
			throws ResourceNotFoundException {
		Assistant assistant = repository.findById(Id)
				.orElseThrow(() -> new ResourceNotFoundException("assistant non trouvé  :: " + Id));
		return ResponseEntity.ok().body(assistant);
	}

	
	@DeleteMapping("/assistants/{id}")
	public Map<String, Boolean> deleteAssistant(@PathVariable(value = "id") Integer AssistantId)
			throws ResourceNotFoundException {
		Assistant assistant = repository.findById(AssistantId)
				.orElseThrow(() -> new ResourceNotFoundException("Assistant non trouvé :: " + AssistantId));
		repository.delete(assistant);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	
	@DeleteMapping("/assistants/delete")
	public ResponseEntity<String> deleteAllassistants() {
		System.out.println("Delete All assistants...");
		repository.deleteAll();
		return new ResponseEntity<>("tout les assistants ont été supprimer de la base ", HttpStatus.OK);
	}

	@PutMapping("/assistants/{id}")
	public ResponseEntity<Assistant> updateAssistant(@PathVariable("id") Integer id, @RequestBody Assistant Assistant) {
		System.out.println("Update Article with ID = " + id + "...");
		Optional<Assistant> assistantInfo = repository.findById(id);
		if (assistantInfo.isPresent()) {
			Assistant assistant = assistantInfo.get();
			assistant.setAdresse( assistant.getAdresse()   );
			assistant.setAge(assistant.getAge()   );
			assistant.setGenre(assistant.getGenre()   );
			assistant.setNom( assistant.getNom()   );
			assistant.setPrenom( assistant.getPrenom()   );
			assistant.setTel( assistant.getTel()  );
			
			return new ResponseEntity<>(repository.save(assistant), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/assistants/user/{email}")
	public ResponseEntity<Assistant> getAssistantByEmail(@PathVariable(value = "email") String email)
			throws ResourceNotFoundException {
		System.out.println("user " + email);
		Optional<UserModel> user = userRepo.findByEmail(email);

		Assistant assistant = repository.findByUser(user)
				.orElseThrow(() -> new ResourceNotFoundException("assistant non trouvé  :: " + user.toString()));
		return ResponseEntity.ok().body(assistant);
	}
}
