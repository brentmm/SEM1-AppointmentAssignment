import java.util.*;
/**
 * Appointment - 
 * This class grabs all required information from the program used by the user
 * It then declares the variables within the class
 * After setting variables within the class it formats a string
 * After fomating it returns it 
 * It then prints out as a clean formated schedual when the user chooses to view the schedual in the user program
 * 
 * 
 * Brent Martin
 * December 11th 2020
 */
// Standard import for the Scanner class
import java.util.*;
public class Appointment { 
    //class variables
    private String clientName = "";
    private String clientNumber = "";
    private short appType = 0;
    private String typeDesc = "";
    private String dayOfWeek = "";
    private int starTimeHour = 0;
    private int endTimeHour = 0;
    private String toString = "";

    Appointment(String _clientName, String _clientNumber,short _apptType, String _dayOfWeek, int _startTimeHour){ //constructor for when all info is called
        clientName = _clientName;
        clientName = _clientNumber; 
        appType = _apptType;
        dayOfWeek = _dayOfWeek;
        starTimeHour = _startTimeHour;       

    }

    Appointment(String _dayOfWeek, int _startTimeHour){ //constructor for when only day and hour are called
        dayOfWeek = _dayOfWeek;
        starTimeHour = _startTimeHour;
    }   

    public String getClientName(){ //grabs client name
        return clientName;  
    }

    public void setClientName (String _clientName){ //sets client name variable for schedual
        clientName = _clientName;
    }

    public String getClientNumber(){ //grabs client number
        return clientNumber;
    }

    public void setClientNumber(String _clientNumber){ //sets clients number for schedual line
        clientNumber = _clientNumber;
    }

    public short getApptType(){ //grabs appointment type
        return appType;
    }

    public String getApptTypeDesc(){ //takes appointment type and converts number to its string couterpart
        if(appType == 0){
            typeDesc = "Available";
        } else if(appType == 1){
            typeDesc = "Mens cut";
        } else if(appType == 2){
            typeDesc = "Ladies cut";
        } else if(appType == 3){
            typeDesc = "Mens colouring";
        }else if(appType == 4){
            typeDesc= "Ladies colouring";
        } 

        return typeDesc;
    }

    public void setApptType(short _apptType){
        appType = _apptType;
    }

    public String getDayOfWeek(){ //grabs day of week
        return dayOfWeek;
    }

    public void setDayOfWeek(String _dayOfWeek){ //sets day of week vaule for schedual
        dayOfWeek = _dayOfWeek;
    }

    public int getStartTimeHour(){ //grabs start time
        return starTimeHour;
    }

    public int getEndTimeHour(){ //calculates end time based off start and returns
        endTimeHour = starTimeHour + 1;
        return endTimeHour;
    }

    public void set(String _clientName, String _clientNumber,short _apptType){ //defines client info and appointment values
        clientName = _clientName;
        clientNumber = _clientNumber;
        appType = _apptType;
    }

    public void cancel() { //cancels an appointment by setting empty values
        clientName = "";
        clientNumber = "";
        appType = 0;
    }

    public String toString() { //returns formated information to be printed
        return String.format("[ %s, %02d:00 to %02d:00 %s, %s Phone: %s ]", dayOfWeek, starTimeHour,getEndTimeHour() , clientName, getApptTypeDesc(), clientNumber);
    }

}
