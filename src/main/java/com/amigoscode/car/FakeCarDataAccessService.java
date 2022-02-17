package com.amigoscode.car;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository("fake")
public class FakeCarDataAccessService implements CarDAO {

    private List<Car> db; //creating list of cars called db

    public FakeCarDataAccessService() {
        this.db = new ArrayList<>();
    }

    @Override
    public Car selectCarById(Integer id) {
        for (Car selectCar: db) {
            if (id == selectCar.getId()){
                return selectCar;
            }
        }
        System.out.println("ID doesn't exist");
        return null;
    }

    @Override
    public List<Car> selectAllCars() {
        return db;
    }

    @Override
    public int insertCar(Car car) {
        db.add(car);
        return 1; //returns 1 to confirm car has successfully been added. used in service class too.
    }

    @Override
    public int deleteCar(Integer deleteID) {
        db.remove(selectCarById(deleteID));
        return 1;
    }

    @Override
    public int updateCar(Integer id, Car updatedCar) {
        Car car = selectCarById(id); //creates car object equal to the car with specific id inputted
        car.setPrice(updatedCar.getPrice());
        car.setBrand(updatedCar.getBrand());
        car.setRegNumber(updatedCar.getRegNumber());
        return 1;
    }

}
