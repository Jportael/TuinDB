/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Bean.Familie;
import Bean.Soort;
import Bean.Groep;
import Bean.Plant;
import Bean.SoortBoom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import util.Cache;

/**
 *
 * @author Jx3
 */
public class Dao {

    // deze variabelen zijn hetzelfde voor elke instancie van deze klasse
    private static DataSource tuinDB = null;
    private static Context ctx = null;

    static {
        /*
         * deze methode zorgt ervoor dat de datasource van de SQL opgehaald
         * wordt out context.xml zodat andere aan de gegevens + credentials voor
         * de database kunnen
         */
        try {
            System.out.println("INFO: Context wordt geinitialiseerd");
            Context context = new InitialContext();
            ctx = (Context) context.lookup("java:comp/env");

            System.out.println("INFO: datasource wordt opgezocht");
            tuinDB = (DataSource) ctx.lookup("jdbc/tuin");
            System.out.println("INFO: datasource gevonden: "
                    + tuinDB.toString());

        } catch (Exception e) {
            System.out
                    .println("ERROR: het initialiseren van de Datasource is gefaald");
            e.printStackTrace();
        }
    }

    public static Dao getDAO() {
        return new Dao();
    }

    protected void GetSQLConnection() {

        try {
            // open een connectie van de DataSource
            Connection conn = tuinDB.getConnection();
        } catch (Exception ex) {
            System.out
                    .println("ERROR: Het leggen van een connectie met de Database is mislukt");
        }
    }

    public void addPlant(Plant plantToAdd) throws SQLException {
        String addPlant = "INSERT INTO `plant`\n"
                + "(`naam`,\n"
                + "`soort_id`,\n"
                + "`groep_id`,\n"
                + "`familie_id`,\n"
                + "`ndls_naam`,\n"
                + "`kleur`, soort_boom_id)\n"
                + "VALUES\n"
                + "(?,?,?,?,?,?,?)";
        try (Connection conn = tuinDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(addPlant)) {

            stmt.setString(1, plantToAdd.getNaam());
            stmt.setInt(2, plantToAdd.getSoort());
            stmt.setInt(3, plantToAdd.getGroep());
            stmt.setInt(4, plantToAdd.getFamilie());
            stmt.setString(5, plantToAdd.getNldsNaam());
            stmt.setString(6, plantToAdd.getKleur());
            stmt.setInt(7, plantToAdd.getSoortBoom());

            stmt.execute();
        }
    }

    public void updatePlant(Plant plant) throws SQLException {
        String addPlant = "UPDATE `plant`\n"
                + "SET\n"
                + "`naam` = ?,\n"
                + "`soort_id` = ?,\n"
                + "`groep_id` = ?,\n"
                + "`familie_id` = ?,\n"
                + "`ndls_naam` = ?,\n"
                + "`kleur` = ?,\n"
                + "`isActive` = ?,\n"
                + "soort_boom_id = ?\n"
                + "WHERE `id` = ?;";
        try (Connection conn = tuinDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(addPlant)) {

            stmt.setString(1, plant.getNaam());
            stmt.setInt(2, plant.getSoort());
            stmt.setInt(3, plant.getGroep());
            stmt.setInt(4, plant.getFamilie());
            stmt.setString(5, plant.getNldsNaam());
            stmt.setString(6, plant.getKleur());
            stmt.setInt(7, plant.getIsActive());
            stmt.setInt(8, plant.getSoortBoom());
            stmt.setInt(9, plant.getId());

            System.out.println(stmt.toString());
            stmt.executeUpdate();

        }
    }

