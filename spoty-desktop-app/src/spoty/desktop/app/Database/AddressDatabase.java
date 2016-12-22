/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spoty.desktop.app.Database;

import java.sql.Connection;

import java.util.Vector;

import spoty.desktop.app.Service.AddressService;

import spoty.desktop.app.data.Address;
import spoty.desktop.app.data.City;
import spoty.desktop.app.data.Country;
import spoty.desktop.app.data.County;



public class AddressDatabase {
	private static AddressDatabase db = null;
	private String connectionString = "";
	private String username = "";
	private String password = "";

	private Connection conn = null;
        
        public static Vector<Address> vecAddresses = new Vector<Address>();
        public static Vector<City> vecCities = new Vector<City>();
        public static Vector<County> vecCounties = new Vector<County>();
        public static Vector<Country> vecCountries = new Vector<Country>();
        

	private AddressDatabase() {
	}

	public static AddressDatabase getInstance() {
		if (db == null) {
			db = new AddressDatabase();
		}
		return db;
	}

        /*
	private Connection createConnection() throws SQLException {
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		return DriverManager.getConnection(connectionString, username, password);
	}*/
        
        public static void generateTestAddresses()
        {
            
            Address address1 = new Address(1, 1, "Big street", 11);
            Address address2 = new Address(2, 1, "Half street",43);
            Address address3 = new Address(3, 2, "Small street", 54);
            Address address4 = new Address(4, 2, "Small street", 23);
            Address address5 = new Address(5, 3, "Richman street", 4);
            
            City city1 = new City(1, 9620, "Villach", 1);
            City city2 = new City(2, 9630, "Hermagor", 1);
            City city3 = new City(3, 9700, "Spittal", 1);
            
            County county1 = new County(1, "Kärnten", 1);
            
            Country country1 = new Country(1, "Österreich");
            
            
            vecAddresses.add(address1);
            vecAddresses.add(address2);
            vecAddresses.add(address3);
            vecAddresses.add(address4);
            vecAddresses.add(address5);
            
            vecCities.add(city1);
            vecCities.add(city2);
            vecCities.add(city3);
            
            vecCounties.add(county1);
            vecCountries.add(country1);
        }
        
        public Vector<County> getCounties()
        {
            return AddressService.getInstance().getCounties();
        }
        
        public Vector<City> getCities()
        {
            return AddressService.getInstance().getCities();
        }
        
        public Vector<Country> getCountries()
        {
            return AddressService.getInstance().getCountries();
        }
        
        public Vector<Address> getAddresses()
        {
            return AddressService.getInstance().getAddresses();
        }
        
        public Address getAddress(int idAddress)
        {
            
            /*Address returnAddress = null;
            
            for (Address address : this.getAddresses())
            {
                if (address.getIdAddress() == idAddress)
                {
                    returnAddress = address;
                }
            }*/
            
            return AddressService.getInstance().getAddress(idAddress);
        }

        public int getNewAddressID() {
            int highestID = -1;
            
            for (Address a : this.getAddresses())
            {
                if (a.getIdAddress()>highestID)
                    highestID = a.getIdAddress();
            }
            
            return highestID+1;
        }
        
        public int getNewCountryID() {
            int highestID = -1;
            
            for (Country c : this.getCountries())
            {
                if (c.getIdCountry()>highestID)
                    highestID = c.getIdCountry();
            }
            
            return highestID+1;
        }
        
        public int getNewCountyID() {
        int highestID = -1;
            
            for (County c : this.getCounties())
            {
                if (c.getIdCounty()>highestID)
                    highestID = c.getIdCounty();
            }
            
            return highestID+1;
        }
        
        public int getNewCityID() {
        int highestID = -1;
            
            for (City c : this.getCities())
            {
                if (c.getIdCity()>highestID)
                    highestID = c.getIdCity();
            }
            
            return highestID+1;
        }
        
        

    public City getCity(int idCity) {
        
        return AddressService.getInstance().getCity(idCity);
        /*
        City returnCity = null;
        
        for (City c: this.getCities())
        {
            if (c.getIdCity() == idCity)
                returnCity = c;
        }
        
        return returnCity;*/
    }

    public County getCounty(int idCounty) {
        return AddressService.getInstance().getCounty(idCounty);
        
        
        /*
        County returnCounty = null;
        for (County c: this.getCounties())
        {
            if(c.getIdCounty() == idCounty)
                returnCounty = c;
        }
        
        return returnCounty;*/
    }

    public Country getCountry(int idCountry) {
        return AddressService.getInstance().getCountry(idCountry);
        
        /*
        Country returnCountry = null;
        for (Country c: this.getCountries())
        {
            if(c.getIdCountry() == idCountry)
                returnCountry = c;
        }
        
        return returnCountry;
*/
    }
    
    public Vector<County> getCountiesByCountry(int idCountry)
    {
        Vector<County> returnCounties = new Vector<County>();
        
        for (County c : this.getCounties())
        {
            if (c.getIdCountry() == idCountry)
                returnCounties.add(c);
        }
        
        return returnCounties;
    }
    
    public Vector<City> getCitiesByCounty(int idCounty)
    {
        Vector<City> returnCities= new Vector<City>();
        
        for (City c : this.getCities())
        {
            if (c.getIdCounty() == idCounty)
                returnCities.add(c);
        }
        
        return returnCities;
    }
    
     public Vector<County> getCountriesByCounty(int idCountry)
    {
        Vector<County> returnCounties= new Vector<County>();
        
        for (County c : this.getCounties())
        {
            if (c.getIdCountry()== idCountry)
                returnCounties.add(c);
        }
        
        return returnCounties;
    }
    

    public void addCountry(Country newCountry) {
        AddressService.getInstance().postCountry(newCountry);
        //this.getCountries().add(newCountry);
    }

    public void addCounty(County newCounty) {
        AddressService.getInstance().postCounty(newCounty);
        //this.getCounties().add(newCounty);
    }

    public void addCity(City newCity) {
        AddressService.getInstance().postCity(newCity);
        //this.getCities().add(newCity);
    }

    public void addAddress(Address newAddress) {
        AddressService.getInstance().postAddress(newAddress);
        //this.getAddresses().add(newAddress);
    }

    public void updateAddress(Address updateAddress)
    {
        AddressService.getInstance().putAddress(updateAddress);
        /*
        int index = -1;
        int i=0;
        for (Address a : this.getAddresses())
        {
            if (a.getIdAddress() == updateAddress.getIdAddress())
                index=i;
                
            i++;
        }
        
        this.getAddresses().setElementAt(updateAddress, index);*/
    }

    
}
