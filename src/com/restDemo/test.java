package com.restDemo;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class test {

	public static void main(String[] args) {
//		done for git push
		Client client = ClientBuilder.newClient();

		WebTarget target = client.target("https://jsonplaceholder.typicode.com/posts/1");

		System.out.println("in the action block with changes");
		System.out.println(

				target.request(MediaType.APPLICATION_JSON).get(String.class));

		PrintWriter writer;
		try {
			writer = new PrintWriter("the-file-name.json", "UTF-8");
			writer.println(target.request(MediaType.APPLICATION_JSON).get(String.class));
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		try {
			Object obj = new JSONParser().parse(new FileReader("the-file-name.json"));
			JSONObject jo = (JSONObject) obj;
			System.out.println("Targeted  output of user id is ");
			long userId = (long) jo.get("userId");
			System.out.println(userId);
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}

	}

}
