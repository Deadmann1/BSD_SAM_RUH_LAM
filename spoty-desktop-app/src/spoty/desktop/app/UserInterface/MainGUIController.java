package spoty.desktop.app.UserInterface;

import java.awt.Toolkit;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import spoty.desktop.app.Database.AddressDatabase;
import spoty.desktop.app.Database.LocationDatabase;
import spoty.desktop.app.data.Address;
import spoty.desktop.app.data.City;
import spoty.desktop.app.data.Country;
import spoty.desktop.app.data.County;
import spoty.desktop.app.data.Location;
import spoty.desktop.app.data.LocationType;


public class MainGUIController {
    @FXML
    private AnchorPane paneMainGUI;

    @FXML
    private GridPane gridPaneInformation;

    @FXML
    private Label lblCity;

    @FXML
    private Label lblCountry;

    @FXML
    private Label lblCounty;

    @FXML
    private Label lblHousenumber;

    @FXML
    private Label lblLocationType;

    @FXML
    private Label lblName;

    @FXML
    private Label lblStreet;
    
    @FXML
    private Label lblPostalcode;
    
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnAddLocation;

    @FXML
    private Button btnUpdateLocation;
    
    @FXML
    private Button btnDeleteLocation;
    
    @FXML
    private Button btnRatings;
    
    @FXML
    private Button btnManageUsers;

    @FXML
    private ListView<Location> listViewLocations;
    
    @FXML
    private Label lblInfoMessageMainMenu;
    
    @FXML
    private ImageView imageViewLogo;


    @FXML
    void onAction_btnAddLocation(ActionEvent event) throws Exception {
        Parent rootframeTable = FXMLLoader.load(getClass().getResource("resources/AddLocationGUI.fxml"));
        Scene sceneTable = new Scene(rootframeTable);
        Stage stage = new Stage();
        stage.setScene(sceneTable);
        stage.setTitle("Location hinzufügen");
        stage.showAndWait();
        fillListViewLocations();
    }

    @FXML
    void onAction_btnUpdateLocation(ActionEvent event) throws Exception {
        if (listViewLocations.getSelectionModel().getSelectedItem()!=null)
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("resources/UpdateLocationGUI.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            UpdateLocationGUIController controller = fxmlLoader.<UpdateLocationGUIController>getController();
            controller.setIdLocation(listViewLocations.getSelectionModel().getSelectedItem().getIdLocation());
            controller.setIdAddress(listViewLocations.getSelectionModel().getSelectedItem().getIdAddress());
            Scene sceneTable = new Scene(root);
            Stage stage = new Stage();     
            stage.setScene(sceneTable);
            stage.setTitle("Location ändern");
            stage.setOnShown(new EventHandler<WindowEvent>() {
              public void handle(WindowEvent we) {
                 controller.fillCmbCity();
                 controller.fillCmbCounty();
                 controller.fillCmbCountry();
                 controller.fillCmbLocationType();
                 controller.fillUpdateLocationValues();
              }
            });  
            
            stage.showAndWait();
            fillListViewLocations();
        }
        
        else
            lblInfoMessageMainMenu.setText("Es muss ein Ort ausgewählt werden!");
        
    }
    
    
    @FXML
    void onAction_btnDeleteLocation(ActionEvent event) throws Exception {
        if (listViewLocations.getSelectionModel().getSelectedItem()!=null)
        {
            LocationDatabase.getInstance().deleteLocation(listViewLocations.getSelectionModel().getSelectedItem());
            fillListViewLocations();
        }
        
        lblInfoMessageMainMenu.setText("Es muss ein Ort ausgewählt werden!");
    }
    
    @FXML
    void onAction_btnManageUsers(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("resources/UserManagementGUI.fxml"));
             Parent root = (Parent) fxmlLoader.load();
             
            UserManagementGUIController controller = fxmlLoader.<UserManagementGUIController>getController();
            
             Scene sceneTable = new Scene(root);
             Stage stage = new Stage();     
             stage.setScene(sceneTable);
             stage.setTitle("User verwalten");
             stage.setOnShown(new EventHandler<WindowEvent>() {
               public void handle(WindowEvent we) {
                 controller.fillListViewUserAccounts();
               }
            });  
            
            stage.showAndWait();
    }

    @FXML
    void initialize() {
        assert btnAddLocation != null : "fx:id=\"btnAddLocation\" was not injected: check your FXML file 'MainGUI.fxml'.";
        assert btnUpdateLocation != null : "fx:id=\"btnUpdateLocation\" was not injected: check your FXML file 'MainGUI.fxml'.";
        assert listViewLocations != null : "fx:id=\"listViewLocations\" was not injected: check your FXML file 'MainGUI.fxml'.";
        
        //AddressDatabase.generateTestAddresses();
        //LocationDatabase.generateTestLocations();
        fillListViewLocations();
        gridPaneInformation.getStyleClass().add("gridPane");
        lblInfoMessageMainMenu.getStyleClass().add("labelInfo");
        paneMainGUI.getStyleClass().add("pane"); 
    }
    
    
    public void fillListViewLocations()
    {
        listViewLocations.setItems(FXCollections.observableArrayList(LocationDatabase.getInstance().getLocations()));
    }
    
    
    @FXML
    void onMouseClicked_ListViewLocations(MouseEvent event) {
        fillInformationData();
    }
    
    
    
    private void fillInformationData()
    {
        Location currentlySelectedLocation = listViewLocations.getSelectionModel().getSelectedItem();
         
        
        lblName.setText(currentlySelectedLocation.getLocationname());
        Address currentlySelectedLocationAddress = AddressDatabase.getInstance().getAddress(currentlySelectedLocation.getIdAddress());
       
        
        City city = AddressDatabase.getInstance().getCity(currentlySelectedLocationAddress.getIdCity());
        lblCity.setText(city.getCityname());
        lblPostalcode.setText(Integer.toString(city.getPostalcode()));
        County county = AddressDatabase.getInstance().getCounty(city.getIdCounty());
        lblCounty.setText(county.getCountyname());
        Country country = AddressDatabase.getInstance().getCountry(county.getIdCountry());
        lblCountry.setText(country.getCountryname());
        lblStreet.setText(currentlySelectedLocationAddress.getStreetname());
        lblHousenumber.setText(Integer.toString(currentlySelectedLocationAddress.getHousenumber()));
        LocationType type = LocationDatabase.getInstance().getLocationType(currentlySelectedLocation.getIdType());
        lblLocationType.setText(type.getLocationtypename());
    }
    
    
    
    @FXML
    void onAction_btnRatings(ActionEvent event) throws Exception
    {
        if (listViewLocations.getSelectionModel().getSelectedItem()!=null)
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("resources/RatingGUI.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            RatingGUIController controller = fxmlLoader.<RatingGUIController>getController();
            controller.setIdLocation(listViewLocations.getSelectionModel().getSelectedItem().getIdLocation());

            Scene sceneTable = new Scene(root);
            Stage stage = new Stage();     
            stage.setScene(sceneTable);
            stage.setTitle("Bewertungs-Statistik");
            stage.setOnShown(new EventHandler<WindowEvent>() {
              public void handle(WindowEvent we) {
                  try {
                      controller.fillRatings();
                  } catch (ParseException ex) {
                      Logger.getLogger(MainGUIController.class.getName()).log(Level.SEVERE, null, ex);
                  }
                 
              }
            });  

            stage.showAndWait();
            fillListViewLocations();
        }
        
        else
            lblInfoMessageMainMenu.setText("Es muss ein Ort ausgewählt werden!");
        
    }
}
