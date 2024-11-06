package src.models;

public class Invoice {
    private int id;
    private Appointment appointment;
    private double totalFee;
    private double taxPercentage;
    private double taxAmount;
    private double finalAmount;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Appointment getAppointment() {
        return this.appointment;
    }

    public void setId(Appointment appointment) {
        this.appointment = appointment;
    }

    public double getTotalFee() {
        return this.totalFee;
    }

    public void setTotalFee(double totalFee) {
        this.totalFee = totalFee;
    }

    public double getTaxPercentage() {
        return this.taxPercentage;
    }

    public void setTaxPercentage(double taxPercentage) {
        this.taxPercentage = taxPercentage;
    }

    public double getTaxAmount() {
        return this.taxAmount;
    }

    public void setTaxAmount(double taxAmount) {
        this.taxAmount = taxAmount;
    }

    public double getFinalAmount() {
        return this.finalAmount;
    }

    public void setFinalAmount(double finalAmount) {
        this.finalAmount = finalAmount;
    }
}
