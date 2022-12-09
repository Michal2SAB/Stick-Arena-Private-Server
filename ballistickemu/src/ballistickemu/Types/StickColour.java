package ballistickemu.Types;

import ballistickemu.Tools.StringTool;


public class StickColour
{
  private int red1;
  private int green1;
  private int blue1;
  private int red2;
  private int green2;
  private int blue2;
  
  public StickColour(int _red1, int _green1, int _blue1, int _red2, int _green2, int _blue2)
  {
    this.red1 = _red1;
    this.green1 = _green1;
    this.blue1 = _blue1;
    this.red2 = _red2;
    this.green2 = _green2;
    this.blue2 = _blue2;
  }
  

  public StickColour() {}
  
  public void setColour1(int _red1, int _green1, int _blue1)
  {
    this.red1 = _red1;
    this.green1 = _green1;
    this.blue1 = _blue1;
  }
  
  public void setColour2(int _red2, int _green2, int _blue2)
  {
    this.red2 = _red2;
    this.green2 = _green2;
    this.blue2 = _blue2;
  }
  
  public String getColour1AsString()
  {
    String red = StringTool.PadStringLeft(String.valueOf(this.red1), "0", 3);
    String green = StringTool.PadStringLeft(String.valueOf(this.green1), "0", 3);
    String blue = StringTool.PadStringLeft(String.valueOf(this.blue1), "0", 3);
    return red + green + blue;
  }
  
  public String getColour2AsString()
  {
    String red = StringTool.PadStringLeft(String.valueOf(this.red2), "0", 3);
    String green = StringTool.PadStringLeft(String.valueOf(this.green2), "0", 3);
    String blue = StringTool.PadStringLeft(String.valueOf(this.blue2), "0", 3);
    return red + green + blue;
  }
  
  public void setColour1FromString(String toParse)
  {
    if (toParse.length() != 9)
      return;
    this.red1 = Integer.valueOf(toParse.substring(0, 3)).intValue();
    this.green1 = Integer.valueOf(toParse.substring(3, 6)).intValue();
    this.blue1 = Integer.valueOf(toParse.substring(6, 9)).intValue();
  }
  
  public void setColour2FromString(String toParse)
  {
    if (toParse.length() != 9)
      return;
    this.red2 = Integer.valueOf(toParse.substring(0, 3)).intValue();
    this.green2 = Integer.valueOf(toParse.substring(3, 6)).intValue();
    this.blue2 = Integer.valueOf(toParse.substring(6, 9)).intValue();
  }
  
  public int getRed1()
  {
    return this.red1;
  }
  
  public int getGreen1()
  {
    return this.green1;
  }
  
  public int getBlue1()
  {
    return this.blue1;
  }
  
  public int getRed2()
  {
    return this.red2;
  }
  
  public int getGreen2()
  {
    return this.green2;
  }
  
  public int getBlue2()
  {
    return this.blue2;
  }
}
