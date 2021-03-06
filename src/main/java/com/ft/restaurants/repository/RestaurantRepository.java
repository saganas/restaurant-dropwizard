package com.ft.restaurants.repository;

import com.ft.restaurants.CSVReader;
import com.ft.restaurants.CSVWriter;
import com.ft.restaurants.domain.Distance;
import com.ft.restaurants.domain.Location;
import com.ft.restaurants.domain.Restaurant;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.google.common.collect.Lists.newArrayList;

public class RestaurantRepository {

    public static final String INPUT_FILE = "southwark3.csv";
    private final CSVReader csvReader;
    private final CSVWriter csvWriter;

    private List<Restaurant> restaurants = Collections.synchronizedList(newArrayList());

    public RestaurantRepository(CSVReader csvReader, CSVWriter csvWriter) {
        this.csvReader = csvReader;
        this.csvWriter = csvWriter;
    }

    public void loadData() throws Exception {
        restaurants.clear();
        restaurants.addAll(csvReader.readCSV(INPUT_FILE));
    }

    public void saveData() throws Exception {
        csvWriter.writeCSV("southwarkwritetest.csv", this.restaurants);
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void addRestaurant(Restaurant restaurant) {
        this.restaurants.add(restaurant);
    }

    public Optional<Restaurant> findRestaurantById(UUID id) {
        return this.restaurants.stream()
                .filter(restaurant -> restaurant.getId().equals(id))
                .findFirst();
    }

    public List<Restaurant> findRestaurantsByName(List<Restaurant> restaurants, String regex) {
        Pattern pattern = Pattern.compile(regex.toLowerCase());
        return restaurants.stream()
                .filter(restaurant -> pattern.matcher(restaurant.getName().toLowerCase()).find())
                .collect(Collectors.toList());
    }

    public List<Restaurant> findRestaurantsByTag(List<Restaurant> restaurants, String tag) {
        Pattern pattern = Pattern.compile(tag.toLowerCase());
        return restaurants.stream()
                .filter(restaurant -> pattern.matcher(restaurant.getTag().toLowerCase()).find())
                .collect(Collectors.toList());
    }

    public List<Restaurant> findRestaurantsByDistance(List<Restaurant> restaurants, Double longitude, Double latitude, Double radius) {
        Location location = new Location(longitude, latitude);
        return restaurants
                .stream()
                .filter(restaurant -> Distance.distance(restaurant.getLocation(), location) <= radius)
                .collect(Collectors.toList());
    }

    public List<Restaurant> findRestaurantsByAddress(List<Restaurant> restaurants, String regex) {
        Pattern pattern = Pattern.compile(regex.toLowerCase());
        return restaurants.stream()
                .filter(restaurant -> pattern.matcher(restaurant.getAddress().toLowerCase()).find())
                .collect(Collectors.toList());
    }

    public List<Restaurant> findRestaurantsByPostCode(List<Restaurant> restaurants, String regex) {
        Pattern pattern = Pattern.compile(regex);
        return restaurants.stream()
                .filter(restaurant -> pattern.matcher(restaurant.getPostcode()).find())
                .collect(Collectors.toList());
    }

    public List<Restaurant> findRestaurantsByHygieneRating(List<Restaurant> restaurants, Integer hygieneRating) {
        return restaurants
                .stream()
                .filter(restaurant -> restaurant.getHygieneRating().equals(hygieneRating))
                .collect(Collectors.toList());
    }

    /*// TODO: use streams for restaurantbyaddress as well
    public List<Restaurant> findRestaurantsByAddress(List<Restaurant> restaurants, String regex) {
        List<Restaurant> restaurantMatches = new ArrayList<>();
        Pattern pattern = Pattern.compile(regex);

        for (Restaurant restaurant : this.restaurants) {
            String restaurantAddress = restaurant.getAddress();
            if (pattern.matcher(restaurantAddress).matches()) {
                restaurantMatches.add(restaurant);
            }
        }
        return restaurantMatches;
    }*/

    public void deleteRestaurantById(UUID id) {


        for(int i = 0; i < this.restaurants.size(); i++) {
            if(this.restaurants.get(i).getId().equals(id)) {
                this.restaurants.remove(i);
            }
        }
        /* Iterator<Restaurant> iterator = restaurants.iterator();
        while(iterator.hasNext()) {
            Restaurant restaurant = iterator.next();
            if(restaurant.getId().equals(id)) {
                iterator.remove();
            }
        }*/
    }
}

