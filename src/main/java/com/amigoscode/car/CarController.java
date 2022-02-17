package com.amigoscode.car;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarController {

    private CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }
    //controller should be simple
    //get request from client and send to service class to perform business logic

    @PostMapping(path = "cars") //method in carService
    public void addCar(@RequestBody Car car) {
        carService.registerNewCar(car);
    }

    @GetMapping(path = "cars") //method in carService
    public List<Car> getCars() {
        return carService.getCars();
    }

    @GetMapping(path = "cars/{id}")
    public Car getCarById(@PathVariable("id") Integer carId) {
        return carService.selectCarByID(carId);
    }

    @DeleteMapping(path = "cars/{id}")
    public void deleteCarById(@PathVariable("id") Integer carId) {
        carService.deleteCarByID(carId);
    }

    @PutMapping(path = "cars/{id}")
    public void updateCar(@PathVariable("id") Integer carId, @RequestBody Car update) {
        carService.updateCar(carId, update);
    }

    //calls on update car method from carService
}
