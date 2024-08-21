package com.taskfusion.taskApp.controller;

import com.taskfusion.taskApp.models.TaskDetailtbl;
import com.taskfusion.taskApp.services.TaskDetailService;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
//@AttributeOverride("Authentication")
@RequestMapping("/taskschedule")
public class TaskSchedulle {

    @Autowired
    private TaskDetailService _taskDetailServices;

    @GetMapping("/getalltask")
    public ResponseEntity<List<TaskDetailtbl>> getAlltaskDetails() {
        try {
            //return _taskDetailServices.getAllTaskDetails();
            return ResponseEntity.ok().body(_taskDetailServices.getAllTaskDetails());
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/createtask")
    public String createTaskDetails(@RequestBody TaskDetailtbl taskDetailEntity) {
        _taskDetailServices.saveTaskDetail(taskDetailEntity);
        return "New record created.";
    }

    @PutMapping("/updatebyid/{id}")
    public ResponseEntity<String> updateTAskDetailsById(@PathVariable Long id, @RequestBody TaskDetailtbl taskDetailtbl) {
        try {
            ResponseEntity<Optional<TaskDetailtbl>> responseEntity = _taskDetailServices.updateTaskDetailsById(id, taskDetailtbl);
            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                return ResponseEntity.ok().body("Record Updated.");
            } else {
                return ResponseEntity.badRequest().body("Bad Request");
            }
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Bad Request");
        }
    }

    @DeleteMapping("/deletetaskbyid/{id}")
    public ResponseEntity<String> deleteTaskDetailsById(@PathVariable Long id) {
        try {
            String test="Abc";

            ResponseEntity<String> responseEntity = _taskDetailServices.deleteTaskById(id);
            System.out.println("deleted");
            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                return ResponseEntity.ok().body("Record Deleted.");
            } else {
                return ResponseEntity.badRequest().body("Bad Request");
            }

        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/gettaskdetalbyid/{id}")
    public ResponseEntity<Optional<TaskDetailtbl>> getTaskDetalsById(@PathVariable Long id) {
        try {
            Optional<TaskDetailtbl> taskDetailtbl = _taskDetailServices.getTaskDetailById(id);
            if (taskDetailtbl.isPresent()) {
                return ResponseEntity.ok().body(taskDetailtbl);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
//            return _taskDetailServices.getTaskDetailById(id).
//                    map(taskDetails -> ResponseEntity.ok(taskDetails))
//                    .orElseGet(()->ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
        } catch (Exception Ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/getrecordbypage")
    public ResponseEntity<Page<TaskDetailtbl>> gettaskdetailsbypage(@RequestParam int pageno, @RequestParam int pagesize) {
        Page<TaskDetailtbl> recordwithpage = _taskDetailServices.getTaskDetailByPagination(pageno, pagesize);
        return ResponseEntity.ok(recordwithpage);
    }

    @GetMapping("/getRecordBySearch")
    public ResponseEntity<List<TaskDetailtbl>> getTaskDetailBySearch(String taskname, String taskdetails) {
        try {
            if (taskname.equals("")) {
                taskname = null;
            }
            List<TaskDetailtbl> taskrecordbysearch = _taskDetailServices.getTaskDetailBySearch(taskname, taskdetails);
            return ResponseEntity.ok(taskrecordbysearch);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        }
    }

    @GetMapping("/filterbydate")
    public ResponseEntity<List<TaskDetailtbl>> filterbydate(LocalDate startdate, LocalDate endate) {

        List<TaskDetailtbl> taskbyfilter=_taskDetailServices.filterbydatetime(startdate, endate);
        return ResponseEntity.ok(taskbyfilter);
    }


}
