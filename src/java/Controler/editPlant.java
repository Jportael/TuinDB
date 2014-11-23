/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import Bean.Plant;
import DAO.Dao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.Cache;
import util.Categorie;

/**
 *
 * @author Jx3
 */
public class editPlant extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int plantId = Integer.parseInt(request.getParameter("plant_id"));
        Plant plant = Cache.getPlantById(plantId);
        request.setAttribute("plant", plant);
        request.getRequestDispatcher("WEB-INF/edit_plant.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Plant plantToAdd = new Plant();

        plantToAdd.setId(Integer.parseInt(request.getParameter("plant_id")));

        plantToAdd.setNaam((String) request.getParameter("plant_naam"));
        plantToAdd.setNldsNaam((String) request.getParameter("plant_nlds_naam"));
        plantToAdd.setKleur((String) request.getParameter("plant_kleur"));
        String[] isActives = request.getParameterValues("is_active");
        System.out.println(plantToAdd.toString());

        if (isActives == null ||isActives.length ==0) {
            System.out.println("plant non active");
            plantToAdd.setIsActive(false);
        } else {
            System.out.println("plant active");
            plantToAdd.setIsActive(true);
        }

        plantToAdd.setSoort(Categorie.getSoort(request.getParameter("plant_soort")).getId());
        plantToAdd.setGroep(Categorie.getGroep(request.getParameter("plant_groep")).getId());
        plantToAdd.setFamilie(Categorie.getFamilie(request.getParameter("plant_familie")).getId());
        plantToAdd.setSoortBoom(Categorie.getSoortBoom(request.getParameter("plant_soort_boom")).getId());

        try {
            //update in DB
            Dao.getDAO().updatePlant(plantToAdd);

            //verwijder huidige plant uit de cache
            Plant plant = Cache.getPlantById(plantToAdd.getId());
            Cache.removePlantById(plant.getId());

            //steek de geupdate plant terug in de cache
            Cache.planten.add(plantToAdd);
        } catch (SQLException ex) {
            Logger.getLogger(editPlant.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<Plant> planten = new ArrayList<>();
        planten.add(plantToAdd);
        request.setAttribute("gevondenPlanten", planten );
        request.getRequestDispatcher("WEB-INF/display_plant.jsp").forward(request, response);

    }

}
