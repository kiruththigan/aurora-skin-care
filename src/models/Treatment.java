package src.models;

public class Treatment {
    private String treatmentType;
    private double price;

    public Treatment() {
    }

    public Treatment(String treatmentType, double price) {
        this.treatmentType = treatmentType;
        this.price = price;
    }

    public String getTreatmentType() {
        return this.treatmentType;
    }

    public void setTreatmentType(String treatmentType) {
        this.treatmentType = treatmentType;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
