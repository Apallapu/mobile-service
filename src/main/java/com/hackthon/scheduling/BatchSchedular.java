package com.hackthon.scheduling;

import com.hackthon.constant.CusomerConstant;
import com.hackthon.entity.CustomerDetailEntity;
import com.hackthon.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BatchSchedular {

    @Autowired
    CustomerRepository customerRepository;


    /**
     *
     */
    @Scheduled(cron = "*/5 * * * * ?")
    public void updateCustomerStatus() {
        CustomerDetailEntity detailEntity=customerRepository.findByStatus(CusomerConstant.APPROVE);
        detailEntity.setStatus(CusomerConstant.ACTIVE);
        detailEntity.setIsConnection(true);
        customerRepository.save(detailEntity);
    }
}
