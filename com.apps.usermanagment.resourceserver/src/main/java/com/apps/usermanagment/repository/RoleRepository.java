package com.apps.usermanagment.repository;

import com.apps.usermanagment.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {

   Role findByName(String name);

}
