package piusys.projects;

import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import piusys.kernel.Environment;

/**
 *
 * @author mrogers
 */
public class Project
{
    static final Logger logger = Logger.getLogger(Project.class.getName());
    public static boolean saveProject(HashMap<String, Object> params)
    {
        boolean successful = false;
        try
        {
            String sql = " INSERT INTO `projects` "
                    + " (`proCustID`, `proTitle`, `proDescription`, "
                    + " `proEstimatedCost`, `proActualCost`, `proLead`, "
                    + " `proLocation`, `proLatutude`, `proLongitude`, `proStatus`) "
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement prep = Environment.getConnection().prepareStatement(sql);
            prep.setInt(1, 1);
        }
        catch (Exception e)
        {
          String message = "An error occurred while saving the project.";
            logger.log(Level.SEVERE, message, e);
        }
        return successful;
    }
    
}
