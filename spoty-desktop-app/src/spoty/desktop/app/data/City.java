/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spoty.desktop.app.data;

/**
 *
 * @author lampr
 */
public class City {
    private int idCity;
    private int postalcode;
    private String cityname;
    private int idCounty;

    public City(int idCity, int postalcode, String cityname, int idCounty) {
        this.idCity = idCity;
        this.postalcode = postalcode;
        this.cityname = cityname;
        this.idCounty = idCounty;
    }
    
    

    public int getIdCity() {
        return idCity;
    }

    public void setIdCity(int idCity) {
        this.idCity = idCity;
    }

    public int getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(int postalcode) {
        this.postalcode = postalcode;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public int getIdCounty() {
        return idCounty;
    }

    public void setIdCountry(int idCountry) {
        this.idCounty = idCounty;
    }

    @Override
    public String toString() {
        return cityname;
    }
    
    
}
