package com.ut.sn.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ut.sn.DTO.UserDTO;
import com.ut.sn.Modeles.UserModel;
import com.ut.sn.Repository.UserRepository;
import com.ut.sn.Service.JwtResponse;
import com.ut.sn.Service.UserService;
import com.ut.sn.exception.Message;
import com.ut.sn.springSecurity.AuthenticationRequest;
import com.ut.sn.springSecurity.JwtUtil;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class UserModelController {

	private final UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;
	@Autowired
	private JwtUtil jwtUtil;

	public UserModelController(UserService userService) {
		super();
		this.userService = userService;
	}

	@RequestMapping(value = "/User/login", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {
		
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),
					authenticationRequest.getPassword()));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
		System.out.println("email : " + authenticationRequest.getEmail());
		System.out.println("password : " + authenticationRequest.getPassword());
		UserDetails userdetails = userService.loadUserByUsername(authenticationRequest.getEmail());

		System.out.println("le user connecté " + userdetails);
		String token = jwtUtil.generateToken(userdetails);
		UserModel user = userRepository.findOneByEmail(authenticationRequest.getEmail());

		return ResponseEntity
				.ok(new JwtResponse(token, user.getIduser(), user.getPassword(), user.getEmail(), user.getRole()));
	}

	@RequestMapping(value = "/User", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody UserDTO user) throws Exception {
		Optional<UserModel> userInfo = userRepository.findByEmail(user.getEmail());
		if (userInfo.isPresent()) {
			return ResponseEntity.badRequest().body(new Message("Error: Utilisateur déja existant! "));
		}
		if (userRepository.existsByEmail(user.getEmail())) {
			return ResponseEntity.badRequest().body(new Message("Error: Email déja pris !"));
		}

		return ResponseEntity.ok(userService.save(user));
	}

	@GetMapping("/User")
//	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<UserModel>> getAllUser() {
		List<UserModel> users = userService.getAllUser();
		return new ResponseEntity<List<UserModel>>(users, HttpStatus.OK);
	}

	@DeleteMapping("/User/delete/{id}")
	public ResponseEntity<UserModel> deleteuser(@PathVariable Integer id) {
		userService.deleteUser(id);
		return new ResponseEntity<UserModel>(HttpStatus.OK);
	}

	@PutMapping("/User/updateuser")
	public ResponseEntity<UserModel> updaterv(@RequestBody UserModel userModel) {
		UserModel updateuser = userService.updateUser(userModel);
		return new ResponseEntity<UserModel>(updateuser, HttpStatus.OK);
	}

	@GetMapping("User/email/{email}")
	public ResponseEntity<UserModel> UserByEmail(@PathVariable String email) {
		UserModel updateuser = userService.findOneUser(email);
		return new ResponseEntity<UserModel>(updateuser, HttpStatus.OK);
	}

	@PutMapping("/User/{id}")
	public ResponseEntity<?> updateUser(@PathVariable("id") Integer id, @RequestBody UserModel userR) {
		System.out.println("Update user with ID = " + id + "...");
		System.out.println("Update user with ID = " + userR);
		Optional<UserModel> userInfo = userRepository.findByIduser(id);

		if (!userInfo.isPresent()) {
			return ResponseEntity.badRequest().body(new Message("Error: Utilisateur non existant! "));
		}
		if (userRepository.existsByEmail(userR.getEmail())) {
			return ResponseEntity.badRequest().body(new Message("Error: Email déja pris !"));
		}

		UserModel user = userInfo.get();
		user.setEmail(userR.getEmail());
		user.setRole(userR.getRole());
		user.setPassword(userR.getPassword());

		return new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);

	}

	

//	@RequestMapping(value = "/User", method = RequestMethod.POST)
//	public ResponseEntity<?> saveUserPatient(@RequestBody UserDTO user,Patient patient) throws Exception {
//		Optional<UserModel> userInfo = userRepository.findByEmail(user.getEmail());
//		if (userInfo.isPresent()) {
//			return ResponseEntity.badRequest().body(new Message("Error: Utilisateur déja existant! "));
//		}
//		if (userRepository.existsByEmail(user.getEmail())) {
//			return ResponseEntity.badRequest().body(new Message("Error: Email déja pris !"));
//		}
//
//		return ResponseEntity.ok(userService.save(user));
//	}

}
