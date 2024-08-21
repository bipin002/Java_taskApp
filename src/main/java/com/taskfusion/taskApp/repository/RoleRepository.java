package com.taskfusion.taskApp.repository;

import com.taskfusion.taskApp.models.roletbl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<roletbl,Integer> {
}
