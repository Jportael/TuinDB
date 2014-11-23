/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import Bean.Plant;
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

/**
 *
 * @author Jx3
 */
public class VerwijderFoto extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fotoLoc = request.getParameter("foto_loc");
        int plantId = Integer.parseInt(request.getParameter("plant_id"));
        request.setAttribute("plant_id", plantId);

        try {
            //update in DB
            Dao.getDAO().deletePicture(fotoLoc);

            //verwijder huidige plant uit de cache
            Plant plant = Cache.getPlantById(plantId);
            Cache.removePlantById(plantId);
            
            //verwijder de foto uit de plant
            ArrayList<String> fotos = plant.getFotos();
            fotos.remove(fotoLoc);

            //steek de geupdate plant terug in de cache
            plant.setFotos(fotos);
            Cache.planten.add(plant);
        } catch (SQLException ex) {
            Logger.getLogger(VerwijderFoto.class.getName()).log(Level.SEVERE, null, ex);
        }

        Plant plant = Cache.getPlantById(plantId);
        request.setAttribute("plant", plant);
        request.getRequestDispatcher("WEB-INF/edit_plant.jsp").forward(request, response);

    }

}
