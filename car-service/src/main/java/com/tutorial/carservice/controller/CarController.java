package com.tutorial.carservice.controller;

import com.tutorial.carservice.identity.Car;
import com.tutorial.carservice.services.CarService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarService carService;


    @GetMapping
    public ResponseEntity<List<Car>> getAll(){
        List<Car> cars = carService.getAll();
        System.out.println(cars + " mmm");
        if(cars.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getUser(
            @PathVariable("id") ObjectId id
    ){
        Car user = carService.getCarById(id);
        if(user == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(user);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Car>> getCarByUserId(
            @PathVariable("userId") String userId
    ){
        List<Car> cars = carService.findByUserId(userId);
        if(cars == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/user/{userId}/{id}")
    public ResponseEntity<Car> getCarByUserIdandId(
            @PathVariable("userId") String userId,
            @PathVariable("id") String id
    ){
        Car car = carService.findByUserIdandid(userId, id);
        if(car == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(car);
    }

    @PostMapping
    public ResponseEntity<Car> save(
            @RequestBody Car user
    ){
        Car carNew = carService.save(user);
        return ResponseEntity.ok(carNew);
    }

}
