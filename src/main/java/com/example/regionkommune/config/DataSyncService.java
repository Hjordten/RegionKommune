package com.example.regionkommune.config;

import com.example.regionkommune.kommune.model.Kommune;
import com.example.regionkommune.kommune.repository.KommuneRepository;
import com.example.regionkommune.kommune.service.interfaces.KommuneServiceInterface;
import com.example.regionkommune.region.model.Region;
import com.example.regionkommune.region.repository.RegionRepository;
import com.example.regionkommune.region.service.interfaces.RegionServiceInterface;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataSyncService {


    /*------------------------------------------Instances-------------------------------------------------------------*/
    private final RestTemplate restTemplate;

    @Autowired
    private KommuneRepository kommuneRepository;

    @Autowired
    private RegionRepository regionRepository;

    private DataSyncService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    /*------------------------------------------Post Construct--------------------------------------------------------*/
    @Async
    @PostConstruct
    public void synchronizeDataOnStartupRegioner() {
        String regionUrl = "https://api.dataforsyningen.dk/regioner";
        List<Region> lst = new ArrayList<>();
        ResponseEntity<List<Region>> regionResponse =
                restTemplate.exchange(regionUrl,
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Region>>() {
                        });
        List<Region> regioner = regionResponse.getBody();
        saveRegioner(regioner);
    }

    @Async
    @PostConstruct
    public void synchronizeDataOnStartupKommuner() {
        String kommuneUrl = "https://api.dataforsyningen.dk/kommuner";
        List<Kommune> kommuneList = new ArrayList<>();
        ResponseEntity<List<Kommune>> kommuneResponse =
                restTemplate.exchange(kommuneUrl, HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<Kommune>>() {
                        });
        List<Kommune> kommuner = kommuneResponse.getBody();
        saveKommuner(kommuner);

    }

    /*------------------------------------------Saves fetched data to database----------------------------------------*/

    private void saveRegioner(List<Region> regioner) {
        regioner.forEach(reg -> regionRepository.save(reg));
    }

    private void saveKommuner(List<Kommune> kommuner) {
        kommuner.forEach(kommuneRepository::save);
    }
}


