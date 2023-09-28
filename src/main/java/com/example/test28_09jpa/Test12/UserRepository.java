package com.example.test28_09jpa.Test12;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    //Method query:
    List<UserDTO> findAllBy();
    //JPQL Query
    @Query("SELECT new com.example.UserDto(u.id, u.name, u.email) FROM User u")
    List<UserDTO> findAllWithDto();
    //Native Query
    @Query(value = "SELECT id, name, email FROM user", nativeQuery = true)
    List<Object[]> findAllWithObjectArray();
    //Projection:
    List<UserProjection> findAllByProt();
}
