package com.example.delightex.controllers;

import com.example.delightex.components.RectangleComponent;
import com.example.delightex.entity.Rectangle;
import com.example.delightex.entity.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by mikhail on 05.07.17.
 */
@RestController
@RequestMapping("/realPoints")
public class RealPointsController {

    private RectangleComponent realRectangleComponent;

    @Autowired
    public RealPointsController() {
        this.realRectangleComponent = new RectangleComponent();
    }

    @RequestMapping(value = "/getCounterSky", method = RequestMethod.GET)
    public ResponseEntity<List<Rectangle>> getCounterSky() {
        realRectangleComponent.generateCounterSky();

        return new ResponseEntity<>(realRectangleComponent.getCounterSky(), HttpStatus.OK);
    }

    @RequestMapping(value = "/generateRectangle", method = RequestMethod.POST)
    public ResponseEntity<List<Rectangle>> generateIntRectangle(@RequestBody Request request) {
        realRectangleComponent.refresh();
        realRectangleComponent.generateRectangle(false, request.getNumber(), request.getMaxRangeX(), request.getMaxRangeY());

        return new ResponseEntity<>(realRectangleComponent.getAllRectangle(), HttpStatus.OK);
    }

}
