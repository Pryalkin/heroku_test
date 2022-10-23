package com.example.heroku;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

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


}
