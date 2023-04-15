package com.tutorial.user.service;

import com.tutorial.user.entity.User;
import com.tutorial.user.feignClient.CarFeignClient;
import com.tutorial.user.models.Car;
import com.tutorial.user.repository.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CarFeignClient carFeignClient;

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User getUserById(ObjectId id){
        return userRepository.findById(id).orElse(null);
    }

    public User save(User user){
        User userNew = userRepository.save(user);
        return userNew;
    }

    public List<Car> getCars(String userId){
        List<Car> cars = restTemplate.getForObject("http://car-service/car/user/"+userId, List.class);
        return cars;
    }

    public Car saveCar(String userId, Car car){
        car.setUserId(userId);
        Car newCar = carFeignClient.save(car);
        return newCar;
    }
    public List<Car> getCarsById(String userId){
        return carFeignClient.getCarByUserId(userId);
    }

    public Map<String, Object> getUserAndCars(ObjectId userId){
        Map<String, Object> result = new HashMap<>();
        User user = userRepository.findById(userId).orElse(null);
        if(user == null){
             result.put("message", "No existe el usuario");
             return result;
        }
        result.put("User", user);
        List<Car> cars = carFeignClient.getCarByUserId(userId.toString());
        if(cars.isEmpty())
            result.put("Cars", "No cars");
        else
            result.put("Cars", cars);
        return result;
    }


}
