package com.example.heroku;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepo customerRepo;

    @Override
    public Customer addCustomer(Customer customer) throws IOException {
        return customerRepo.save(customer);
    }

    @Override
    public List<Customer> getCustomers() {
        return customerRepo.findAll();
    }
}
