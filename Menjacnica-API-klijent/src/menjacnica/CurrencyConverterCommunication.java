package menjacnica;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
	
	public static LinkedList<String> getZemlje(){
		LinkedList<String> naziviZemalja = new LinkedList<String>();
		try {
			String response = sendGet(url);
			Gson gson = new GsonBuilder().create();
			Mapa map = gson.fromJson(response, Mapa.class);
			for (Zemlja zemlja : map.getResults().values()) {
				naziviZemalja.add(zemlja.getName());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return naziviZemalja;
	}

}
