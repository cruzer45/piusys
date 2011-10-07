/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package piusys.customers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
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

    public static DefaultTableModel searchCustomers()
    {
        DefaultTableModel model = new DefaultTableModel()
        {

            @Override
            public boolean isCellEditable(int rowIndex, int mColIndex)
            {
                return false;
            }

            @Override
            public Class getColumnClass(int columnIndex)
            {
                Object o = getValueAt(0, columnIndex);
                if (o == null)
                {
                    return Object.class;
                }
                else
                {
                    return o.getClass();
                }
            }
        };

        ArrayList<String> custIDs = new ArrayList<String>();
        ArrayList<String> custNames = new ArrayList<String>();
        ArrayList<String> custContact1s = new ArrayList<String>();
        ArrayList<String> custContact2s = new ArrayList<String>();

        try
        {
            String sql = "SELECT `custID`,`custName`,`custContact1`,`custContact2` FROM `customers` ORDER BY `custName` ;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                custIDs.add(rs.getString("custID"));
                custNames.add(rs.getString("custName"));
                custContact1s.add(rs.getString("custContact1"));
                custContact2s.add(rs.getString("custContact2"));
            }
            rs.close();
            prep.close();
        }
        catch (Exception e)
        {
            String message = "An error occurred while getting the list of customers.";
            logger.log(Level.SEVERE, message, e);
        }

        model.addColumn("ID", custIDs.toArray());
        model.addColumn("Name", custNames.toArray());
        model.addColumn("Pri. Contact", custContact1s.toArray());
        model.addColumn("Sec. Contact", custContact2s.toArray());
        return model;
    }
}
