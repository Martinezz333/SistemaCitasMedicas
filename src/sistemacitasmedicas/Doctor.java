package sistemacitasmedicas;

public class Doctor {

    private String identificador;
    private String nombreCompleto;
    private String especialidad;

    public Doctor(String identificador, String nombreCompleto, String especialidad) {
        this.identificador = identificador;
        this.nombreCompleto = nombreCompleto;
        this.especialidad = especialidad;
    }

    public String getIdentificador() {
        return identificador;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getEspecialidad() {
        return especialidad;
    }
}