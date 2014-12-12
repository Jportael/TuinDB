/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import Bean.Familie;
import Bean.Groep;
import Bean.Pit;
import Bean.Plant;
import Bean.Snoei;
import Bean.Soort;
import Bean.SoortBoom;
import Bean.Vermeerder;
import DAO.Dao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jx3
 */
public class Categorie {

    public static Soort getSoort(String naam) {
        if (naam == null) {
            System.out.println("soort naam nullvalue");
            return new Soort(1, "N/A");
        }
        boolean found = false;

        while (!found) {
            for (Soort soort : Cache.soort) {
                if (soort.getNaam() != null) {
                    if (soort.getNaam().equalsIgnoreCase(naam)) {
                        System.out.println("returning soort: " + soort.getNaam());
                        return soort;
                    }
                }
            }
            newSoort(naam);

        }
        System.out.println("returning soort nullvalue");
        return null;
    }

    public static Groep getGroep(String naam) {
        if (naam == null) {
            return new Groep(1, "N/A");
        }
        boolean found = false;

        while (!found) {
            for (Groep groep : Cache.groep) {
                if (groep.getNaam() != null) {
                    if (groep.getNaam().equalsIgnoreCase(naam)) {
                        return groep;
                    }
                }
            }
            newGroep(naam);

        }
        return null;
    }

    public static Familie getFamilie(String naam) {
        if (naam == null) {
            return new Familie(1, "N/A");
        }
        boolean found = false;

        while (!found) {
            for (Familie familie : Cache.familie) {
                if (familie.getNaam() != null) {
                    if (familie.getNaam().equalsIgnoreCase(naam)) {
                        return familie;
                    }
                }
            }
            newFamilie(naam);

        }
        return null;
    }

    public static SoortBoom getSoortBoom(String naam) {
        if (naam == null) {
            return new SoortBoom(1, "N/A");
        }
        boolean found = false;

        while (!found) {
            for (SoortBoom soortBoom : Cache.soortBoom) {
                if (soortBoom.getNaam() != null) {
                    if (soortBoom.getNaam().equalsIgnoreCase(naam)) {
                        return soortBoom;
                    }
                }
            }
            NewSoortBoom(naam);

        }
        return null;
    }

    public static Vermeerder getVermeerder(String naam) {
        boolean found = false;

        while (!found) {
            for (Vermeerder vermeerder : Cache.vermeerder) {

                if (vermeerder.getNaam().equalsIgnoreCase(naam)) {
                    return vermeerder;
                }
            }
            newVermeerder(naam);

        }
        return null;
    }

    public static Snoei getSnoei(String naam) {
        boolean found = false;

        while (!found) {
            for (Snoei snoei : Cache.snoei) {

                if (snoei.getNaam().equalsIgnoreCase(naam)) {
                    return snoei;
                }
            }
            newSnoei(naam);

        }
        return null;
    }

    private static void newSoort(String naam) {
        try {
            Soort soort = Dao.getDAO().addSoort(naam);
            Cache.soort.add(soort);
        } catch (SQLException ex) {
            Logger.getLogger(Categorie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void newGroep(String naam) {
        try {
            Groep groep = Dao.getDAO().addGroep(naam);
            Cache.groep.add(groep);
        } catch (SQLException ex) {
            Logger.getLogger(Categorie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void newFamilie(String naam) {
        try {
            Familie familie = Dao.getDAO().addfamilie(naam);
            Cache.familie.add(familie);
        } catch (SQLException ex) {
            Logger.getLogger(Categorie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void NewSoortBoom(String naam) {
        try {
            SoortBoom soortBoom = Dao.getDAO().addSoortBoom(naam);
            Cache.soortBoom.add(soortBoom);
        } catch (SQLException ex) {
            Logger.getLogger(Categorie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void newVermeerder(String naam) {
        try {
            Vermeerder vermeerder = Dao.getDAO().addVermeerder(naam);
            Cache.vermeerder.add(vermeerder);
        } catch (SQLException ex) {
            Logger.getLogger(Categorie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void newSnoei(String naam) {
        try {
            Snoei snoei = Dao.getDAO().addSnoei(naam);
            Cache.snoei.add(snoei);
        } catch (SQLException ex) {
            Logger.getLogger(Categorie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Pit getPlantPit(int pitId, int subPitId) {
        Pit pitFound = new Pit();

        for (Pit pit : Cache.pit) {
            if (pit.getId() == pitId) {
                //main pit
                pitFound.setId(pit.getId());
                pitFound.setNaam(pit.getNaam());
                
                for (Pit subPitFound : pit.getSubPit()) {
                    if (subPitFound.getId() == subPitId) {
                        ArrayList<Pit> subPitFoundArray = new ArrayList<>();
                        subPitFoundArray.add(subPitFound);
                        pitFound.setSubPit(subPitFoundArray);
                    }
                }

            }
        }

        return pitFound;
    }

}
