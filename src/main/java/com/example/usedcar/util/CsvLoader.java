package com.example.usedcar.util;

import com.example.usedcar.model.Car;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CsvLoader {

    public static List<Car> loadCarsFromCsv(String filename) {
        List<Car> cars = new ArrayList<>();

        try {
            InputStream is = CsvLoader.class.getClassLoader().getResourceAsStream(filename);
            if (is == null) {
                throw new RuntimeException("File not found: " + filename);
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String line;
            br.readLine(); // skip header
            while ((line = br.readLine()) != null) {
                // Split CSV by comma
                String[] parts = line.split(",");

                if (parts.length < 4) continue; // skip invalid lines

                Car c = new Car();
                // Remove quotes and trim spaces
                c.setMileage(Double.parseDouble(parts[0].replaceAll("\"", "").trim()));
                c.setYear(Integer.parseInt(parts[1].replaceAll("\"", "").trim()));
                c.setEngineSize(Double.parseDouble(parts[2].replaceAll("\"", "").trim()));
                c.setPrice(Double.parseDouble(parts[3].replaceAll("\"", "").trim()));

                cars.add(c);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cars;
    }
}
