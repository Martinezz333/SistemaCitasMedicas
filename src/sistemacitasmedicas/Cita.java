package sistemacitasmedicas;

import java.time.LocalDateTime;

public class Cita {

    private String identificador;
    private LocalDateTime fechaHora;
    private String motivo;
    private Doctor doctor;
    private Paciente paciente;

    public Cita(String identificador, LocalDateTime fechaHora, String motivo,
                Doctor doctor, Paciente paciente) {
        this.identificador = identificador;
        this.fechaHora = fechaHora;
        this.motivo = motivo;
        this.doctor = doctor;
        this.paciente = paciente;
    }

    public String getIdentificador() {
        return identificador;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public String getMotivo() {
        return motivo;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Paciente getPaciente() {
        return paciente;
    }
}