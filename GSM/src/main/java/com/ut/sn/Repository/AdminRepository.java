package com.ut.sn.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ut.sn.Modeles.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {

}
