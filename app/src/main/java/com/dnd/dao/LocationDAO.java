package com.dnd.dao;

import com.dnd.model.Location;
import com.dnd.utils.DatabaseConnector;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LocationDAO {

    // TODO: Insert a new location into the database
    public void insertLocation(Location location) {
        // Use PreparedStatement to insert location
    }

    // TODO: Retrieve all locations from the database
    public List<Location> getAllLocations() {
        List<Location> locations = new ArrayList<>();
        // Execute SELECT * FROM LOCATION
        return locations;
    }

    // TODO: Update location details
    public void updateLocation(Location location) {
        // Update location attributes
    }

    // TODO: Delete a location by ID
    public void deleteLocation(int locationId) {
        // Use PreparedStatement to delete location
    }

    // TODO: Retrieve a location by ID
    public Location getLocationById(int locationId) {
        // Execute SELECT WHERE LOC_ID = ?
        return null;
    }
}

