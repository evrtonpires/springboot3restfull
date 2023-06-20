package com.evrtonpires.springboot3restfull.repositories;

import com.evrtonpires.springboot3restfull.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AddressRepository extends JpaRepository<Address, UUID> {
}
