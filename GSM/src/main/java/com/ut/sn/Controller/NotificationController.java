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

import com.ut.sn.Modeles.Notification;
import com.ut.sn.Repository.NotificationReposiroty;
import com.ut.sn.exception.ResourceNotFoundException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class NotificationController {

	@Autowired
	NotificationReposiroty repository;

	@GetMapping("/notifications")
	public List<Notification> getAllnotification() {
		System.out.println("Get all notifications...");

		List<Notification> notification = new ArrayList<>();
		repository.findAll().forEach(notification::add);

		return notification;
	}

	@PostMapping("/notifications")
	public Notification ajouterNotification(@RequestBody Notification pd) {
		Notification newPr = repository.save(pd);
		return newPr;
	}

	@GetMapping("/notifications/{id}")
	public ResponseEntity<Notification> getNotificationById(@PathVariable(value = "id") Integer Id)
			throws ResourceNotFoundException {
		Notification notification = repository.findById(Id)
				.orElseThrow(() -> new ResourceNotFoundException("notification non trouvé  :: " + Id));
		return ResponseEntity.ok().body(notification);
	}

	@DeleteMapping("/notifications/{id}")
	public Map<String, Boolean> deleteNotification(@PathVariable(value = "id") Integer NotificationId)
			throws ResourceNotFoundException {
		Notification notification = repository.findById(NotificationId)
				.orElseThrow(() -> new ResourceNotFoundException("Notification non trouvé :: " + NotificationId));
		repository.delete(notification);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	@DeleteMapping("/notifications/delete")
	public ResponseEntity<String> deleteAllnotifications() {
		System.out.println("Delete All notifications...");
		repository.deleteAll();
		return new ResponseEntity<>("tout les notifications ont été supprimer de la base ", HttpStatus.OK);
	}

	@PutMapping("/notifications/{id}")
	public ResponseEntity<Notification> updateNotification(@PathVariable("id") Integer id,
			@RequestBody Notification Notification) {
		System.out.println("Update Article with ID = " + id + "...");
		Optional<Notification> notificationInfo = repository.findById(id);
		if (notificationInfo.isPresent()) {
			Notification notification = notificationInfo.get();
			notification.setMessage(notification.getMessage());
			notification.setTitre(notification.getTitre());

			return new ResponseEntity<>(repository.save(notification), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
