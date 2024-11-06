package src.skin_care;

import java.util.Scanner;

import src.services.AppointmentService;
import src.services.AppointmentServiceImpl;

public class Main {
    public static void main(String args[]) {
        AppointmentService appointmentService = new AppointmentServiceImpl();

        Scanner scanner = new Scanner(System.in);
        boolean loop = true;

        while (loop) {
            System.out.println("\n=== AURORA SKIN CARE MANAGEMENT SYSTEM ===");
            System.out.println("N   : New Appointment");
            System.out.println("U   : Update Appointment");
            System.out.println("V   : View Appointments");
            System.out.println("SD  : Search Appointment by date");
            System.out.println("SID : Search Appointment by id");
            System.out.println("SN  : Search Appointment by name");
            System.out.println("I   : Generate Invoice");
            System.out.println("Q   : Quit");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();

            switch (choice.toLowerCase()) {
                case "n":
                    appointmentService.addAppointment();
                    break;

                case "u":
                    appointmentService.updateAppointment();
                    break;

                case "v":
                    appointmentService.viewAllAppointments();
                    break;

                case "sd":
                    appointmentService.searchAppointments("date");
                    break;

                case "sid":
                    appointmentService.searchAppointments("id");
                    break;

                case "sn":
                    appointmentService.searchAppointments("name");
                    break;

                case "i":
                    appointmentService.generateInvoice();
                    break;

                case "q":
                    loop = false;
                    break;

                default:
                    System.out.println("Your input is not valid!");
                    break;
            }
        }

        scanner.close();

    }
}
