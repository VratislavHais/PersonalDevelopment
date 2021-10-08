package com.vratislav.hais.learningspring.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vratislav.hais.learningspring.data.entity.Guest;

@Repository
public interface GuestRepository extends CrudRepository<Guest, Long> {
}
