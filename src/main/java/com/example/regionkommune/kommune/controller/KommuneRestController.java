package com.example.regionkommune.kommune.controller;

import com.example.regionkommune.kommune.model.Kommune;
import com.example.regionkommune.kommune.service.interfaces.KommuneServiceInterface;
import com.example.regionkommune.region.model.Region;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class KommuneRestController {

    @Autowired
    private KommuneServiceInterface kommuneServiceInterface;

    @GetMapping("api/kommuner")
    public ResponseEntity<List<Kommune>> getKommunerFromApi(){

        List<Kommune> kommuneList = kommuneServiceInterface.getKommuner();
        return ResponseEntity.ok(kommuneList);
    }

    @GetMapping("/kommuner")
    public ResponseEntity<List<Kommune>> getKommunerFromDatabase() {
        List<Kommune> kommuneList = kommuneServiceInterface.getKommunerFromDatabase();
        return ResponseEntity.ok(kommuneList);
    }


    @GetMapping("/kommune/find/kode/{kode}")
    public ResponseEntity<Kommune> findKommuneAsEntityUsingKode(@PathVariable String kode){
        Kommune kommune = kommuneServiceInterface.findKommuneAsEntityUsingKode(kode);

        if (kode.isEmpty()){
            throw new EntityNotFoundException("No Kommune with desired name exists");
        } else {
            return ResponseEntity.ok(kommune);
        }
    }


    @PutMapping("/kommune/opdater/kode/{kode}")
    public ResponseEntity<String> updateKommuneWithKode(@PathVariable String kode, @RequestBody Kommune kommune) {
        Kommune foundKommune = kommuneServiceInterface.findKommuneAsEntityUsingKode(kode);

        if (foundKommune.getKode() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No kommune with the desired kode exists");
        } else {
            foundKommune.setKode(kommune.getKode());
            foundKommune.setNavn(kommune.getNavn());
            foundKommune.setHref(kommune.getHref());

            Kommune updatedKommune = kommuneServiceInterface.save(foundKommune);

            if (updatedKommune != null) {
                return ResponseEntity.ok("Kommune was successfully updated");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update the kommune");
            }
        }
    }

    @DeleteMapping("/kommune/slet/kode/{kode}")
    public ResponseEntity<String> deleteKommuneUsingKode(@PathVariable String kode) {
        Kommune foundKommune = kommuneServiceInterface.findKommuneAsEntityUsingKode(kode);

        if (foundKommune.getKode() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No kommune with the desired kode exists");
        } else {
            kommuneServiceInterface.deleteKommuneUsingKode(kode);
            return ResponseEntity.ok("Kommune successfully deleted");
        }
    }





    @PostMapping("/kommune/indsaet")
    public ResponseEntity<String> postKommune(@RequestBody Kommune kommune) {
        Kommune savedKommune = kommuneServiceInterface.save(kommune);
        if (savedKommune.getHref() == null || savedKommune.getNavn() == null || savedKommune.getKode() == null) {
            return ResponseEntity.ok("Missing required fields");
        } else {
            return ResponseEntity.ok("New kommune successfully saved");
        }
    }


}
