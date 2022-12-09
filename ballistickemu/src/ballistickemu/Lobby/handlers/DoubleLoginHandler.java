package ballistickemu.Lobby.handlers;

import ballistickemu.Types.StickClient;


public class DoubleLoginHandler
{
  public static void HandlePacket(StickClient client)
  {
      System.out.println("Secondary login");
      client.getSecondaryLoginPacket();
  }
}
