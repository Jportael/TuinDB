package Bean;

import java.io.Serializable;

/**
 *
 * @author Jx3
 */
public class SoortBoom implements Serializable{
    int id;
    String naam;

    public SoortBoom(int id, String naam) {
        this.id = id;
        this.naam = naam;
    }

    public SoortBoom() {
    }
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    @Override
    public String toString() {
        return "SoortBoom{" + "id=" + id + ", naam=" + naam + '}';
    }
    
    
}