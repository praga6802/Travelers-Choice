package com.example.travellers_choice.service;

import com.example.travellers_choice.exception.AlreadyExistsException;
import com.example.travellers_choice.model.Customer;
import com.example.travellers_choice.model.CustomerRegistry;
import com.example.travellers_choice.repository.CustomerRegister;
import com.example.travellers_choice.repository.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    CustomerRegister registerRepo;

    public CustomerRegistry bookTour(CustomerRegistry customerRegistry, String packageName) {

        CustomerRegistry customer= new CustomerRegistry();
        customer.setName(customerRegistry.getName());
        customer.setEmail(customerRegistry.getEmail());
        customer.setPhone(customerRegistry.getPhone());
        customer.setPackage_name(packageName);
        customer.setRegion(customerRegistry.getRegion());
        customer.setBdate(customerRegistry.getBdate());
        customer.setTdate(customerRegistry.getTdate());
        customer.setNum_seats(customerRegistry.getNum_seats());
        customer.setNum_adults(customerRegistry.getNum_adults());
        customer.setNum_children(customerRegistry.getNum_children());
        customer.setCity(customerRegistry.getCity());
        customer.setState(customerRegistry.getState());
        customer.setCountry(customerRegistry.getCountry());
        return registerRepo.save(customer);
    }


    //user sign up
    public Customer customerSignUp(Customer customer) {

        if(userRepo.existsByUsername(customer.getUsername())){
            throw new AlreadyExistsException("User Name",customer.getUsername());
        }
        if(userRepo.existsByContact(customer.getContact())){
            throw new AlreadyExistsException("Mobile Number",customer.getContact());
        }
        if(userRepo.existsByEmail(customer.getEmail())){
            throw new AlreadyExistsException("Email",customer.getEmail());
        }
        return userRepo.save(customer);
    }

    public boolean customerLogin(Customer customer) {

        Optional<Customer> exisitingCustomer=userRepo.findByEmailAndPassword(customer.getEmail(),customer.getPassword());
        boolean result=exisitingCustomer.isPresent()?true:false;
        return result;
    }


}
