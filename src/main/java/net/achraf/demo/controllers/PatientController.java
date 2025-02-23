package net.achraf.demo.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import net.achraf.demo.dao.impl.ConsultationDaoImp;
import net.achraf.demo.dao.impl.PatientDaoImp;
import net.achraf.demo.entities.Patient;
import net.achraf.demo.service.CabinetService;
import net.achraf.demo.service.ICabinetService;

import javafx.scene.control.TableColumn;
import java.net.URL;
import java.util.ResourceBundle;

public class PatientController implements Initializable {
    @FXML private TextField textFieldNom;
    @FXML private TextField textFieldPrenom;
    @FXML private TextField textFieldTel;
    @FXML private TextField textFieldEmail;
    @FXML private TextField textFieldSearch;
    @FXML private TableView<Patient> tablePatients;
    @FXML private TableColumn<Patient,Long> columnId;
    @FXML private TableColumn<Patient,Long> columnNom;
    @FXML private TableColumn<Patient,Long> columnPrenom;
    @FXML private TableColumn<Patient,Long> columnTel;
    @FXML private TableColumn<Patient,Long> columnEmail;
    private ICabinetService cabinetService;
    private ObservableList<Patient> patients = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cabinetService = new CabinetService(new PatientDaoImp(), new ConsultationDaoImp());
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        columnPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        columnTel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        loadPatients();
        tablePatients.setItems(patients);

        tablePatients.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                loadSelectedPatient();
            }
        });

        textFieldSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("old Value :" +oldValue +" new Value :" +newValue);
            try{
                patients.setAll(cabinetService.searchPatientsByQuery(newValue));

            }catch (Exception e){
                e.printStackTrace();
            }
        });

    }

    public void addPatient(){
        Patient patient = new Patient();
        patient.setNom(textFieldNom.getText());
        patient.setPrenom(textFieldPrenom.getText());
        patient.setTel(textFieldTel.getText());
        patient.setEmail(textFieldEmail.getText());

        cabinetService.addPatient(patient);
        loadPatients();
        textFieldNom.setText("");
        textFieldPrenom.setText("");
        textFieldTel.setText("");
        textFieldEmail.setText("");
    }

    public void deletePatient(){
        Patient patient =tablePatients.getSelectionModel().getSelectedItem();
        cabinetService.deletePatient(patient);
        loadPatients();
        textFieldNom.setText("");
        textFieldPrenom.setText("");
        textFieldTel.setText("");
        textFieldEmail.setText("");
    }

    public void loadSelectedPatient(){
        Patient patient =tablePatients.getSelectionModel().getSelectedItem();
       textFieldNom.setText(patient.getNom());
       textFieldPrenom.setText(patient.getPrenom());
       textFieldTel.setText(patient.getTel());
       textFieldEmail.setText(patient.getEmail());

    }

    public void updatePatient(){
        Patient patient = tablePatients.getSelectionModel().getSelectedItem();
        patient.setNom(textFieldNom.getText());
        patient.setPrenom(textFieldPrenom.getText());
        patient.setTel(textFieldTel.getText());
        patient.setEmail(textFieldEmail.getText());
        cabinetService.updatePatient(patient);
        loadPatients();
        textFieldNom.setText("");
        textFieldPrenom.setText("");
        textFieldTel.setText("");
        textFieldEmail.setText("");

    }


    private void loadPatients(){
        patients.setAll(cabinetService.getAllPatient());

    }
}
