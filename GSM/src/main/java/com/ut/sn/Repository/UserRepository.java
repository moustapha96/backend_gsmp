package com.ut.sn.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ut.sn.Modeles.Patient;
import com.ut.sn.Modeles.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {

	Optional<UserModel> findByEmail(String email);
	
	UserModel findOneByEmail(String email);

	Optional<UserModel> findByIduser(Integer iduser);
	
	Boolean existsByEmail(String email);
	
	
}
