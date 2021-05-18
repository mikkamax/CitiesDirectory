package com.mike.directory.service;

import com.mike.directory.model.City;

import java.io.*;
import java.util.*;

public class Service {
    private static Scanner consoleScanner = new Scanner(System.in);

    public static void onExit() {
        consoleScanner.close();
    }

    public static int moduleChooser() {
        Service.printMessage("");
        Service.printMessage("Введите номер модуля или 0 для выхода:");
        Service.printMessage("1) Загрузка и вывод всех городов");
        Service.printMessage("2) Сортировка списка городов");
        Service.printMessage("3) Поиск города с наибольшим количеством жителей");
        Service.printMessage("4) Поиск количества городов в разрезе регионов");
        Service.printMessage("0) Выход из программы");

        int input;
        while (true) {
            if (consoleScanner.hasNextInt() &&
                    ((input = consoleScanner.nextInt()) >= 0 && input <= 4)) {
                return input;
            } else {
                Service.printMessage("Нужно ввести цифру 1-4 или 0. Повторите ввод:");
                consoleScanner.nextLine();
            }
        }
    }

    public static int sortChooser() {
        Service.printMessage("");
        Service.printMessage("Выберите вариант сортировки или 0 для возврата в предыдущее меню:");
        Service.printMessage("1) Города в алфавитном порядке");
        Service.printMessage("2) Округа и города внутри округов в алфавитном порядке");
        Service.printMessage("0) Возврат в предыдущее меню");

        int input;
        while (true) {
            if (consoleScanner.hasNextInt() &&
                    ((input = consoleScanner.nextInt()) >= 0 && input <= 2)) {
                return input;
            } else {
                Service.printMessage("Нужно ввести цифру 1-2 или 0. Повторите ввод:");
                consoleScanner.nextLine();
            }
        }
    }

    public static List<City> readFromFile(String filePath) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(filePath));
        } catch (FileNotFoundException e) {
            printMessage("Ошибка! Файл для загрузки не найден.");
            e.printStackTrace();
        }
        List<City> citiesList = new ArrayList<>();
        String[] input;


        while (scanner.hasNext()) {
            input = scanner.nextLine().split(";");
            citiesList.add(new City(input[1], input[2], input[3], Integer.parseInt(input[4]), Integer.parseInt(input[5])));
        }

        scanner.close();

        return citiesList;
    }

    public static List<City> sort(List<City> citiesToSort, int sortType) {
        List<City> sortedCities = new ArrayList<>(citiesToSort);
        Comparator<City> comparator = null;

        //1 - сортировка по городам
        //2 - сортировка по городам и округам
        switch (sortType) {
            case 1:
                comparator = Comparator.comparing(city -> city.getName().toLowerCase());
                break;
            case 2:
                comparator = (city1, city2) -> {
                    if (city1.getDistrict().equalsIgnoreCase(city2.getDistrict())) {
                        return city1.getName().toLowerCase().compareTo(city2.getName().toLowerCase());
                    }

                    return city1.getDistrict().toLowerCase().compareTo(city2.getDistrict().toLowerCase());
                };
                break;
        }

        if (comparator != null) {
            sortedCities.sort(comparator);
        }

        return sortedCities;
    }

    public static void printLargestCityWithIndex(List<City> cities) {
        City[] citiesArray = new City[cities.size()];
        cities.toArray(citiesArray);

        int maxPopulation = -1;
        int indexOfTheCity = -1;

        for (int i = 0; i < citiesArray.length; i++) {
            if (citiesArray[i].getPopulation() > maxPopulation) {
                maxPopulation = citiesArray[i].getPopulation();
                indexOfTheCity = i;
            }
        }

        printMessage(String.format("[%d] = %d", indexOfTheCity, maxPopulation));
    }

    public static void printCitiesInRegion(List<City> cities) {
        Map<String, Integer> map = new TreeMap<>();
        String region;
        for (City city : cities) {
            region = city.getRegion();
            if (!map.containsKey(region)) {
                map.put(region, 0);
            }

            map.put(region, map.get(region) + 1);
        }

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            printMessage(entry.getKey() + " - " + entry.getValue());
        }
    }

    public static void printCities(List<City> cities) {
        for (City city : cities) {
            printMessage(city.toString());
        }
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }
}
