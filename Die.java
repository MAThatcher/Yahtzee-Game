import java.util.Random;

public class Die{
  private int numSides = 0;
  private int result = -1;
  
  public Die(int side){
    numSides = side;
  }  
  
  public int roll(){
    result = (int) ( (Math.random() * numSides) + 1.0 );
    return result;
  }
  
  public int getResult(){
    return result;
  }
  
}