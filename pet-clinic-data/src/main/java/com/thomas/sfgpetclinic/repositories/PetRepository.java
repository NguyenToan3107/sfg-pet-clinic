package com.thomas.sfgpetclinic.repositories;

import com.thomas.sfgpetclinic.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
