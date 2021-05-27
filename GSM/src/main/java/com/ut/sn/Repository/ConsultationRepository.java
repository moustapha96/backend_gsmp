package com.ut.sn.Repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ut.sn.Modeles.Consultation;
import com.ut.sn.Modeles.Dossier_medical;


@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, Integer> {


	Collection<Consultation> findByDossierMedical(Dossier_medical dossier_medical);
}
