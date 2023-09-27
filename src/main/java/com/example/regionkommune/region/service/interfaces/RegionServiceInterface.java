package com.example.regionkommune.region.service.interfaces;

import com.example.regionkommune.region.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegionServiceInterface {

    List<Region> getRegionerFromDatabase();

    Region findRegionAsEntityUsingKode(String kode);

    void deleteRegionUsingKode(String kode);

    Region save(Region region);
}
