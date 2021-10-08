package com.vratislav.hais.learningspring.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vratislav.hais.learningspring.data.entity.Reservation;

import java.sql.Date;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {
	Iterable<Reservation> findReservationByResDate(Date date);
}
