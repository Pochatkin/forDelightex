package com.example.delightex.controllers;

import com.example.delightex.components.IntRectangleComponent;
import com.example.delightex.components.RealRectangleComponent;
import com.example.delightex.entity.IntRectangle;
import com.example.delightex.entity.RealRectangle;
import com.example.delightex.entity.Request;
import com.example.delightex.reps.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by mikhail on 05.07.17.
 */
@RestController
@RequestMapping("/realPoints")
public class RealPointsController {

    private RealRectangleComponent realRectangleComponent;
    private final RequestRepository requestRepository;

    @Autowired
    public RealPointsController(RequestRepository requestRepository) {
        this.realRectangleComponent = new RealRectangleComponent();
        this.requestRepository = requestRepository;
    }

    @RequestMapping(value = "/getCounterSky", method = RequestMethod.GET)
    public ResponseEntity<List<RealRectangle>> getCounterSky() {
        realRectangleComponent.generateCounterSky();

        return new ResponseEntity<>(realRectangleComponent.getCounterSky(), HttpStatus.OK);
    }

    @RequestMapping(value = "/generateRectangle", method = RequestMethod.POST)
    public ResponseEntity<List<RealRectangle>> generateIntRectangle(@RequestBody Request request) {
        realRectangleComponent.refresh();
        realRectangleComponent.generateRectangle(request.getNumber(), request.getMaxRangeX(), request.getMaxRangeY());

        return new ResponseEntity<>(realRectangleComponent.getAllRectangle(), HttpStatus.OK);
    }

}
