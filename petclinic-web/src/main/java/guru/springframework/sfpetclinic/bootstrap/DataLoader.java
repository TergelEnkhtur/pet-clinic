package guru.springframework.sfpetclinic.bootstrap;

import guru.springframework.sfpetclinic.model.Owner;
import guru.springframework.sfpetclinic.model.Pet;
import guru.springframework.sfpetclinic.model.PetType;
import guru.springframework.sfpetclinic.model.Vet;
import guru.springframework.sfpetclinic.services.OwnerService;
import guru.springframework.sfpetclinic.services.PetTypeService;
import guru.springframework.sfpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    //@Autowired - no longer required here
    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Owner owner1 = new Owner();
        owner1.setFirstName("Jesse");
        owner1.setLastName("McCree");
        owner1.setAddress("123 Santa Fe");
        owner1.setCity("New Mexico");
        owner1.setTelephone("1231231234");

        Pet McCreesPet = new Pet();
        McCreesPet.setName("Max");
        McCreesPet.setPetType(savedDogPetType);
        McCreesPet.setOwner(owner1);
        McCreesPet.setBirthDate(LocalDate.now());
        owner1.getPets().add(McCreesPet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Genji");
        owner2.setLastName("Shimada");
        owner2.setAddress("123 Shimada Castle");
        owner2.setCity("Hanamura");
        owner2.setTelephone("1231231234");

        Pet GenjisCat = new Pet();
        GenjisCat.setName("Shiro");
        GenjisCat.setPetType(savedCatPetType);
        GenjisCat.setOwner(owner2);
        GenjisCat.setBirthDate(LocalDate.now());
        owner2.getPets().add(GenjisCat);

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
