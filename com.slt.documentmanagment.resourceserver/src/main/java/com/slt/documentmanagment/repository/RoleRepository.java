package com.slt.documentmanagment.repository;

import com.slt.documentmanagment.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {

   Role findByName(String name);

}
