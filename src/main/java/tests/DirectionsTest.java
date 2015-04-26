package tests;

import java.io.IOException;

import main.Building;

import org.junit.Test;

import api.Directions;

public class DirectionsTest {

	@Test
	public void testDirecionsResponse() {
		Building[] stops = new Building[7];
		stops[0] = new Building("3205 Walnut Street Philadelphia,PA", null, null);
		stops[1] = new Building("210 South 33rd Street Philadelphia,PA", null, null);
		stops[2] = new Building("3330 Walnut Street Philadelphia,PA", null, null);
		stops[3] = new Building("3231 Walnut Street Philadelphia,PA", null, null);
		stops[4] = new Building("200 South 33rd Street Philadelphia,PA", null, null);
		stops[5] = new Building("220 S. 33rd Street Philadelphia,PA", null, null);
		stops[6] = new Building("3320 Smith Walk Philadelphia,PA", null, null);

		Directions dir = new Directions(stops);
		try {
			System.out.println(dir.sendRequest(dir.getUrl()));
			System.out.println(dir.getUrl());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
