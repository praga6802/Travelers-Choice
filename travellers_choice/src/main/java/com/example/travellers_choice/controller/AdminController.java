package com.example.travellers_choice.controller;

import com.example.travellers_choice.model.Admin;
import com.example.travellers_choice.model.Customer;
import com.example.travellers_choice.model.CustomerRegistry;
import com.example.travellers_choice.service.AdminService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://127.0.0.1:5500", allowCredentials = "true")
public class AdminController {


    @Autowired
    AdminService adminService;


    // ADMIN
    //admin signup
    @PostMapping("/adminsignup")
    public ResponseEntity<?> signUp(@ModelAttribute Admin admin){
        Admin saveAdmin=adminService.signUp(admin);
        return ResponseEntity.ok(saveAdmin);
    }

    //admin login
    @PostMapping("/adminlogin")
    public String adminLogin(@RequestParam("email") String email, @RequestParam("password") String password, HttpSession httpSession){

        Admin adminLogin=adminService.adminLogin(email,password);
        if(adminLogin!=null){
            httpSession.setAttribute("username",adminLogin.getUsername());
            return "redirect:http://127.0.0.1:5500/travel-admin/html/left.html";
        }
        return "redirect:http://127.0.0.1:5500/travel-admin/html/loginform?error=true";
    }


    @GetMapping("/username")
    public ResponseEntity<?> getUsername(HttpSession session){
        String username=(String)session.getAttribute("username");
        if(username!=null){
            return ResponseEntity.ok(username);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User Not Found");
    }

    //get all admins
    @GetMapping("/alladmins")
    public ResponseEntity<List<Admin>> getAllAdmins(){
        List<Admin> allAdmins= adminService.getAllAdmins();
        return ResponseEntity.ok(allAdmins);
    }

    //get admin by id
    @GetMapping("/findadmin/{id}")
    public Admin getAdmin(@PathVariable("id") int id){
        return adminService.getAdmin(id);
    }


//    //delete admin
//    @DeleteMapping("/deleteadmin")
//    public ResponseEntity<Map<String, String>> deleteAdmin(@RequestParam("adminId") int adminId, @RequestParam("password") String password){
//        boolean result=adminService.deleteAdmin(adminId, password);
//        if(result==true)
//            return ResponseEntity.ok(Map.of("message","Admin Deleted Successfully"));
//        else
//            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("error","Admin Deletion Failed"));
//    }

    // update admin
    @PutMapping("/updateadmin")
    public ResponseEntity<Map<String, String>> updateAdmin(@ModelAttribute Admin admin){
        adminService.updateAdmin(admin);
        return ResponseEntity.ok(Map.of("message","Admin Updated Successfully"));
    }


    // CUSTOMERS
    // get all register customers
    @GetMapping("/allregusers")
    public ResponseEntity<List<CustomerRegistry>> getAllUsers(){
        List<CustomerRegistry> allUsers= adminService.getAllRegUsers();
        return ResponseEntity.ok(allUsers);
    }


    //get all customers
    @GetMapping("/allusers")
    public ResponseEntity<List<Customer>> getAllCustomers(){
        List<Customer> allCustomers= adminService.getAllCustomers();
        return ResponseEntity.ok(allCustomers);
    }



}
