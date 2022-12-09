package ballistickemu.Types;



public class StickItem
{
  private int ItemID;
  

  private int dbID;
  
  private int userDBID;
  
  private int itemType;
  
  private Boolean selected;
  
  private StickColour colour;
  

  public StickItem(int _ItemID, int _dbID, int _userDBID, int _itemType, Boolean _selected, StickColour _colour)
  {
    this.ItemID = _ItemID;
    this.dbID = _dbID;
    this.userDBID = _userDBID;
    this.itemType = _itemType;
    this.selected = _selected;
    this.colour = _colour;
  }
  

  public int getItemID()
  {
    return this.ItemID;
  }
  
  public int getItemDBID()
  {
    return this.dbID;
  }
  
  public int getUserDBID() {
    return this.userDBID;
  }
  
  public int getitemType() {
    return this.itemType;
  }
  
  public Boolean isSelected() {
    return this.selected;
  }
  
  public StickColour getColour() {
    return this.colour;
  }
  
  public void setItemID(int _itemID)
  {
    this.ItemID = _itemID;
  }
  
  public void setItemDBID(int _itemDBID)
  {
    this.dbID = _itemDBID;
  }
  
  public void setUserDBID(int _userDBID)
  {
    this.ItemID = _userDBID;
  }
  
  public void setSelected(Boolean _selected)
  {
    this.selected = _selected;
  }
  
  public void setColour(StickColour _colour)
  {
    this.colour = _colour;
  }
  
  public void setItemType(int Type)
  {
    this.itemType = Type;
  }
}
