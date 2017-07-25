package com.franciscogarrido.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.franciscogarrido.entities.Reserva;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer> {

}
