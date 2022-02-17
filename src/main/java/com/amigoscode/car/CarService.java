package com.amigoscode.car;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    private CarDAO carDAO;

    //@qualifier decides which database is being used
    public CarService(@Qualifier("postgres") CarDAO carDAO) {
        this.carDAO = carDAO;
    }

    private Car getCarOrThrow(Integer id){ //business logic to check if car exists, if not throw exception
        Car car = carDAO.selectCarById(id);
            if (car == null){
                throw new CarNotFoundException("Error. Car does not exist in database"); //implementing own exception to give specific error
            }
        return car;
    }

    public void registerNewCar(Car car) {
        // business logic. check if reg number is valid and not take
        if (car.getPrice() <= 0) {
            throw new IllegalStateException("Car price cannot be 0 or less");
        }
        int result = carDAO.insertCar(car);

        if (result != 1) {
            throw new IllegalStateException("Could not save car...");
        }
    }

    public List<Car> getCars() {
        return carDAO.selectAllCars();
    }

    public Car selectCarByID(Integer idNumber){
        Car car = getCarOrThrow(idNumber);
        return carDAO.selectCarById(idNumber);
    }

    public void updateCar(Integer id, Car updatedCar){
       Car car = getCarOrThrow(id);

        int result = carDAO.updateCar(id, updatedCar); //if car was successful in updating car, it returns int 1.

        if (result !=1){ //if result isn't one then you know that something failed.
            throw new IllegalStateException("Could not update car in database. Input not valid");
        }
    }

    public void deleteCarByID(Integer deleteID){
        Car car = getCarOrThrow(deleteID);
//        int deleted = carDAO.deleteCar(deleteID); // deleting car and then checking that car is deleted by checking result

        if (carDAO.selectCarById(deleteID) == null) {
            throw new IllegalStateException("Could not delete car.");
        }
        carDAO.deleteCar(deleteID);
    }

    }