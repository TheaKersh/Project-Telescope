import java.util.ArrayList;
import java.util.Scanner;
import java.util.Hashtable;

class Main {
  public static void main(String[] args) {
    int[] a = {200,190,170,160,150,160,140,160};
    int[] b = {2000, 2100, 2300, 2400,2500, 2400,2600, 2400};
    Hashtable database_1 = new Hashtable();
    database_1.put("Seal", a);
    database_1.put("Salmon", b);
    
    System.out.println(database_1.get(
    "Seal"));
  }
}
