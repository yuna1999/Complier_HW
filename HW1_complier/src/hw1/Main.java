package hw1;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

/**
 * HW1 : Scanner.
 * @author Yuna Lin.
 */
public class Main {

  public static boolean isInt = false;
  public static boolean isFloat = false;
  public static boolean isId = false;
  public static boolean isError = false;
  public static boolean isToken = false;

  /**
   * reset all boolean function.
   */
  public static void resetStatus() {
    isInt = false;
    isFloat = false;
    isId = false;
    isError = false;
    isToken = false;
  }

  public static void main(String[] args) throws IOException {

    int totalInt = 0;
    int totalFloat = 0;
    int totalId = 0;
    int totalError = 0;

    try {
      /**
       * Rules of scanner. 
       * integer -> -?[0-9]+;\n 
       * float -> -?[0-9]+\.[0-9]+;\n 
       * id -> [a-zA-z][a-zA-Z0-9_]*;\n 
       * error -> others
       */
      Function f = new Function();
      String line = "";
      BufferedReader src = new BufferedReader(new FileReader("test.txt"));
      while ((line = src.readLine()) != null) {
        resetStatus();
        // start to recognize, for first char only contains 3 case
        if (f.isDigit(line.charAt(0)) || line.charAt(0) == '-') {
          isInt = true;
          for (int i = 1; i < line.length(); i++) {
             if(line.charAt(i) == '.') {
               if(isFloat) {
                 isError = true;
                 break;
               } else {
                 isInt = false;
                 isFloat = true;
               }
             } else if(line.charAt(i) == ';') {
               isToken = true;
             } else if(f.isDigit(line.charAt(i))) {
               isInt = true;
             } else {
               isError = true;
               break;
             }
          }
          if(!isError) {
            if(isFloat && !isInt) isError = true;
            if(isFloat && isInt) isInt = false;
          }
          
        } else if (f.isAlpha(line.charAt(0))) {
          isId = true;
          for (int i = 1; i < line.length(); i++) {
            if(line.charAt(i) == ';') {
              isToken = true;
            } else if(f.isDigit(line.charAt(i)) || f.isAlpha(line.charAt(i)) || line.charAt(i) == '_') {
              continue;
            } else {
              isError = true;
              break;
            }
          }
        } else {
          isError = true;
        }
        
        //print token and total
        if(isError || !isToken) {
          System.out.println("Find an error : " + line);
          totalError++;
        } else if(isToken) {
          if(isInt) {
            System.out.println("Find an integer : " + f.printToken(line));
            totalInt++;
          } else if(isFloat) {
            System.out.println("Find a float : " + f.printToken(line));
            totalFloat++;
          } else if(isId) {
            System.out.println("Find an id : " + f.printToken(line));
            totalId++;
          }
        }
      }
      System.out.println();
      System.out.println("totalInt = " + totalInt);
      System.out.println("totalFloat = " + totalFloat);
      System.out.println("totalId = " + totalId);
      System.out.println("totalError = " + totalError);
      src.close();
    } catch (IOException e) {
      System.out.println(e);
    }
  }
}
