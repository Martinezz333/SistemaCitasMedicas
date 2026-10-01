package sistemacitasmedicas;

import java.util.ArrayList;
import java.util.List;

public class AuthService {

    private List<Administrador> administradores;

    public AuthService() {
        administradores = new ArrayList<>();

        // Usuario administrador inicial
        administradores.add(
                new Administrador("admin", "1234")
        );
    }

    public boolean iniciarSesion(String identificador, String contrasena) {

        for (Administrador administrador : administradores) {

            if (administrador.getIdentificador().equals(identificador)
                    && administrador.getContrasena().equals(contrasena)) {

                return true;
            }
        }

        return false;
    }
}