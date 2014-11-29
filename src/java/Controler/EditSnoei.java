package Controler;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Controler.*;
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
import util.Categorie;

/**
 *
 * @author Jx3
 */
@WebServlet(name = "editSnoei", urlPatterns = {"/editsnoei"})
public class EditSnoei extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //delete snoei
        int plant_snoei_id = Integer.parseInt(request.getParameter("plant_snoei_id"));
        
        try {
            Dao.getDAO().deletePlantSnoei(plant_snoei_id);
        } catch (SQLException ex) {
            Logger.getLogger(EditSnoei.class.getName()).log(Level.SEVERE, null, ex);
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

        //nieuwe snoei
        Snoei snoei = new Snoei();
        snoei.setPlantId(Integer.parseInt(request.getParameter("plant_id")));
        snoei.setMaand(Integer.parseInt(request.getParameter("plant_snoei_maand")));
        snoei.setId(Categorie.getSnoei(request.getParameter("plant_snoei_naam")).getId());

        try {
            Dao.getDAO().addPlantSnoei(snoei);
        } catch (SQLException ex) {
            Logger.getLogger(EditSnoei.class.getName()).log(Level.SEVERE, null, ex);
        }

        //dispatcher
        int plantId = Integer.parseInt(request.getParameter("plant_id"));
        Plant plant = Cache.getPlantById(plantId);
        request.setAttribute("plant", plant);
        
        try {
            //get vermeerders for plant
            ArrayList<Vermeerder> vermeerder = Dao.getDAO().plantVermeerd(plant);
            request.setAttribute("plantVermeerder", vermeerder);
            //get snoei for plant
            ArrayList<Snoei> snoei1 = Dao.getDAO().plantSnoei(plant);
            request.setAttribute("plantSnoei", snoei1);
            
        } catch (SQLException ex) {
            Logger.getLogger(PlantDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("plantId", plantId);
        request.setAttribute("snoei", Cache.snoei);
        request.setAttribute("vermeerder", Cache.vermeerder);
        request.getRequestDispatcher("WEB-INF/plant_details.jsp").forward(request, response);

    }

}
