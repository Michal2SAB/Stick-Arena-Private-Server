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
package ballistickemu.Lobby;
import ballistickemu.Types.StickClient;
import ballistickemu.Types.StickClientRegistry;
import ballistickemu.Types.StickPacket;
import ballistickemu.Types.StickRoomRegistry;
import ballistickemu.Types.StickShop;
import ballistickemu.Tools.StickPacketMaker;
import java.util.ArrayList;
/**
 *
 * @author Simon
 */
public class LobbyServer {
    private StickClientRegistry ClientRegistry;
    private StickRoomRegistry RoomRegistry;
    private StickShop StickShop;

    public LobbyServer()
    {
        ClientRegistry = new StickClientRegistry(true);
        RoomRegistry = new StickRoomRegistry();
        StickShop = new StickShop();
    }

    public StickClientRegistry getClientRegistry()
    {
        return this.ClientRegistry;
    }

    public StickRoomRegistry getRoomRegistry()
    {
        return this.RoomRegistry;
    }

    public StickShop getShop()
    {
        return this.StickShop;
    }

    //broadcast to everything except specified ID
    public void BroadcastPacket(StickPacket packet, Boolean ExcludeChar, String client_UID)
    {
        this.ClientRegistry.ClientsLock.readLock().lock();
        ArrayList<StickClient> ToDC = new ArrayList<StickClient>();
        try
        {
            for (StickClient c : this.ClientRegistry.getAllClients())
            {
                try
                {
                    if (c.getLobbyStatus() && !((ExcludeChar) && (c.getUID().equalsIgnoreCase(client_UID)))) //jesus that logic is a bitch
                        c.write(packet);
                }
                catch(Exception e)
                {
                    if((c.getIoSession() != null))
                        e.printStackTrace();
                    ToDC.add(c);
                }
            }
        } finally {
            this.ClientRegistry.ClientsLock.readLock().unlock();
        }
        for (StickClient c : ToDC)
        {
            this.ClientRegistry.deregisterClient(c);
        }
        ToDC.removeAll(ToDC);
    }


   public void BroadcastPacket(StickPacket packet)
    {
       this.ClientRegistry.ClientsLock.readLock().lock();
       ArrayList<StickClient> ToDC = new ArrayList<StickClient>();
        try
        {
            for (StickClient c : this.ClientRegistry.getAllClients())
            {
                try
                {
                    if (c.getLobbyStatus())
                        c.write(packet);
                }
                catch(Exception e)
                {
                    if(c.getIoSession() != null)
                        e.printStackTrace();
                    ToDC.add(c);
                }
            }
        } finally {
            this.ClientRegistry.ClientsLock.readLock().unlock();
        }
        for (StickClient c : ToDC)
        {
            this.ClientRegistry.deregisterClient(c);
        }
        ToDC.removeAll(ToDC);
   }

   public void BroadcastAnnouncement (String Announcement)
   {
       this.ClientRegistry.ClientsLock.readLock().lock();
       ArrayList<StickClient> ToDC = new ArrayList<StickClient>();
        try
        {
            for (StickClient c : this.ClientRegistry.getAllClients())
                    {
                        try
                        {
                                c.writeCallbackMessage("Announcement: " + Announcement);
                        }
                        catch(Exception e)
                        {
                            ToDC.add(c);
                        }
                    }
        } finally {
            this.ClientRegistry.ClientsLock.readLock().unlock();
        }
        for (StickClient c : ToDC)
        {
            this.ClientRegistry.deregisterClient(c);
        }
        ToDC.removeAll(ToDC);
    }

      public void BroadcastAnnouncement2 (String Announcement)
   {
       this.ClientRegistry.ClientsLock.readLock().lock();
       ArrayList<StickClient> ToDC = new ArrayList<StickClient>();
        try
        {
            for (StickClient c : this.ClientRegistry.getAllClients())
                    {
                        try
                        {
                                c.write(StickPacketMaker.getAnnouncePacket(Announcement));
                        }
                        catch(Exception e)
                        {
                            ToDC.add(c);
                        }
                    }
        } finally {
            this.ClientRegistry.ClientsLock.readLock().unlock();
        }
        for (StickClient c : ToDC)
        {
            this.ClientRegistry.deregisterClient(c);
        }
        ToDC.removeAll(ToDC);
    }

   public void sendToUID(String ToUID, StickPacket packet)
   {
       if(this.ClientRegistry.UIDExists(ToUID))
           this.ClientRegistry.getClientfromUID(ToUID).write(packet);
   }

}
