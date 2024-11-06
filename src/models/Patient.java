package src.models;

public class Patient {
    private String name;
    private String email;
    private String nic;
    private String phoneNo;

    public Patient() {
    }

    public Patient(String name, String email, String nic, String phoneNo) {
        this.name = name;
        this.email = email;
        this.nic = nic;
        this.phoneNo = phoneNo;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNic() {
        return this.nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getPhoneNo() {
        return this.phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

}
