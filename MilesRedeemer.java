/**********************************************************
 * Class: MilesRedeemer                                   *
 *                                                        *
 * Author: Zubaidah Alqaisi                               *
 *                                                        *
 * Private members: int remainingMiles, destinationList   *
 *                                                        *
 * Public members: ReadDestinations(Scanner fileScanner)  *
 *     getCityNames(), redeemMiles(int miles, int month), *
 *       getRemainingMiles().                             *
 *                                                        *
 * Purpose: This class encapsulate the logic for redeeming*
 *          mileage. It reads a file and place it in a    *
 *          arrayList of destinations, return a list of   *
 *          cities. redeem user miles, and return user's  *
 *          remaining miles.                              *
 *********************************************************/

import java.io.*;
import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

public class MilesRedeemer {

    //declaring a private member called remainingMiles and initialize it to 0
    private int remainingMiles = 0;

    //declaring ArrayList of type Destination called destinationList
    private ArrayList<Destination> destinationList = new ArrayList<Destination>();

     /*********************************************************************
     * Function: readDestination():                                       *
     *                                                                    *
     * Purpose: This method uses the Scanner object to read and parse the *
     *          destination data into an ArrayList of Destination objects *
     *          ,which should then be sorted by their normal mileage.     *
     * Arguments: the Scanner object                                      *
     * Return: none (void)                                                *
     *********************************************************************/

    public void readDestinations(Scanner fileScanner)
    {
        //declaring a variable that will hold the inputs from the file
        String record;

        //loop while there are still inputs from the file
        while (fileScanner.hasNext())
        {
            record = fileScanner.nextLine();

            //find the pattern of ; to read information
            String [] arrayString = record.split(";");
            //find pattern of - to determine the months
            String [] monthsArray = arrayString[4].split("-");

            //add in the arrayList
            destinationList.add(new Destination(arrayString[0], Integer.parseInt(arrayString[1]),
                    Integer.parseInt(arrayString[2]),
                    Integer.parseInt(arrayString[3]),
                    Integer.parseInt(monthsArray[0]),
                    Integer.parseInt(monthsArray[1])));
        }

    } // End of readDestination() method

    /***************************************************************
     * function: getCityName()                                     *
     *
     * Purpose: This method creates an array of type string to     *
     *           store list of cities. Then it loops through the   *
     *           array to get the city name. Once it gets it, it   *
     *           will sort the names alphabetically and return the *
     *           city name.                                        *
     * Arguments: none                                             *
     * Return: string array of cities names                        *
     **************************************************************/

    public String[] getCityNames()
    {
        //declaring array of type string to hold the cities names
        String[] cityName = new String[destinationList.size()];

        //loop through the arrayList DestinationList and add each city name to the array cityName
        for(int i = 0; i < destinationList.size(); i++){

            cityName[i] = destinationList.get(i).getCityName();

        }
        //sort the cities by name
        Arrays.sort(cityName);

        return cityName;

    } //End of getCityName() method
    
    /*****************************************************
     * Function: findDestination()                       *
     * Purpose: This method loops through the destination*
     *      list and compare the string argument cityName*
     *      to the city name in the destinationList.Once *
     *      it is equal, it returns the destination info *
     *      that includes the required miles, miles for  *
     *      upgrading, miles for superSaver,and months   *
     *      for superSaver associated with that city Name*
     *                                                   *
     * Argument: String cityName                         *
     * Return: ArrayList Destination                     *
     ****************************************************/
    
    public Destination findDestination(String cityName)
    {
        // looping through the destinations and find a match to the city name
        for (Destination destination : destinationList)
        {
            // if the city name equal to the city name in the destinationList return its info
            if (destination.getCityName().equals(cityName))
             return destination; // return info associated with that city name
        }
        
        // if no match with city name return null
        return null; 
    }

    /***************************************************************
     * Function: redeemMiles()                                     *
     *                                                             *
     * Purpose: This method should return an ArrayList of String   *
     *          objects containing descriptions of redeemed tickets*
     *          to be printed by the main program. It also save the*
     *         miles remaining after the tickets have been redeemed*
     *                                                             *
     * Arguments: miles(total available miles) and month (the      *
     * desired month of departure).                                *
     *                                                             *
     * Return:  ArrayList of type string (userInfo)                *
     **************************************************************/

    public ArrayList<String> redeemMiles(int miles, int month) {

        int mileUsage;

        //declaring ArrayList of type string called userInfo to hold the user flight info
        ArrayList<String> userInfo = new ArrayList<>();

        //declaring ArrayList of type String to hold the list of available cities for the use based on the miles the user's has
        ArrayList<String> availableCityList = new ArrayList<String>();

        //sort the array based on normal destination using lambda expression
        destinationList.sort((p1, p2) -> p2.getNormMiles() - p1.getNormMiles());

        //initialize remainingMiles
        remainingMiles = miles;

        // looping through the ArrayList
        for (Destination destination : destinationList)
        {
            // if the departure month provided by the user is within the range of months provided
            if (month >= destination.getFirstMileMonth() && month <= destination.getEndMileMonth())
            {
                // then it is the superSaver mileage
                mileUsage = destination.getSuperSaverMiles();
            }
            else {
                // or it is the normal mileage
                mileUsage = destination.getNormMiles();
            }
            //if the user miles less than the remaining miles that means they can pay for it so add the cities to the list of the destinations
            if (mileUsage < remainingMiles) {

                availableCityList.add(destination.getCityName());
                //subtract from remaining miles
                remainingMiles -= mileUsage;
            }
        }

        // if the arrayList of available cities in not empty
        if (!availableCityList.isEmpty()) {

            int i = 0;
            // create arrayList of string type to hold the flight class types
            ArrayList<String> flightClass = new ArrayList<String>();

            // loop through the destinations
            for (Destination destination : destinationList) {

                // if i exceeded the size of the availableCityList ArrayList then break outside of the loop
                if (i == availableCityList.size()) {
                    break;
                }

                if (availableCityList.get(i).equals(destination.getCityName())) {
                    i++;

                    // if the user's upgrade miles are less than the user's remaining miles then the user can upgrade to first class
                    if (destination.getUpgradeMiles() <= remainingMiles) {

                        // subtract the upgradeMiles from remaining miles
                        remainingMiles -= destination.getUpgradeMiles();
                        flightClass.add("First class");

                    }

                    //otherwise list it as economy
                    else {

                        flightClass.add("Economy class");
                    }

                }
            }

            int index = 0;
            // loop through the arrayList available cities add it to user info and  print it out
            for (String city : availableCityList) {
                userInfo.add("* A Trip to " + city + ", " + flightClass.get(index));
                index++;
            }
        }

        return userInfo;

    } // End of redeemMiles()

    //return remainingMiles
    public int getRemainingMiles() {
        return remainingMiles;

    } //End of getRemainingMiles()

} // End of MilesRedeemer class
