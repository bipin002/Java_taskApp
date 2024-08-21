package com.taskfusion.taskApp.repository;

import com.taskfusion.taskApp.models.TaskDetailtbl;

import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface TaskDetailRepository extends JpaRepository<TaskDetailtbl, Long> {


    @Query(value = "select * from task_detailtbl where task_name LIKE Lower(Concat('%',:taskname,'%'))" +
            "or task_details Like Lower(Concat('%',:taskdetails,'%'))", nativeQuery = true)
    List<TaskDetailtbl> searchbyTaskName(@Param("taskname") String TaskName, @Param("taskdetails") String taskdetails);

    @Query(value="select * from task_detailtbl where task_date_time >= Concat(date_format(:startdate,'%Y-%m-%d'),' 00:00:00')" +
            "and task_date_time < Concat(date_format(:endate + Interval 1 day,'%Y-%m-%d'),' 00:00:00')",nativeQuery = true)
    List<TaskDetailtbl> filterbydatetime(@Param("startdate") LocalDate startdate, @Param("endate") LocalDate endate);
}
