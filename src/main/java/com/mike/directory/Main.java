package com.mike.directory;

import com.mike.directory.model.City;
import com.mike.directory.service.Service;

import java.util.List;

public class Main {
    private static List<City> cities;
    private static String filePath = "city.txt";

    public static void main(String[] args) {
        while (true) {
            int module = Service.moduleChooser();
            if (cities == null && module != 0 && module != 1) {
                Service.printMessage("Вначале нам нужно загрузить города (пункт 1), иначе нам не с чем работать.");
                continue;
            }

            switch (module) {
                case 1:
                    cities = Service.readFromFile(filePath);
                    Service.printCities(cities);
                    break;
                case 2:
                    int sort = Service.sortChooser();
                    if (sort != 0) {
                        Service.printCities(Service.sort(cities, sort));
                    }
                    break;
                case 3:
                    Service.printLargestCityWithIndex(cities);
                    break;
                case 4:
                    Service.printCitiesInRegion(cities);
                    break;
                default:
                    Service.onExit();
                    System.exit(0);
            }
        }
    }
}
