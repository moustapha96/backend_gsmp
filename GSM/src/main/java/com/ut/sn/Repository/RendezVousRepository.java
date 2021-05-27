package com.ut.sn.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ut.sn.Modeles.Medecin;
import com.ut.sn.Modeles.Patient;
import com.ut.sn.Modeles.RendezVous;


@Repository
public interface RendezVousRepository extends JpaRepository<RendezVous, Integer> {

	List<RendezVous> findByMedecin(Optional<Medecin> medecin);

	List<RendezVous> findByPatient(Optional<Patient> patient);
}
