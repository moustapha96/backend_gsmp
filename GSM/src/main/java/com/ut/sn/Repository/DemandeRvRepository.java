package com.ut.sn.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ut.sn.Modeles.DemandeRV;



@Repository
public interface DemandeRvRepository extends JpaRepository<DemandeRV, Integer> {

	Optional<DemandeRV> findById(Integer id );
}