    public Plant getPlant(Plant plant) throws SQLException {
        String addPlant = "select id,naam,soort_id,groep_id,familie_id,ndls_naam,kleur,isActive from plant "
                + "where naam=? "
                + "and soort_id=? "
                + "and groep_id=? "
                + "and familie_id=?; ";
        try (Connection conn = tuinDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(addPlant)) {

            stmt.setString(1, plant.getNaam());
            stmt.setInt(2, plant.getSoort());
            stmt.setInt(3, plant.getGroep());
            stmt.setInt(4, plant.getFamilie());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                plant.setId(rs.getInt(1));
                plant.setNaam(rs.getString(2));
                plant.setSoort(rs.getInt(3));
                plant.setGroep(rs.getInt(4));
                plant.setFamilie(rs.getInt(5));
                plant.setNldsNaam(rs.getString(6));
                plant.setKleur(rs.getString(7));
                plant.setIsActive(rs.getInt(8));
            }
            return plant;
        }
    }

    public ArrayList<Plant> getPlants() throws SQLException {
        String addPlant = "select p.id,p.naam,p.soort_id,p.groep_id,p.familie_id,p.ndls_naam,p.kleur,p.isActive,s.naam,g.naam,f.naam,p.isActive,sb.naam,p.soort_boom_id \n"
                + "from plant p,soort s,groep g, familie f, soort_boom sb\n"
                + "where p.soort_id = s.id\n"
                + "and p.groep_id = g.id\n"
                + "and p.familie_id = f.id\n"
                + "and p.soort_boom_id = sb.id;";
        try (Connection conn = tuinDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(addPlant)) {

            ArrayList<Plant> planten = new ArrayList<>();

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Plant plant = new Plant();
                plant.setId(rs.getInt(1));
                plant.setNaam(rs.getString(2));
                plant.setSoort(rs.getInt(3));
                plant.setGroep(rs.getInt(4));
                plant.setFamilie(rs.getInt(5));
                plant.setNldsNaam(rs.getString(6));
                plant.setKleur(rs.getString(7));
                plant.setIsActive(rs.getInt(8));
                plant.setSoortNaam(rs.getString(9));
                plant.setGroepNaam(rs.getString(10));
                plant.setFamilieNaam(rs.getString(11));
                plant.setIsActive(rs.getInt(12));
                plant.setFotos(getPictures(plant));
                plant.setSoortBoomNaam(rs.getString(13));
                plant.setSoortBoom(rs.getInt(14));
                planten.add(plant);
            }

            return planten;
        }
    }

    public void addPicture(Plant plant) throws SQLException {
        String add = "insert into foto(loc,plant_id) values(?,?);";
        try (Connection conn = tuinDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(add)) {
            System.out.println("aantal fotos: " + plant.getFotos().size());
            for (int i = 0; i < plant.getFotos().size(); i++) {
                System.out.println(plant.getFotos().get(i));
                stmt.setString(1, plant.getFotos().get(i));
                stmt.setInt(2, plant.getId());

                stmt.execute();
                System.out.println("foto toegevoegd");
            }

        }
    }

    public void deletePicture(String loc) throws SQLException {
        String add = "delete from foto where loc=?";
        try (Connection conn = tuinDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(add)) {
            stmt.setString(1, loc);
            stmt.execute();
        }
    }

    public ArrayList<String> getPictures(Plant plant) throws SQLException {
        String add = "select loc from foto where plant_id=?";
        try (Connection conn = tuinDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(add)) {

            stmt.setInt(1, plant.getId());

            ResultSet rs = stmt.executeQuery();
            ArrayList<String> fotos = new ArrayList<>();
            while (rs.next()) {
                fotos.add(rs.getString(1));
            }

            return fotos;
        }
    }

    public Soort addSoort(String naam) throws SQLException {
        String add = "insert into soort(naam) values(?);";
        String getNew = "select id,naam from soort where naam=?;";

        try (Connection conn = tuinDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(add);
                PreparedStatement stmt2 = conn.prepareStatement(getNew);) {
            //voeg nieuwe soort toe
            stmt.setString(1, naam);
            stmt.execute();

            //haal de gegevens van de soort op
            stmt2.setString(1, naam);
            ResultSet rs = stmt2.executeQuery();

            if (rs.next()) {
                return new Soort(rs.getInt(1), rs.getString(2));
            }
            return null;
        }
    }

    public Groep addGroep(String naam) throws SQLException {
        String add = "insert into groep(naam) values(?);";
        String getNew = "select id,naam from groep where naam=?;";

        try (Connection conn = tuinDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(add);
                PreparedStatement stmt2 = conn.prepareStatement(getNew);) {
            //voeg nieuwe soort toe
            stmt.setString(1, naam);
            stmt.execute();

            //haal de gegevens van de soort op
            stmt2.setString(1, naam);
            ResultSet rs = stmt2.executeQuery();
            if (rs.next()) {
                return new Groep(rs.getInt(1), rs.getString(2));
            }
            return null;
        }
    }

    public Familie addfamilie(String naam) throws SQLException {
        String add = "insert into familie(naam) values(?);";
        String getNew = "select id,naam from familie where naam=?;";

        try (Connection conn = tuinDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(add);
                PreparedStatement stmt2 = conn.prepareStatement(getNew);) {
            //voeg nieuwe soort toe
            stmt.setString(1, naam);
            stmt.execute();

            //haal de gegevens van de soort op
            stmt2.setString(1, naam);
            ResultSet rs = stmt2.executeQuery();

            if (rs.next()) {
                return new Familie(rs.getInt(1), rs.getString(2));
            }
            return null;
        }
    }
    
        public SoortBoom addSoortBoom(String naam) throws SQLException {
        String add = "insert into soort_boom(naam) values(?);";
        String getNew = "select id,naam from soort_boom where naam=?;";

        try (Connection conn = tuinDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(add);
                PreparedStatement stmt2 = conn.prepareStatement(getNew);) {
            //voeg nieuwe soort toe
            stmt.setString(1, naam);
            stmt.execute();

            //haal de gegevens van de soort op
            stmt2.setString(1, naam);
            ResultSet rs = stmt2.executeQuery();

            if (rs.next()) {
                return new SoortBoom(rs.getInt(1), rs.getString(2));
            }
            return null;
        }
    }

    public void cacheLoader() throws SQLException {
        String soortQuery = "select s.id, s.naam  from soort s";

        String groepQuery = "select g.id, g.naam  from groep g";

        String familieQuery = "select f.id, f.naam  from familie f";

        String soortBoomQuery = "select sb.id, sb.naam from soort_boom sb";

        try (Connection conn = tuinDB.getConnection();
                PreparedStatement stmt = conn.prepareStatement(soortQuery);
                PreparedStatement stmt2 = conn.prepareStatement(groepQuery);
                PreparedStatement stmt3 = conn.prepareStatement(familieQuery);
                PreparedStatement stmt4 = conn.prepareStatement(soortBoomQuery);) {

            /* soort */
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Soort soortBean = new Soort(rs.getInt(1), rs.getString(2));
                Cache.soort.add(soortBean);
            }

            /*groep*/
            rs = stmt2.executeQuery();
            while (rs.next()) {
                Groep groepBean = new Groep(rs.getInt(1), rs.getString(2));
                Cache.groep.add(groepBean);
            }

            /*familie*/
            rs = stmt3.executeQuery();
            while (rs.next()) {
                Familie familieBean = new Familie(rs.getInt(1), rs.getString(2));
                Cache.familie.add(familieBean);
            }

            rs = stmt4.executeQuery();
            while (rs.next()) {
                SoortBoom soortBoom = new SoortBoom(rs.getInt(1), rs.getString(2));
                Cache.soortBoom.add(soortBoom);
            }
        }

    }

}
