package com.hackthon.repository;

import com.hackthon.entity.CustomerDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerDetailEntity,Long> {

    CustomerDetailEntity findByCustomerReferenceId(String customerReferenceId);
    CustomerDetailEntity findByStatus(String status);
}
