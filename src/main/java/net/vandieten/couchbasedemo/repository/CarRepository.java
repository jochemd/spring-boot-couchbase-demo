package net.vandieten.couchbasedemo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.vandieten.couchbasedemo.entity.Car;

public interface CarRepository extends CrudRepository<Car, String> {

    List<Car> findByModelStartingWith(String model);

}
