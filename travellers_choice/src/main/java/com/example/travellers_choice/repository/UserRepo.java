package com.example.travellers_choice.repository;

import com.example.travellers_choice.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepo extends JpaRepository<Customer,Integer> {

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByContact(String contact);
    Optional<Customer> findByEmailAndPassword(String email, String password);
}
