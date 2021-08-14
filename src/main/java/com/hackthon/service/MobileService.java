package com.hackthon.service;

import com.hackthon.constant.CusomerConstant;
import com.hackthon.entity.CustomerDetailEntity;
import com.hackthon.entity.DocumentEntity;
import com.hackthon.entity.PlansEntity;
import com.hackthon.model.*;
import com.hackthon.repository.CustomerRepository;
import com.hackthon.repository.DocumentRepository;
import com.hackthon.repository.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MobileService implements IMobileService {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    DocumentRepository documentRepository;
    @Autowired
    PlanRepository planRepository;
    @Override
    @Transactional
    public CustomerResponse createCustomer(CustomerDTO customerDTO) {
        CustomerResponse customerResponse=new CustomerResponse();
        CustomerDetailEntity customerDetailEntity=new CustomerDetailEntity();
        customerDetailEntity.setCustomerName(customerDTO.getCustomerName());
        customerDetailEntity.setCustomerReferenceId("cust"+ UUID.randomUUID().toString());
        customerDetailEntity.setEmailId(customerDTO.getEmail());
        customerDetailEntity.setPhoneNumber(customerDTO.getContactDTO().getMobileNumber());
        customerDetailEntity.setPhoneType(customerDTO.getContactDTO().getType());
        customerDetailEntity.setStatus(CusomerConstant.INPROGRESS);
        customerDetailEntity.setDate(new Timestamp(new Date().getTime()));
        customerDetailEntity.setIsConnection(false);
        customerDetailEntity= customerRepository.save(customerDetailEntity);

        createDocuments(customerDetailEntity.getCustomerId(),customerDTO.getDocumentsDTO());
        createPlans(customerDetailEntity.getCustomerId(),customerDTO.getPlans());



        customerResponse.setCustomerReferenceId(customerDetailEntity.getCustomerReferenceId());




        return customerResponse;
    }



  @Override
   public CustomerEnquiryResponse findCustomerByCustomerReferenceId(String customerReferenceId){
       CustomerEnquiryResponse customerEnquiryResponse=new CustomerEnquiryResponse();
       CustomerDetailEntity customerDetailEntity=customerRepository.findByCustomerReferenceId(customerReferenceId);
       customerEnquiryResponse.setCustomerReferenceId(customerDetailEntity.getCustomerReferenceId());
       customerEnquiryResponse.setIsConnection(customerDetailEntity.getIsConnection());
       customerEnquiryResponse.setStatus(customerDetailEntity.getStatus());
       return customerEnquiryResponse;
   }

    @Override
    public List<CustomerDTO> findAllCustomers(){
        List<CustomerDetailEntity> customerDetailEntity= customerRepository.findAll();
      return customerDetailEntity.stream().map(this::getCustomerDetails).collect(Collectors.toList());

    }
    @Override
   public void approveCustomer(String customerReferenceId, CustomerStatus customerStatus){
       CustomerDetailEntity customerDetailEntity=customerRepository.findByCustomerReferenceId(customerReferenceId);
        customerDetailEntity.setStatus(customerStatus.getStatus());
        customerRepository.save(customerDetailEntity);
   }

    private CustomerDTO getCustomerDetails(CustomerDetailEntity customerDetailEntity) {
        CustomerDTO customerDTO=new CustomerDTO();
        customerDTO.setCustomerName(customerDetailEntity.getCustomerName());
        customerDTO.setEmail(customerDetailEntity.getEmailId());
        customerDTO.setContactDTO(getContact(customerDetailEntity));
        customerDTO.setDocumentsDTO(getDocuments(customerDetailEntity.getDocuments()));
        customerDTO.setPlans(getPlans(customerDetailEntity.getPlans()));
        customerDTO.setCustomerReferenceId(customerDetailEntity.getCustomerReferenceId());
        return customerDTO;
    }

    private List<PlanDTO> getPlans(List<PlansEntity> plans) {
        return plans.stream().map(this::getPlan).collect(Collectors.toList());

    }

    private PlanDTO getPlan(PlansEntity plansEntity) {
        PlanDTO planDTO=new PlanDTO();
        planDTO.setPlanName(plansEntity.getPlanName());
        planDTO.setPlanType(plansEntity.getPlanType());
        return planDTO;
    }

    private DocumentsDTO getDocuments(List<DocumentEntity> documents) {
        DocumentsDTO documentsDTO=new DocumentsDTO();
        documents.forEach(document->{
            documentsDTO.setDocumentId(document.getDocumentId().toString());
            documentsDTO.setDocumentName(document.getDocumentName());
            documentsDTO.setDocumentType(document.getDocumentType());
        });
        return documentsDTO;

    }

    private ContactDTO getContact(CustomerDetailEntity customerDetailEntity) {
        ContactDTO contactDTO=new ContactDTO();
        contactDTO.setMobileNumber(customerDetailEntity.getPhoneNumber());
        contactDTO.setType(customerDetailEntity.getPhoneType());
        return contactDTO;
    }

    private void createPlans(Long customerId, List<PlanDTO> plans) {

        List<PlansEntity> list=new ArrayList<>();
        plans.forEach(plan->list.add(preparePlans(plan,customerId)));
        planRepository.saveAll(list);

    }

    private PlansEntity preparePlans(PlanDTO planDTO,Long customerId) {
        PlansEntity plansEntity=new PlansEntity();
        plansEntity.setCustomerId(customerId);
        plansEntity.setDate(new Timestamp(new Date().getTime()));
        plansEntity.setPlanName(planDTO.getPlanName());
        plansEntity.setPlanType(planDTO.getPlanType());
        return plansEntity;
    }

    private void createDocuments(Long customerId, DocumentsDTO documentsDTO) {
        DocumentEntity documentEntity=new DocumentEntity();
        documentEntity.setCustomerId(customerId);
        documentEntity.setDate(new Timestamp(new Date().getTime()));
        documentEntity.setDocumentName(documentsDTO.getDocumentName());
        documentEntity.setDocumentType(documentsDTO.getDocumentType());
        documentRepository.save(documentEntity);
    }
}
