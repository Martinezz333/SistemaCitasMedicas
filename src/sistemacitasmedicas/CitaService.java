package sistemacitasmedicas;

import java.util.ArrayList;
import java.util.List;

public class CitaService {

    private List<Cita> citas;

    public CitaService() {
        citas = new ArrayList<>();
    }

    public void registrarCita(Cita cita) {
        citas.add(cita);
    }

    public List<Cita> obtenerCitas() {
        return citas;
    }

    public Cita buscarCita(String identificador) {

        for (Cita cita : citas) {

            if (cita.getIdentificador().equals(identificador)) {
                return cita;
            }
        }

        return null;
    }
}