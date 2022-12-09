package ballistickemu.Types;

public class StickPacket
{
  private StringBuilder Builder;
  
  public StickPacket()
  {
    this.Builder = new StringBuilder();
  }
  
  public void Append(String ToAppend)
  {
    this.Builder.append(ToAppend);
  }
  
  public void Append(int ToAppend)
  {
    this.Builder.append(ToAppend);
  }
  
  public byte[] getAOB()
  {
    return this.Builder.toString().getBytes();
  }
  
  public String getString()
  {
    return this.Builder.toString();
  }
}
