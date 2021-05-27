package com.ut.sn.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ut.sn.Modeles.ServiceMedical;


@Repository
public interface ServiceMedicalRepository extends JpaRepository<ServiceMedical, Integer> {

}
