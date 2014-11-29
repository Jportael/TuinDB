/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Jx3
 */
public class Pit implements Serializable{
    int id;
    String naam;
    ArrayList<Pit> subPit = new ArrayList<>();

    public Pit() {
    }

    public Pit(int id, String naam) {
        this.id = id;
        this.naam = naam;
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

    public ArrayList<Pit> getSubPit() {
        return subPit;
    }

    public void setSubPit(ArrayList<Pit> subPit) {
        this.subPit = subPit;
    }
    
    
    
}
