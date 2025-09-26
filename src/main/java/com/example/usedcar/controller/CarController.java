package com.example.usedcar.controller;

import com.example.usedcar.model.Car;
import com.example.usedcar.service.PriceEstimationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private PriceEstimationService priceService;

    @GetMapping
    public List<Car> getAllCars() {
        return priceService.getAllCars();
    }

    @PostMapping("/estimate")
    public double estimatePrice(@RequestBody Car inputCar) {
        return priceService.estimatePrice(inputCar);
    }
}
