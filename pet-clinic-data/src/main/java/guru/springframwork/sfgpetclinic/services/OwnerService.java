package guru.springframwork.sfgpetclinic.services;

import guru.springframwork.sfgpetclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);

}
