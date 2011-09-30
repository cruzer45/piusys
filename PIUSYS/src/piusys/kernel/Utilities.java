package piusys.kernel;

import java.awt.Component;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author mrogers
 */
public class Utilities
{

    public static DateFormat YMD_Formatter = new SimpleDateFormat("yyyy-MM-dd");
    public static DateFormat MDY_Formatter = new SimpleDateFormat("MMM d, yyyy");

    /**
     *
     * @param content
     * @return Returns the parameter surrounded by single quotes.
     */
    public static String quotate(String content)
    {
        return "'" + content + "'";
    }

    /**
     *
     * @param a string you wish to be wrapped in percent signs
     * @return Returns the parameter surrounded by percent signs.
     */
    public static String percent(String content)
    {
        return "%" + content + "%";
    }

    /**
     * 
     * @param a double value.
     * @return a string formatted as money;
     */
    public static String formatAsMoney(double value)
    {
        DecimalFormat df = new DecimalFormat("$#,##0.00");
        String total = df.format(value);
        return total;
    }

    public static String roundDouble(double value)
    {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        String total = df.format(value);
        return total;
    }

    
    public static int showConfirmDialog(Component rootpane, String message)
    {
        return JOptionPane.showConfirmDialog(rootpane, message, "PIUSYS", JOptionPane.YES_NO_OPTION);
    }

    public static void showInfoMessage(Component rootpane, String message)
    {
        JOptionPane.showMessageDialog(rootpane, message, "PIUSYS", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void showErrorMessage(Component rootpane, String message)
    {
        JOptionPane.showMessageDialog(rootpane, message, "PIUSYS", JOptionPane.ERROR_MESSAGE);
    }

    public static void showWarningMessage(Component rootpane, String message)
    {
        JOptionPane.showMessageDialog(rootpane, message, "PIUSYS", JOptionPane.WARNING_MESSAGE);
    }

    public static void showCancelScreen(JInternalFrame frame)
    {
        String message = "Are you sure you want to close this window?";
        int response = JOptionPane.showConfirmDialog(frame, message, "PIUSYS", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION)
        {
            frame.dispose();
        }
    }
}
