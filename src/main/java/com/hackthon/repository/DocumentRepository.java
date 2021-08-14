package com.hackthon.repository;

import com.hackthon.entity.CustomerDetailEntity;
import com.hackthon.entity.DocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<DocumentEntity,Long> {
}
