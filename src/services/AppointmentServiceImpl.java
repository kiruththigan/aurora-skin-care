package src.services;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import src.models.Appointment;
import src.models.Dermatologist;
import src.models.Patient;
import src.models.Treatment;

public class AppointmentServiceImpl implements AppointmentService {
    Scanner scanner = new Scanner(System.in);
    DermatologistService dermatologistService = new DermatologistServiceImpl();
    TimeSlotsService timeSlotsService = new TimeSlotsServiceImpl();
    TreatmentService treatmentService = new TreatmentServiceImpl();

    HashMap<Integer, Appointment> appointments = new HashMap<>();
    Integer appointmentId = 1;

    public void addAppointment() {
        System.out.println("\n\n=== Make New Appointment ===");

        Patient patient = getPationsDetailsFromUser();

        Dermatologist dermatologist = getDermatologistDetailsFromUser();

        String dateStr = getAppointmentDateFromUser();

        if (dateStr != null) {
            LocalDate date = LocalDate.parse(dateStr);
            DayOfWeek day = date.getDayOfWeek();

            LocalDateTime appointmentDateTime = getAppointmentTimeFromUser(dateStr, date, day, dermatologist);

            Treatment treatment = getTreatmentFromUser();
            Appointment appointment = new Appointment(appointmentId, appointmentDateTime, patient,
                    treatment,
                    dermatologist);

            appointments.put(appointmentId, appointment);
            dermatologistService.addBookedDateTime(dermatologist,
                    appointmentDateTime);
            appointmentId++;

            System.out.println("\nSuccessfully added.");
        }

    }

    public void updateAppointment() {
        System.out.println("\n\n=== Update Appointment ===");

        Appointment appointment = getAppointmentByIdFromUser();

        if (appointment != null) {
            displayAppointment(appointment);
            dermatologistService.removeBookedDateTime(appointment.getDermatologist(), appointment.getDateTime());
        }

        Patient patient = getPationsDetailsFromUser();

        Dermatologist dermatologist = getDermatologistDetailsFromUser();

        String dateStr = getAppointmentDateFromUser();

        if (dateStr != null) {
            LocalDate date = LocalDate.parse(dateStr);
            DayOfWeek day = date.getDayOfWeek();

            LocalDateTime appointmentDateTime = getAppointmentTimeFromUser(dateStr, date, day, dermatologist);

            Treatment treatment = getTreatmentFromUser();

            appointment.setDateTime(appointmentDateTime);
            appointment.setPatient(patient);
            appointment.setTreatment(treatment);
            appointment.setDermatologist(dermatologist);

            dermatologistService.addBookedDateTime(dermatologist,
                    appointmentDateTime);
            appointmentId++;

            System.out.println("\nSuccessfully updated.");
        }
    }

    public HashMap<Integer, Appointment> getAllAppointments() {
        return appointments;
    }

    public void viewAllAppointments() {
        if (appointments.get(1) == null) {
            System.out.println("Appointments is empty!");
            return;
        }

        for (Appointment appointment : appointments.values()) {
            displayAppointment(appointment);
        }
    }

    public void searchAppointments(String key) {
        switch (key) {
            case "id":
                Appointment appointment = getAppointmentByIdFromUser();
                if (appointment != null) {
                    displayAppointment(appointment);
                }
                break;

            case "date":
                int totalRecord = 0;
                String dateStr = getAppointmentDateFromUser();
                if (dateStr == null) {
                    break;
                }
                LocalDate date = LocalDate.parse(dateStr);

                for (Appointment appointmentObj : appointments.values()) {
                    if (appointmentObj.getDateTime().toLocalDate().equals(date)) {
                        displayAppointment(appointmentObj);
                        totalRecord++;
                    }
                }

                if (totalRecord == 0) {
                    System.out.println("Appointments is empty !");
                }
                break;

            case "name":
                int totalRecordName = 0;
                System.out.println("\nEnter the name : ");
                String name = scanner.nextLine();

                if (name.toLowerCase().equals("q")) {
                    break;
                }

                for (Appointment appointmentObj : appointments.values()) {
                    if (appointmentObj.getPatient().getName().equals(name)) {
                        displayAppointment(appointmentObj);
                        totalRecordName++;
                    }
                }

                if (totalRecordName == 0) {
                    System.out.println("Appointments is empty !");
                }
                break;

            default:
                break;
        }

    }

