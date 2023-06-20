package com.evrtonpires.springboot3restfull.repositories;

import com.evrtonpires.springboot3restfull.domain.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StateRepository extends JpaRepository<State, UUID> {
}
