package com.example.heroku.controller;

import com.example.heroku.exceptions.CustomerNotFoundException;
import com.example.heroku.model.Customer;
import com.example.heroku.service.CustomerService;
import com.example.heroku.model.ExpandCustomer;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class Controller {

    private final CustomerService customerService;

    @GetMapping("/home")
    public String home(){
        return "Hello heroku";
    }

    @PostMapping("/customer/add")
    public void addUser(@RequestBody ExpandCustomer expandCustomer) throws IOException {
        customerService.addCustomer(expandCustomer);
    }

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getUsers(){
        return new ResponseEntity<>(customerService.getCustomers(), HttpStatus.OK);
    }

    @GetMapping("/customer/enroll/{id}")
    public ResponseEntity<List<Customer>> enrollEntrant(@PathVariable Long id) throws CustomerNotFoundException {
        return new ResponseEntity<>(customerService.enrollCustomer(id, true), HttpStatus.OK);
    }

    @GetMapping("/customer/{id}/delete")
    public ResponseEntity<List<Customer>> deleteEntrant(@PathVariable Long id) throws CustomerNotFoundException {
        return new ResponseEntity<>(customerService.deleteCustomer(id), HttpStatus.OK);
    }


}
