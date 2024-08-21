package com.taskfusion.taskApp.repository;

import com.taskfusion.taskApp.models.roletbl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<roletbl, Integer> {

    @Query(value = "Select * from role_tbl where role_id=:role_id",nativeQuery = true)
    roletbl FindRoleNameById(@Param("role_id") Integer role_id);
}
