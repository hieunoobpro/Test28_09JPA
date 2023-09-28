package com.example.test28_09jpa.Test8;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    //1, Tìm tất cả các Employee theo emailAddress và lastName
    List<Employee> findByEmailAddressAndLastName(String emailAddress, String lastName);
    //2, Tìm tất cả các Employee khác nhau theo firstName hoặc lastName
    List<Employee> findByFirstNameNotOrLastNameNot(String firstName, String lastName);
    //3, List<Employee> findByFirstNameNotOrLastNameNot(String firstName, String lastName);
    List<Employee> findByLastNameOrderByFirstNameAsc(String lastName);
    //4,Tìm tất cả các Employee theo firstName không phân biệt hoa thường:
    List<Employee> findByFirstNameIgnoreCase(String firstName);
}
