package com.sl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;

public class GmapDistance {
  public static void main(String[] args) throws IOException {
	  // Reference - https://developers.google.com/maps/documentation/distancematrix/
	  URL url = new URL("https://maps.googleapis.com/maps/api/distancematrix/json?origins=Vancouver+BC|Seattle&destinations=San+Francisco|Victoria+BC");
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("GET");
      String line, outputString = "";
      BufferedReader reader = new BufferedReader(
      new InputStreamReader(conn.getInputStream()));
      while ((line = reader.readLine()) != null) {
          outputString += line;
      }
      System.out.println(outputString);
      DistancePojo capRes = new Gson().fromJson(outputString, DistancePojo.class);
      System.out.println(capRes);
  }
}
