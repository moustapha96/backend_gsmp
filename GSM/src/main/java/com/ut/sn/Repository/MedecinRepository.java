package com.ut.sn.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ut.sn.Modeles.Medecin;
import com.ut.sn.Modeles.UserModel;

@Repository
public interface MedecinRepository extends JpaRepository<Medecin, Integer> {
	
	Optional<Medecin> findByUser(Integer user);
	
	Optional<Medecin> findByUser(Optional<UserModel> user);
	
//	@Query("SELECT id_dossier_medical, date_creation, medecin_id_medecin, patient_id_patient  FROM dossier_medical p WHERE p.medecin_id_medecin > :idMedecin")
//	   List<Dossier_medical>  findAllDMByidMedecin(@Param("idMedecin") Integer idMedecin);
}
