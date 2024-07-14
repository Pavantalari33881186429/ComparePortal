package com.comparator.compare.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.comparator.compare.model.CProduct;

@Repository
public interface comparePortalDao extends JpaRepository<CProduct, Long>{

}
