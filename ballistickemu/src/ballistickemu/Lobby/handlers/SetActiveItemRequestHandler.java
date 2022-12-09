/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ballistickemu.Lobby.handlers;
import ballistickemu.Tools.DatabaseTools;
import ballistickemu.Types.StickClient;
import ballistickemu.Types.StickItem;
import java.sql.PreparedStatement;
/**
 *
 * @author Simon
 */
public class SetActiveItemRequestHandler {
    public static void HandlePacket(StickClient client, String packet)
    {
        try
        {
            //System.out.println("Packet: " + packet);
            //if (packet.replaceAll("\0", "").equalsIgnoreCase("0d1")) //for some reason this packet gets sent sometimes; discard it
                //return;
            if (packet.indexOf("undefined") != -1)
                return; //strange client thingy going on here, discard packet

            int itemDBID = Integer.valueOf(packet.substring(2, packet.length() - 1));
            StickItem toChange = client.getItemByID(itemDBID);
            if(toChange != null)
            {
                //toChange.setSelected(true);
                client.setSelectedItem(toChange.getitemType(), itemDBID);
                PreparedStatement ps = DatabaseTools.getDbConnection().prepareStatement("UPDATE `inventory` SET `selected` = 0 WHERE `itemtype` = ? AND `userid` = ?");
                ps.setInt(1, toChange.getitemType());
                ps.setInt(2, toChange.getUserDBID());
                ps.executeUpdate();
                ps = DatabaseTools.getDbConnection().prepareStatement("UPDATE `inventory` SET `selected` = 1 WHERE `id` = ? AND `userid` = ?");
                ps.setInt(1, toChange.getItemDBID());
                ps.setInt(2, toChange.getUserDBID());
                ps.executeUpdate();

            }
            else
            {
                System.out.println("Error setting active thingy as it was null or something.");
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
