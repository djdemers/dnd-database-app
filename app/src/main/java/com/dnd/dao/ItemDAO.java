package com.dnd.dao;

import com.dnd.model.Item;
import com.dnd.utils.DatabaseConnector;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO {

    public void insertItem(Item item) {
        // Use PreparedStatement to insert an item
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseConnector.getConnection();
            String query = "INSERT INTO ITEM (ITEM_ID, ITEM_NAME, INFO_TEXT, RARITY, ITEM_TYPE) VALUES (?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(query);
            statement.setInt(1, item.getId());
            statement.setString(2, item.getItemName());
            statement.setString(3, item.getInfoText());
            statement.setString(4, item.getRarity());
            statement.setString(5, item.getType());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the connection
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            // Close the statement
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<Item> getAllItems() {
        List<Item> items = new ArrayList<>();
        // Execute SELECT * FROM ITEM
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DatabaseConnector.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM ITEM");
            while (resultSet.next()) {
                int id = resultSet.getInt("ITEM_ID");
                String itemName = resultSet.getString("ITEM_NAME");
                String infoText = resultSet.getString("INFO_TEXT");
                String rarity = resultSet.getString("RARITY");
                String type = resultSet.getString("ITEM_TYPE");
                Item item = new Item(id, itemName, infoText, rarity, type);
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the connection
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            // Close the statement
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            // Close the result set
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return items;
    }

    public void updateItem(int ID, Item updatedItem) {
        // Update item using its ID
        deleteItem(ID);
        insertItem(updatedItem);
    }

    public void deleteItem(int itemId) {
        // Remove item using its ID
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DatabaseConnector.getConnection();
            String query = "DELETE FROM ITEM WHERE ITEM_ID = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, itemId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the connection
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            // Close the statement
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Item getItemById(int itemId) {
        // Execute SELECT WHERE ITEM_ID = ?
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DatabaseConnector.getConnection();
            String query = "SELECT * FROM ITEM WHERE ITEM_ID = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, itemId);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("ITEM_ID");
                String itemName = resultSet.getString("ITEM_NAME");
                String infoText = resultSet.getString("INFO_TEXT");
                String rarity = resultSet.getString("RARITY");
                String type = resultSet.getString("ITEM_TYPE");
                return new Item(id, itemName, infoText, rarity, type);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the connection
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            // Close the statement
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            // Close the result set
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    // TODO: Manage item stats (INSERT, UPDATE, DELETE)

    public static void main(String[] args) {
        DatabaseConnector.setCredentials("jdbc:mysql://localhost:3306/d&d_database", "root", "");

        // Test the ItemDAO class
        ItemDAO itemDAO = new ItemDAO();
        Item newItem = new Item(4, "Sword of Sharpness", "A sword that can cut through anything", "Legendary", "Weapon");
        itemDAO.insertItem(newItem);
        List<Item> items = itemDAO.getAllItems();
        for (Item item : items) {
            System.out.println(item);
        }

        // delete item
        itemDAO.deleteItem(4);

        System.out.println("After deleting item 4:");
        items = itemDAO.getAllItems();
        for (Item item : items) {
            System.out.println(item);
        }
    }
}
