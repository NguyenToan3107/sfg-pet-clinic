package com.thomas.sfgpetclinic.services.springdatajpa;

import com.thomas.sfgpetclinic.model.Owner;
import com.thomas.sfgpetclinic.repositories.OwnerRepository;
import com.thomas.sfgpetclinic.services.OwnerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class OwnerSDJpaService implements OwnerService {
    private final OwnerRepository ownerRepository;

    public OwnerSDJpaService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }
    @Override
    public Set<Owner> findAll() {
        Set<Owner> owners = new HashSet<>();
//        ownerRepository.findAll().iterator().forEachRemaining(owners::add);
        ownerRepository.findAll().forEach(owners::add);
        return owners;
    }

    @Override
    public Owner findById(Long id) {
//        Owner owner = null;
//        Optional<Owner> optional = ownerRepository.findById(id);
//        if(optional.isPresent()) {
//            owner = optional.get();
//        }
//        return owner;
        return ownerRepository.findById(id).orElse(null);
    }

    @Override
    public Owner save(Owner object) {
        return ownerRepository.save(object);
    }

    @Override
    public void delete(Owner object) {
        ownerRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        ownerRepository.deleteById(aLong);
    }

    @Override
    public Owner findByLastName(String lastName) {
        return ownerRepository.findByLastName(lastName);
    }

    @Override
    public List<Owner> findAllByLastNameLike(String lastName) {
        return ownerRepository.findAllByLastNameLike(lastName);
    }
}
