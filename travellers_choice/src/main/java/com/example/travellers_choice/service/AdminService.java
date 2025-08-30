package com.example.travellers_choice.service;


import com.example.travellers_choice.exception.AlreadyExistsException;
import com.example.travellers_choice.exception.IDNotFoundException;
import com.example.travellers_choice.exception.UnAuthorizedException;
import com.example.travellers_choice.model.Admin;
import com.example.travellers_choice.model.Customer;
import com.example.travellers_choice.model.CustomerRegistry;
import com.example.travellers_choice.model.Packages;
import com.example.travellers_choice.repository.AdminRepo;
import com.example.travellers_choice.repository.CustomerRegister;
import com.example.travellers_choice.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    CustomerRegister customerRegisterRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    AdminRepo adminRepo;

    //admin sign up
    public Admin signUp(Admin admin) {
        if(adminRepo.existsByUsername(admin.getUsername())){
            throw new AlreadyExistsException("User Name",admin.getUsername());
        }
        if(adminRepo.existsByEmail(admin.getEmail())){
            throw new AlreadyExistsException("Email", admin.getEmail());
        }
        if(adminRepo.existsByContact(admin.getContact())){
            throw new AlreadyExistsException("Contact", admin.getEmail());
        }
        return adminRepo.save(admin);
    }

    //get all admin
    public List<Admin> getAllAdmins() {
        return adminRepo.findAll();
    }

    public Admin getAdmin(int adminId) {
        return adminRepo.findById(adminId).orElse(null);
    }

//    public boolean deleteAdmin(int id, String password) {
//        Admin deleteadminId=adminRepo.findById(id).orElseThrow(()-> new IDNotFoundException("Admin ID", id));
//
//
//        if(exisitingAdmin.getPassword().equals(password)){
//            adminRepo.delete(deleteadminId);
//            return true;
//        }
//        return false;
//    }

    public Admin updateAdmin(Admin admin) {
        Admin exisitingAdmin=adminRepo.findById(admin.getAdminId()).orElseThrow(()-> new IDNotFoundException("Admin ID", admin.getAdminId()));

        if(exisitingAdmin.getPassword().equals(admin.getPassword())){
            if(admin.getUsername() !=null && !admin.getUsername().isBlank())
                exisitingAdmin.setUsername(admin.getUsername());
            if(admin.getEmail() !=null && !admin.getEmail().isBlank())
                exisitingAdmin.setEmail(admin.getEmail());
            if(admin.getContact() !=null && !admin.getContact().isBlank())
                exisitingAdmin.setContact(admin.getContact());
            if(admin.getPassword() !=null && !admin.getPassword().isBlank())
                exisitingAdmin.setPassword(admin.getPassword());
        }
        return adminRepo.save(exisitingAdmin);
    }

    public Admin adminLogin(String email, String password) {

        Admin admin=adminRepo.findByEmailAndPassword(email,password).orElseThrow(()-> new UnAuthorizedException("Credentials", email));
        return admin;

    }


    // CUSTOMERS
    public List<CustomerRegistry> getAllRegUsers() {
        return customerRegisterRepo.findAll();
    }

    public List<Customer> getAllCustomers() {
        return userRepo.findAll();
    }
}
