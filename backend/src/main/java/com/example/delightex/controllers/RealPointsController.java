package com.example.delightex.controllers;

import com.example.delightex.entity.RealRectangle;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by mikhail on 05.07.17.
 */
@RestController
@RequestMapping("/realNumber")
public class RealPointsController {


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<RealRectangle>> generateRealRectangle() {

        return new ResponseEntity<List<RealRectangle>>(new LinkedList<>(), HttpStatus.OK);

    }

}
