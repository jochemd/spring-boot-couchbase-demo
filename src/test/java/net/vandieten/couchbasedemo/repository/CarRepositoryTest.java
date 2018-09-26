package net.vandieten.couchbasedemo.repository;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import net.vandieten.couchbasedemo.entity.Car;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarRepositoryTest {

    @Autowired
    CarRepository repo;
    
    @Autowired
    CouchbaseTemplate template;

    @Test
    public void crud() {
        Car car = getCar1();
        repo.save(car);
        
        car.setYear(2004);
        repo.save(car);
        
        assertNotEquals(car.getCreatedAt(), car.getModifiedAt());
        
        repo.delete(car);
        
        assertFalse(repo.findById("my-first-car").isPresent());
    }

    private Car getCar1() {
        Car car = new Car();
        car.setId("my-first-car");
        car.setBrand("Honda");
        car.setYear(2003);
        car.setModel("Civic");
        return car;
    }

}
