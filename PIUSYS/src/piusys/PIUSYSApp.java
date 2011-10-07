/*
 * PIUSYSApp.java
 */
package piusys;

import java.util.EventObject;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;
import piusys.kernel.Environment;
import piusys.kernel.Utilities;

/**
 * The main class of the application.
 */
public class PIUSYSApp extends SingleFrameApplication
{

    /**
     * At startup create and show the main frame of the application.
     */
    @Override
    protected void startup()
    {
        show(new PIUSYSView(this));
        PIUSYSApp.getApplication().addExitListener(new ExitListener()
        {

            public boolean canExit(EventObject arg0)
            {
                return exitMethod();
            }

            public void willExit(EventObject arg0)
            {
            }

            private boolean exitMethod()
            {
                String message = "Are you sure you want to exit?";
                int response = Utilities.showConfirmDialog(Environment.getMainFrame(), message);
                if (response == JOptionPane.YES_OPTION)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
        });
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override
    protected void configureWindow(java.awt.Window root)
    {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of PIUSYSApp
     */
    public static PIUSYSApp getApplication()
    {
        return Application.getInstance(PIUSYSApp.class);
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args)
    {
         String[] li =
        {
            "Licensee=JYLOO Software", "LicenseRegistrationNumber=------", "Product=Synthetica", "LicenseType=For internal tests only", "ExpireDate=--.--.----", "MaxVersion=2.999.999"
        };
        UIManager.put("Synthetica.license.info", li);
        UIManager.put("Synthetica.license.key", "A90D3B2B-53186981-0ED9A1C2-F00601BF-889A89BA");

        String[] li2 =
        {
            "Licensee=JYLOO Software", "LicenseRegistrationNumber=------", "Product=SyntheticaAddons", "LicenseType=For internal tests only", "ExpireDate=--.--.----", "MaxVersion=1.999.999"
        };
        UIManager.put("SyntheticaAddons.license.info", li2);
        UIManager.put("SyntheticaAddons.license.key", "CC98A980-13381C7D-30604C91-F5BC5376-776C6006");
        
        launch(PIUSYSApp.class, args);
    }
}
