package Controller;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

import model_Classes.*;

public class HolidayController {
    
    public final static String FILENAME = "src/datastorage/holidays.txt";

    public void create(LocalDate holidayDate) {
        Holiday holiday = new Holiday(holidayDate);
        ArrayList<Holiday> allData = new ArrayList<Holiday>();
        File tempFile = new File(FILENAME);
        if (tempFile.exists()) 
            allData = read();
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILENAME));
            allData.add(holiday);
            out.writeObject(allData);
            out.flush();
            out.close();
        } catch (IOException e) {
        }
    } 

    @SuppressWarnings("unchecked")
    public ArrayList<Holiday> read() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME));   
            ArrayList<Holiday> holidayListing = (ArrayList<Holiday>) ois.readObject();
            ois.close();
            return holidayListing;
        } catch (ClassNotFoundException | IOException e) {
        } 
        return new ArrayList<Holiday>();
    }

    public void delete(LocalDate valueToSearch) {
        ArrayList<Holiday> allData = read();
        ArrayList<Holiday> returnData = new ArrayList<Holiday>();
        
        for (int i=0; i<allData.size(); i++){
            Holiday h = allData.get(i);
            if (!(h.getHolidayDate().equals(valueToSearch)))
                returnData.add(h);
        }

        replaceExistingFile(FILENAME, returnData);
    }

    public boolean isHoliday(LocalDate valueToSearch) {
        ArrayList<Holiday> allData = read();
        for (int i=0; i<allData.size(); i++){
            if (allData.get(i).getHolidayDate().equals(valueToSearch))
                return true;
        }
        return false;
    }

    public void replaceExistingFile(String filename, ArrayList<Holiday> data){
        File tempFile = new File(filename);
        if (tempFile.exists()) 
            tempFile.delete();
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
            out.writeObject(data);
            out.flush();
            out.close();
        } catch (IOException e) {
        }
    }
}