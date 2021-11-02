package com.springsecurity.petdetails.service;

import com.springsecurity.petdetails.model.PetDetailsModel;
import com.springsecurity.petdetails.model.repository.PetDetailsModelRepository;
import org.hibernate.annotations.GeneratorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/petdetailsService")
public class PetsDetailsServiceController {

    @Autowired
    PetDetailsModelRepository petDetailsModelRepository;


    @PostMapping("/petDetails")
    public PetDetailsModel createPetDetails(@RequestBody PetDetailsModel petDetailsModel){

        System.out.println(">>>>>>"+petDetailsModel.getPetBreed());

        PetDetailsModel petDetailsModel1 =  petDetailsModelRepository.save(petDetailsModel);

        return  petDetailsModel1;
    }


    @GetMapping("/petDetails/{petID}")
    @PreAuthorize("hasRole('admin_user')") // This is enabled in GlobalMethodWebSecurity class
    public PetDetailsModel getpetDetails(@PathVariable("petID") int petID){

        PetDetailsModel petDetailsModel = petDetailsModelRepository.findByPetID(petID);
        return petDetailsModel;
    }

   @GetMapping("/dummy")
   @PostAuthorize("returnObject.equals(' The Dummy')") // This is enabled in GlobalMethodWebSecurity class , returnObject
   // is wrapped by Spring which of method return Object, "returnObject.equals(' The Dummy12')" will not authirize the request
    public String getDummy(){

        return " The Dummy";
    }

}
