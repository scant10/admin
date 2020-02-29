package com.example.admin.repository;

import com.example.admin.model.entity.Category;
import com.example.admin.model.entity.Partner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@org.springframework.stereotype.Repository
public interface PartnerRepository extends JpaRepository<Partner,Long> {
    List<Partner> findByCategory(Category category);
}
