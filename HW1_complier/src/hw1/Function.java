package hw1;

public class Function {
  
  public boolean isDigit(char input) {
    if (input >= '0' && input <= '9') return true;
    else return false;    
  }
  
  public boolean isAlpha(char input) {
    if ((input >= 'a' && input <= 'z') || (input >= 'A' && input <= 'Z')) return true;
    else return false;    
  }
  
  public String printToken(String token) {
      return token.substring(0, token.length() - 1);
  }
}
