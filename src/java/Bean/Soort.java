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
public class Soort implements Serializable{
    int id;
    String naam;

    public Soort(int id, String naam) {
        this.id = id;
        this.naam = naam;
    }

    public Soort() {
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

    @Override
    public String toString() {
        return "Soort{" + "id=" + id + ", naam=" + naam + '}';
    }
    
    
}
