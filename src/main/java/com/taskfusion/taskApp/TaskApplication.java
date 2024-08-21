package com.taskfusion.taskApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootApplication
public class TaskApplication {

    public static void main(String[] args) {

        SpringApplication.run(TaskApplication.class, args);
//		String url="jdbc:mysql://127.0.0.1:3306/taskapplication_java";
//		String username="root";
//		String password="admin@123";
//
//		try(Connection coonnection= DriverManager.getConnection(url,username,password)){
//System.out.println("Connection true");
//		}
//		catch (SQLException sqex){
//System.err.println("Connection Failed:"+sqex);
//		}
    }

}
