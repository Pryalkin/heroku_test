package com.example.heroku;

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
    public void addUser(@RequestBody Customer customer) throws IOException {
        customerService.addCustomer(customer);
    }

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getUsers(){
        return new ResponseEntity<>(customerService.getCustomers(), HttpStatus.OK);
    }


}
