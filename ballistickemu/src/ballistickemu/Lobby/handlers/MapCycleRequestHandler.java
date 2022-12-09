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
import ballistickemu.Tools.StickPacketMaker;
/**
 *
 * @author Simon
 */
public class MapCycleRequestHandler {
    public static void HandlePacket(StickClient client, String Packet)
    {
        String RoomName = Packet.substring(2, Packet.length() - 4);
        String Action = Packet.substring(Packet.length() - 3, Packet.length() - 1);
     //   System.out.println("Action: " + Action);
        //Console.WriteLine(RoomName);
        StickRoom Room = Main.getLobbyServer().getRoomRegistry().GetRoomFromName(RoomName);
        if (Action.equalsIgnoreCase("mp"))
        {
            if (Room != null && Room.getMapCycleList() != null)
            {
                client.write(StickPacketMaker.getMapCycleRequestResponse(Room.getMapCycleList()));
                return;
            }
        } else if (Action.equalsIgnoreCase("rc"))
        {
            if(Room.getCreatorName() == null)
                client.write(StickPacketMaker.getRoomCreatorResponse("bad player"));
            else
                client.write(StickPacketMaker.getRoomCreatorResponse(Room.getCreatorName()));
            return;
                //insert code for something here
        }
        //client.write(StickPacketMaker.getMapCycleRequestResponse(Room.getMapID()));
        client.write(StickPacketMaker.getMapCycleRequestResponse("No WORKY"));
    }
}
