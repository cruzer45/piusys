/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package piusys.customers;

import java.sql.PreparedStatement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import piusys.kernel.Environment;
import piusys.kernel.Utilities;

/**
 *
 * @author m.rogers
 */
public class Customer
{

    static final Logger logger = Logger.getLogger(Customer.class.getName());

    public static boolean saveCustomer(String custName, String custContact1,
            String custContact2, String custAddress, String custPhone1,
            String custPhone2, String custEmail, String custFax,
            String custTIN, String custNotes)
    {
        boolean successful = false;
        try
        {
            String custCreated = Utilities.YMD_Formatter.format(new Date());
            String custStatus = "Active";

            String sql = "INSERT INTO `customers` "
                    + " (`custName`, `custContact1`, `custContact2`, `custAddress`, `custPhone1`, `custPhone2`, `custEmail`, `custFax`, `custTIN`, `custNotes`, `custCreated`, `custStatus`) "
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, custName);
            prep.setString(2, custContact1);
            prep.setString(3, custContact2);
            prep.setString(4, custAddress);
            prep.setString(5, custPhone1);
            prep.setString(6, custPhone2);
            prep.setString(7, custEmail);
            prep.setString(8, custFax);
            prep.setString(9, custTIN);
            prep.setString(10, custNotes);
            prep.setString(11, custCreated);
            prep.setString(12, custStatus);
            prep.execute();
            prep.close();
            successful = true;
        }
        catch (Exception e)
        {
            String message = "An error occurred while saving the customer.";
            logger.log(Level.SEVERE, message, e);
        }
        return successful;
    }
}
