/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import Bean.Plant;
import Bean.Snoei;
import Bean.Vermeerder;
import DAO.Dao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.Cache;

/**
 *
 * @author Jx3
 */
@WebServlet(name = "plantDetails", urlPatterns = {"/plantdetails"})
public class PlantDetails extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int plantId = Integer.parseInt(request.getParameter("plant_id"));
        Plant plant = Cache.getPlantById(plantId);

        try {
            //get vermeerders for plant
            ArrayList<Vermeerder> vermeerder = Dao.getDAO().plantVermeerd(plant);
            request.setAttribute("plantVermeerder", vermeerder);
            //get snoei for plant
            ArrayList<Snoei> snoei = Dao.getDAO().plantSnoei(plant);
            request.setAttribute("plantSnoei", snoei);
            //plantpits
            plant.setPit(new ArrayList<>());
            plant.setPit(Dao.getDAO().getPlantPit(plant));
            
        } catch (SQLException ex) {
            Logger.getLogger(PlantDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(plant.getPit().toString());
        request.setAttribute("plant", plant);
        request.setAttribute("plantId", plantId);
        request.setAttribute("snoei", Cache.snoei);
        request.setAttribute("vermeerder", Cache.vermeerder);
        request.getRequestDispatcher("WEB-INF/plant_details.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
