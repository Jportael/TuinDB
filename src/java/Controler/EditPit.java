/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import Bean.Pit;
import Bean.Plant;
import Bean.Snoei;
import Bean.Vermeerder;
import DAO.Dao;
import java.io.IOException;
import java.io.PrintWriter;
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
public class EditPit extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int plantId = Integer.parseInt(request.getParameter("plantId"));
        int pitId = Integer.parseInt(request.getParameter("pitId"));
        int subPitId = Integer.parseInt(request.getParameter("subPitId"));

        try {
            Dao.getDAO().deletePlantPit(plantId, pitId, subPitId);

            Cache.refresh();
        } catch (SQLException ex) {
            Logger.getLogger(EditPit.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Plant plant = Cache.getPlantById(plantId);
        request.setAttribute("plant", plant);

        try {
            //get vermeerders for plant
            ArrayList<Vermeerder> vermeerder = Dao.getDAO().plantVermeerd(plant);
            request.setAttribute("plantVermeerder", vermeerder);
            //get snoei for plant
            ArrayList<Snoei> snoei = Dao.getDAO().plantSnoei(plant);
            request.setAttribute("plantSnoei", snoei);

        } catch (SQLException ex) {
            Logger.getLogger(PlantDetails.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.setAttribute("plantId", plantId);
        request.setAttribute("snoei", Cache.snoei);
        request.setAttribute("vermeerder", Cache.vermeerder);
        request.getRequestDispatcher("WEB-INF/plant_details.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int plantId = Integer.parseInt(request.getParameter("plant_id"));
        int pitId = Integer.parseInt(request.getParameter("Category"));
        int subPitId = Integer.parseInt(request.getParameter("SubCat"));

        try {
            //schrijf naar DB
            Dao.getDAO().addPlantPit(plantId, pitId, subPitId);

            ArrayList<Pit> pit = Cache.getPlantById(plantId).getPit();
            pit.add(Categorie.getPlantPit(pitId, subPitId));

            Cache.getPlantById(plantId).setPit(pit);
        } catch (SQLException ex) {
            Logger.getLogger(EditPit.class.getName()).log(Level.SEVERE, null, ex);
        }

        Plant plant = Cache.getPlantById(plantId);
        request.setAttribute("plant", plant);

        try {
            //get vermeerders for plant
            ArrayList<Vermeerder> vermeerder = Dao.getDAO().plantVermeerd(plant);
            request.setAttribute("plantVermeerder", vermeerder);
            //get snoei for plant
            ArrayList<Snoei> snoei = Dao.getDAO().plantSnoei(plant);
            request.setAttribute("plantSnoei", snoei);

        } catch (SQLException ex) {
            Logger.getLogger(PlantDetails.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.setAttribute("plantId", plantId);
        request.setAttribute("snoei", Cache.snoei);
        request.setAttribute("vermeerder", Cache.vermeerder);
        request.getRequestDispatcher("WEB-INF/plant_details.jsp").forward(request, response);
    }

}
