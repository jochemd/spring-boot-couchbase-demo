package net.vandieten.couchbasedemo.repository;

import org.springframework.data.repository.CrudRepository;

import net.vandieten.couchbasedemo.entity.Car;

public interface CarRepository extends CrudRepository<Car, String> {

}
