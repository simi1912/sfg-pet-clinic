package guru.springframwork.sfgpetclinic.repositories;

import guru.springframwork.sfgpetclinic.model.PetType;
import org.springframework.data.repository.CrudRepository;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}
