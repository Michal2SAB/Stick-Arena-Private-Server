/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ballistickemu.Tools;
import java.sql.ResultSet;
import java.util.Random;
import java.util.LinkedHashMap;
import ballistickemu.Types.StickItem;
import ballistickemu.Types.StickColour;
import java.sql.SQLException;
/**
 *
 * @author Simon
 */
public class QuickplayTool {
    private static LinkedHashMap<Integer, String> FirstNames;
    private static LinkedHashMap<Integer, String> LastNames;
    
 public static Boolean PopulateNameList()
    {
     FirstNames = new LinkedHashMap<>();
     LastNames = new LinkedHashMap<>();
        try
        {

            ResultSet rs = DatabaseTools.executeSelectQuery("select * from quickplay_names");
            int i_First = 0;
            int i_Last = 0;
            while (rs.next())
            {
                int type = rs.getInt("type");
                String value = rs.getString("value");

                if(type == 0)
                {
                    FirstNames.put(i_First, value);
                    i_First++;
                }
                else if(type == 1)
                {
                    LastNames.put(i_Last, value);
                    i_Last++;
                }
            }
         return (FirstNames.size() > 1) && (LastNames.size() > 1);
        }
        catch (SQLException e)
        {
            return false;
        }
    }

    public static String getRandomName()
    {
        String[] Name = new String[2];
        Name[0] = FirstNames.get(getRandomNum(0, FirstNames.size()));
        Name[1] = LastNames.get(getRandomNum(1, FirstNames.size()));
        return Name[0] + Name[1];
    }

    private static int getRandomNum(int min, int max)
    {
        Random r = new Random();
        return r.nextInt(max - min) + min;
    }

    public static LinkedHashMap<Integer, StickItem> getRandomInventory()
    {
        LinkedHashMap<Integer, StickItem> Inventory = new LinkedHashMap<>();
        
        StickColour SpinnerCol = new StickColour(getRandomNum(-99, 255), getRandomNum(-99, 255), getRandomNum(-99, 255),
                getRandomNum(-99, 255), getRandomNum(-99, 255), getRandomNum(-99, 255));

        int SpinnerID = getRandomNum(100, 141);

        //    public StickItem(int _ItemID, int _dbID, int _userDBID, int _itemType, Boolean _selected, StickColour _colour)
        StickItem Spinner = new StickItem(SpinnerID, 0, -1, 1, true, SpinnerCol);
        StickItem DummyPet = new StickItem(200, 1, -1, 2, true, SpinnerCol);

        Inventory.put(0, Spinner);
        Inventory.put(1, DummyPet);

        return Inventory;
    }
}
