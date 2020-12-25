package guru.springframwork.sfgpetclinic.services.map;

import guru.springframwork.sfgpetclinic.model.Pet;
import guru.springframwork.sfgpetclinic.services.CrudService;

import java.util.Set;

public class PerServiceMap extends AbstractMapService<Pet, Long> implements CrudService<Pet, Long> {

    @Override
    public Set<Pet> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Pet object) {
        super.delete(object);
    }

    @Override
    public Pet save(Pet object) {
        return super.save(object.getId(), object);
    }

    @Override
    public Pet findById(Long id) {
        return super.findById(id);
    }
}
