/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import Bean.Plant;

import DAO.Dao;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import util.Cache;
import util.Categorie;

/**
 *
 * @author Jx3
 */
@WebServlet(name = "AddPlant", urlPatterns = {"/addplant"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, //10MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class AddPlant extends HttpServlet {

    private static final String SAVE_DIR = "uploadFiles";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/new_plant.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Plant plantToAdd = new Plant();

        plantToAdd.setNaam((String) request.getParameter("plant_naam"));
        plantToAdd.setNldsNaam((String) request.getParameter("plant_nlds_naam"));
        plantToAdd.setKleur((String) request.getParameter("plant_kleur"));

        plantToAdd.setSoort(Categorie.getSoort(request.getParameter("plant_soort")).getId());
        plantToAdd.setGroep(Categorie.getGroep(request.getParameter("plant_groep")).getId());
        plantToAdd.setFamilie(Categorie.getFamilie(request.getParameter("plant_familie")).getId());

        try {
            Dao.getDAO().addPlant(plantToAdd);

        } catch (SQLException ex) {
            System.out.println("fout in de DB " + ex.toString());
        }

        System.out.println("plant toegevoegd");

        try {
            //check de ID van de plant
            plantToAdd = Dao.getDAO().getPlant(plantToAdd);
        } catch (SQLException ex) {
            Logger.getLogger(AddPlant.class.getName()).log(Level.SEVERE, null, ex);
        }

        // gets absolute path of the web application
        String appPath = request.getServletContext().getRealPath("");
        // constructs path of the directory to save uploaded file
        String savePath = appPath + File.separator + SAVE_DIR;
        //get current date
        DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        Date date = new Date();

        // creates the save directory if it does not exists
        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }

        //array to hold picture names
        ArrayList<String> fotoNamen = plantToAdd.getFotos();
        for (Part part : request.getParts()) {
            String fileName = extractFileName(part);
            if (!fileName.equalsIgnoreCase("")) {
                String datumTijd = dateFormat.format(date);
                fileName = datumTijd + fileName;
                System.out.println("filename: " + fileName);
                System.out.println("savepath: " + savePath);
                part.write(savePath + File.separator + fileName);
                fotoNamen.add(fileName);
            }
        }
        plantToAdd.setFotos(fotoNamen);
        System.out.println("foto('s) geupload");

        try {
            //upload de bestandspaden naar de DB
            Dao.getDAO().addPicture(plantToAdd);
        } catch (SQLException ex) {
            Logger.getLogger(AddPlant.class.getName()).log(Level.SEVERE, null, ex);
        }
        Cache.refresh();
        
        //autocomplete search
        ArrayList<String> zoekTermen = new ArrayList<>();
        for (Plant plant : Cache.planten) {
            zoekTermen.add(plant.toString());
        }
        request.setAttribute("zoektermen", zoekTermen);
        
        request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
    }

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }

}
