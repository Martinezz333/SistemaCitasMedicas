package sistemacitasmedicas;

public class Administrador {

    private String identificador;
    private String contrasena;

    public Administrador(String identificador, String contrasena) {
        this.identificador = identificador;
        this.contrasena = contrasena;
    }

    public String getIdentificador() {
        return identificador;
    }

    public String getContrasena() {
        return contrasena;
    }
}
