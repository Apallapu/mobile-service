package com.hackthon.entity;
import lombok.Data;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import javax.persistence.*;
@Entity
@Table(name = "plan_details")
@NamedQuery(name="PlansEntity.findAll", query="SELECT p FROM PlansEntity p")
@Data
public class PlansEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="plan_id",unique = true,nullable = false)
    private Long planId;
    @Column(name="plan_name")
    private String planName;
    @Column(name="customer_id")
    private Long customerId;
    @Column(name="type")
    private String planType;
    @Column(name="date")
    private Timestamp date;
}
