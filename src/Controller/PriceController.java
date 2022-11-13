package Controller;

import model_Classes.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class PriceController {

    private HolidayController holidayCtrl;
    private Map<PriceChanger,Double> priceMap = new HashMap<>();

    public PriceController(){
        this.holidayCtrl = new HolidayController();
        populateDefaultPrices(priceMap);
    }

    private void populateDefaultPrices(Map<PriceChanger,Double> priceMap) {
        priceMap.put(MovieType.TWO_D, 0.0);
        priceMap.put(MovieType.THREE_D, 3.0);
        priceMap.put(MovieType.BLOCKBUSTER, 4.0);
        priceMap.put(CinemaType.PREMIUM, 1.0);
        priceMap.put(CinemaType.STANDARD, 0.0);
        priceMap.put(PriceType.STUDENT, 8.0);
        priceMap.put(PriceType.HOLIDAY, 14.0);
        priceMap.put(PriceType.NORMAL, 10.0);
        priceMap.put(PriceType.SENIOR_CITIZEN, 6.0);
        priceMap.put(PriceType.WEEKEND, 3.0);
    }

    public void addPriceChanger(PriceChanger priceChanger, double value){
        priceMap.put(priceChanger, value);
    }

    public void changePriceChanger(PriceChanger priceChanger, double newPrice){
        if(priceMap.containsKey(priceChanger)){
            priceMap.replace(priceChanger, newPrice);
        }
    }

    public void removePriceChanger(PriceChanger priceChanger){
        priceMap.remove(priceChanger);
    }

    public Map<PriceChanger,Double> getAllPriceChangers(){
        return priceMap;
    }

    public String getAllPriceChangersToString(){
        String result = "";
        for(PriceChanger priceChanger : priceMap.keySet()){
            result += priceChanger.toString() + " " + priceMap.get(priceChanger) + "\n";
        }
        return result;
    }

    public double getPrice(PriceChanger priceChanger){
        return priceMap.getOrDefault(priceChanger, 0.0);
    }

    public double computePrice(Session session, Cinema cinema, PriceType priceType){
        double addToPrice   = getPrice(session.getMovie().getType()) 
                            + getPrice(cinema.getCinemaType());
        if (session.isWeekend())
            addToPrice += 2.0;
        if(holidayCtrl.isHoliday(session.getSessionDateTime().toLocalDate())){
            return getPrice(PriceType.HOLIDAY) + addToPrice;
        }
        else{
            return getPrice(priceType) + addToPrice;
        }
    }
    
    public double queryTicketPrice (MovieType movieType, CinemaType cinemaType, boolean isWeekend, boolean isHoliday, PriceType ageGroup){
    	double addToPrice = getPrice(movieType)
    						+ getPrice(cinemaType);
    	
    	if (isWeekend) 
		{
    		addToPrice += 2.0;
		}
    	    	
//    	if (holidayCtrl.isHoliday(LocalDate.parse("2022-11-20")))
//    	{
//    		addToPrice += getPrice(PriceType.HOLIDAY);
//    	}
    	
    	if (isHoliday)
    	{
    		addToPrice += getPrice(PriceType.HOLIDAY);
    	}
    	
    	else 
    	{
    		// final component of price depends on whether ticket is sold as STUDENT, SENIOR or STANDARD ticket
//    		if (ageGroup.equals(PriceType.STUDENT))
//    		{
//    			addToPrice += getPrice(PriceType.STUDENT);
//    		}
    		
    		addToPrice += getPrice(ageGroup);
    	}
    	
    	return addToPrice;
    }

}