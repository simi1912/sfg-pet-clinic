package guru.springframwork.sfgpetclinic.services;

import guru.springframwork.sfgpetclinic.model.Vet;

import java.util.Set;

interface VetService {

    Vet findById(Long id);

    Vet save(Vet vet);

    Set<Vet> findAll();
}
