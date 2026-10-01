package sistemacitasmedicas;

import java.util.ArrayList;
import java.util.List;

public class PacienteService {

    private List<Paciente> pacientes;

    public PacienteService() {
        pacientes = new ArrayList<>();
    }

    public void registrarPaciente(Paciente paciente) {
        pacientes.add(paciente);
    }

    public List<Paciente> obtenerPacientes() {
        return pacientes;
    }

    public Paciente buscarPaciente(String identificador) {

        for (Paciente paciente : pacientes) {

            if (paciente.getIdentificador().equals(identificador)) {
                return paciente;
            }
        }

        return null;
    }
}