    public void generateInvoice() {
        System.out.println("\n=== Generate Invoice ===");

        Appointment appointment = getAppointmentByIdFromUser();

        if (appointment == null) {
            System.out.println("No appointment found!");
            return;
        }

        if (appointment.getTreatment() == null) {
            System.out.println("Please update the treatment details");
            return;
        }

        String treatmentType = appointment.getTreatment().getTreatmentType();

        double baseFee = appointment.getTreatment().getPrice();

        double taxRate = 0.025;
        double taxAmount = baseFee * taxRate;

        double totalFee = baseFee + taxAmount;

        double roundedTotalFee = Math.ceil(totalFee * 100) / 100;

        System.out.println("\nInvoice Details:");
        System.out.println("------------------");
        System.out.println("Treatment  : " + treatmentType);
        System.out.println("Fee        : Rs. " + baseFee);
        System.out.println("Tax (" + taxRate * 100 + "%) : Rs. " + taxAmount);
        System.out.println("Total Fee  : Rs. " + roundedTotalFee);
    }

    // private functions

    private Patient getPationsDetailsFromUser() {
        System.out.print("Enter patient NIC: ");
        String nic = scanner.nextLine();
        System.out.print("Enter patient name: ");
        String name = scanner.nextLine();
        System.out.print("Enter patient email: ");
        String email = scanner.nextLine();
        System.out.print("Enter patient phone: ");
        String phone = scanner.nextLine();

        return new Patient(name, email, nic, phone);
    }

    private Dermatologist getDermatologistDetailsFromUser() {
        boolean loop = false;
        Dermatologist dermatologist;
        do {

            System.out.print("\nAvailable Dermatologist : ");
            List<Dermatologist> dermatologists = dermatologistService.getAllDermatologist();
            for (Dermatologist dermatologistObj : dermatologists) {
                System.out.print(dermatologistObj.getName() + ", ");
            }

            if (loop) {
                System.out.println("\nDermatologist doesn't exist re enter dermatologist name: ");
            } else {
                System.out.println("\nEnter dermatologist name: ");
            }

            String dermatologistName = scanner.nextLine();
            dermatologist = new Dermatologist(dermatologistName);
            boolean isExist = dermatologistService.isDermatologistExist(dermatologist);

            if (!isExist) {
                loop = true;
                continue;
            } else {
                loop = false;
            }
        } while (loop);

        return dermatologist;
    }

    private String getAppointmentDateFromUser() {
        boolean loop = false;
        String dateStr;
        DayOfWeek[] validDays = { DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY };
        do {
            if (loop) {
                System.out.println("Date is not valid re enter appointment date (YYYY-MM-DD): ");
            } else {
                System.out.println("Enter appointment date (YYYY-MM-DD): ");
            }

            dateStr = scanner.nextLine();
            try {
                if (dateStr.toLowerCase().equals("q")) {
                    return null;
                }
                LocalDate date = LocalDate.parse(dateStr);
                DayOfWeek day = date.getDayOfWeek();
                boolean isValidDay = Arrays.asList(validDays).contains(day);
                if (!isValidDay) {
                    loop = true;
                    System.out.println(day.toString() + " is not valid day!");
                } else {
                    loop = false;
                }
            } catch (Exception e) {
                loop = true;
            }
        } while (loop);

        return dateStr;
    }

