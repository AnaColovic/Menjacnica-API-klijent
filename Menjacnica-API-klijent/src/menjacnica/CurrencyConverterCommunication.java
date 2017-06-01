package menjacnica;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import gui.GUIKontroler;

public class CurrencyConverterCommunication {
	public static String url = "http://free.currencyconverterapi.com/api/v3/countries"; 

	public static String sendGet(String url) throws IOException{
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		
		con.setRequestMethod("GET");
		
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		
		boolean endReading = false;
		String response = "";
		
		while (!endReading) {
			String s = in.readLine();
			
			if (s != null) {
				response += s;
			} else {
				endReading = true;
			}
		}
		in.close();
 
		return response.toString();
	}
	
	public static LinkedList<Zemlja> getZemlje(){
		LinkedList<Zemlja> zemlje = new LinkedList<Zemlja>();
		try {
			String response = sendGet(url);
			Gson gson = new GsonBuilder().create();
			Mapa map = gson.fromJson(response, Mapa.class);
			for (Zemlja zemlja : map.getResults().values()) {
				zemlje.add(zemlja);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return zemlje;
	}
	
	public static double konverzija(String q){
		String url1 = "http://free.currencyconverterapi.com/api/v3/convert?q=" + q;
		try {
			String response = sendGet(url1);
			 Gson gson = new GsonBuilder().create();
			 JsonObject query = gson.fromJson(response, JsonObject.class).getAsJsonObject("query");
			 int count = query.get("count").getAsInt();
			 if(count == 0) {
			 return 0;
			 	}
			JsonObject results = gson.fromJson(response, JsonObject.class).getAsJsonObject("results");
			JsonObject qObj = gson.fromJson(results, JsonObject.class).getAsJsonObject(q);
			 	return qObj.get("val").getAsDouble();
			 } catch (IOException e) {
			 	e.printStackTrace();
			 }
			 return 0;
	}
	

}
