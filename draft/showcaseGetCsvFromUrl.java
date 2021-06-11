import java.util.ArrayList;
import java.util.Scanner;
import java.util.Hashtable;
import java.awt.Point;
import java.util.List;
import java.util.Map;
import java.lang.Math;
import java.io.*;
import java.util.Scanner;
import java.lang.Double;
import java.net.*;
import java.net.*;
import java.io.*;

class Main3 {
  public static void main(String[] args) {
    try
    {
      //Enter the filenames of the csv files for the independent and dependent variables below
      //URL dependent = new URL("https://population.un.org/wpp/Download/Files/1_Indicators%20(Standard)/CSV_FILES/WPP2019_TotalPopulationBySex.csv");
      //URL independent = new URL("https://data.un.org/Data.aspx?q=united+states&d=PopDiv&f=variableID%3a12%3bcrID%3a840%2c850");
      //Intializes 2 ArrayList<Object> for the 2 variables given. (They are ArrayList<Objects> so that they can be filled with Integer objects or Double objects). The method call inside the ArrayList<Object> initialization allows the numbers from the file to be inserted into the ArrayList directly
      //System.out.println(getListFromCsvUrl(dependent));
      /*ArrayList<Double> dependent = new ArrayList<Double>(getListFromUrl(dependent));
      ArrayList<Double> independent = new ArrayList<Double>(getListFromUrl(independent));
      System.out.println(dependent);
      System.out.println(independent);
      for (int i = 0; i <  independent.size(); i++){
        System.out.println(i + " " + dependent.get(i) + independent.get(i));
      }
      System.out.println(correlationCoefficientOverTimeD(independent, independent.size()));
      System.out.println(correlationCoefficientOverTimeD(dependent, dependent.size()));
      System.out.println(correlationCoefficientAListDouble(independent, dependent, dependent.size()));
      
    }catch( Exception e ){
     System.out.println(e.getMessage());
    }*/
  }

  // Method to retrieve information from a Csv file when given a file name
  public static ArrayList<Double> getNumbersFromCsvFile(String pathTocsv) {
    ArrayList<Double> d = new ArrayList<Double>();
    try {
      BufferedReader csvReader = new BufferedReader(new FileReader(pathTocsv));
      String row = csvReader.readLine();
      while ((row = csvReader.readLine()) != null) {
        String[] data = row.split(",");
        if (data.length >= 4) {
          String val = data[3];
          val = val.replace("\"", "");
          double dvar = Double.parseDouble(val);
          d.add(dvar);
        }
      }
      csvReader.close();
    } catch (Exception e) {
      System.out.print(e.getMessage());
    }

    return d;
  }

  // Methods to return a pearson's correlation when given an independent and
  // dependent variable. Some of these are for returning a pearsons with only one
  // list. These are for finding the correlation of your variable with the
  // progress of time.
  public static double correlationCoefficientAListDouble(ArrayList<Double> X, ArrayList<Double> Y, int n) {
    double sum_X = 0, sum_Y = 0, sum_XY = 0;
    double squareSum_X = 0, squareSum_Y = 0;

    for (int i = 0; i < n; i++) {
      // sum of elements of array X.
      sum_X = sum_X + X.get(i);

      // sum of elements of array Y.
      sum_Y = sum_Y + Y.get(i);

      // sum of X[i] * Y[i].
      sum_XY = sum_XY + X.get(i) * Y.get(i);

      // sum of square of array elements.
      squareSum_X = squareSum_X + X.get(i) * X.get(i);
      squareSum_Y = squareSum_Y + Y.get(i) * Y.get(i);
    }

    // use formula for calculating correlation
    // coefficient.
    double corr = (n * sum_XY - sum_X * sum_Y)
        / (Math.sqrt((n * squareSum_X - sum_X * sum_X) * (n * squareSum_Y - sum_Y * sum_Y)));

    return corr;
  }

  public static float correlationCoefficientInteger(ArrayList<Integer> X, ArrayList<Integer> Y, int n) {
    int sum_X = 0, sum_Y = 0, sum_XY = 0;
    int squareSum_X = 0, squareSum_Y = 0;

    for (int i = 0; i < n; i++) {
      // sum of elements of array X.
      sum_X = sum_X + X.get(i);

      // sum of elements of array Y.
      sum_Y = sum_Y + Y.get(i);

      // sum of X[i] * Y[i].
      sum_XY = sum_XY + X.get(i) * Y.get(i);

      // sum of square of array elements.
      squareSum_X = squareSum_X + X.get(i) * X.get(i);
      squareSum_Y = squareSum_Y + Y.get(i) * Y.get(i);
    }

    // use formula for calculating correlation
    // coefficient.
    float corr = (float) (n * sum_XY - sum_X * sum_Y)
        / (float) (Math.sqrt((n * squareSum_X - sum_X * sum_X) * (n * squareSum_Y - sum_Y * sum_Y)));

    return corr;
  }

  // use formula for calculating correlation
  // coefficient.
  public static float correlationCoefficientOverTime(ArrayList<Integer> Y, int n) {
    ArrayList<Integer> X = new ArrayList<Integer>();
    for (int g = 0; g < Y.size(); g++) {
      X.add(g);
    }
    int sum_X = 0, sum_Y = 0, sum_XY = 0;
    int squareSum_X = 0, squareSum_Y = 0;
    for (int i = 0; i < n; i++) {
      // sum of elements of array X.
      sum_X = sum_X + X.get(i);

      // sum of elements of array Y.
      sum_Y = sum_Y + Y.get(i);

      // sum of X[i] * Y[i].
      sum_XY = sum_XY + X.get(i) * Y.get(i);

      // sum of square of array elements.
      squareSum_X = squareSum_X + X.get(i) * X.get(i);
      squareSum_Y = squareSum_Y + Y.get(i) * Y.get(i);
    }

    // use formula for calculating correlation
    // coefficient.
    float corr = (float) (n * sum_XY - sum_X * sum_Y)
        / (float) (Math.sqrt((n * squareSum_X - sum_X * sum_X) * (n * squareSum_Y - sum_Y * sum_Y)));

    return corr;
  }

  public static double correlationCoefficientOverTimeD(ArrayList<Double> Y, int n) {
    ArrayList<Integer> X = new ArrayList<Integer>();
    for (int g = 0; g < Y.size(); g++) {
      X.add(g);
    }
    double sum_X = 0, sum_Y = 0, sum_XY = 0;
    double squareSum_X = 0, squareSum_Y = 0;
    for (int i = 0; i < n; i++) {
      // sum of elements of arraylist X.
      sum_X = sum_X + X.get(i);

      // sum of elements of arraylist Y.
      sum_Y = sum_Y + Y.get(i);

      // sum of X.get(i) * Y,get(i).
      sum_XY = sum_XY + X.get(i) * Y.get(i);

      // sum of square of array elements.
      squareSum_X = squareSum_X + X.get(i) * X.get(i);
      squareSum_Y = squareSum_Y + Y.get(i) * Y.get(i);
    }

    // use formula for calculating correlation
    // coefficient.
    double corr = (double) (n * sum_XY - sum_X * sum_Y)
        / (double) (Math.sqrt((n * squareSum_X - sum_X * sum_X) * (n * squareSum_Y - sum_Y * sum_Y)));

    return corr;
  }

  public static ArrayList<Double> getListFromCsvUrl(URL u) {
    ArrayList<Double> d = new ArrayList<Double>();
    try {
      BufferedReader read = new BufferedReader(new InputStreamReader(u.openStream()));
      String row = read.readLine();
      while ((row = read.readLine()) != null) {
        String[] data = row.split(",");
        if (data.length >= 4) {
          String val = data[8];
          // val = val.replace("\"", "");
          double dvar = Double.parseDouble(val);
          d.add(dvar);
        }
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return d;
  }
}