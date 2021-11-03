package com.springsecurity.petdetails.service;

import com.springsecurity.petdetails.model.PetDetailsModel;
import com.springsecurity.petdetails.model.repository.PetDetailsModelRepository;
import org.hibernate.annotations.GeneratorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/petdetailsService")
public class PetsDetailsServiceController {

    @Autowired
    PetDetailsModelRepository petDetailsModelRepository;


    @PostMapping("/petDetails")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public PetDetailsModel createPetDetails(@RequestBody PetDetailsModel petDetailsModel){

        System.out.println(">>>>>>"+petDetailsModel.getPetBreed());

        PetDetailsModel petDetailsModel1 =  petDetailsModelRepository.save(petDetailsModel);

        return  petDetailsModel1;
    }


    @GetMapping("/petDetails/{petID}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')") // This is enabled in GlobalMethodWebSecurity class
    public PetDetailsModel getpetDetails(@PathVariable("petID") int petID){

        PetDetailsModel petDetailsModel = petDetailsModelRepository.findByPetID(petID);
        return petDetailsModel;
    }

   @GetMapping("/getPetStoreAppDetails")
   @PostAuthorize("returnObject.equals('Pet Store - Is am online store for buying pets and pet food ')") // This is enabled in GlobalMethodWebSecurity class , returnObject
   // is wrapped by Spring which of method return Object, "returnObject.equals(' The Dummy12')" will not authirize the request
    public String getPetStoreAppDetails(){

        return "Pet Store - Is am online store for buying pets and pet food ";
    }

}
