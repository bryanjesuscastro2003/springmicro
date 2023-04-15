package com.tutorial.user.controller;

import com.tutorial.user.entity.User;
import com.tutorial.user.models.Car;
import com.tutorial.user.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

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

    @PostMapping
    public ResponseEntity<User> save(
            @RequestBody User user
    ){
        User userNew = userService.save(user);
        return ResponseEntity.ok(userNew);
    }

    @PostMapping("/saveCar/{userId}")
    public ResponseEntity<Car> saveCar(
            @PathVariable("userId") String userId,
            @RequestBody Car car
    ){
         Car carNew = userService.saveCar(userId, car);
         return ResponseEntity.ok(carNew);
    }

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


    @GetMapping("/all/{userid}")
    public ResponseEntity<Map<String, Object>> getCars(
            @PathVariable("userid") ObjectId userid
    ){
        Map<String, Object> result = userService.getUserAndCars(userid);
        return ResponseEntity.ok(result);
    }




}
