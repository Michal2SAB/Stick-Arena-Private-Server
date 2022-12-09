/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ballistickemu.Lobby.handlers;
import ballistickemu.Tools.StickPacketMaker;
import ballistickemu.Types.StickClient;
/**
 *
 * @author Simon
 */
public class InventoryRequestHandler {
    public static void HandlePacket (StickClient client, String packet)
    {
        //just for the time being
        if (client.getInventory().size() < 2)
            client.write(StickPacketMaker.getDefaultInventoryPacket());
        else
            client.write(StickPacketMaker.getInventoryPacket(client.getFormattedInventoryData()));
    }
}
