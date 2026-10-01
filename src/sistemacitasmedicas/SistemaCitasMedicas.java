package sistemacitasmedicas;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class SistemaCitasMedicas {

    private static Scanner scanner = new Scanner(System.in);

    private static AuthService authService = new AuthService();
    private static DoctorService doctorService = new DoctorService();
    private static PacienteService pacienteService = new PacienteService();
    private static CitaService citaService = new CitaService();
    private static FileService fileService = new FileService();

    public static void main(String[] args) {

        cargarInformacion();

        System.out.println("========================================");
        System.out.println("     SISTEMA DE CITAS MEDICAS");
        System.out.println("========================================");

        if (!iniciarSesion()) {
            System.out.println("Acceso denegado.");
            return;
        }

        mostrarMenu();
    }

    private static boolean iniciarSesion() {

        System.out.println("\n--- INICIO DE SESION ---");

        System.out.print("Identificador: ");
        String identificador = scanner.nextLine();

        System.out.print("Contraseña: ");
        String contrasena = scanner.nextLine();

        if (authService.iniciarSesion(identificador, contrasena)) {
            System.out.println("\nInicio de sesión correcto.");
            return true;
        }

        System.out.println("\nUsuario o contraseña incorrectos.");
        return false;
    }

    private static void mostrarMenu() {

        int opcion;

        do {

            System.out.println("\n========================================");
            System.out.println("              MENU PRINCIPAL");
            System.out.println("========================================");
            System.out.println("1. Registrar doctor");
            System.out.println("2. Registrar paciente");
            System.out.println("3. Crear cita");
            System.out.println("4. Consultar doctores");
            System.out.println("5. Consultar pacientes");
            System.out.println("6. Consultar citas");
            System.out.println("7. Salir");
            System.out.println("========================================");

            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                opcion = 0;
            }

            switch (opcion) {

                case 1:
                    registrarDoctor();
                    break;

                case 2:
                    registrarPaciente();
                    break;

                case 3:
                    crearCita();
                    break;

                case 4:
                    consultarDoctores();
                    break;

                case 5:
                    consultarPacientes();
                    break;

                case 6:
                    consultarCitas();
                    break;

                case 7:
                    System.out.println("\nGracias por utilizar el sistema.");
                    break;

                default:
                    System.out.println("\nOpción no válida.");
            }

        } while (opcion != 7);
    }

    private static void registrarDoctor() {

        System.out.println("\n--- REGISTRAR DOCTOR ---");

        System.out.print("Identificador: ");
        String identificador = scanner.nextLine();

        if (doctorService.buscarDoctor(identificador) != null) {
            System.out.println("Ya existe un doctor con ese identificador.");
            return;
        }

        System.out.print("Nombre completo: ");
        String nombre = scanner.nextLine();

        System.out.print("Especialidad: ");
        String especialidad = scanner.nextLine();

        Doctor doctor = new Doctor(
                identificador,
                nombre,
                especialidad
        );

        doctorService.registrarDoctor(doctor);
        fileService.guardarDoctor(doctor);

        System.out.println("Doctor registrado correctamente.");
    }

    private static void registrarPaciente() {

        System.out.println("\n--- REGISTRAR PACIENTE ---");

        System.out.print("Identificador: ");
        String identificador = scanner.nextLine();

        if (pacienteService.buscarPaciente(identificador) != null) {
            System.out.println("Ya existe un paciente con ese identificador.");
            return;
        }

        System.out.print("Nombre completo: ");
        String nombre = scanner.nextLine();

        Paciente paciente = new Paciente(
                identificador,
                nombre
        );

        pacienteService.registrarPaciente(paciente);
        fileService.guardarPaciente(paciente);

        System.out.println("Paciente registrado correctamente.");
    }

    private static void crearCita() {

        System.out.println("\n--- CREAR CITA ---");

        if (doctorService.obtenerDoctores().isEmpty()) {
            System.out.println("Primero debe registrar al menos un doctor.");
            return;
        }

        if (pacienteService.obtenerPacientes().isEmpty()) {
            System.out.println("Primero debe registrar al menos un paciente.");
            return;
        }

        System.out.print("Identificador de la cita: ");
        String identificador = scanner.nextLine();

        if (citaService.buscarCita(identificador) != null) {
            System.out.println("Ya existe una cita con ese identificador.");
            return;
        }

        System.out.print("Fecha y hora (yyyy-MM-dd HH:mm): ");
        String fechaTexto = scanner.nextLine();

        LocalDateTime fechaHora;

        try {

            DateTimeFormatter formato =
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

            fechaHora = LocalDateTime.parse(fechaTexto, formato);

        } catch (Exception e) {

            System.out.println("Formato de fecha incorrecto.");
            return;
        }

        System.out.print("Motivo de la cita: ");
        String motivo = scanner.nextLine();

        System.out.println("\nDoctores disponibles:");

        for (Doctor doctor : doctorService.obtenerDoctores()) {

            System.out.println(
                    doctor.getIdentificador()
                    + " - "
                    + doctor.getNombreCompleto()
                    + " - "
                    + doctor.getEspecialidad()
            );
        }

        System.out.print("Identificador del doctor: ");
        String idDoctor = scanner.nextLine();

        Doctor doctor = doctorService.buscarDoctor(idDoctor);

        if (doctor == null) {
            System.out.println("No se encontró el doctor.");
            return;
        }

        System.out.println("\nPacientes disponibles:");

        for (Paciente paciente : pacienteService.obtenerPacientes()) {

            System.out.println(
                    paciente.getIdentificador()
                    + " - "
                    + paciente.getNombreCompleto()
            );
        }

        System.out.print("Identificador del paciente: ");
        String idPaciente = scanner.nextLine();

        Paciente paciente = pacienteService.buscarPaciente(idPaciente);

        if (paciente == null) {
            System.out.println("No se encontró el paciente.");
            return;
        }

        Cita cita = new Cita(
                identificador,
                fechaHora,
                motivo,
                doctor,
                paciente
        );

        citaService.registrarCita(cita);
        fileService.guardarCita(cita);

        System.out.println("\nCita creada correctamente.");
    }

    private static void consultarDoctores() {

        System.out.println("\n--- DOCTORES REGISTRADOS ---");

        List<Doctor> doctores = doctorService.obtenerDoctores();

        if (doctores.isEmpty()) {
            System.out.println("No hay doctores registrados.");
            return;
        }

        for (Doctor doctor : doctores) {

            System.out.println(
                    "ID: " + doctor.getIdentificador()
                    + " | Nombre: " + doctor.getNombreCompleto()
                    + " | Especialidad: " + doctor.getEspecialidad()
            );
        }
    }

    private static void consultarPacientes() {

        System.out.println("\n--- PACIENTES REGISTRADOS ---");

        List<Paciente> pacientes = pacienteService.obtenerPacientes();

        if (pacientes.isEmpty()) {
            System.out.println("No hay pacientes registrados.");
            return;
        }

        for (Paciente paciente : pacientes) {

            System.out.println(
                    "ID: " + paciente.getIdentificador()
                    + " | Nombre: " + paciente.getNombreCompleto()
            );
        }
    }

    private static void consultarCitas() {

        System.out.println("\n--- CITAS REGISTRADAS ---");

        List<Cita> citas = citaService.obtenerCitas();

        if (citas.isEmpty()) {
            System.out.println("No hay citas registradas.");
            return;
        }

        for (Cita cita : citas) {

            System.out.println(
                    "ID: " + cita.getIdentificador()
                    + " | Fecha: " + cita.getFechaHora()
                    + " | Motivo: " + cita.getMotivo()
                    + " | Doctor: " + cita.getDoctor().getNombreCompleto()
                    + " | Paciente: " + cita.getPaciente().getNombreCompleto()
            );
        }
    }

    private static void cargarInformacion() {

        List<Doctor> doctores = fileService.cargarDoctores();

        for (Doctor doctor : doctores) {
            doctorService.registrarDoctor(doctor);
        }

        List<Paciente> pacientes = fileService.cargarPacientes();

        for (Paciente paciente : pacientes) {
            pacienteService.registrarPaciente(paciente);
        }
    }
}