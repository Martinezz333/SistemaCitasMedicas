package sistemacitasmedicas;

public class Paciente {

    private String identificador;
    private String nombreCompleto;

    public Paciente(String identificador, String nombreCompleto) {
        this.identificador = identificador;
        this.nombreCompleto = nombreCompleto;
    }

    public String getIdentificador() {
        return identificador;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }
}