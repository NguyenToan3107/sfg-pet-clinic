package com.thomas.sfgpetclinic.services;

import com.thomas.sfgpetclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long>{
    Owner findByLastName(String lastName);
}
