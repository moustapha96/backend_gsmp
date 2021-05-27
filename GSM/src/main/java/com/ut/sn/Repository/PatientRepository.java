package com.ut.sn.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ut.sn.Modeles.Assistant;
import com.ut.sn.Modeles.Patient;
import com.ut.sn.Modeles.UserModel;


@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
	
	
	Optional<Patient> findByUser(Optional<UserModel> user);
	
	Optional<Patient> findByIdPatient(Integer idPatient);

}
