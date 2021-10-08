package com.vhais.practice.tacocloud.tacos.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vhais.practice.tacocloud.tacos.Taco;

@Repository
public interface TacoRepository extends CrudRepository<Taco, Long> {
}
