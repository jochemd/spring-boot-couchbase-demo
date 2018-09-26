package net.vandieten.couchbasedemo.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.couchbase.client.core.CouchbaseException;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.query.N1qlQuery;
import com.couchbase.client.java.query.N1qlQueryResult;

import net.vandieten.couchbasedemo.entity.Car;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarRepositoryTest {

    @Autowired
    CarRepository repo;
    
    @Autowired
    CouchbaseTemplate template;

    private String tstString1 = "Civic";
    private String tstString2 = "C";
    private String tstString3 = "C\" OR `model` LIKE \"";
    
    @Before
    public void cleanUp() {
        repo.deleteAll();
    }
    
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

    @Test
    public void startingWith() {
        Car car1 = getCar1();
        repo.save(car1);
        
        Car car2 = getCar2();
        repo.save(car2);

        assertEquals(1, repo.findByModelStartingWith(tstString1).size());
        assertEquals(1, repo.findByModelStartingWith(tstString2).size());
        assertEquals(0, repo.findByModelStartingWith(tstString3).size());
        
    }

    @Test
    public void nativeN1QL() {
        Car car1 = getCar1();
        repo.save(car1);
        
        Car car2 = getCar2();
        repo.save(car2);

        assertEquals(1, runN1qlQuery(tstString1).allRows().size());
        assertEquals(1, runN1qlQuery(tstString2).allRows().size());
        assertEquals(0, runN1qlQuery(tstString3).allRows().size());
        
    }
    
    private N1qlQueryResult runN1qlQuery(String like) {
        String statement = " SELECT * FROM demo car WHERE model LIKE $model ";
        N1qlQuery query = N1qlQuery.parameterized(statement, JsonObject.create().put("model", like + "%"));
        N1qlQueryResult result = template.queryN1QL(query);
        if (!result.finalSuccess())
            throw new CouchbaseException(result.errors().toString());
        return result;
    }
    

    private Car getCar1() {
        Car car = new Car();
        car.setId("my-first-car");
        car.setBrand("Honda");
        car.setYear(2003);
        car.setModel("Civic");
        return car;
    }
    
    private Car getCar2() {
        Car car = new Car();
        car.setId("my-second-car");
        car.setBrand("Honda");
        car.setYear(2008);
        car.setModel("Accord");
        return car;
    }
    

}
