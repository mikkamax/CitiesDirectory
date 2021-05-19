package com.mike.directory.model;

import java.util.Objects;

public class City {
    private String name;
    private String region;
    private String district;
    private int population;
    private String foundation;

    public City(String name, String region, String district, int population, String foundation) {
        this.name = name;
        this.region = region;
        this.district = district;
        this.population = population;
        this.foundation = foundation;
    }

    public String getName() {
        return name;
    }

    public String getRegion() {
        return region;
    }

    public String getDistrict() {
        return district;
    }

    public int getPopulation() {
        return population;
    }

    public String getFoundation() {
        return foundation;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("City{");
        sb.append("name='").append(name).append('\'');
        sb.append(", region='").append(region).append('\'');
        sb.append(", district='").append(district).append('\'');
        sb.append(", population=").append(population);
        sb.append(", foundation=").append(foundation);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return population == city.population && Objects.equals(name, city.name) && Objects.equals(region, city.region) && Objects.equals(district, city.district) && Objects.equals(foundation, city.foundation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, region, district, population, foundation);
    }
}
