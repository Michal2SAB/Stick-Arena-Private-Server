package ballistickemu.Types;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;


public class StickRoomRegistry
{
  private LinkedHashMap<Integer, StickRoom> RoomList;
  private ScheduledExecutorService STP;
  public ReentrantReadWriteLock RoomLock = new ReentrantReadWriteLock(true);
  
  public LinkedHashMap<Integer, StickRoom> getRoomList()
  {
    return this.RoomList;
  }
  

  @SuppressWarnings("unchecked")
  public StickRoomRegistry()
  {
    this.STP = Executors.newScheduledThreadPool(3);
    this.RoomList = new LinkedHashMap();
  }
  
  public Collection<StickRoom> GetAllRooms()
  {
    return this.RoomList.values();
  }
  
  public void RegisterRoom(StickRoom room)
  {
    this.RoomLock.writeLock().lock();
    
    try
    {
      room.setStorageKey(this.RoomList.size());
      this.RoomList.put(this.RoomList.size(), room);

    }
    finally
    {
      this.RoomLock.writeLock().unlock();
    }
  }
  
  public void deRegisterRoom(StickRoom room)
  {
    this.RoomLock.writeLock().lock();
    try
    {
      for (StickRoom R : GetAllRooms())
      {
        if ((room != null) && (room.equals(R)))
        {
          this.RoomList.remove(room.getStorageKey());
          break;
        }
      }
    }
    finally
    {
      this.RoomLock.writeLock().unlock();
    }
  }
  
  public void scheduleRoomTimer(Runnable r)
  {
    this.STP.scheduleAtFixedRate(r, 0L, 1L, TimeUnit.SECONDS);
  }
  

  public Boolean RoomExists(String Name)
  {
    return GetRoomFromName(Name) == null;
  }
  
  public StickRoom GetRoomFromName(String Name)
  {
    this.RoomLock.readLock().lock();
    try
    {
      for (StickRoom S : GetAllRooms())
      {
        if (S.getName().equalsIgnoreCase(Name))
          return S;
      }
    } finally {
      this.RoomLock.readLock().unlock();
    }
    return null;
  }
  

  public String GetRoomPacketInfo()
  {
    this.RoomLock.readLock().lock();
    try
    {
      StringBuilder SB = new StringBuilder();
      SB.append("_0;");
      for (StickRoom S : GetAllRooms())
      {
        if (!S.getPrivacy().booleanValue())
        {
          SB.append(S.getName());
          
          if (S.getNeedsPass().booleanValue()) {
            SB.append("1");
          } else
            SB.append("0");
          SB.append(";");
        }
      }
      

      return SB.toString();
    } finally {
      this.RoomLock.readLock().unlock();
    }
  }
}
