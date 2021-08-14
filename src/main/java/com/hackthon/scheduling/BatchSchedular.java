package com.hackthon.scheduling;

import com.hackthon.constant.CusomerConstant;
import com.hackthon.entity.CustomerDetailEntity;
import com.hackthon.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BatchSchedular {

    @Autowired
    CustomerRepository customerRepository;


    /**
     *
     */
    @Scheduled(cron = "*/2 * * * * ?")
    public void updateCustomerStatus() {
        List<CustomerDetailEntity> detailEntity=customerRepository.findByStatus(CusomerConstant.APPROVE);

        detailEntity.forEach(customerDetail->{
            customerDetail.setStatus(CusomerConstant.ACTIVE);
            customerDetail.setIsConnection(true);
            customerRepository.save(customerDetail);
        });

    }
}
