package org.example.model;

public class Team {
    private int id;
    private String name;
    private String region;
    private double rating;
    private String logoUrl;

    public Team() {}

    public Team(int id, String name, String region, double rating, String logoUrl) {
        this.id = id;
        this.name = name;
        this.region = region;
        this.rating = rating;
        this.logoUrl = logoUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }
}
