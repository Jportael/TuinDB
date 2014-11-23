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
public class Plant implements Serializable{

    int id;
    String naam;
    int soort;
    String soortNaam;
    int groep;
    String groepNaam;
    int familie;
    String familieNaam;
    String nldsNaam;
    String kleur;
    int isActive;
    ArrayList<String> fotos;

    public Plant() {
        naam = "";
        soort = 0;
        groep = 0;
        familie = 0;
        nldsNaam = "";
        kleur = "";
        isActive = 1;
        fotos = new ArrayList<>();
    }
    public boolean isActive(){
        if(isActive==1){
            return true;
        }else{
            return false;
        }
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

    public int getSoort() {
        return soort;
    }

    public void setSoort(int soort) {
        this.soort = soort;
    }

    public int getGroep() {
        return groep;
    }

    public void setGroep(int groep) {
        this.groep = groep;
    }

    public int getFamilie() {
        return familie;
    }

    public void setFamilie(int familie) {
        this.familie = familie;
    }

    public String getNldsNaam() {
        return nldsNaam;
    }

    public void setNldsNaam(String nldsNaam) {
        this.nldsNaam = nldsNaam;
    }

    public String getKleur() {
        return kleur;
    }

    public void setKleur(String kleur) {
        this.kleur = kleur;
    }

    public ArrayList<String> getFotos() {
        return fotos;
    }

    public void setFotos(ArrayList<String> fotos) {
        this.fotos = fotos;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }
    public void setIsActive(boolean isActive){
        if(isActive){
            this.isActive = 1;
        }else{
            this.isActive = 0; 
        }
    }

    public int getIsActive() {
        return isActive;
    }

    public String getSoortNaam() {
        return soortNaam;
    }

    public void setSoortNaam(String soortNaam) {
        this.soortNaam = soortNaam;
    }

    public String getGroepNaam() {
        return groepNaam;
    }

    public void setGroepNaam(String groepNaam) {
        this.groepNaam = groepNaam;
    }

    public String getFamilieNaam() {
        return familieNaam;
    }

    public void setFamilieNaam(String familieNaam) {
        this.familieNaam = familieNaam;
    }

    @Override
    public String toString() {
        String EMPTY = "N/A";
        
        if (soortNaam != null && soortNaam.equalsIgnoreCase("")) {
            soortNaam = EMPTY;
        }
        if (groepNaam != null && groepNaam.equalsIgnoreCase("")) {
            groepNaam = EMPTY;
        }
        if (familieNaam != null && familieNaam.equalsIgnoreCase("")) {
            familieNaam = EMPTY;
        }

        return naam + " (" + nldsNaam + ") |S: " + soortNaam + " |G: " + groepNaam + " |F: " + familieNaam + " |plantID: " + id;
    }

}