    private LocalDateTime getAppointmentTimeFromUser(String dateStr, LocalDate date, DayOfWeek day,
            Dermatologist dermatologist) {

        boolean loop = false;
        LocalDateTime appointmentDateTime = null;

        do {
            System.out.print("\nAvailable time slots : ");

            List<LocalTime> availableTimeSlots = getAvailableTimeSlots(date, dermatologist);
            for (LocalTime localTime : availableTimeSlots) {
                System.out.print(localTime + ", ");
            }

            System.out.println("\nEnter appointment time (HH:mm): ");
            String timeStr = scanner.nextLine();
            try {
                LocalTime.parse(timeStr);
                loop = false;
            } catch (Exception e) {
                loop = true;
                System.out.print("\nTime is not valid!");
                continue;
            }

            boolean isExistTimeSlot = availableTimeSlots.stream()
                    .anyMatch(slot -> slot.equals(LocalTime.parse(timeStr)));
            if (isExistTimeSlot) {
                loop = false;
            } else {
                loop = true;
                System.out.print("\nTime slot not available");
                continue;
            }

            appointmentDateTime = LocalDateTime.parse(dateStr + "T" + timeStr);

        } while (loop);

        return appointmentDateTime;
    }

    private List<LocalTime> getAvailableTimeSlots(LocalDate date, Dermatologist dermatologist) {
        List<LocalTime> bookedTimeSlots = dermatologistService.getBookedTimeSlotsByDermatologistAndDate(date,
                dermatologist);
        List<LocalTime> timeSlots = timeSlotsService.getTimeSlotsByDay(date.getDayOfWeek());

        List<LocalTime> availableTimeSlots = new ArrayList<>(timeSlots);
        if (bookedTimeSlots != null) {
            availableTimeSlots.removeAll(bookedTimeSlots);
        }

        return availableTimeSlots;
    }

    private Treatment getTreatmentFromUser() {
        boolean loop = false;
        Treatment treatment = null;
        do {
            System.out.print("\nTreatments : ");
            HashMap<String, Treatment> treatments = treatmentService.getAllTreatments();
            for (Treatment treatmentObj : treatments.values()) {
                System.out.print(treatmentObj.getTreatmentType() + ", ");
            }
            System.out.println("\nEnter Treatment type : ");
            String tratementType = scanner.nextLine();

            if (tratementType.equals("")) {
                loop = false;
                return null;
            } else {
                boolean isContains = treatments.containsKey(tratementType);
                if (isContains) {
                    treatment = treatments.get(tratementType);
                    loop = false;
                } else {
                    loop = true;
                    System.out.print("\nTreatment not available!");
                }
            }

        } while (loop);

        return treatment;
    }

    private void displayAppointment(Appointment appointment) {
        System.out.print(appointment.getId() + ", ");
        System.out.print(appointment.getPatient().getName() + ", ");
        System.out.print(appointment.getPatient().getEmail() + ", ");
        System.out.print(appointment.getPatient().getNic() + ", ");
        System.out.print(appointment.getPatient().getPhoneNo() + ", ");
        System.out.print(appointment.getDateTime() + ", ");
        System.out.print(appointment.getDermatologist().getName() + ", ");
        if (appointment.getTreatment() != null) {
            System.out.println(appointment.getTreatment().getTreatmentType() + ", ");
        } else {
            System.out.println("");
        }
    }

    private Appointment getAppointmentByIdFromUser() {
        boolean loop = false;
        Appointment appointment = null;
        do {

            try {
                System.out.println("\nEnter Appointment id : ");
                String appointmentId = scanner.nextLine();
                if (appointmentId.toLowerCase().equals("q")) {
                    return null;
                } else {
                    if (appointments.get(Integer.parseInt(appointmentId)) != null) {
                        loop = false;
                        appointment = appointments.get(Integer.parseInt(appointmentId));
                    } else {
                        loop = true;
                        System.out.print("\nAppointment not exist!");
                    }
                }

            } catch (Exception e) {
                System.out.println("Please enter valid input!");
                return null;
            }

        } while (loop);
        return appointment;
    }
}
