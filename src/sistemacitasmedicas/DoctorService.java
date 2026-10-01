package sistemacitasmedicas;

import java.util.ArrayList;
import java.util.List;

public class DoctorService {

    private List<Doctor> doctores;

    public DoctorService() {
        doctores = new ArrayList<>();
    }

    public void registrarDoctor(Doctor doctor) {
        doctores.add(doctor);
    }

    public List<Doctor> obtenerDoctores() {
        return doctores;
    }

    public Doctor buscarDoctor(String identificador) {

        for (Doctor doctor : doctores) {

            if (doctor.getIdentificador().equals(identificador)) {
                return doctor;
            }
        }

        return null;
    }
}