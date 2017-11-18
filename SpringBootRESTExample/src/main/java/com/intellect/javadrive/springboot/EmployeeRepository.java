package com.intellect.javadrive.springboot;

import org.springframework.data.jpa.repository.JpaRepository;

import com.intellect.javadrive.springboot.Employee;

/**
 * Created by Parimal Kashyap.
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
