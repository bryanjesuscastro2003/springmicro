package com.tutorial.carservice.identity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import javax.persistence.Entity;
import javax.persistence.Id;

@Document(collection = "_microCarv1")
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Field(targetType = FieldType.OBJECT_ID)
    private String id;
    private String brand;
    private String model;
    private String userId;
}
