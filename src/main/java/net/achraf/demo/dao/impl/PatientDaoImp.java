package net.achraf.demo.dao.impl;

import net.achraf.demo.DBConnection;
import net.achraf.demo.dao.IPatientDao;
import net.achraf.demo.entities.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatientDaoImp implements IPatientDao {
    @Override
    public void create(Patient patient) throws SQLException {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO PATIENTS(NOM,PRENOM,TEL,EMAIL)" + "VALUES(?,?,?,?)");
        pstm.setString(1, patient.getNom());
        pstm.setString(2,patient.getPrenom());
        pstm.setString(3,patient.getTel());
        pstm.setString(4,patient.getEmail());
        pstm.executeUpdate();
    }

    @Override
    public void update(Patient patient) throws SQLException {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("UPDATE PATIENTS SET NOM = ? ,PRENOM = ?,TEL =? , EMAIL =? WHERE ID_PATIENT = ?");
        pstm.setString(1, patient.getNom());
        pstm.setString(2,patient.getPrenom());
        pstm.setString(3,patient.getTel());
        pstm.setString(4,patient.getEmail());
        pstm.setLong(5,patient.getId());
        pstm.executeUpdate();

    }

    @Override
    public void delete(Patient patient) throws SQLException {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("DELETE FROM PATIENTS WHERE ID_PATIENT= ?");
        pstm.setLong(1, patient.getId());
        pstm.executeUpdate();


    }

    @Override
    public Patient findById(Long id) throws SQLException {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM PATIENTS WHERE ID_PATIENT=?");
        pstm.setLong(1, id);
        ResultSet rs = pstm.executeQuery();
        Patient patient = new Patient();
        while (rs.next()) {
            patient.setId(rs.getLong("id_patient"));
            patient.setNom(rs.getString("nom"));
            patient.setPrenom(rs.getString("prenom"));
            patient.setTel(rs.getString("tel"));
        }
        return patient;
    }

    @Override
    public List<Patient> findAll() throws SQLException {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM PATIENTS");
        ResultSet rs = pstm.executeQuery();
        List<Patient> patients = new ArrayList<>();
        while (rs.next()) {
            Patient patient = new Patient();
            patient.setId(rs.getLong("id_patient"));
            patient.setNom(rs.getString("nom"));
            patient.setPrenom(rs.getString("prenom"));
            patient.setTel(rs.getString("tel"));
            patient.setEmail(rs.getString("email"));
            patients.add(patient);
        }
        return patients;
    }

    public List<Patient> searchByQuery(String query) throws SQLException {
        Connection connection = DBConnection.getConnection();
        String sql = "SELECT * FROM PATIENTS WHERE nom LIKE ? OR prenom LIKE ? OR tel LIKE ? OR email LIKE ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        // Ajouter les paramètres avec les wildcards pour une recherche partielle
        String searchQuery = "%" + query + "%";
        for (int i = 1; i <= 4; i++) {
            pstm.setString(i, searchQuery);
        }
        ResultSet rs = pstm.executeQuery();
        List<Patient> patients = new ArrayList<>();
        while(rs.next()){
            Patient patient = new Patient();
            patient.setId(rs.getLong("id_patient"));
            patient.setNom(rs.getString("nom"));
            patient.setPrenom(rs.getString("prenom"));
            patient.setTel(rs.getString("tel"));
            patient.setEmail(rs.getString("email"));
            patients.add(patient);
        }
        return patients;
    }
}
