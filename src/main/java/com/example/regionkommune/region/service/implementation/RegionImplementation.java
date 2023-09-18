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

    private final RestTemplate restTemplate;

    @Autowired
    private RegionRepository regionRepository;

    private RegionImplementation(RestTemplateBuilder restTemplateBuilder){
        this.restTemplate = restTemplateBuilder.build();
    }

    String regionUrl = "https://api.dataforsyningen.dk/regioner";


    @Override
    public List<Region> getRegioner() {
        List<Region> lst = new ArrayList<>();
        ResponseEntity<List<Region>> regionResponse =
                restTemplate.exchange(regionUrl,
                        HttpMethod.GET, null, new      ParameterizedTypeReference<List<Region>>(){
                        });
        List<Region> regioner = regionResponse.getBody();
        saveRegioner(regioner);
        return regioner;
    }

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

    private void saveRegioner(List<Region> regioner) {
        regioner.forEach(reg -> regionRepository.save(reg));
    }
}
