package guru.springframwork.sfgpetclinic.repositories;

import guru.springframwork.sfgpetclinic.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
