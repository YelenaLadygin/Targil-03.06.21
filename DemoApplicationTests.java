package com.example.demo;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import org.testng.annotations.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() {	}

	@Test
	public void test_get_restaurant_id_1() {
		ClientConfig clientConfig = new DefaultClientConfig();

		Client client = Client.create(clientConfig);
		WebResource webResource =   client.resource(UriBuilder.fromUri("http://localhost:8080/restaurant/1").build());

		String result =
				webResource.path("").path("").accept(String.valueOf(MediaType.APPLICATION_JSON)).get(String.class);
		System.out.println(result);

		Gson gson = new Gson();
		RestaurantDTO restaurant_id =
				gson.fromJson(JsonParser.parseString(result).getAsJsonObject(),
						RestaurantDTO.class);

		assert restaurant_id.getId() == 1;
		assert restaurant_id.getName().equals("japanika") == true;
	}

	@Test
	public void test_post_restaurant() {

		Gson gson = new Gson();

		ClientConfig clientConfig = new DefaultClientConfig();

		Client client = Client.create(clientConfig);
		WebResource webResource =
				client.resource(UriBuilder.fromUri("http://localhost:8080/restaurant").build());

		RestaurantDTO restaurant = new RestaurantDTO(4, "oishi","ashdod","potato",5);

		ClientResponse resp =
				webResource.path("").accept("application/json").type("application/json").post(ClientResponse.class, gson.toJson(restaurant));

		if(resp.getStatus() != 201) {
			assert false;}

		// ---- get for assert

		// not ideal

		webResource = client.resource(UriBuilder.fromUri("http://localhost:8080/restaurant/3").build());
		String result =  webResource.path("").accept(String.valueOf(MediaType.APPLICATION_JSON)).get(String.class);

		System.out.println(result);

		RestaurantDTO restaurant_id4 = gson.fromJson(JsonParser.parseString(result).getAsJsonObject(), RestaurantDTO.class);
		assert restaurant_id4.getId() == 4;
		assert restaurant_id4.getName().equals("oishi") == true;
	}

	@Test
	public void test_put_restaurant() {
		Gson gson = new Gson();

		ClientConfig clientConfig = new DefaultClientConfig();

		Client client = Client.create(clientConfig);
		WebResource webResource =  client.resource(UriBuilder.fromUri("http://localhost:8080/coupon/2").build());

		RestaurantDTO coupon = new RestaurantDTO(2, "nafis","rishon le tzion","steak",56);

		ClientResponse resp = webResource.accept("application/json").type("application/json").put(ClientResponse.class,
				gson.toJson(coupon));

		if(resp.getStatus() != 200) { assert false;
		}

		// ---- get for assert

		// not ideal

		webResource = client.resource(UriBuilder.fromUri("http://localhost:8080/restaurant/2").build());
		String result =  webResource.path("").accept(String.valueOf(MediaType.APPLICATION_JSON)).get(String.class);

		System.out.println(result);

		RestaurantDTO restaurant_id2 = gson.fromJson(JsonParser.parseString(result).getAsJsonObject(), RestaurantDTO.class);

		assert restaurant_id2.getId() == 2;
		assert restaurant_id2.getName().equals("nafis") == true;
	}

	@Test
	public void test_delete_restaurant() {
		Gson gson = new Gson();

		ClientConfig clientConfig = new DefaultClientConfig();

		Client client = Client.create(clientConfig);
		WebResource webResource =  client.resource(UriBuilder.fromUri("http://localhost:8080/restaurantn").build());

		RestaurantDTO restaurant = new RestaurantDTO(3, "humburg","tel aviv","humburger",10);

		ClientResponse resp = webResource.path("").accept("application/json").type("application/json").post(ClientResponse.class,
				gson.toJson(restaurant));

		if(resp.getStatus() != 201) {assert false;}

		webResource =  client.resource(UriBuilder.fromUri("http://localhost:8080/restaurant/3").build());
		webResource.accept("application/json").type("application/json").delete();


		// ---- get for assert
		// not ideal

		webResource = client.resource(UriBuilder.fromUri("http://localhost:8080/restaurant/3").build());

		String result =  webResource.path("").accept(String.valueOf(MediaType.APPLICATION_JSON)).get(String.class);

		System.out.println(result);

		assert result.equals("") == true;
	}
}