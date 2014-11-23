/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import Bean.Plant;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.Cache;

@WebServlet(name = "SearchPlant", urlPatterns = {"/search"})
public class SearchPlant extends HttpServlet {

    @Override

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ArrayList<String> zoekTermen = new ArrayList<>();
        for (Plant plant : Cache.planten) {
            zoekTermen.add(plant.toString());
        }
        request.setAttribute("zoektermen", zoekTermen);
        request.getRequestDispatcher("WEB-INF/search.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String search = request.getParameter("search_bar");
        ArrayList<Plant> planten = new ArrayList<>();

        //handler voor specifieke zoekterm (met id)
        //haal de ID uit de string        
        int length = search.length();
        //System.out.println("length: "+length);    
        int start = search.indexOf("plantID: ");
        //als e plantID: gevonden is
        if (start != -1) {
            //System.out.println("start: "+start);    
            int plantID = Integer.parseInt(search.substring((start + 9), length));
            //System.out.println("plantid= " + plantID);

            //haal deze plant op en verzend hem
            for (Plant plant : Cache.planten) {
                if (plant.getId() == plantID) {
                    planten.add(plant);
                    request.setAttribute("gevondenPlanten", planten);
                    request.getRequestDispatcher("WEB-INF/display_plant.jsp").forward(request, response);
                }

            }
        }
        //handler voor generieke zoekterm (zonder id)
        for (Plant plant : Cache.planten) {
            if (plant.toString().contains(search)) {
                planten.add(plant);

            }
        }
        try {
            request.setAttribute("gevondenPlanten", planten);
            request.getRequestDispatcher("WEB-INF/display_plant.jsp").forward(request, response);
        } catch (Exception e) {
            System.out.println("");;
        }
    }

}
