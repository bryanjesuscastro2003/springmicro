package com.tutorial.user.feignClient;


import com.tutorial.user.models.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="car-service")//, url = "http://localhost:8002")
//@RequestMapping("/car")
public interface CarFeignClient {
  @PostMapping("/car")
  Car save(@RequestBody Car car);
  @GetMapping("/car/user/{userId}")
  List<Car> getCarByUserId(@PathVariable("userId") String userId);
}
