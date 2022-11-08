package Boundary;

import java.util.Scanner;
import java.time.LocalDate;
import java.util.ArrayList;

import Controller.*;
import model_Classes.*;

public class ConfigSettingsUI {
    
    private static Scanner sc;
    private HolidayController holCtrl = new HolidayController();
	private PriceController priceCtrl = new PriceController();

    public void main() {

        int menuChoice;
        boolean exitMenu = false;
        sc = new Scanner(System.in);

        do {
            System.out.println("--------------------------------------------------");
            System.out.println("------------- ADMIN CONFIG SETTINGS --------------");
            System.out.println("--------------------------------------------------");
            System.out.println("(1) Update Ticket Prices");
            System.out.println("(2) Add/Delete/List Holidays");
            System.out.println("(3) Return to menu");
            System.out.println("");
            System.out.print("Select choice: ");

            menuChoice = sc.nextInt();

            System.out.println("");

            switch (menuChoice) {
                case 1:
                    updateTicketPrices();
                    break;

                case 2:
                    addDeleteListHolidays();
                    break;

                case 3:
                    exitMenu = true;
                    System.out.println("Returning to menu...");
                    System.out.println("");
                    break;

                default:
                    System.out.println("Please enter a correct number");
                    System.out.println("");

            }
        }

        while (!exitMenu);

    }

    public void updateTicketPrices() {
        
        int menuChoice;
        double updatedPrice;
        boolean exitMenu = false;
        sc = new Scanner(System.in);

        do {
            System.out.println("--------------------------------------------------");
            System.out.println("---------- ADMIN UPDATE TICKET PRICES ------------");
            System.out.println("--------------------------------------------------");
            System.out.println("(1) Update Student Ticket Price (Current Price: " + priceCtrl.getPrice(PriceType.STUDENT) + ")");
            System.out.println("(2) Update Senior Citizen Ticket Price (Current Price: " + priceCtrl.getPrice(PriceType.SENIOR_CITIZEN) + ")");
            System.out.println("(3) Update Standard Ticket Price (Current Price: " + priceCtrl.getPrice(PriceType.NORMAL) + ")");
            System.out.println("(4) Update Weekend Ticket Price (Current Price: " + priceCtrl.getPrice(PriceType.WEEKEND) + ")");
            System.out.println("(5) Update Holiday Ticket Price (Current Price: " + priceCtrl.getPrice(PriceType.HOLIDAY) + ")");
            System.out.println("(6) Return to menu");
            System.out.println("");
            System.out.print("Select choice: ");

            menuChoice = sc.nextInt();

            System.out.println("");

            switch (menuChoice) {
                case 1:
                    System.out.print("Enter Student Ticket Price: ");
                    updatedPrice = InputController.getDoubleFromUser();
                    priceCtrl.changePriceChanger(PriceType.STUDENT, updatedPrice);
                    System.out.print("Student Price Updated to: ");
                    System.out.println(priceCtrl.getPrice(PriceType.STUDENT));
                    break;

                case 2:
                    System.out.print("Enter Senior Citizen Ticket Price: ");
                    updatedPrice = InputController.getDoubleFromUser();
                    priceCtrl.changePriceChanger(PriceType.SENIOR_CITIZEN, updatedPrice);
                    System.out.print("Senior Citizen Price Updated to: ");
                    System.out.println(priceCtrl.getPrice(PriceType.SENIOR_CITIZEN));
                    break;

                case 3:
                    System.out.print("Enter Standard Ticket Price: ");
                    updatedPrice = InputController.getDoubleFromUser();
                    priceCtrl.changePriceChanger(PriceType.NORMAL, updatedPrice);
                    System.out.print("Standard Price Updated to: ");
                    System.out.println(priceCtrl.getPrice(PriceType.NORMAL));
                    break;

                case 4:
                    System.out.print("Enter Weekend Ticket Price: ");
                    updatedPrice = InputController.getDoubleFromUser();
                    priceCtrl.changePriceChanger(PriceType.WEEKEND, updatedPrice);
                    System.out.print("Weekend Price Updated to: ");
                    System.out.println(priceCtrl.getPrice(PriceType.WEEKEND));
                    break;

                case 5:
                    System.out.print("Enter Holiday Ticket Price: ");
                    updatedPrice = InputController.getDoubleFromUser();
                    priceCtrl.changePriceChanger(PriceType.HOLIDAY, updatedPrice);
                    System.out.print("Holiday Price Updated to: ");
                    System.out.println(priceCtrl.getPrice(PriceType.HOLIDAY));
                    break;
               
                case 6:
                    exitMenu = true;
                    System.out.println("Returning to menu...");
                    System.out.println("");
                    break;

                default:
                    System.out.println("Please enter a correct number");
                    System.out.println("");

            }
        }

        while (!exitMenu);

    }

    public void addDeleteListHolidays() {

        int menuChoice;
        boolean exitMenu = false;
        sc = new Scanner(System.in);

        do {
            System.out.println("--------------------------------------------------");
            System.out.println("----------- ADD/DELETE/LIST HOLIDAYS -------------");
            System.out.println("--------------------------------------------------");
            System.out.println("(1) Add Holiday");
            System.out.println("(2) Delete Holiday");
            System.out.println("(3) List All Holidays");
            System.out.println("(4) Return to menu");
            System.out.println("");
            System.out.print("Select choice: ");

            menuChoice = sc.nextInt();

            System.out.println("");

            switch (menuChoice) {
                case 1:
                    addHoliday();
                    break;

                case 2:
                    deleteHoliday();
                    break;

                case 3:
                    listHolidays();
                    break;
               
                case 4:
                    exitMenu = true;
                    System.out.println("Returning to menu...");
                    System.out.println("");
                    break;

                default:
                    System.out.println("Please enter a correct number");
                    System.out.println("");

            }
        }

        while (!exitMenu);
    
    }

    public void addHoliday() {

        System.out.println("Enter holiday date: ");
		LocalDate holiday = InputController.getDateFromUser();
		if (holCtrl.isHoliday(holiday)) {
			System.out.println("Holiday already exists!\n");
			return;
		}
		holCtrl.create(holiday);

    }

    public boolean listHolidays() {

        ArrayList<Holiday> holList = holCtrl.read();
		if(holList.isEmpty()){
			System.out.println("\nThere are no holidays declared!");
			return false;
		}
		else{
			System.out.println("\nCurrently declared holidays: \n");
			holList.forEach(Holiday -> printHoliday(Holiday));
		}
		System.out.println();
		return true;

    }

    public void deleteHoliday() {

        if(listHolidays()){
			System.out.println("Enter holiday date to delete: ");
			LocalDate holiday = InputController.getDateFromUser();
			if (!holCtrl.isHoliday(holiday)) {
				System.out.println("Holiday does not exist!\n");
				return;
			}
			holCtrl.delete(holiday);
		}

    }

    public void printHoliday(Holiday holiday) {
		System.out.println(holiday.getHolidayDateToString());
	}
}
