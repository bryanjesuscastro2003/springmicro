package com.tutorial.user.controller;

import com.tutorial.user.entity.User;
import com.tutorial.user.models.Car;
import com.tutorial.user.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAll(){
          List<User> users = userService.getAll();
          System.out.println(users + " mmm");
          if(users.isEmpty())
                 return ResponseEntity.noContent().build();
          return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(
            @PathVariable("id") ObjectId id
    ){
        User user = userService.getUserById(id);
        if(user == null)
              return ResponseEntity.notFound().build();
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<User> save(
            @RequestBody User user
    ){
        User userNew = userService.save(user);
        return ResponseEntity.ok(userNew);
    }

    ////////////////////////////////////////////////////////////////////

    @CircuitBreaker(name = "carsCB", fallbackMethod = "fallbackGetCars")
    @GetMapping("/cars/{userid}")
    public ResponseEntity<List<Car>> getCars(
            @PathVariable("userid") String userid
    ){
        System.out.println(" bb");
        List<Car> cars = userService.getCars(userid);
        System.out.println(cars + " bb");
        if(cars == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(cars);
    }

    // save  car
    @CircuitBreaker(name = "carsCB", fallbackMethod = "fallbackSaveCar")
    @PostMapping("/saveCar/{userId}")
    public ResponseEntity<Car> saveCar(
            @PathVariable("userId") String userId,
            @RequestBody Car car
    ){
         Car carNew = userService.saveCar(userId, car);
         return ResponseEntity.ok(carNew);
    }

    // Get car by id
    @CircuitBreaker(name = "carsCB", fallbackMethod = "fallbackGetCars2")
    @GetMapping("/cars/fen/{userId}")
    public ResponseEntity<List<Car>> getCarsByUserId(
            @PathVariable("userId") String userId
    ){
        System.out.println(" bb");
        List<Car> cars = userService.getCarsById(userId);
        System.out.println(cars + " bb");
        if(cars == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(cars);
    }

    // Get all
    @CircuitBreaker(name = "carsCB", fallbackMethod = "fallbackGetAll")
    @GetMapping("/all/{userid}")
    public ResponseEntity<Map<String, Object>> getCars(
            @PathVariable("userid") ObjectId userid
    ){
        Map<String, Object> result = userService.getUserAndCars(userid);
        return ResponseEntity.ok(result);
    }

    // Process 1
    private ResponseEntity<List<Car>> fallbackGetCars(@PathVariable("userid") String userid, RuntimeException e){
        return new ResponseEntity("Time out for process 1", HttpStatus.OK);
    }

    // Process 2
    private ResponseEntity<List<Car>> fallbackSaveCar(
            @PathVariable("userId") String userId,
            @RequestBody Car car,
            RuntimeException e
    ){
        return new ResponseEntity("Time out for process 2", HttpStatus.OK);
    }

    // Process 3
    private ResponseEntity<List<Car>> fallbackGetCars2(@PathVariable("userId") String userId, RuntimeException e){
        return new ResponseEntity("Time out for process 3", HttpStatus.OK);
    }

    // Process 4
    private ResponseEntity<List<Car>> fallbackGetAll( @PathVariable("userid") ObjectId userid, RuntimeException e){
        return new ResponseEntity("Time out for process 4", HttpStatus.OK);
    }



}
