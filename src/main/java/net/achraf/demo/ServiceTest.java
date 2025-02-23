package net.achraf.demo;

import net.achraf.demo.dao.impl.ConsultationDaoImp;
import net.achraf.demo.dao.impl.PatientDaoImp;
import net.achraf.demo.entities.Patient;
import net.achraf.demo.service.CabinetService;
import net.achraf.demo.service.ICabinetService;

public class ServiceTest {
    public static void main(String[] args) {
        ICabinetService service = new CabinetService(new PatientDaoImp(), new ConsultationDaoImp());
        Patient patient = new Patient();
        patient.setNom("John Doe");
        patient.setPrenom("fzfezf");
        patient.setTel("123456789");
        patient.setEmail("john.doe@gmail.com");
        service.addPatient(patient);
/*        List<Patient> patients = service.getAllPatient();*/
    }
}
