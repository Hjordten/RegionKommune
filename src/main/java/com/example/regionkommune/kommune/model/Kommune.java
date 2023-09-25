package com.example.regionkommune.kommune.model;

import com.example.regionkommune.region.model.Region;
import jakarta.persistence.*;


@Entity
public class Kommune {

    @Id
    @Column(length = 4)
    private String kode;
    private String navn;
    private String href;
    private String hrefPhoto;

    @ManyToOne
    @JoinColumn(name = "region", referencedColumnName = "kode")
    private Region region;

    public void setHrefPhoto(String hrefPhoto) {
        this.hrefPhoto = hrefPhoto;
    }

    public String getHrefPhoto() {
        return hrefPhoto;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Region getRegion() {
        return region;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getKode() {
        return kode;
    }

    public String getNavn() {
        return navn;
    }

    public String getHref() {
        return href;
    }
}
