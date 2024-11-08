package src.services;

import java.util.HashMap;

import src.models.Treatment;

public class TreatmentServiceImpl implements TreatmentService {
    private HashMap<String, Treatment> treatments = new HashMap<>();

    public TreatmentServiceImpl() {
        treatments.put("Acne Treatment", new Treatment("Acne Treatment", 2750.00));
        treatments.put("Skin Whitening", new Treatment("Skin Whitening", 7650.00));
        treatments.put("Mole Removal", new Treatment("Mole Removal", 3850.00));
        treatments.put("Laser Treatment", new Treatment("Laser Treatment", 12500.00));
    }

    public Treatment getTreatmentByType(String treatmentType) {
        try {
            Treatment treatment = treatments.get(treatmentType);
            if (treatment == null) {
                throw new IllegalArgumentException("Treatment type not found: " + treatmentType);
            }
            return treatment;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public HashMap<String, Treatment> getAllTreatments() {
        return treatments;
    }

}
