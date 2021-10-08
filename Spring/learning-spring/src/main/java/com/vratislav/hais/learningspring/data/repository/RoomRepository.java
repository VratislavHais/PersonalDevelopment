package com.vratislav.hais.learningspring.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vratislav.hais.learningspring.data.entity.Room;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {
}
