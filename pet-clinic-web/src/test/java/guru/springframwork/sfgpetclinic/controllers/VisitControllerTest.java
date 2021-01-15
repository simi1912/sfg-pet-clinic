package guru.springframwork.sfgpetclinic.controllers;

import guru.springframwork.sfgpetclinic.model.Owner;
import guru.springframwork.sfgpetclinic.model.Pet;
import guru.springframwork.sfgpetclinic.model.PetType;
import guru.springframwork.sfgpetclinic.services.PetService;
import guru.springframwork.sfgpetclinic.services.VisitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.UriTemplate;

import java.net.URI;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class VisitControllerTest {

    private static final String PETS_CREATE_OR_UPDATE_VISIT_FORM = "pets/createOrUpdateVisitForm";
    private static final String REDIRECT_OWNERS_1 = "redirect:/owners/{ownerId}";
    private static final String YET_ANOTHER_VISIT_DESCRIPTION = "yet another visit";

    @Mock
    PetService petService;
    @Mock
    VisitService visitService;
    @InjectMocks
    VisitController visitController;
    MockMvc mockMvc;

    private final UriTemplate visitsUriTemplate = new UriTemplate("/owners/{ownerId}/pets/{petId}/visits/new");
    private final Map<String, String> uriVariables = new HashMap<>();
    private URI visitsUri;

    @BeforeEach
    void set(){
        Owner owner = new Owner();
        owner.setId(1L);
        owner.setLastName("Doe");
        owner.setFirstName("Joe");

        PetType petType = new PetType();
        petType.setName("Dog");

        Pet pet = new Pet();
        pet.setId(1L);
        pet.setBirthDate(LocalDate.of(2018, 11, 13));
        pet.setName("Cutie");
        pet.setVisits(new HashSet<>());
        pet.setOwner(owner);
        pet.setPetType(petType);

        when(petService.findById(anyLong())).thenReturn(pet);

        uriVariables.clear();
        uriVariables.put("ownerId", owner.getId().toString());
        uriVariables.put("petId", pet.getId().toString());
        visitsUri = visitsUriTemplate.expand(uriVariables);

        mockMvc = MockMvcBuilders.standaloneSetup(visitController).build();
    }

    @Test
    void initNewVisitForm() throws Exception{
        mockMvc.perform(get(visitsUri))
                .andExpect(status().isOk())
                .andExpect(view().name(PETS_CREATE_OR_UPDATE_VISIT_FORM));
    }

    @Test
    void processNewVisitForm() throws Exception{
        mockMvc.perform(post(visitsUri)
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .param("date","2018-11-11")
                    .param("description", YET_ANOTHER_VISIT_DESCRIPTION))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name(REDIRECT_OWNERS_1))
                .andExpect(model().attributeExists("visit"));
    }
}
