package com.parking.ms_parking.profiles;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Role, Integer> {
    Role findByName(String name);
}
