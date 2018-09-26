package net.vandieten.couchbasedemo.entity;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.couchbase.core.mapping.Document;

import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Car {

    @Id
    private String id;
    
    @Version
    private long version;
    
    @Field
    @CreatedDate
    private Date createdAt;

    @Field
    @LastModifiedDate
    private Date modifiedAt;

    @Field
    private String brand;
    
    @Field
    private Integer year;

    @Field
    private String model;
}
