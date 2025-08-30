package com.example.travellers_choice.repository;

import com.example.travellers_choice.model.CustomerRegistry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRegister extends JpaRepository<CustomerRegistry, Integer> {
}
