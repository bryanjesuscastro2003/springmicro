package com.tutorial.carservice.services;

import com.tutorial.carservice.identity.Car;
import com.tutorial.carservice.repository.CarEepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    @Autowired
    private CarEepo carRepository;

    public List<Car> getAll(){
        return carRepository.findAll();
    }

    public Car getCarById(ObjectId id){
        return carRepository.findById(id).orElse(null);
    }

    public Car save(Car car){
        Car carNew = carRepository.save(car);
        return carNew;
    }

    public List<Car> findByUserId(String userId){
        List<Car> myCar = carRepository.findByUserId(userId);
        return myCar;
    }

    public Car findByUserIdandid(String userId, String id){
        Optional<Car> myCar = carRepository.findByUserIdAndId(userId, id);
        return myCar.orElse(null);
    }
}
