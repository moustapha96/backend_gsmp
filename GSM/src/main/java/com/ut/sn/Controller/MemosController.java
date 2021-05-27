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

import com.ut.sn.Modeles.Memos;
import com.ut.sn.Repository.MemosRepository;
import com.ut.sn.exception.ResourceNotFoundException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class MemosController {
	@Autowired
	MemosRepository repository;

	@GetMapping("/Memos")
	public List<Memos> getAllmemos() {
		System.out.println("Get all Memos...");

		List<Memos> memos = new ArrayList<>();
		repository.findAll().forEach(memos::add);

		return memos;
	}

	@PostMapping("/Memos")
	public Memos ajouterMemos(@RequestBody Memos pd) {
		Memos newPr = repository.save(pd);
		return newPr;
	}

	@GetMapping("/Memos/{id}")
	public ResponseEntity<Memos> getMemosById(@PathVariable(value = "id") Integer Id) throws ResourceNotFoundException {
		Memos memos = repository.findById(Id)
				.orElseThrow(() -> new ResourceNotFoundException("memos non trouvé  :: " + Id));
		return ResponseEntity.ok().body(memos);
	}

	@DeleteMapping("/Memos/{id}")
	public Map<String, Boolean> deleteMemos(@PathVariable(value = "id") Integer MemosId)
			throws ResourceNotFoundException {
		Memos memos = repository.findById(MemosId)
				.orElseThrow(() -> new ResourceNotFoundException("Memos non trouvé :: " + MemosId));
		repository.delete(memos);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	@DeleteMapping("/Memos/delete")
	public ResponseEntity<String> deleteAllMemos() {
		System.out.println("Delete All Memos...");
		repository.deleteAll();
		return new ResponseEntity<>("tout les Memos ont été supprimer de la base ", HttpStatus.OK);
	}

	@PutMapping("/Memos/{id}")
	public ResponseEntity<Memos> updateMemos(@PathVariable("id") Integer id, @RequestBody Memos Memos) {
		System.out.println("Update Article with ID = " + id + "...");
		Optional<Memos> memosInfo = repository.findById(id);
		if (memosInfo.isPresent()) {
			Memos memos = memosInfo.get();
			memos.setMessage(memos.getMessage());
			memos.setTitre(memos.getTitre());

			return new ResponseEntity<>(repository.save(memos), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
