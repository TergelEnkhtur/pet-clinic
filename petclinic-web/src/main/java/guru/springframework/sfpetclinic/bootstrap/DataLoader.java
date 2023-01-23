package guru.springframework.sfpetclinic.bootstrap;

import guru.springframework.sfpetclinic.model.*;
import guru.springframework.sfpetclinic.services.OwnerService;
import guru.springframework.sfpetclinic.services.PetTypeService;
import guru.springframework.sfpetclinic.services.SpecialtyService;
import guru.springframework.sfpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;

    //@Autowired - no longer required here
    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialtyService specialtyService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
    }

    @Override
    public void run(String... args) throws Exception {

        int count = petTypeService.findAll().size();

        if(count ==0) {
            loadData();
        }
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

        Specialty radiology = new Specialty();
        radiology.setDescription("Radiology");
        Specialty savedRadiology = specialtyService.save(radiology);

        Specialty surgery = new Specialty();
        surgery.setDescription("Surgery");
        Specialty savedSurgery = specialtyService.save(surgery);

        Specialty dentistry = new Specialty();
        dentistry.setDescription("Dentistry");
        Specialty savedDentistry = specialtyService.save(dentistry);

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
        vet1.getSpecialties().add(savedRadiology);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Kiriko");
        vet2.setLastName("Kamori");
        vet2.getSpecialties().add(savedSurgery);

        vetService.save(vet2);

        System.out.println("Loaded Vets...");
    }
}
