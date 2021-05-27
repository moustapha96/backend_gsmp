package com.ut.sn.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ut.sn.Modeles.Dossier_medical;
import com.ut.sn.Modeles.Patient;

@Repository
public interface DossierMedicalRepository extends JpaRepository<Dossier_medical, Integer> {

 Optional<Dossier_medical>	findByPatient(Patient patient);

List<Dossier_medical> findByPatient(Optional<Patient> patient);
	
	
}
