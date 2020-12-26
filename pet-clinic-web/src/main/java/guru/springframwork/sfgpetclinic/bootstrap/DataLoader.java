package guru.springframwork.sfgpetclinic.bootstrap;

import guru.springframwork.sfgpetclinic.model.Owner;
import guru.springframwork.sfgpetclinic.model.Vet;
import guru.springframwork.sfgpetclinic.services.OwnerService;
import guru.springframwork.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception {
        Owner owner1 = new Owner();
        owner1.setId(1L);
        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setId(2L);
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glenanne");
        ownerService.save(owner2);

        Vet ve1 = new Vet();
        ve1.setId(1L);
        ve1.setFirstName("Sam");
        ve1.setLastName("Axe");
        vetService.save(ve1);

        Vet ve2 = new Vet();
        ve2.setId(2L);
        ve2.setFirstName("Jessie");
        ve2.setLastName("Porter");
        vetService.save(ve2);

        System.out.println("Data loaded");
    }
}
