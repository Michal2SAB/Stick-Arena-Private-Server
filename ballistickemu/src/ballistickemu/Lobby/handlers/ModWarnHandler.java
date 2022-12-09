/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ballistickemu.Lobby.handlers;
import ballistickemu.Types.StickClient;
import ballistickemu.Main;
import ballistickemu.Tools.StickPacketMaker;
/**
 *
 * @author Simon
 */
public class ModWarnHandler {
    public static void HandlePacket(StickClient client, String Packet)
    {
        
        if (Packet.length() < 5 || (!client.getModStatus())) { return; }
            String ToUID = Packet.substring(2, 5);
            String WarnMSG = Packet.substring(5);

        Main.getLobbyServer().sendToUID(ToUID, StickPacketMaker.getModWarn(WarnMSG));
    }
}
