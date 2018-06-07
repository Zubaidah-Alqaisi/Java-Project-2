/***********************************************************
 * Class: Destination                                      *
 *                                                         *
 * Author: Zubaidah Alqaisi                                *
 *                                                         *
 * Private members: String city, int normMiles, int        *
 *                  upgradeMiles, int superSaverMiles, int *
 *                  firstMileMonth, int endMileMonth.      *
 *                                                         *
 * Public members: String getCityName, int getNormMiles,   *
 *            int getSuperSaverMiles, int getUpgradeMiles, *
 *            int getFirstMileMonth, int getEndMileMonth,  *
 *            Destination(constructor).                    *
 *                                                         *
 * Purpose: This class will encapsulate information about  *
 *        a destination such as the name of the destination*
 *        city, the normal miles required for a ticket,    *
 *        the additional miles for upgrading, superSaver   *
 *        miles, the start month of the superSaver program *
 *        ,and the end month of the superSaver program.    *
 **********************************************************/

public class Destination {

    private String city;
    private int normMiles;
    private int upgradeMiles;
    private int superSaverMiles;
    private int firstMileMonth;
    private int endMileMonth;

    //constructor method
    public Destination(String city, int normMiles, int superSaverMiles, int upgradeMiles, int firstMileMonth, int endMileMonth)
    {
        this.city = city;
        this.normMiles = normMiles;
        this.upgradeMiles = upgradeMiles;
        this.superSaverMiles = superSaverMiles;
        this.firstMileMonth = firstMileMonth;
        this.endMileMonth = endMileMonth;
    }

    //get the name of the city variable
    public String getCityName()
    {
        return city;
    }

    //get the normal Mile variable
    public int getNormMiles()
    {
        return normMiles;
    }

    //get the superSaverMiles variable
    public int getSuperSaverMiles()
    {
        return superSaverMiles;
    }

    //get the upgradeMiles variable
    public int getUpgradeMiles()
    {
        return upgradeMiles;
    }

    //get the firstMileMonth variable
    public int getFirstMileMonth()
    {
        return firstMileMonth;
    }

    //get the endMileMonth variable
    public int getEndMileMonth()
    {
        return endMileMonth;
    }
}
