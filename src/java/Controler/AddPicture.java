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
import java.io.PrintWriter;
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

/**
 *
 * @author Jx3
 */
@WebServlet(name = "AddPicture", urlPatterns = {"/addpicture"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, //10MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class AddPicture extends HttpServlet {

    private static final String SAVE_DIR = "uploadFiles";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int plantID = Integer.parseInt(request.getParameter("plant_id"));
        Plant plant = Cache.getPlantById(plantID);

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
        Plant plantNewPictures = plant;
        ArrayList<String> newFotoNamen = new ArrayList<>();
        ArrayList<String> fotoNamen = plant.getFotos();
        

        for (Part part : request.getParts()) {
            String fileName = extractFileName(part);
            if (!fileName.equalsIgnoreCase("")) {
                String datumTijd = dateFormat.format(date);
                fileName = datumTijd + fileName;
                System.out.println("filename: " + fileName);
                System.out.println("savepath: " + savePath);
                part.write(savePath + File.separator + fileName);
                fotoNamen.add(fileName);
                newFotoNamen.add(fileName);
                //Cache.getPlantById(plantID).getFotos().add(fileName);
            }
        }
        //zit maar 1 foto in
        plantNewPictures.setFotos(newFotoNamen);
        
        
        try {
            //upload de bestandspaden naar de DB
            Dao.getDAO().addPicture(plantNewPictures);
            System.out.println("foto('s) geupload");
        } catch (SQLException ex) {
            Logger.getLogger(AddPlant.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        plant.setFotos(fotoNamen);
        request.setAttribute("plant", plant);
        request.getRequestDispatcher("WEB-INF/edit_plant.jsp").forward(request, response);
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
