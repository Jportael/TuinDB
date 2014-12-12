/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

import Bean.Pit;
import Bean.Plant;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.Cache;

@WebServlet(name = "index", urlPatterns = {"/index"})
public class Index extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cache.refresh();
        
        
        //autocomplete categorien
        request.getSession().setAttribute("soorten", Cache.soort);
        request.getSession().setAttribute("groepen", Cache.groep);
        request.getSession().setAttribute("families", Cache.familie);
        request.getSession().setAttribute("soort_bomen", Cache.soortBoom);
        request.getSession().setAttribute("snoei", Cache.snoei);
        request.getSession().setAttribute("vermeerder", Cache.vermeerder);
        request.getSession().setAttribute("pit", Cache.pit);

        //autocomplete search
        ArrayList<String> zoekTermen = new ArrayList<>();
        for (Plant plant : Cache.planten) {
            zoekTermen.add(plant.toString());
        }
        
        //statistieken
        request.setAttribute("soortStatistic", Cache.soort.size());
        request.setAttribute("groepStatistic", Cache.groep.size());
        request.setAttribute("familieStatistic", Cache.familie.size());
        request.setAttribute("plantStatistics", Cache.planten.size());

        request.setAttribute("zoektermen", zoekTermen);

        request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
