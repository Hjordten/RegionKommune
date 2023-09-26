package com.example.regionkommune.config;

import com.example.regionkommune.kommune.model.Kommune;
import com.example.regionkommune.kommune.service.interfaces.KommuneServiceInterface;
import com.example.regionkommune.region.model.Region;
import com.example.regionkommune.region.service.interfaces.RegionServiceInterface;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataSyncService {
    @Autowired
    private KommuneServiceInterface kommuneService;

    @Autowired
    private RegionServiceInterface regionService;

    @PostConstruct
    public List<Region> synchronizeDataOnStartupRegioner() {
        // This method will be executed once when the Spring application starts.
         return regionService.getRegioner();
    }

    @PostConstruct
    public List<Kommune> synchronizeDataOnStartupKommuner() {
        // This method will be executed once when the Spring application starts.
        return kommuneService.getKommuner();
    }
}


