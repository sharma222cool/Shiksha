package com.center.shiksha.repository.address;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.center.shiksha.model.Standard;
import com.center.shiksha.model.Section;
import com.center.shiksha.model.address.Address;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RepositoryRestResource(collectionResourceRel = "addresses", path="addresses")
public interface AddressRepository extends JpaRepository<Address, Integer>{

}
