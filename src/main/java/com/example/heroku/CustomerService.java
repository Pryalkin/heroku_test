package com.example.heroku;

import java.io.IOException;
import java.util.List;

public interface CustomerService {

    Customer addCustomer(Customer customer) throws IOException;
    List<Customer> getCustomers();

}
