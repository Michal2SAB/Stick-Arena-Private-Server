package ballistickemu.Types;

import ballistickemu.Tools.DatabaseTools;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;

public class StickShop
{
  private LinkedHashMap<Integer, Integer> ShopList;
  
  @SuppressWarnings("unchecked")
  public StickShop()
  {
    this.ShopList = new LinkedHashMap();
  }
  
  public int getPriceByItemID(int itemID)
  {
    if (this.ShopList.containsKey(itemID))
    {
      return (this.ShopList.get(itemID)).intValue();
    }
    
    return -1;
  }
  

  public Boolean PopulateShop()
  {
    try
    {
      ResultSet rs = DatabaseTools.executeSelectQuery("select * from shop");
      while (rs.next())
      {
        int IID = rs.getInt("itemID");
        int cost = rs.getInt("cost");
        this.ShopList.put(IID, cost);
      }
      if (this.ShopList.size() > 1) {
        return true;
      }
      return false;
    }
    catch (SQLException e)
    {
    }
    return false;
  }
}
