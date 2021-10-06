package com.springsecurity.petstore.model.repository;

import com.springsecurity.petstore.model.PetStoreDao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetStoreDaoRepository extends JpaRepository<PetStoreDao,Long> {
    PetStoreDao findByStoreID(int storeID);
}
