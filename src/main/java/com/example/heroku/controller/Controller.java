package com.example.heroku.controller;

import com.dropbox.core.v2.files.FileMetadata;
import com.example.heroku.exceptions.CustomerNotFoundException;
import com.example.heroku.model.Customer;
import com.example.heroku.service.CustomerService;
import com.example.heroku.model.ExpandCustomer;
import com.example.heroku.service.DropboxService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;

@RestController
@RestControllerAdvice
@AllArgsConstructor
@RequestMapping("/")
public class Controller {

        private final CustomerService customerService;
        private final DropboxService dropboxService;

        @PostMapping("/customer/add")
        public void addUser(@RequestPart Customer customer,
                            @RequestPart(required = false) MultipartFile file) throws IOException {
            customerService.addCustomer(customer, file);
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

        @GetMapping(path = "/image", produces = IMAGE_JPEG_VALUE)
        public byte[] getProfileImage(@RequestParam String image) throws IOException {
            FileMetadata fileMetadata = dropboxService.getFileDetails(image);
            return fileMetadata.getPathLower().getBytes();
        }

    }
