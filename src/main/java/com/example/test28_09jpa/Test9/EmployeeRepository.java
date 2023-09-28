package com.example.test28_09jpa.Test9;

import org.hibernate.annotations.NamedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT e FROM Employee e WHERE e.lastName = :lastName")
    List<Employee> findByLastName(@Param("lastName") String lastName);

    @Query(value = "SELECT * FROM employees WHERE last_name = :lastName", nativeQuery = true)
    List<Employee> findByLastNameNative(@Param("lastName") String lastName);

}