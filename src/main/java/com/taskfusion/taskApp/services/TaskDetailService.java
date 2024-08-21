package com.taskfusion.taskApp.services;

import com.taskfusion.taskApp.models.TaskDetailtbl;

import com.taskfusion.taskApp.repository.TaskDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class TaskDetailService {

    @Autowired
    private TaskDetailRepository _taskdetailrepo;

    public List<TaskDetailtbl> getAllTaskDetails() {
        return _taskdetailrepo.findAll(Sort.by(Sort.Direction.DESC,"id"));
    }

    public void saveTaskDetail(TaskDetailtbl taskDetails) {
        //for create date
        Date currentDateTime = new Date();
        taskDetails.setTask_DateTime(currentDateTime);

        //for status
        //Boolean status=true;
        taskDetails.setStatus(true);

        _taskdetailrepo.save(taskDetails);
    }

    public ResponseEntity<Optional<TaskDetailtbl>> updateTaskDetailsById(Long id, TaskDetailtbl taskDetailtbl) {
        try {
            if (_taskdetailrepo.existsById(id)) {
                Date updatedate = new Date();
                _taskdetailrepo.findById(id).
                        map(taskDetailsRecord -> {
                            if (taskDetailtbl.getTask_Name() != null) {
                                taskDetailsRecord.setTask_Name(taskDetailtbl.getTask_Name());
                            }
                            if (taskDetailtbl.getTask_Details() != null) {
                                taskDetailsRecord.setTask_Details(taskDetailtbl.getTask_Details());
                            }
                            taskDetailsRecord.setTask_updatedDate(updatedate);
                            taskDetailsRecord.setStatus(true);
                            return _taskdetailrepo.save(taskDetailsRecord);
                        });
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<String> deleteTaskById(Long id) {
        try {
            if (_taskdetailrepo.existsById(id)) {
                _taskdetailrepo.deleteById(id);
                return ResponseEntity.ok().body("Record Deleted.");
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    public Optional<TaskDetailtbl> getTaskDetailById(Long id) {
        return _taskdetailrepo.findById(id);
    }

    public Page<TaskDetailtbl> getTaskDetailByPagination(int pageno, int pagesize){
        //Pageable
        return _taskdetailrepo.findAll(PageRequest.of(pageno,pagesize).withSort(Sort.Direction.DESC,"id"));
    }

    public List<TaskDetailtbl> getTaskDetailBySearch(String taskname,String taskdetails){
        return _taskdetailrepo.searchbyTaskName(taskname,taskdetails);
    }

    public List<TaskDetailtbl> filterbydatetime(LocalDate startdate,LocalDate endate){
        return _taskdetailrepo.filterbydatetime(startdate,endate);
    }
}
