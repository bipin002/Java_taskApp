package com.taskfusion.taskApp.repository;

import com.taskfusion.taskApp.models.UserTbl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailRepository extends JpaRepository<UserTbl, Long> {
    @Query(value = "Select * from user_tbl  where username=:username and password=:password", nativeQuery = true)
    UserTbl findbyusernameandpassword(@Param("username") String username, @Param("password") String password);

    @Query(value = "Select * from user_tbl where username=:username", nativeQuery = true)
    UserTbl fingbyusername(@Param("username") String username);
}
