package com.example.regionkommune.region.controller;

import com.example.regionkommune.region.model.Region;
import com.example.regionkommune.region.service.interfaces.RegionServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class RegionRestController {

    @Autowired
    RegionServiceInterface regionServiceInterface;

    @GetMapping("/regioner")
    public ResponseEntity<Object> getRegioner() {
        List<Region> listRegioner = regionServiceInterface.getRegioner();


        return ResponseEntity.ok(listRegioner);
    }

}
