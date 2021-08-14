package com.hackthon.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
@Entity
@Table(name = "customer_details")
@NamedQuery(name="CustomerDetailEntity.findAll", query="SELECT c FROM CustomerDetailEntity c")
@Data
public class CustomerDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="customer_id",unique = true,nullable = false)
    private Long customerId;
    @Column(name="customer_name")
    private String customerName;
    @Column(name="email_id")
    private String emailId;
    @Column(name="customer_reference_id")
    private String customerReferenceId;
    @Column(name="phone_number")
    private String phoneNumber;
    @Column(name="phone_type")
    private String phoneType;
    @Column(name="status")
    private String status;
    @Column(name="date")
    private Timestamp date;
    @Column(name="isConnection")
    private Boolean isConnection;
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private List<PlansEntity> plans;
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private List<DocumentEntity> documents;
}
