package net.achraf.demo.service;

import net.achraf.demo.dao.impl.ConsultationDaoImp;
import net.achraf.demo.dao.impl.PatientDaoImp;
import net.achraf.demo.entities.Consultation;
import net.achraf.demo.entities.Patient;

import java.sql.SQLException;
import java.util.List;

public class CabinetService implements ICabinetService {
    PatientDaoImp patientDao;
    ConsultationDaoImp consultationDao;

    public CabinetService(PatientDaoImp patientDao, ConsultationDaoImp consultationDao) {
        this.patientDao = patientDao;
        this.consultationDao = consultationDao;
    }

    @Override
    public void addPatient(Patient patient) {
        try {
            patientDao.create(patient);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePatient(Patient patient) {
        try{
            patientDao.delete(patient);

        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updatePatient(Patient patient) {
        try{
            patientDao.update(patient);

        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Patient> getAllPatient() {
        try {
            return patientDao.findAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Patient getPatientById(Long id) {
       try{
           return patientDao.findById(id);
       } catch(SQLException e){
           throw new RuntimeException(e);
       }
    }

    @Override
    public List<Patient> searchPatientsByQuery(String query) throws SQLException {
        return patientDao.searchByQuery(query);
    }

    @Override
    public void addConsultation(Consultation consultation) {
        try{
            consultationDao.create(consultation);
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deleteConsultation(Consultation consultation) {
        try{
            consultationDao.delete(consultation);
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void updateConsultation(Consultation consultation) {
        try{
            consultationDao.update(consultation);
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Consultation> getAllConsultation() {
        try {
            return consultationDao.findAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Consultation getConsultationById(Long id) {
        try{
            return consultationDao.findById(id);
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
}
