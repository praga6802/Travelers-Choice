package com.example.travellers_choice.repository;

import com.example.travellers_choice.model.Tour;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourRepo extends JpaRepository<Tour, Integer> {
}
