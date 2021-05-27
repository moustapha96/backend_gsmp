package com.ut.sn.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ut.sn.Modeles.Assistant;
import com.ut.sn.Modeles.UserModel;


@Repository
public interface AssistantRepository extends JpaRepository<Assistant, Integer> {

	Optional<Assistant> findByUser(Integer user);
	
	Optional<Assistant> findByUser(Optional<UserModel> user);
}
