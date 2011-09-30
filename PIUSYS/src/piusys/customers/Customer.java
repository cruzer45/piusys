/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package piusys.customers;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author m.rogers
 */
public class Customer
{

    static final Logger logger = Logger.getLogger(Customer.class.getName());

    public static boolean saveCustomer()
    {
        boolean successful = false;
        try
        {
            String sql = "";
        }
        catch (Exception e)
        {
            String message = "An error occurred while saving the customer.";
            logger.log(Level.SEVERE, message, e);
        }
        return successful;
    }
}
