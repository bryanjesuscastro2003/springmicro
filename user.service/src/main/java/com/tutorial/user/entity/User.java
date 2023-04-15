package com.tutorial.user.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import javax.persistence.Entity;
import javax.persistence.Id;

@Document(collection = "_microUserv1")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

  @Id
  //@GeneratedValue(strategy = GenerationType.IDENTITY)
  @Field(targetType = FieldType.OBJECT_ID)
  private String id;
  private String name;
  private String email;
}
