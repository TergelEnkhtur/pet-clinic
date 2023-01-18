package guru.springframework.sfpetclinic.bootstrap;

import guru.springframework.sfpetclinic.model.Owner;
import guru.springframework.sfpetclinic.model.Vet;
import guru.springframework.sfpetclinic.services.OwnerService;
import guru.springframework.sfpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    //@Autowired - no longer required here
    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception {

        Owner owner1 = new Owner();
        owner1.setFirstName("Jesse");
        owner1.setLastName("McCree");

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Genji");
        owner2.setLastName("Shimada");

        ownerService.save(owner2);

        System.out.println("Loaded Owners...");

        Vet vet1 = new Vet();
        vet1.setFirstName("Angela");
        vet1.setLastName("Ziegler");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Kiriko");
        vet2.setLastName("Kamori");

        vetService.save(vet2);

        System.out.println("Loaded Vets...");
    }
}
