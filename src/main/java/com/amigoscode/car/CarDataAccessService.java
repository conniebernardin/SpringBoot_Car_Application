package com.amigoscode.car;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("postgres")
public class CarDataAccessService implements CarDAO {

    private JdbcTemplate jdbcTemplate; //injecting template allows interaction with external database

    public CarDataAccessService(JdbcTemplate jdbcTemplate) { //needs constructor as you would with list
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Car selectCarById(Integer id) {
        //todo: implement this method to get car by id from database
        return null;
    }

    @Override
    public List<Car> selectAllCars() {
        String sql = """
                SELECT id, regNumber, brand, price
                From car
                """; //specify each column instead of using *

                //rs is result set - highlights whole row
                //query method tells code to read through each line as long as there is a next line
        //row number is the number of each row - not related to id
        List<Car> cars = jdbcTemplate.query(sql, (rs, rowNum) -> {
            //rowMapper takes each row and maps it into java object (result set)
            Car car = new Car( //constructing car from properties retrieved by rowMapper
                    rs.getInt("id"), //call whole row and get integer from column named "id"
                                                //for result set, retrieving integer for ID from "id"
                    rs.getString("regNumber"),
                    Brand.valueOf(rs.getString("Brand")),
                    rs.getDouble("price")

                    ); //return java object with each property from rs process

                    return car; //return cars one by one and store in list of cars.
                                //if you don't return car it will be lost in heap and cannot be added to list
        });
        return cars; //returning list of cars because you want to select ALL cars
    }

    @Override
    public int insertCar(Car car) {
        String sql = """ 
        INSERT INTO car(regNumber, brand, price)
        VALUES(?, ?, ?)
                """;

        int rowsAffected = jdbcTemplate.update( //update row can be used to insert new car
                sql, //first argument is sql query, then pass a list of arguments corresponding with three ? above.
                car.getRegNumber(),
                car.getBrand().name(),
                car.getPrice()
        );
        return rowsAffected;
    }

    @Override
    public int deleteCar(Integer id) {
        String sql = "DELETE FROM car WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public int updateCar(Integer id, Car update) {
        return 0;
    }
}
