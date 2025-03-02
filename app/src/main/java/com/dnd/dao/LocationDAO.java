package com.dnd.dao;

import com.dnd.model.Location;
import com.dnd.utils.DatabaseConnector;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LocationDAO {

    /**
     * Insert a new location into the database.
     * @param location The location to insert.
     */
    public void insertLocation(Location location) {
        // Use PreparedStatement to insert location
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DatabaseConnector.getConnection();
            String query = "INSERT INTO LOCATION (LOC_ID, LOC_NAME, LOC_TYPE, INFO_TEXT) VALUES (?, ?, ?, ?)";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, location.getID());
            stmt.setString(2, location.getName());
            stmt.setString(3, location.getType());
            stmt.setString(4, location.getInfo_text());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Get all locations from the database.
     * @return A list of all locations.
     */
    public List<Location> getAllLocations() {
        List<Location> locations = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        // Execute SELECT * FROM LOCATION
        try {
            conn = DatabaseConnector.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM LOCATION");
            while (rs.next()) {
                int id = rs.getInt("LOC_ID");
                String name = rs.getString("LOC_NAME");
                String type = rs.getString("LOC_TYPE");
                String info_text = rs.getString("INFO_TEXT");
                Location location = new Location(id, name, type, info_text);
                locations.add(location);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return locations;
    }

    /**
     * Update a location in the database.
     * @param location The updated location object.
     */
    public void updateLocation(Location location) {
        deleteLocation(location.getID());
        insertLocation(location);
    }

    /**
     * Delete a location from the database.
     * @param locationId The ID of the location to delete.
     */
    public void deleteLocation(int locationId) {
        // Use PreparedStatement to delete location
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DatabaseConnector.getConnection();
            String query = "DELETE FROM LOCATION WHERE LOC_ID = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, locationId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Get a location by its ID.
     * @param locationId The ID of the location to retrieve.
     * @return The location object, or null if not found.
     */
    public Location getLocationById(int locationId) {
        // Execute SELECT WHERE LOC_ID = ?
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DatabaseConnector.getConnection();
            String query = "SELECT * FROM LOCATION WHERE LOC_ID = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, locationId);
            rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("LOC_ID");
                String name = rs.getString("LOC_NAME");
                String type = rs.getString("LOC_TYPE");
                String info_text = rs.getString("INFO_TEXT");
                return new Location(id, name, type, info_text);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}

