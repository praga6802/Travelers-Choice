package com.example.travellers_choice.service;


import com.example.travellers_choice.exception.IDNotFoundException;
import com.example.travellers_choice.exception.PackageNameNotFoundException;
import com.example.travellers_choice.exception.UnAuthorizedException;
import com.example.travellers_choice.model.Admin;
import com.example.travellers_choice.model.Packages;
import com.example.travellers_choice.model.Tour;
import com.example.travellers_choice.repository.AdminRepo;
import com.example.travellers_choice.repository.PackageRepo;
import com.example.travellers_choice.repository.TourRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TourService {


    @Autowired
    TourRepo tourRepo;

    @Autowired
    PackageRepo packageRepo;

    @Autowired
    AdminRepo adminRepo;

    public Tour addTour(int packageName, Tour tour, int adminId, String password) {

        Packages packageEntity = packageRepo.findById(packageName).orElseThrow(() -> new PackageNameNotFoundException("Package Name",String.valueOf(packageName)));

        Admin exisitingAdmin= adminRepo.findById(adminId).orElseThrow(()-> new IDNotFoundException("ID",adminId));

        if(!exisitingAdmin.getPassword().equals(password)){
            throw new UnAuthorizedException("Password",password);
        }

        tour.setPackageName(packageEntity);
        return tourRepo.save(tour);

    }


    public Tour updateTour(int packageId, Tour tour, int adminId, String password) {

        Packages packageEntity = packageRepo.findById(packageId).orElseThrow(() -> new PackageNameNotFoundException("Package Name", String.valueOf(packageId)));
        Tour tourEntity = tourRepo.findById(tour.getTourId()).orElseThrow(() -> new IDNotFoundException("Tour ID", tour.getTourId()));
        Admin exisitingAdmin = adminRepo.findById(adminId).orElseThrow(() -> new IDNotFoundException("ID", adminId));

        if (!exisitingAdmin.getPassword().equals(password)) {
            throw new UnAuthorizedException("Password", password);
        }

        if (tour.getTourName() != null && !tour.getTourName().isBlank())
            tourEntity.setTourName(tour.getTourName());

        if (tour.getTourSlogan() != null && !tour.getTourSlogan().isBlank())
            tourEntity.setTourSlogan(tour.getTourSlogan());

        if (tour.getPlaces() != null && !tour.getPlaces().isBlank())
            tourEntity.setPlaces(tour.getPlaces());

        if (tour.getDays()!=null)
            tourEntity.setDays(tour.getDays());

        if (tour.getNights() != null)
            tourEntity.setNights(tour.getNights());

        if (tour.getPrice() != null)
            tourEntity.setPrice(tour.getPrice());

        return tourRepo.save(tourEntity);

    }

    public boolean deleteTour(int packageId, int tourId, int adminID, String password) {
        Packages packageEntity = packageRepo.findById(packageId).orElseThrow(() -> new PackageNameNotFoundException("Package Name", String.valueOf(packageId)));
        Tour tourEntity = tourRepo.findById(tourId).orElseThrow(() -> new IDNotFoundException("Tour ID", tourId));
        Admin exisitingAdmin = adminRepo.findById(adminID).orElseThrow(() -> new IDNotFoundException("ID", adminID));

        if (!exisitingAdmin.getPassword().equals(password)) {
            throw new UnAuthorizedException("Password", password);
        }

        tourRepo.delete(tourEntity);
        return true;

    }

    public List<Tour> getAllTours() {

        return tourRepo.findAll();
    }
}
