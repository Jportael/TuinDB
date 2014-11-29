/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import Bean.Familie;
import Bean.Groep;
import Bean.Pit;
import Bean.Plant;
import Bean.Snoei;
import Bean.Soort;
import Bean.SoortBoom;
import Bean.Vermeerder;
import DAO.Dao;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Jx3
 */
public class Cache {

    public static ArrayList<Familie> familie = null;
    public static ArrayList<Groep> groep = null;
    public static ArrayList<Soort> soort = null;
    public static ArrayList<Plant> planten = null;
    public static ArrayList<SoortBoom> soortBoom = null;
    public static ArrayList<Vermeerder> vermeerder = null;
    public static ArrayList<Snoei> snoei = null;
    public static ArrayList<Pit> pit = null;

    public static void refresh() {
        //wipe cache
        familie = new ArrayList<>();
        groep = new ArrayList<>();
        soort = new ArrayList<>();
        planten = new ArrayList<>();
        soortBoom = new ArrayList<>();
        vermeerder = new ArrayList<>();
        snoei = new ArrayList<>();
        pit = new ArrayList<>();

        //get data from db
        try {
            Dao.getDAO().cacheLoader();
            planten = Dao.getDAO().getPlants();
            System.out.println("cache refreshed");
        } catch (SQLException ex) {
            System.out.println("fout in DB " + ex.toString());
        }
        
        //maak de plant bean een beetje niet reduntant door de pit erin te steken
        
        

    }

    public static Plant getPlantById(int id) {
        for (Plant plant : Cache.planten) {
            if (plant.getId() == id) {
                return plant;
            }
        }
        return null;

    }

    public static void removePlantById(int id) {
        Plant plant = getPlantById(id);
        planten.remove(plant);

    }
    
}
