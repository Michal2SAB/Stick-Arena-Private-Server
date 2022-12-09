/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
 
package ballistickemu.Game.handlers;
import ballistickemu.Types.StickClient;
/**
 *
 * @author Simon
 */
public class KillHandler {
    public static void HandlePacket(StickClient client, String packet)
    {
        //victim id = client.getID
        String KillerID = packet.substring(1, 4);
        StickClient killer = client.getRoom().GetCR().getClientfromUID(KillerID);
        if(killer != null)
        {
            killer.setGameKills(killer.getGameKills() + 1);
        }
 
        client.setGameDeaths(client.getGameDeaths() + 1);
        GamePacketBroadcastHandler.HandlePacket(client, packet);
    }
 
}
