package com.parasoft.parabank.util;

import java.util.UUID;

public class RandomUserGenerator {

    //función para generar un usuario aleatorio, pues si se usa el mismo arrojará error porque ya está en la base de datos
    public static String RandomUserGeneratorFunction() {
        String fullUsername;
        String shortUsername;
        fullUsername = UUID.randomUUID().toString();
        fullUsername = fullUsername.replace("-", "");
        shortUsername = fullUsername.substring(0,7);

        return shortUsername;
    }

}
