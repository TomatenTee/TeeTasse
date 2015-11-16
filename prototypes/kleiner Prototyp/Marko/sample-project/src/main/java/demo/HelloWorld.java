package demo;

import com.google.maps.*;
import com.google.maps.model.*;
import java.net.*;
import java.io.*;
import com.google.appengine.api.search.*;
import org.json.*;
import org.json.JSONObject;

class HelloWorld 
{
	public static void testGoogleMapsDistanceMatrixAPI(GeoApiContext context)
	{
		// This example uses the Google Maps Distance Matrix API.
		// Only 2500 requests per day  
		try
		{
			System.out.println();
			System.out.println("This example uses the Google Maps Distance Matrix API.");
			String[] origins = {"Nöthnitzer Straße 46, Dresden"};
			String[] destinations = {					
					"Freiburger Straße 28, Dresden"
					,"Haupbahnhof, Dresden"
					,"01217"
					};
			DistanceMatrix results3 = DistanceMatrixApi.getDistanceMatrix(context, origins, destinations).await();
			for(int i = 0;i<destinations.length;i++)
			{
				System.out.println("  Origin:      " + results3.originAddresses[0]);
				System.out.println("  Destination: " + results3.destinationAddresses[i]);
				System.out.println("  Distance:    " + results3.rows[0].elements[i].distance);
				System.out.println();
			}			
		}
		catch(Exception e)
		{
			System.out.println("Error " + e.toString()); 			
		}	
	}
	
	public static void testGoogleMapsGeocodingAPI(GeoApiContext context)
	{
		// This example uses the Google Maps Geocoding API.
		// Only 2500 requests per day  
		try
		{
			System.out.println("This example uses the Google Maps Geocoding API.");
			String address = "Nöthnitzer Straße 46, Dresden";
			GeocodingResult[] results = GeocodingApi.geocode(context, address).await();
			address = "Haupbahnhof, Dresden";
			GeocodingResult[] results2 = GeocodingApi.geocode(context, address).await();			
			System.out.println("  Origin:      " + results[0].formattedAddress);
			System.out.println("  Latitude:    " + results[0].geometry.location.lat);
			System.out.println("  Longtitude:  " + results[0].geometry.location.lng);
			System.out.println();
			System.out.println("  Destination: " + results2[0].formattedAddress);
			System.out.println("  Latitude:    " + results2[0].geometry.location.lat);
			System.out.println("  Longtitude:  " + results2[0].geometry.location.lng);
			
			// https://en.wikipedia.org/wiki/Haversine_formula
			// http://stackoverflow.com/questions/837872/calculate-distance-in-meters-when-you-know-longitude-and-latitude-in-java
			double earthRadius = 6371000;
		    double dLat = Math.toRadians(results2[0].geometry.location.lat - results[0].geometry.location.lat);
		    double dLng = Math.toRadians(results2[0].geometry.location.lng - results[0].geometry.location.lng);
		    double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
		               Math.cos(Math.toRadians(results[0].geometry.location.lat)) * Math.cos(Math.toRadians(results2[0].geometry.location.lat)) *
		               Math.sin(dLng/2) * Math.sin(dLng/2);
		    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		    float dist = (float) (earthRadius * c);
		    System.out.println();
		    System.out.println("  Distance:    " + dist + " meter");
		}
		catch(Exception e)
		{
			System.out.println("Error " + e.toString()); 			
		}	
	}
	
	public static GeoPoint getGeoPointFromAddress(String locationAddress) 
	{   
		GeoPoint locationPoint = null;   
        try
        { 
        	System.out.println();
        	System.out.println("This example uses the Google json file to parse the GeoPoint from an address");
	        URL scanThis = new URL(
	        		"http://maps.googleapis.com/maps/api/geocode/json?address="
	                + locationAddress.replaceAll(" ", "%20") 
	                + "&sensor=true"
	                );
	        BufferedReader in = new BufferedReader(
	        new InputStreamReader(scanThis.openStream()));
	        String inputLine;
	        String googleResponse = "";
	        while ((inputLine = in.readLine()) != null)
	        {
	        	googleResponse += inputLine + "\n";
	        }
	        in.close();        
	        JSONObject json;
            json = new JSONObject(googleResponse);            
            JSONObject geoMetryObject = new JSONObject();
            JSONObject locations = new JSONObject();
            JSONArray jarr = json.getJSONArray("results");
            for (int i = 0; i < jarr.length(); i++) 
            {
                json = jarr.getJSONObject(i);
                geoMetryObject = json.getJSONObject("geometry");
                locations = geoMetryObject.getJSONObject("location");
                System.out.println("  Address:     " + locationAddress);
                System.out.println("  Latitude:    " + locations.getDouble("lat"));
                System.out.println("  Longtitude:  " + locations.getDouble("lng"));
                locationPoint = new GeoPoint(locations.getDouble("lat"),locations.getDouble("lng"));
            }       
        }
        catch(Exception e)
        {
        	 System.out.println("Error " + e.toString());        	
        }
        return locationPoint;
    }
		
	public static void main(String ...strings )
	{
		try
		{
			// Restriction to 2500 requests per day and must shown on google map			
			System.out.println("Client Libraries for Google Maps Web Services - Introduction");
			System.out.println("The Java and Python client libraries for Google Maps Web Services enable you to work with Google Maps Web services on your server.");
			GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyCmXe39AZrJtIF2FzhQH-xzu8q2lNFxj_k");
			testGoogleMapsDistanceMatrixAPI(context);
			testGoogleMapsGeocodingAPI(context);			
			getGeoPointFromAddress("Hauptbahnhof Dresden");	
		}
		
		catch(Exception e)
		{
			System.out.println("Error: " + e.toString());	
		}
	}		
}
