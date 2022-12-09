/*
 *     THIS FILE AND PROJECT IS SUPPLIED FOR EDUCATIONAL PURPOSES ONLY.
 *
 *     This program is free software; you can redistribute it
 *     and/or modify it under the terms of the GNU General
 *     Public License as published by the Free Software
 *     Foundation; either version 2 of the License, or (at your
 *     option) any later version.
 *
 *     This program is distributed in the hope that it will be
 *     useful, but WITHOUT ANY WARRANTY; without even the
 *     implied warranty of MERCHANTABILITY or FITNESS FOR A
 *     PARTICULAR PURPOSE. See the GNU General Public License
 *     for more details.
 *
 *     You should have received a copy of the GNU General
 *     Public License along with this program; if not, write to
 *     the Free Software Foundation, Inc., 59 Temple Place,
 */
package ballistickemu.Lobby.handlers;
import ballistickemu.Main;
import ballistickemu.Types.StickClient;
import ballistickemu.Types.StickRoom;
import ballistickemu.Types.StickPacket;
import ballistickemu.Tools.StickPacketMaker;
/**
 *
 * @author Simon
 */
public class RoomDetailRequestHandler {
public static void HandlePacket(StickClient client, String Packet)
{
    StickRoom Room;
        try
        {
            String RoomName = Packet.substring(2, Packet.length()).replaceAll("\0", "");
            Room = Main.getLobbyServer().getRoomRegistry().GetRoomFromName(RoomName);
            if (Room != null)
            {
                StickPacket response = StickPacketMaker.getSendRoundDetail(Room.getMapID(), Room.getCycleMode(), Room.GetCR().getAllClients().size(), Room.getCurrentRoundTime());
                client.write(response);
            }
        }
        catch (Exception e)
        {
            System.out.println("Exception parsing RoomDetailRequest packet");
            System.out.println(e.toString());

        }
    }
}
