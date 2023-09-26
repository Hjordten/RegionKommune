package com.example.regionkommune.kommune.service.interfaces;

import com.example.regionkommune.kommune.model.Kommune;
import com.example.regionkommune.region.model.Region;

import java.util.List;

public interface KommuneServiceInterface {


    List<Kommune> getKommunerFromDatabase();

    Kommune save(Kommune kommune);

    Kommune findKommuneAsEntityUsingKode(String kode);

    void deleteKommuneUsingKode(String kode);

    Kommune findKommuneAsEntityUsingNavn(String navn);
}
