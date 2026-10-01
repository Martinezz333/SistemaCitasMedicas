package sistemacitasmedicas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FileService {

    private static final String DB_FOLDER = "db";
    private static final String DOCTORES_FILE = DB_FOLDER + "/doctores.csv";
    private static final String PACIENTES_FILE = DB_FOLDER + "/pacientes.csv";
    private static final String CITAS_FILE = DB_FOLDER + "/citas.csv";

    public FileService() {
        crearArchivosSiNoExisten();
    }

    public void crearArchivosSiNoExisten() {

        File carpeta = new File(DB_FOLDER);

        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }

        crearArchivo(DOCTORES_FILE);
        crearArchivo(PACIENTES_FILE);
        crearArchivo(CITAS_FILE);
    }

    private void crearArchivo(String ruta) {

        File archivo = new File(ruta);

        if (!archivo.exists()) {

            try {
                archivo.createNewFile();
            } catch (IOException e) {
                System.out.println("No se pudo crear el archivo: " + ruta);
            }
        }
    }

    public void guardarDoctor(Doctor doctor) {

        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(DOCTORES_FILE, true))) {

            writer.write(
                    doctor.getIdentificador() + "," +
                    doctor.getNombreCompleto() + "," +
                    doctor.getEspecialidad()
            );

            writer.newLine();

        } catch (IOException e) {
            System.out.println("Error al guardar el doctor.");
        }
    }

    public void guardarPaciente(Paciente paciente) {

        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(PACIENTES_FILE, true))) {

            writer.write(
                    paciente.getIdentificador() + "," +
                    paciente.getNombreCompleto()
            );

            writer.newLine();

        } catch (IOException e) {
            System.out.println("Error al guardar el paciente.");
        }
    }

    public void guardarCita(Cita cita) {

        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(CITAS_FILE, true))) {

            writer.write(
                    cita.getIdentificador() + "," +
                    cita.getFechaHora() + "," +
                    cita.getMotivo() + "," +
                    cita.getDoctor().getIdentificador() + "," +
                    cita.getPaciente().getIdentificador()
            );

            writer.newLine();

        } catch (IOException e) {
            System.out.println("Error al guardar la cita.");
        }
    }

    public List<Doctor> cargarDoctores() {

        List<Doctor> doctores = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(
                new FileReader(DOCTORES_FILE))) {

            String linea;

            while ((linea = reader.readLine()) != null) {

                String[] datos = linea.split(",");

                if (datos.length >= 3) {

                    Doctor doctor = new Doctor(
                            datos[0],
                            datos[1],
                            datos[2]
                    );

                    doctores.add(doctor);
                }
            }

        } catch (IOException e) {
            System.out.println("No se pudieron cargar los doctores.");
        }

        return doctores;
    }

    public List<Paciente> cargarPacientes() {

        List<Paciente> pacientes = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(
                new FileReader(PACIENTES_FILE))) {

            String linea;

            while ((linea = reader.readLine()) != null) {

                String[] datos = linea.split(",");

                if (datos.length >= 2) {

                    Paciente paciente = new Paciente(
                            datos[0],
                            datos[1]
                    );

                    pacientes.add(paciente);
                }
            }

        } catch (IOException e) {
            System.out.println("No se pudieron cargar los pacientes.");
        }

        return pacientes;
    }
}