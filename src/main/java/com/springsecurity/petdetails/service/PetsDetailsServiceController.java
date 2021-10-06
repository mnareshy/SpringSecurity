package com.springsecurity.petdetails.service;

import com.springsecurity.petdetails.model.PetDetailsModel;
import com.springsecurity.petdetails.model.repository.PetDetailsModelRepository;
import org.hibernate.annotations.GeneratorType;
import org.springframework.beans.factory.annotation.Autowired;
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
    public PetDetailsModel getpetDetails(@PathVariable("petID") int petID){

        PetDetailsModel petDetailsModel = petDetailsModelRepository.findByPetID(petID);
        return petDetailsModel;
    }

   @GetMapping("/dummy")
    public String getDummy(){

        return " The Dummy";
    }

}
