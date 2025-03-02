package com.dnd.dao;

import com.dnd.model.Location;
import com.dnd.utils.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.dnd.model.Model;

public class LocationDAO extends ModelDAO {

    private String modelName = "DND_LOCATION";

    private List<String> attributes = new ArrayList<>();

    public LocationDAO() {
        attributes.add("NAME");
        attributes.add("DESCRIPTION");
    }   

    public List<String> getAttributes() {
        return attributes;
    }   

    @Override
    public String getModelName() {
        return modelName;
    }

    @Override
    public void insert(Model model) {
        // TODO: Implement insert method
    }

    @Override
    public Model get(Model model) {
        // TODO: Implement get method
        return null;
    }

    @Override
    public void update(Model model) {
        // TODO: Implement update method
    }

    @Override
    public void delete(Model model) {
        // TODO: Implement delete method
    }

        // TODO: Retrieve all locations from the database
    public List<Location> getAllLocations() {
        List<Location> locations = new ArrayList<>();
        // Execute SELECT * FROM LOCATION
        return locations;
    }

    // TODO: Retrieve a location by ID
    public Location getLocationById(int locationId) {
        // Execute SELECT WHERE LOC_ID = ?
        return null;
    }
}

