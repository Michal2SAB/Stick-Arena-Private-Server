/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ballistickemu.Lobby.handlers;
import ballistickemu.Types.StickClient;
import ballistickemu.Tools.StickPacketMaker;
import ballistickemu.Main;
/**
 *
 * @author Simon
 */
public class CheckCapacityHandler {
    public static void HandlePacket(StickClient client)
    {
        if (Main.getLobbyServer().getClientRegistry().getAllClients().size() >= Main.maxPlayers)
            client.write(StickPacketMaker.getErrorPacket("0")); //sorry, the inn's full
        else
            client.write(StickPacketMaker.ServerHello());
    }
}
