package com.example.regionkommune.kommune.service.implementation;

import com.example.regionkommune.kommune.model.Kommune;
import com.example.regionkommune.kommune.repository.KommuneRepository;
import com.example.regionkommune.kommune.service.interfaces.KommuneServiceInterface;
import com.example.regionkommune.region.model.Region;
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
public class KommuneImplementation implements KommuneServiceInterface {

    private final RestTemplate restTemplate;

    @Autowired
    private KommuneRepository kommuneRepository;

    private KommuneImplementation(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    String kommuneUrl = "https://api.dataforsyningen.dk/kommuner";

    @Override
    public List<Kommune> getKommuner() {
        List<Kommune> kommuneList = new ArrayList<>();
        ResponseEntity<List<Kommune>> kommuneResponse =
                restTemplate.exchange(kommuneUrl, HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<Kommune>>() {
                        });
        List<Kommune> kommuner = kommuneResponse.getBody();
        saveKommuner(kommuner);
        return kommuner;
    }

    private void saveKommuner(List<Kommune> kommuner) {
        kommuner.forEach(kommuneRepository::save);
    }

    @Override
    public List<Kommune> getKommunerFromDatabase() {
        return kommuneRepository.findAll();
    }

    @Override
    public Kommune save(Kommune kommune) {
        return kommuneRepository.save(kommune);
    }


    @Override
    public Kommune findKommuneAsEntityUsingKode(String kode) {
        return kommuneRepository.findKommuneAsEntityUsingKode(kode);
    }

    @Override
    public void deleteKommuneUsingKode(String kode) {
        kommuneRepository.deleteById(kode);
    }

    @Override
    public Kommune findKommuneAsEntityUsingNavn(String navn) {
        return kommuneRepository.findKommuneAsEntityUsingNavn(navn);
    }



}
