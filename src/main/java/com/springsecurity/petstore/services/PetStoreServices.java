package com.springsecurity.petstore.services;

import com.springsecurity.petdetails.model.PetDetailsModel;
import com.springsecurity.petstore.model.PetStoreDao;
import com.springsecurity.petstore.model.repository.PetStoreDaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/petStoreService")
public class PetStoreServices {

     @Autowired
    PetStoreDaoRepository petStoreDaoRepository;

    @Autowired
    RestTemplate restTemplate;

    @Value("${petdetailsService.url}")
    String petdetailsServiceUrl;

    @PostMapping("/createPetOrder")
    public PetStoreDao createPetOrder(@RequestBody PetStoreDao petStoreDao){

        System.out.println("URL >>>>"+petdetailsServiceUrl+petStoreDao.getPetID());
        PetDetailsModel petDetailsModel = restTemplate.getForObject(petdetailsServiceUrl+petStoreDao.getPetID(),PetDetailsModel.class);

        petStoreDao.setCost(petDetailsModel.getCost().add(petStoreDao.getCost()));

        PetStoreDao PetStoreDao1 = petStoreDaoRepository.save(petStoreDao);

        return PetStoreDao1;
    }



    @GetMapping("/getpetStore/{storeID}")
    public PetStoreDao getpetstoreDetails(@PathVariable("storeID") int storeID){

        PetStoreDao PetStoreDao = petStoreDaoRepository.findByStoreID(storeID);
        return PetStoreDao;

    }
}
