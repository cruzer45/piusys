/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package piusys.customers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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

    public static boolean saveCustomer(HashMap<String, String> params)
    {
        boolean successful = false;
        try
        {

            java.sql.Timestamp custCreated = new Timestamp(new Date().getTime());
            String custStatus = "Active";

            String sql = "INSERT INTO `customers` "
                    + " (`custName`, `custContact1`, `custContact2`, `custAddress`, `custPhone1`, `custPhone2`, `custEmail`, `custFax`, `custTIN`, `custNotes`, `custCreated`, `custStatus`) "
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, params.get("custName"));
            prep.setString(2, params.get("custContact1"));
            prep.setString(3, params.get("custContact2"));
            prep.setString(4, params.get("custAddress"));
            prep.setString(5, params.get("custPhone1"));
            prep.setString(6, params.get("custPhone2"));
            prep.setString(7, params.get("custEmail"));
            prep.setString(8, params.get("custFax"));
            prep.setString(9, params.get("custTIN"));
            prep.setString(10, params.get("custNotes"));
            prep.setTimestamp(11, custCreated);
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

    public static DefaultTableModel searchCustomers(String criteria)
    {
        criteria = Utilities.percent(criteria);
        DefaultTableModel model = new DefaultTableModel()
        {

            final Class<?>[] columnClasses =
            {
                String.class, String.class, String.class, String.class
            };

            @Override
            public boolean isCellEditable(int rowIndex, int mColIndex)
            {
                return false;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex)
            {
                return columnClasses[columnIndex];
            }
        };

        ArrayList<String> custIDs = new ArrayList<String>();
        ArrayList<String> custNames = new ArrayList<String>();
        ArrayList<String> custContact1s = new ArrayList<String>();
        ArrayList<String> custContact2s = new ArrayList<String>();

        try
        {
            String sql = " SELECT `custID`,`custName`,`custContact1`,`custContact2` "
                    + " FROM `customers` "
                    + " WHERE (custID LIKE ? OR custName LIKE ? OR custContact1 LIKE ? OR "
                    + " custContact2 LIKE ?) "
                    + " ORDER BY `custName`;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, criteria);
            prep.setString(2, criteria);
            prep.setString(3, criteria);
            prep.setString(4, criteria);
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

    public static HashMap<String, String> getCustomerDetails(String custID)
    {
        HashMap<String, String> details = new HashMap<String, String>();

        try
        {
            String sql = "SELECT `custID`, `custName`, `custContact1`, `custContact2`, `custAddress`, `custPhone1`, `custPhone2`, `custEmail`, `custFax`, `custTIN`, `custNotes`, `custStatus` "
                    + " FROM `PIUSYS`.`customers` "
                    + "WHERE `custID` = ?;";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setString(1, custID);
            ResultSet rs = prep.executeQuery();
            while (rs.next())
            {
                details.put("custID", rs.getString("custID"));
                details.put("custName", rs.getString("custName"));
                details.put("custContact1", rs.getString("custContact1"));
                details.put("custContact2", rs.getString("custContact2"));
                details.put("custAddress", rs.getString("custAddress"));
                details.put("custPhone1", rs.getString("custPhone1"));
                details.put("custPhone2", rs.getString("custPhone2"));
                details.put("custEmail", rs.getString("custEmail"));
                details.put("custFax", rs.getString("custFax"));
                details.put("custTIN", rs.getString("custTIN"));
                details.put("custNotes", rs.getString("custNotes"));
                details.put("custStatus", rs.getString("custStatus"));
            }
            rs.close();
            prep.close();

        }
        catch (Exception e)
        {
            String message = "An error occurred while getting the customer details.";
            logger.log(Level.SEVERE, message, e);
        }

        return details;
    }
}
