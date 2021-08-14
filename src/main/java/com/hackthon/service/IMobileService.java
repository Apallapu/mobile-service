package com.hackthon.service;

import com.hackthon.model.CustomerDTO;
import com.hackthon.model.CustomerEnquiryResponse;
import com.hackthon.model.CustomerResponse;
import com.hackthon.model.CustomerStatus;

import java.util.List;

public interface IMobileService {


    CustomerResponse createCustomer(CustomerDTO customerDTO);

    CustomerEnquiryResponse findCustomerByCustomerReferenceId(String customerReferenceId);

    List<CustomerDTO> findAllCustomers();

    void approveCustomer(String customerReferenceId, CustomerStatus customerStatus);
}
