package src.providers;

public class Asteroids {
    private Integer id = 0;
    private Float km_to_earth = 0f;
    private Float km_diameter = 0f;
    private Boolean hazardous_asteroid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getKm_to_earth() {
        return km_to_earth;
    }

    public void setKm_to_earth(Float km_to_earth) {
        this.km_to_earth = km_to_earth;
    }

    public Float getKm_diameter() {
        return km_diameter;
    }

    public void setKm_diameter(Float km_diameter) {
        this.km_diameter = km_diameter;
    }

    public Boolean getHazardous_asteroid() {
        return hazardous_asteroid;
    }

    public void setHazardous_asteroid(Boolean hazardous_asteroid) {
        this.hazardous_asteroid = hazardous_asteroid;
    }

    public Asteroids(Integer id, Float km_to_earth, Float km_diameter, Boolean hazardous_asteroid) {
        this.id = id;
        this.km_to_earth = km_to_earth;
        this.km_diameter = km_diameter;
        this.hazardous_asteroid = hazardous_asteroid;
    }

    @Override
    public String toString() {
        return  "Asteroid id = '" + id + "\'\n" +
                String.format("%.0f", km_to_earth) + " km from Earth \n" +
                String.format("%.2f", km_diameter) + " km diameter \n" +
                "Is it hazardous: " + hazardous_asteroid + '\n';
    }
}
// 2020-03-01