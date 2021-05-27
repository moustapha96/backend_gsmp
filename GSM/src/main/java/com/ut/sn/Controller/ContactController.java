package com.ut.sn.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ut.sn.Modeles.Contact;
import com.ut.sn.Repository.ContactRepository;
import com.ut.sn.Repository.UserRepository;
import com.ut.sn.exception.ResourceNotFoundException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ContactController {

	@Autowired
	ContactRepository repository;

	@Autowired
	UserRepository userRepo;

	@GetMapping("/contact")
	public List<Contact> getAllContact() {
		System.out.println("Get all contacts...");

		List<Contact> contacts = new ArrayList<>();
		repository.findAll().forEach(contacts::add);

		return contacts;
	}

	@PostMapping("/contact")
	public Contact ajouterContact(@RequestBody Contact pd) {
		Contact newPr = repository.save(pd);
		return newPr;
	}

	@GetMapping("/contact/{id}")
	public ResponseEntity<Contact> getContactById(@PathVariable(value = "id") Integer Id)
			throws ResourceNotFoundException {
		Contact Contact = repository.findById(Id)
				.orElseThrow(() -> new ResourceNotFoundException("Contact non trouvé  :: " + Id));
		return ResponseEntity.ok().body(Contact);
	}



	@DeleteMapping("/contact/{id}")
	public Map<String, Boolean> deleteContact(@PathVariable(value = "id") Integer ContactId)
			throws ResourceNotFoundException {
		Contact Contact = repository.findById(ContactId)
				.orElseThrow(() -> new ResourceNotFoundException("Contact non trouvé :: " + ContactId));
		repository.delete(Contact);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	@DeleteMapping("/contact/delete")
	public ResponseEntity<String> deleteAllcontacts() {
		System.out.println("Delete All contacts...");
		repository.deleteAll();
		return new ResponseEntity<>("tout les contacts ont été supprimer de la base ", HttpStatus.OK);
	}

	

}
