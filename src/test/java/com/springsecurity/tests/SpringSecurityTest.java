package com.springsecurity.tests;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class SpringSecurityTest {

    @Autowired
    MockMvc mockMvc;


    @Test
    public void testGetpetDetailsWithoutAuth_Forbidden() throws Exception {

        mockMvc.perform(get("/petdetailsService/petDetails/1214")).
                andExpect(status().isForbidden());

    }

    @Test
    public void testGetpetDetailsWithoutAuth_UnAuthorized() throws Exception {

        mockMvc.perform(get("/petdetailsService/petDetails/1214")).
                andExpect(status().isUnauthorized());

    }

    @Test
    @WithMockUser
    public void testGetpetDetailsWithAuth_OK() throws Exception {

        mockMvc.perform(get("/petdetailsService/petDetails/1214")).
                andExpect(status().isOk());

    }

    @Test
    @WithUserDetails("nareshm@n.com")
    public void testGetpetDetailsWithUserDetailsAuth_OK() throws Exception {

        mockMvc.perform(get("/petdetailsService/petDetails/1214").with(csrf())).
                andExpect(status().isOk());

    }


    @Test
    public void testGetpetDetailsWithoutAuth_401() throws Exception {

        mockMvc.perform(get("/petdetailsService/petDetails/1214")).
                andExpect(status().is4xxClientError());

    }

    @Test
    //@WithMockUser
    @WithMockUser(roles = {"USER", "ADMIN"})
    public void testPetStoreAppDetailsWithAuth_OK() throws Exception {

        mockMvc.perform(get("/petdetailsService/getPetStoreAppDetails")).
                andExpect(status().isOk()).andExpect(content().string("Pet Store - Is am online store for buying pets and pet food "));

    }


    //Test with CSRF
    @Test
    //@WithMockUser
    @WithMockUser(roles = {"ADMIN"})
    public void testcreatePetDetailsWithAuth_OK() throws Exception {

        String payload = "{\n" +
                "    \"petType\" : \"dog\",\n" +
                "    \"petBreed\" : \"Jack Rassel\",\n" +
                "    \"cost\" : 200000,\n" +
                "    \"petID\" : 124447\n" +
                "  \n" +
                "}";

        mockMvc.perform(post("/petdetailsService/petDetails").content(payload).contentType(MediaType.APPLICATION_JSON)
                .with(csrf().asHeader())).andExpect(status().isOk());

    }


    //Test with CSRF
    @Test
    //@WithMockUser
    @WithMockUser(roles = {"ADMIN"})
    public void testCORScreatePetDetailsWithAuth_OK() throws Exception {

        String payload = "{\n" +
                "    \"petType\" : \"dog\",\n" +
                "    \"petBreed\" : \"Jack Rassel\",\n" +
                "    \"cost\" : 200000,\n" +
                "    \"petID\" : 124447\n" +
                "  \n" +
                "}";

        mockMvc.perform(options("/petdetailsService/petDetails")
                        .header("Access-Control-Request-Method", "POST")
                        .header("Origin","https://www.somesite.com/"))
                .andExpect(status().isOk())
                .andExpect(header().exists("Access-Control-Allow-Origin"))
                .andExpect(header().exists("Access-Control-Allow-Methods"))
                .andExpect(header().string("Access-Control-Allow-Origin","*"))
                .andExpect(header().string("Access-Control-Allow-Methods","POST"));
    }
}

