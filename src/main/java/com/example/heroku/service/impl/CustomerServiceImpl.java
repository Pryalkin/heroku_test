package com.example.heroku.service.impl;

import com.example.heroku.exceptions.CustomerNotFoundException;
import com.example.heroku.model.Customer;
import com.example.heroku.model.ExpandCustomer;
import com.example.heroku.repository.CustomerRepo;
import com.example.heroku.service.CustomerService;
import com.example.heroku.service.DropboxService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static com.example.heroku.contstans.Constants.CUSTOMER_NOT_FOUND_EXCEPTION;
import static com.example.heroku.contstans.FileConstant.*;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepo customerRepo;
    private final DropboxService dropboxService;

    @Override
    public Customer addCustomer(Customer customer, MultipartFile file) throws IOException {
        saveProfileImage(customer, file);
        return customerRepo.save(customer);
    }

    private void saveProfileImage(Customer customer, MultipartFile profileImage) throws IOException {
        if (profileImage != null) {
            String generate = generate();
            customer.setImageUrl(setProfileImageUrl(generate, customer));
            InputStream inputStream = profileImage.getInputStream();
            dropboxService.uploadFile(customer.getImageUrl(), inputStream);
            inputStream.close();
            customerRepo.save(customer);
        }
    }

    @Override
    public List<Customer> getCustomers() {
        return customerRepo.findAll();
    }

    @Override
    public List<Customer> enrollCustomer(Long id, Boolean isStatus) throws CustomerNotFoundException {
        Customer customer = customerRepo.findById(id).orElseThrow(() -> new CustomerNotFoundException(CUSTOMER_NOT_FOUND_EXCEPTION));
        customer.setStatus(isStatus);
        customerRepo.save(customer);
        return customerRepo.findAll();
    }

    @Override
    public List<Customer> deleteCustomer(Long id) throws CustomerNotFoundException {
        Customer entrant = customerRepo.findById(id).orElseThrow(() -> new CustomerNotFoundException(CUSTOMER_NOT_FOUND_EXCEPTION));
        customerRepo.deleteById(entrant.getId());
        return customerRepo.findAll();
    }

    private String setProfileImageUrl(String generate, Customer customer) {
        return "/image/" + generate + DOT + JPG_EXTENSION;
    }

    private String generate() {
        return RandomStringUtils.randomAlphanumeric(10);
    }
}
