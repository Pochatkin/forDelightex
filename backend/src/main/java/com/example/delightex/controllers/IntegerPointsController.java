package com.example.delightex.controllers;

import com.example.delightex.components.IntRectangleComponent;
import com.example.delightex.entity.IntRectangle;
import com.example.delightex.entity.Request;
import com.example.delightex.reps.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by mikhail on 04.07.17.
 */
@RestController
@RequestMapping("/intPoints")
public class IntegerPointsController {

    private IntRectangleComponent intRectangleComponent;
    private final RequestRepository requestRepository;

    @Autowired
    public IntegerPointsController(RequestRepository requestRepository) {
        this.intRectangleComponent = new IntRectangleComponent();
        this.requestRepository = requestRepository;
    }

    @RequestMapping(value = "/getCounterSky", method = RequestMethod.GET)
    public ResponseEntity<List<IntRectangle>> getCounterSky() {
        intRectangleComponent.generateCounterSky();

        return new ResponseEntity<>(intRectangleComponent.getCounterSky(), HttpStatus.OK);
    }

    @RequestMapping(value = "/generateRectangle", method = RequestMethod.POST)
    public ResponseEntity<List<IntRectangle>> generateIntRectangle(@RequestBody Request request) {
        intRectangleComponent.refresh();
        intRectangleComponent.generateRectangle(request.getNumber(), request.getMaxRangeX(), request.getMaxRangeY());

        return new ResponseEntity<>(intRectangleComponent.getAllRectangle(), HttpStatus.OK);
    }
}
