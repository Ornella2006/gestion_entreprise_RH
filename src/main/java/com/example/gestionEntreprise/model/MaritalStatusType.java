package com.example.gestionEntreprise.model;

public enum MaritalStatusType {
    single,
    married,
    divorced,
    widowed;

    // Méthode optionnelle pour convertir depuis String (utile pour les API)
    public static MaritalStatusType fromString(String value) {
        if (value != null) {
            for (MaritalStatusType status : MaritalStatusType.values()) {
                if (status.name().equalsIgnoreCase(value)) {
                    return status;
                }
            }
        }
        throw new IllegalArgumentException("Statut marital invalide: " + value);
    }
}