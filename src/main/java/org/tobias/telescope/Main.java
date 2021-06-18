package org.tobias.telescope;

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

class Main {
  public static void main(String[] args) {
    try {
      // Enter the URLs
      URL dependent = new URL("https://population.un.org/wpp/Download/Files/1_Indicators%20(Standard)/CSV_FILES/WPP2019_TotalPopulationBySex.csv");
      // URL independent = new
      // URL("https://data.un.org/Data.aspx?q=united+states&d=PopDiv&f=variableID%3a12%3bcrID%3a840%2c850");
      BufferedReader read = new BufferedReader(new InputStreamReader(dependent.openStream()));
      System.out.println(read.readLine());
      ArrayList<NumberContainer> n_list = new ArrayList<NumberContainer>(getListFromCsvUrl(dependent, "United States of America", "PopTotal"));
      for (NumberContainer n : n_list){
        System.out.println(n.getA());
        System.out.println(n.getC());
      }

      //ArrayList<Double> d_list = new ArrayList<Double>();
      //ArrayList<Integer> int_list = new ArrayList<Integer>();
      //for (NumberContainer b : n_list) {
        //d_list.add(b.getA());
        //int_list.add(b.getC());
      //}

      /*
       * Hashtable equivalentCountryNames = new Hashtable<String, String>(); String[]
       * countryNames = {"America", "USA", "US", "United States of America",
       * "United States"}; ArrayList<String> countryNamesAmerica = new
       * ArrayList<String>(); String name = ""; for (int i = 0; i <
       * countryNamesAmerica.size(); i++){ name = countryNamesAmerica.get(i);
       * countryNamesAmerica.map(name, "United States"); }
       */

      /*
       * ArrayList<Double> dependent = new
       * ArrayList<Double>(getListFromUrl(dependent)); ArrayList<Double> independent =
       * new ArrayList<Double>(getListFromUrl(independent));
       * System.out.println(dependent); System.out.println(independent); for (int i =
       * 0; i < independent.size(); i++){ System.out.println(i + " " +
       * dependent.get(i) + independent.get(i)); }
       * System.out.println(correlationCoefficientOverTimeD(independent,
       * independent.size()));
       * System.out.println(correlationCoefficientOverTimeD(dependent,
       * dependent.size()));
       * System.out.println(correlationCoefficientAListDouble(independent, dependent,
       * dependent.size()));
       */
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  // Method to return a pearson's correlation when given an
  // ArrayList<NumberContainer>.
  // See NumberContainer Documentation on Line 221
  public static double correlationCoefficient(ArrayList<NumberContainer> XandY, int n) {
    double sum_X = 0, sum_Y = 0, sum_XY = 0;
    double squareSum_X = 0, squareSum_Y = 0;

    for (int i = 0; i < n; i++) {
      sum_X = sum_X + XandY.get(i).getA();
      sum_Y = sum_Y + XandY.get(i).getB();
      sum_XY = sum_XY + XandY.get(i).getA() * XandY.get(i).getB();
      squareSum_X = squareSum_X + XandY.get(i).getA() * XandY.get(i).getA();
      squareSum_Y = squareSum_Y + XandY.get(i).getB() * XandY.get(i).getB();
    }
    double corr = (n * sum_XY - sum_X * sum_Y)/ (Math.sqrt((n * squareSum_X - sum_X * sum_X) * (n * squareSum_Y - sum_Y * sum_Y)));
    return corr;
  }

  /* Method to return the first line of a csv file */
  public static int[] getColumnOfVariable(BufferedReader read, String varToGet, String country) throws IOException {
    String firstRow = read.readLine();
    String[] splitFirstRow = firstRow.split(",");
    int[] indices = new int[3];
    for (int a = 0; a < splitFirstRow.length; a++) {
      if (splitFirstRow[a].equals(country)){
        indices[0] = a;
      }
      if (splitFirstRow[a].equals("Time")){
        indices[1] = a;
      }
      if (splitFirstRow[a].equals(varToGet)) {
        indices[2] = a;
      }
    }
    if(indices[0] == 0){
      throw new IllegalArgumentException();
    }
    return indices
  }
  public static int getColumnOfVariable(String row, String varToGet) throws IOException {
    String[] splitFirstRow = row.split(",");
    for (int a = 0; a < splitFirstRow.length; a++) {
      if (splitFirstRow[a].equals(varToGet)) {
        return a;
      }
    }
    return 0;
  }

  public static ArrayList<NumberContainer> mergeVars(ArrayList<NumberContainer> x, ArrayList<NumberContainer> y) {
    ArrayList<NumberContainer> listOfCoordinates = new ArrayList<NumberContainer>();
    NumberContainer temp = new NumberContainer();
    for (int i = 0; i < Math.min(x.size(), y.size()); i++) {
      for (int g = 0; g < Math.max(x.size(), y.size()); g++) {
        if (x.get(i).getC() == y.get(g).getC()) {
          temp.setA(x.get(i).getA());
          temp.setB(y.get(i).getA());
          temp.setC(x.get(i).getC());
          listOfCoordinates.add(temp);
        }
      }
    }
    return listOfCoordinates;
  }

  public static ArrayList<NumberContainer> getListFromCsvUrl(URL u, String Country, String varToGet) {
    ArrayList<NumberContainer> fromLink = new ArrayList<NumberContainer>();
    /*
     * if (equivalentCountryNames.get(Country) == null){ throw
     * Exception("Country Name not recognized"); }
     */
    //Checks if given file is a csv file
    if (!(u.getFile().substring(u.getFile().length() - 3).equals("csv"))) {
      System.out.println(u.getFile().substring(u.getFile().length() - 3));
      System.out.println("Not a csv file");
      return null;
    }
    
    try {
      //Creates a BufferedReader object to read the file with
      BufferedReader read = new BufferedReader(new InputStreamReader(u.openStream()));
      //Reads the first line of the file(var names)
      String row = read.readLine();
      //Call to the getColumnOfVariable method returns the index which the requested variable is stored at
      int indexOf = getColumnOfVariable(row, varToGet);
      if(indexOf == 0){
        throw new IllegalArgumentException("The variable you are searching for was not in this database");
      }
      //Loops through the csv file from the given URL until it reaches the end of it.
      String buffer = read.readLine();
      while (buffer != null) {
        //Splits the line into a String[]
        String[] data = buffer.split(",");
        //Checks the length of the line
        if (data.length > 4) {
          //Gets the numerical data and the year collected in string form
          String val = data[indexOf];
          String year_string = data[4];
          //Checks that the country name matches the country given in the method call
          if (Country.equals(data[1])) {
            //Gets a double and an int from the Strings above
            //And sets the corresponding values of temp to them
            double dvar = Double.parseDouble(val);
            int year = Integer.parseInt(year_string);
            NumberContainer temp = new NumberContainer();
            temp.setA(dvar);
            temp.setC(year);
            fromLink.add(temp);
          }
          // val = val.replace("\"", "");
          
        }
        buffer = read.readLine();
      }
      read.close();
    }catch(Exception e){
      System.out.println(e.getMessage());
    }
    return fromLink;
  }
}


