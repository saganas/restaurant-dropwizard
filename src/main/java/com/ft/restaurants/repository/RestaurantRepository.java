package com.ft.restaurants.repository;

import com.ft.restaurants.domain.Restaurant;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Jorge on 6/21/2016.
 */
public class RestaurantRepository {
    public Set<Restaurant> restaurants = new HashSet<>();

    public void addToRepository(Restaurant restaurant) {
        this.restaurants.add(restaurant);
    }
}
