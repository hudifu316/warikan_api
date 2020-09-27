package com.cosa2.warikan_api.domain.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cosa2.warikan_api.domain.model.Bill;

@Repository
public interface BillRepository extends JpaRepository<Bill, UUID>{

}
