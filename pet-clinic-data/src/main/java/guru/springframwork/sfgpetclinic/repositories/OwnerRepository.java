package guru.springframwork.sfgpetclinic.repositories;

import guru.springframwork.sfgpetclinic.model.Owner;
import org.springframework.data.repository.CrudRepository;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
}
