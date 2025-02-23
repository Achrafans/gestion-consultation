package net.achraf.demo.dao.impl;

import net.achraf.demo.DBConnection;
import net.achraf.demo.dao.IConsultationDao;
import net.achraf.demo.entities.Consultation;
import net.achraf.demo.entities.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConsultationDaoImp implements IConsultationDao {
    @Override
    public void create(Consultation consultation) throws SQLException {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO CONSULTATIONS(date_consultation, description, id_patient)" + "VALUES (?,?,?)");
        pstm.setDate(1, consultation.getDateConsultation());
        pstm.setString(2, consultation.getDescription());
        pstm.setLong(3, consultation.getPatient().getId());

        pstm.executeUpdate();

    }

    @Override
    public void update(Consultation consultation) throws SQLException {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("UPDATE CONSULTATIONS SET date_consultation = ?, description = ?, id_patient = ? WHERE id_consultation = ?");
        pstm.setDate(1, consultation.getDateConsultation());
        pstm.setString(2, consultation.getDescription());
        pstm.setLong(3, consultation.getPatient().getId());
        pstm.setLong(4, consultation.getId());

        pstm.executeUpdate();

    }

    @Override
    public void delete(Consultation consultation) throws SQLException {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("DELETE FROM CONSULTATIONS WHERE ID_CONSULTATION = ?");
        pstm.setLong(1, consultation.getId());

        pstm.executeUpdate();

    }

    @Override
    public Consultation findById(Long id) throws SQLException {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM CONSULTATIONS WHERE ID_CONSULTATION = ?");
        pstm.setLong(1, id);
        ResultSet rs = pstm.executeQuery();
        Consultation consultation = new Consultation();
        while(rs.next()){
            consultation.setId(rs.getLong("id_consultation"));
            consultation.setDateConsultation(rs.getDate("date_consultation"));
            consultation.setDescription(rs.getString("description"));

            PatientDaoImp patientDaoImp = new PatientDaoImp();
            Patient patient =  patientDaoImp.findById(rs.getLong("id_patient"));

            consultation.setPatient(patient);
        }

       return consultation;
    }

    @Override
    public List<Consultation> findAll() throws SQLException {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM CONSULTATIONS");
        ResultSet rs = pstm.executeQuery();
        List<Consultation> consultations = new ArrayList<>();
        while(rs.next()){
            Consultation consultation = new Consultation();
            consultation.setId(rs.getLong("id_consultation"));
            consultation.setDateConsultation(rs.getDate("date_consultation"));
            consultation.setDescription(rs.getString("description"));

            PatientDaoImp patientDaoImp = new PatientDaoImp();
            Patient patient =  patientDaoImp.findById(rs.getLong("id_patient"));
            consultation.setPatient(patient);

            consultations.add(consultation);

        }

        return consultations;
    }
}
