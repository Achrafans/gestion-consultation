package net.achraf.demo.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import net.achraf.demo.dao.impl.ConsultationDaoImp;
import net.achraf.demo.dao.impl.PatientDaoImp;
import net.achraf.demo.entities.Consultation;
import net.achraf.demo.entities.Patient;
import net.achraf.demo.service.CabinetService;
import net.achraf.demo.service.ICabinetService;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class ConsultationController implements Initializable {
    @FXML private TextField textFieldDescription;
    @FXML private DatePicker dateConsultation;
    @FXML private ComboBox<Patient> comboPatient;
    @FXML private TableView<Consultation> tableConsultation;
    @FXML private TableColumn<Consultation,Long> columnId;
    @FXML private TableColumn<Consultation,Long> columnDateConsultation;
    @FXML private TableColumn<Consultation,Long> columnDescription;
    @FXML private TableColumn<Consultation,String> columnPatient;
    private ICabinetService cabinetService;
    private ObservableList<Consultation> consultations = FXCollections.observableArrayList();
    private ObservableList<Patient> patients = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cabinetService = new CabinetService(new PatientDaoImp(), new ConsultationDaoImp());
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnDateConsultation.setCellValueFactory(new PropertyValueFactory<>("dateConsultation"));
        columnDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        columnPatient.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getPatient().getNom())
        );

        loadConsultations();
        tableConsultation.setItems(consultations);

        patients.setAll(cabinetService.getAllPatient());
        comboPatient.setItems(patients);

        tableConsultation.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                loadSelectedConsultation();
            }
        });


    }

    private void loadConsultations(){
        consultations.setAll(cabinetService.getAllConsultation());
    }

    public void loadSelectedConsultation(){
        Consultation consultation =tableConsultation.getSelectionModel().getSelectedItem();
        textFieldDescription.setText(consultation.getDescription());
        comboPatient.setValue(consultation.getPatient());
        dateConsultation.setValue(consultation.getDateConsultation().toLocalDate());
    }
    public void addConsultation(){
        Consultation consultation = new Consultation();
        consultation.setDateConsultation(Date.valueOf(dateConsultation.getValue()));
        consultation.setDescription(textFieldDescription.getText());
        consultation.setPatient(comboPatient.getSelectionModel().getSelectedItem());
        cabinetService.addConsultation(consultation);
        loadConsultations();
        dateConsultation.setValue(null);
        textFieldDescription.setText("");
        comboPatient.setValue(null);

    }

    public void deleteConsultation(){
        Consultation consultation = tableConsultation.getSelectionModel().getSelectedItem();
        cabinetService.deleteConsultation(consultation);
        loadConsultations();
        dateConsultation.setValue(null);
        textFieldDescription.setText("");
        comboPatient.setValue(null);
    }

    public void updateConsultation(){
        Consultation consultation = tableConsultation.getSelectionModel().getSelectedItem();
        consultation.setDateConsultation(Date.valueOf(dateConsultation.getValue()));
        consultation.setDescription(textFieldDescription.getText());
        consultation.setPatient(comboPatient.getSelectionModel().getSelectedItem());
        loadConsultations();
        dateConsultation.setValue(null);
        textFieldDescription.setText("");
        comboPatient.setValue(null);
    }
}
