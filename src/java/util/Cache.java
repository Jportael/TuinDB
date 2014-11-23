/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import Bean.Familie;
import Bean.Groep;
import Bean.Plant;
import Bean.Soort;
import Bean.SoortBoom;
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

    public static void refresh() {
        //wipe cache
        familie = new ArrayList<>();
        groep = new ArrayList<>();
        soort = new ArrayList<>();
        planten = new ArrayList<>();
        soortBoom = new ArrayList<>();

        //get data from db
        try {
            Dao.getDAO().cacheLoader();
            planten = Dao.getDAO().getPlants();
            System.out.println("cache refreshed");
        } catch (SQLException ex) {
            System.out.println("fout in DB " + ex.toString());
        }

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
