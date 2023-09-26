package com.example.regionkommune.region.service.implementation;

import com.example.regionkommune.region.model.Region;
import com.example.regionkommune.region.repository.RegionRepository;
import com.example.regionkommune.region.service.interfaces.RegionServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
@Service
public class RegionImplementation implements RegionServiceInterface {

    @Autowired
    private RegionRepository regionRepository;

    @Override
    public Region save(Region region) {
        return regionRepository.save(region);
    }

    @Override
    public Region findRegionAsEntityUsingKode(String kode) {
        return regionRepository.findRegionAsEntityUsingKode(kode);
    }

    @Override
    public List<Region> getRegionerFromDatabase() {
        return regionRepository.findAll();
    }

    @Override
    public void deleteRegionUsingKode(String kode) {
        regionRepository.deleteById(kode);
    }


}
