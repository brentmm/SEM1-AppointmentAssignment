/**
 * ApptManager - Application program that:
 *     Creates a empty calendar for the week.
 *     Allows the user to add/remove appointments
 *     Prints out the calendar for a specific day
 * All appointments are between 9 AM - 4 PM daily. All appointments are one hour long.
 * This appointment Manager is for a six day week (Monday-Saturday).
 * 
 * Skeleton code adapted by Brent Martin 
 * December 11th 2020
 */
// Standard import for the Scanner class
import java.util.*;
public class ApptManagerSkel {
    public static void main (String [] args) {
        // Create a Scanner object attached to the keyboard
        Scanner in = new Scanner (System.in);
        int selection = 0;
        ArrayList<Appointment> a = new ArrayList<Appointment>();
        // Create an empty Schedule
        createEmptyWeeklySchedule(a);
        System.out.println(a);
        selection = printMenu(in);
        while (selection != 9) {
            // clear the screen
            System.out.printf ("\f");
            switch (selection) {
                case 1: // Book Appointment
                //String clientName = "";
                //String clientPhone = "";
                System.out.printf ("Book Appointment%n");
                System.out.printf ("================%n%n");                
                // read in appointment info here
                System.out.printf ("Enter day of week: ");
                String dayOfWeek = in.next();
                System.out.printf ("End the start time (24 hour clock): ");
                int startTime = in.nextInt();
                System.out.printf ("1 = Mens cut $50, 2= Ladies cut $80, 3= Mens Colouring $50, 4= Ladies Colouring $120%n");
                System.out.printf ("Type of appointment: ");
                short appType = in.nextShort();
                System.out.printf ("Client Name: ");
                String clientName = in.next();
                System.out.print ("Client Number: ");
                String clientPhone = in.next();
                in.nextLine();
                Appointment p = findFreeAppointment(a, dayOfWeek, startTime);
                if(p != null){ //if appointment slot is not filled it sets values to customers info
                    p.setClientName(clientName);
                    p.setClientNumber(clientPhone);
                    p.setApptType(appType);
                    System.out.printf ("OK, your appointment is booked!%n");
                }else {
                    System.out.printf ("Sorry that time slot is booked already!%n"); //prints if slot is already full
                }

                break;
                case 2: // Find appointment by Name
                System.out.printf ("Search for Appointment By Name%n");
                System.out.printf ("==============================%n%n");
                System.out.printf ("Search for appointment%n");
                System.out.printf ("Enter Client Name: ");
                String _clientName = in.nextLine();
                findApptByName (a, _clientName);

                break;
                case 3: // show all appointments for a specific day
                System.out.printf ("Show appointments for a specific day%n%n");
                System.out.printf ("Enter day of week: ");
                String _dayOfWeek = in.nextLine();
                showAppointments(a, _dayOfWeek);

                break;
                case 4: 
                // Cancel appointment (bonus)
                System.out.printf ("Cancel Appointment%n");
                System.out.printf ("================%n%n");
                // read in dayOfWeek and startTime to be cancelled
                System.out.printf ("Enter day of week: ");
                dayOfWeek = in.next();
                System.out.printf ("End the start time (24 hour clock): ");
                startTime = in.nextInt();
                in.nextLine();
                p = findExistingAppointment (a, dayOfWeek, startTime);
                if(p != null){ //sets appoint slot to empty values and prints out to user appointment is cancelled
                    System.out.printf ("Appointment: %s %s%02d:00 - %s%02d:00 for %s has been cancelled!%n", p.getDayOfWeek(),"", p.getStartTimeHour(), "", p.getEndTimeHour(), p.getClientName());
                    p.setClientName("");
                    p.setClientNumber("");
                    p.setApptType((short)0);                    
                }else{
                    System.out.printf ("No appointment in that timeslot!%n"); //prints if no appoinment is found
                }  
                break;
                case 9:
                break;
                default:
                System.out.printf ("Invalid option%n");
            }
            System.out.printf ("Press Enter to continue...");
            in.nextLine();
            selection = printMenu(in);
        }
    }

    /** findExistingAppointment - given an arraylist of appointments and a specific time/date find if the appointment
     * exists (it is booked) and if so, return that appointment; otherwise, null.
     * @param a an Arraylist of Appointments
     * @param dayOfWeek the day of week of the appointment that we are searching for
     * @parameter startTime start time of the appointment that we are searching for
     * @return returns the Appointment object of the existing appointment; otherwise, null.
     */
    public static Appointment findExistingAppointment (ArrayList<Appointment> a, String dayOfWeek, int startTime) {
        Appointment p = null;
        boolean found = false;
        // write this method
        for (int i = 0; i < a.size()&& !found; i++) { ////loops through indexes in the array until specified parameters found
            p = a.get(i);

            if (p.getDayOfWeek().equals(dayOfWeek) && p.getStartTimeHour() == startTime && p.getApptType() != 0) {//checks to see if appointment slot is full
                found = true;
                //System.out.printf ("%20s %15s %10s %5s%02d:00 %5s%02d:00 %-10s%n", p.getClientName(), p.getClientNumber(), p.getDayOfWeek(),"", p.getStartTimeHour(), "", p.getEndTimeHour(), p.getApptTypeDesc());
            } else {
               p = null; 
            }
        }
        return p;
    }

