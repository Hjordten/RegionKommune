package com.example.regionkommune.region.service.interfaces;

import com.example.regionkommune.region.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegionServiceInterface {
    List<Region> getRegioner();

    Region save(Region region);

    Region findRegionAsEntityUsingKode(String kode);

    List<Region> getRegionerFromDatabase();
}
