package com.hackthon.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "document_details")
@NamedQuery(name="DocumentEntity.findAll", query="SELECT d FROM DocumentEntity d")
@Data
public class DocumentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="doc_id",unique = true,nullable = false)
    private Long documentId;
    @Column(name="document_name")
    private String documentName;
    @Column(name="document_type")
    private String documentType;
    @Column(name="customer_id")
    private Long customerId;
    @Column(name="date")
    private Timestamp date;
}
