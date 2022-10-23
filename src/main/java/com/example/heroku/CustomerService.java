package com.example.heroku;

import java.io.IOException;

public interface CustomerService {

    Customer addCustomer(Customer customer) throws IOException;

}
