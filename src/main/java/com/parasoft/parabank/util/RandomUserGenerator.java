package com.parasoft.parabank.util;

import java.util.UUID;

public class RandomUserGenerator {
    public static String RandomUserGeneratorFunction() {
        String fullUsername;
        String shortUsername;
        fullUsername = UUID.randomUUID().toString();
        fullUsername = fullUsername.replace("-", "");
        shortUsername = fullUsername.substring(0,7);

        return shortUsername;
    }

}