    /** findFreeAppointment - given an arraylist of appointments and a specific time/date this
     * method will find if the time slot is available. If the spot is available it will return the appointment otherwise null
     * @param a an Arraylist of Appointments
     * @param dayOfWeek day of week of the available slot
     * @param startTime start time of the available slot
     * @return returns the Appointment object if the appointment was available; otherwise, null.
     * 
     */
    public static Appointment findFreeAppointment (ArrayList<Appointment> a, String dayOfWeek, int startTime) {
        Appointment p = null;
        // write this method, very similiar to findExistingAppointment
        boolean found = false;
        // write this method
        for (int i = 0; i < a.size()&& !found; i++) { //loops through indexes in the array until specified parameters found
            p = a.get(i);

            if (p.getDayOfWeek().equals(dayOfWeek) && p.getStartTimeHour() == startTime && p.getApptType() == 0) { //checks to see if appointment slot is free
                found = true;
                //System.out.printf ("%20s %15s %10s %5s%02d:00 %5s%02d:00 %-10s%n", p.getClientName(), p.getClientNumber(), p.getDayOfWeek(),"", p.getStartTimeHour(), "", p.getEndTimeHour(), p.getApptTypeDesc());
            }else {
               p = null; 
            }
        }
        return p;
    }

    /**
     * showAppointments - prints a report of all appointments for a specific _dayOfWeek
     * @param a ArrayList of appointments
     * @param _dayOfWeek input day of week
     */
    public static void showAppointments (ArrayList<Appointment> a, String _dayOfWeek)
    {
        // slam some java here
        String dayOfweek = _dayOfWeek;
        System.out.printf("%20s %15s %10s %10s %10s %-10s%n", "Client Name", "Phone", "Day", "Start", "End", "Type");
        System.out.printf ("==================================================================================%n");
        for (int i = 0; i < a.size(); i++) { //loops through indexes in the array
            Appointment p = a.get(i);
            if (p.getDayOfWeek().equals (dayOfweek)){//if statement to check for specific day                
                System.out.printf ("%20s %15s %10s %5s%02d:00 %5s%02d:00 %-10s%n", p.getClientName(), p.getClientNumber(), p.getDayOfWeek(),"", p.getStartTimeHour(), "", p.getEndTimeHour(), p.getApptTypeDesc()); 
            }
        }
    }

    /** findApptByName - prints all appointments that match a partial _clientName  
     * @param a ArrayList of Appointments
     * @param _clientName the client name to search for
     * 
     */
    public static void findApptByName (ArrayList<Appointment> a, String _clientName)
    {
        // slam some java here
        boolean found = false;
        System.out.printf("%20s %15s %10s %10s %10s %-10s%n", "Client Name", "Phone", "Day", "Start", "End", "Type");
        System.out.printf ("==================================================================================%n");
        for (int i = 0; i < a.size(); i++) { //loops through indexes in the array
            Appointment p = a.get(i);
            String clientName = _clientName;
            if (p.getClientName().equals (clientName)){ //if statement to check for specific client name
                System.out.printf ("%20s %15s %10s %5s%02d:00 %5s%02d:00 %-10s%n", p.getClientName(), p.getClientNumber(), p.getDayOfWeek(),"", p.getStartTimeHour(), "", p.getEndTimeHour(), p.getApptTypeDesc());
                found = true;
            }

        }
        
        if(found == false){
             System.out.printf ("No appointments found.%n");
        }

    }

    /**
     * createEmptyWeeklySchedule - create an empty six day schedule
     * Adds appointments for Monday-Saturday, 9 AM - 4 PM daily
     * @param a - ArrayList of Appointments
     * 
     */
    public static void createEmptyWeeklySchedule(ArrayList<Appointment> a) {
        String [] daysOfWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};  

        // process for all the days of the week
        for(int day = 0; day < daysOfWeek.length; day++){
            // add a schedule entry for every hour of the day that the salon is open
            for (int time = 9; time <= 16; time++) {
                // constructor used to create a blank appointment
                Appointment t;
                t = new Appointment (daysOfWeek[day], time);
                a.add(t);
            }
        } 

    }

    public static int printMenu(Scanner in) { //user GUI interface
        System.out.printf ("\f");
        System.out.printf ("Sandy's Hair Salon%n");
        System.out.printf ("1) Book an appointment%n");
        System.out.printf ("2) Find an appointment by Name%n");
        System.out.printf ("3) Print Calendar for a specific day%n");
        System.out.printf ("4) Cancel an appointment (Bonus)%n");
        System.out.printf ("9) To quit%n%n");
        System.out.printf ("Enter your selection: ");
        int select = in.nextInt();
        in.nextLine(); // consume \n
        return select;
    }
}
