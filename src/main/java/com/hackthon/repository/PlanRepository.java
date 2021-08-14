package com.hackthon.repository;

import com.hackthon.entity.DocumentEntity;
import com.hackthon.entity.PlansEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanRepository extends JpaRepository<PlansEntity,Long> {
}
