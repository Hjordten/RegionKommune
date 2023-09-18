package com.example.regionkommune.region.controller;

import com.example.regionkommune.region.model.Region;
import com.example.regionkommune.region.service.interfaces.RegionServiceInterface;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
public class RegionRestController {

    @Autowired
    RegionServiceInterface regionServiceInterface;

    @GetMapping("api/regioner")
    public ResponseEntity<Object> getRegioner() {
        List<Region> listRegioner = regionServiceInterface.getRegioner();

        return ResponseEntity.ok(listRegioner);
    }

    @GetMapping("/regioner")
    public ResponseEntity<List<Region>> getRegionerFromDatabase(){
        List<Region> regionList = regionServiceInterface.getRegionerFromDatabase();

        return ResponseEntity.ok(regionList);
    }


     @GetMapping("/region/find/kode/{kode}")
    public ResponseEntity<Region> findRegionAsEntityUsingKode(@PathVariable String kode){
       Region region = regionServiceInterface.findRegionAsEntityUsingKode(kode);

        if (kode.isEmpty()){
            throw new EntityNotFoundException("No Kommune with desired name exists");
        } else {
            return ResponseEntity.ok(region);
        }
    }


    @PostMapping("region/indæst")
    public ResponseEntity<String> postRegion(@RequestBody Region Region){
            Region savedRegion = regionServiceInterface.save(Region);
            if (savedRegion.getHref() == null || savedRegion.getNavn() == null || savedRegion.getKode() == null){
                return ResponseEntity.ok("Missing required fields");
            } else {
                return ResponseEntity.ok("New region succesfully saved");
            }
        }


    @PutMapping("/region/opdater/kode/{kode}")
    public ResponseEntity<String> updateRegionWithKode(@PathVariable String kode, @RequestBody Region region) {
        Region foundRegion = regionServiceInterface.findRegionAsEntityUsingKode(kode);

        if (foundRegion.getKode() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No region with the desired kode exists");
        } else {
            foundRegion.setKode(region.getKode());
            foundRegion.setNavn(region.getNavn());
            foundRegion.setHref(region.getHref());

            Region updatedRegion = regionServiceInterface.save(foundRegion);

            if (updatedRegion != null) {
                return ResponseEntity.ok("Region was successfully updated");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update the region");
            }
        }
    }

    @DeleteMapping("/region/slet/kode/{kode}")
    public ResponseEntity<String> deleteRegionUsingKode(@PathVariable String kode){
        Region foundRegion = regionServiceInterface.findRegionAsEntityUsingKode(kode);

        if (foundRegion.getKode() == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No region with the desired kode exists");
        } else {
            regionServiceInterface.deleteRegionUsingKode(kode);
            return ResponseEntity.ok("Region succesfully deleted");
        }
    }




}



