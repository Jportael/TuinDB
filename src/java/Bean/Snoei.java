/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import java.io.Serializable;

/**
 *
 * @author Jx3
 */
public class Snoei implements Serializable {

    int id;
    String naam;
    int maand;
    int plantId;
    
    public Snoei(int id, String naam) {
        this.id = id;
        this.naam = naam;
        this.maand = maand;
    }

    public Snoei(int id, String naam, int maand) {
        this.id = id;
        this.naam = naam;
        this.maand = maand;
    }

    public Snoei() {
    }

    public int getMaand() {
        return maand;
    }

    public void setMaand(int maand) {
        this.maand = maand;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public int getPlantId() {
        return plantId;
    }

    public void setPlantId(int plantId) {
        this.plantId = plantId;
    }

}
