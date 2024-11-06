package src.services;

import java.util.HashMap;

import src.models.Treatment;

public interface TreatmentService {
    public Treatment getTreatmentByType(String treatmentType);

    public HashMap<String, Treatment> getAllTreatments();
}
