package com.mike.directory.service;

import com.mike.directory.model.City;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class ServiceTest {
    City city1 = new City("Байкальск", "Иркутская область", "Сибирский", 13589, 1961);
    City city2 = new City("Абаза", "Хакасия", "Сибирский", 17111, 1867);
    City city3 = new City("Арзамас", "Нижегородская область", "Приволжский", 106367, 1552);
    City city4 = new City("Абакан", "Хакасия", "Сибирский", 165183, 1734);
    City city5 = new City("Астрахань", "Астраханская область", "Южный", 520662, 1558);
    City city6 = new City("Абдулино", "Оренбургская область", "Приволжский", 20663, 1795);

    List<City> testList = new ArrayList<>(List.of(city1, city2, city3, city4, city5, city6));

    String brokenFile = "/Users/u19208062/IdeaProjects/CitiesDirectory/src/test/java/com/mike/directory/service/brokenFile.txt";
    String testFile = "/Users/u19208062/IdeaProjects/CitiesDirectory/src/test/java/com/mike/directory/service/citiesTest.txt";

    ByteArrayOutputStream baos = new ByteArrayOutputStream();

    @Before
    public void setupStream() {
        System.setOut(new PrintStream(baos));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void readFromFileWrongPath() {
        Service.readFromFile(brokenFile);
    }

    @Test
    public void readFromFile() {
        List<City> inputList = Service.readFromFile(testFile);
        Assert.assertEquals(testList, inputList);
    }

    @Test
    public void sortByCities() {
        //сортировка по городам
        List<City> expectedList = List.of(city2, city4, city6, city3, city5, city1);
        List<City> actualList = Service.sort(testList, 1);
        Assert.assertEquals(expectedList, actualList);
    }

    @Test
    public void sortByDistrictAndCities() {
        //сортировка по округам и городам
        List<City> expectedList = List.of(city6, city3, city2, city4, city1, city5);
        List<City> actualList = Service.sort(testList, 2);
        Assert.assertEquals(expectedList, actualList);
    }

    @Test
    public void printLargestCityWithIndex() {
        Service.printLargestCityWithIndex(testList);
        Assert.assertEquals("[4] = 520662\n", baos.toString());
    }

    @Test
    public void printCitiesInRegion() {
        String expected = "Астраханская область - 1\n" +
                "Иркутская область - 1\n" +
                "Нижегородская область - 1\n" +
                "Оренбургская область - 1\n" +
                "Хакасия - 2\n";
        Service.printCitiesInRegion(testList);
        Assert.assertEquals(expected, baos.toString());
    }

    @Test
    public void printCities() {
        String expected = "City{name='Байкальск', region='Иркутская область', district='Сибирский', population=13589, foundation=1961}\n" +
                "City{name='Абаза', region='Хакасия', district='Сибирский', population=17111, foundation=1867}\n" +
                "City{name='Арзамас', region='Нижегородская область', district='Приволжский', population=106367, foundation=1552}\n" +
                "City{name='Абакан', region='Хакасия', district='Сибирский', population=165183, foundation=1734}\n" +
                "City{name='Астрахань', region='Астраханская область', district='Южный', population=520662, foundation=1558}\n" +
                "City{name='Абдулино', region='Оренбургская область', district='Приволжский', population=20663, foundation=1795}\n";
        Service.printCities(testList);
        Assert.assertEquals(expected, baos.toString());
    }

    @Test
    public void printMessage() {
        String expected = "test message\n";
        Service.printMessage("test message");
        Assert.assertEquals(expected, baos.toString());
    }

    @After
    public void cleanUpStream() {
        System.setOut(null);
    }
}