package com.springsecurity.petdetails.model.repository;

import com.springsecurity.petdetails.model.PetDetailsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface PetDetailsModelRepository extends JpaRepository<PetDetailsModel,Long> {
    PetDetailsModel findByPetID(int petID);
}
