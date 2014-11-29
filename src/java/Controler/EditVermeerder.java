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
import java.io.PrintWriter;
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
import util.Categorie;

/**
 *
 * @author Jx3
 */
@WebServlet(name = "editVermeerder", urlPatterns = {"/editvermeerder"})
public class EditVermeerder extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int plant_vermeerder_id = Integer.parseInt(request.getParameter("plant_vermeerder_id"));
        
        try {
            Dao.getDAO().deletePlantVermeerder(plant_vermeerder_id);
        } catch (SQLException ex) {
            Logger.getLogger(EditVermeerder.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        int plant_id = Integer.parseInt(request.getParameter("plant_id"));
        
        Plant plant = Cache.getPlantById(plant_id);
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
        request.setAttribute("plantId", plant_id);
        request.setAttribute("snoei", Cache.snoei);
        request.setAttribute("vermeerder", Cache.vermeerder);
        request.getRequestDispatcher("WEB-INF/plant_details.jsp").forward(request, response);
        

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Vermeerder vermeerder = new Vermeerder();
        vermeerder.setPlantId(Integer.parseInt(request.getParameter("plant_id")));
        vermeerder.setMaand(Integer.parseInt(request.getParameter("plant_vermeerder_maand")));
        vermeerder.setId(Categorie.getVermeerder(request.getParameter("plant_vermeerder_naam")).getId());

        try {
            Dao.getDAO().addPlantVermeerder(vermeerder);
        } catch (SQLException ex) {
            Logger.getLogger(EditVermeerder.class.getName()).log(Level.SEVERE, null, ex);
        }

        //dispatcher
        Plant plant = Cache.getPlantById(vermeerder.getPlantId());
        request.setAttribute("plant", plant);

        try {
            //get vermeerders for plant
            ArrayList<Vermeerder> vermeerder2 = Dao.getDAO().plantVermeerd(plant);
            request.setAttribute("plantVermeerder", vermeerder2);
            //get snoei for plant
            ArrayList<Snoei> snoei = Dao.getDAO().plantSnoei(plant);
            request.setAttribute("plantSnoei", snoei);

        } catch (SQLException ex) {
            Logger.getLogger(PlantDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("plantId", vermeerder.getPlantId());
        request.setAttribute("snoei", Cache.snoei);
        request.setAttribute("vermeerder", Cache.vermeerder);
        request.getRequestDispatcher("WEB-INF/plant_details.jsp").forward(request, response);

    }

}
