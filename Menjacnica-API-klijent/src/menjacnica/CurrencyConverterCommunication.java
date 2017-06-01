package menjacnica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import gui.GUIKontroler;

public class CurrencyConverterCommunication {
	public static String url = "http://free.currencyconverterapi.com/api/v3/countries"; 
	public static File file = new File("data/log.json");

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
	
	public static JsonArray ucitajIzFajla(){
		FileReader reader;
		JsonArray array = new JsonArray();
		try {
			reader = new FileReader("data/log.json");
			Gson gson = new GsonBuilder().create();
			array = gson.fromJson(reader, JsonArray.class);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return array;
	}
	
	public static void sacuvajUFajl(String izValuta, String uValuta, double kurs){
		JsonObject object = new JsonObject();
		JsonArray array = new JsonArray();
		
		SimpleDateFormat datum = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS");
		String datumVreme = datum.format(new Date());
		object.addProperty("datumVreme", datumVreme);
		object.addProperty("izValuta", izValuta);
		object.addProperty("uValuta", uValuta);
		if(kurs==0){
		object.addProperty("kurs", "null");
		} else object.addProperty("kurs", kurs);
		
		if(file.exists()){
			array = ucitajIzFajla();
		}
		
		array.add(object);
		
		Gson gson = new GsonBuilder().create();
		FileWriter writer;
		try {
			writer = new FileWriter("data/log.json");
			writer.write(gson.toJson(array));
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
