package com.dnd.dao;

import com.dnd.model.Item;
import com.dnd.model.ItemStatType;
import com.dnd.utils.DatabaseConnector;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.dnd.model.ItemStats;

public class ItemDAO {

    /**
     * Insert an item into the database
     * @param item The item to insert
     */
    public void insertItem(Item item) {
        // Use PreparedStatement to insert an item
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DatabaseConnector.getConnection();
            String query = "INSERT INTO ITEM (ITEM_ID, ITEM_NAME, INFO_TEXT, RARITY, ITEM_TYPE) VALUES (?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, item.getId());
            stmt.setString(2, item.getItemName());
            stmt.setString(3, item.getInfoText());
            stmt.setString(4, item.getRarity());
            stmt.setString(5, item.getType());
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
     * Get all items from the database
     * @return A list of all items
     */
    public List<Item> getAllItems() {
        List<Item> items = new ArrayList<>();
        // Execute SELECT * FROM ITEM
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = DatabaseConnector.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM ITEM");
            while (rs.next()) {
                int id = rs.getInt("ITEM_ID");
                String itemName = rs.getString("ITEM_NAME");
                String infoText = rs.getString("INFO_TEXT");
                String rarity = rs.getString("RARITY");
                String type = rs.getString("ITEM_TYPE");
                Item item = new Item(id, itemName, infoText, rarity, type);
                items.add(item);
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
        return items;
    }

    /**
     * Update an item in the database
     * @param updatedItem The updated item
     */
    public void updateItem(Item updatedItem) {
        // Update item using its ID
        deleteItem(updatedItem.getId());
        insertItem(updatedItem);
    }

    /**
     * Delete an item from the database
     * @param itemId The ID of the item to delete
     */
    public void deleteItem(int itemId) {
        // Remove item using its ID
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DatabaseConnector.getConnection();
            String query = "DELETE FROM ITEM WHERE ITEM_ID = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, itemId);
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
     * Get an item by its ID
     * @param itemId The ID of the item to retrieve
     * @return The item with the given ID, or null if not found
     */
    public Item getItemById(int itemId) {
        // Execute SELECT WHERE ITEM_ID = ?
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DatabaseConnector.getConnection();
            String query = "SELECT * FROM ITEM WHERE ITEM_ID = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, itemId);
            rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("ITEM_ID");
                String itemName = rs.getString("ITEM_NAME");
                String infoText = rs.getString("INFO_TEXT");
                String rarity = rs.getString("RARITY");
                String type = rs.getString("ITEM_TYPE");
                return new Item(id, itemName, infoText, rarity, type);
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

    /**
     * Insert an item stat into the database
     * @param itemId The ID of the item to insert the stat for
     * @param itemStat The item stat to insert
     */
    public void insertItemStat(int itemId, ItemStats itemStat) {
        // Use PreparedStatement to insert an item stat
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DatabaseConnector.getConnection();
            String query = "INSERT INTO ITEM_STATS (ITEM_ID, STAT_NAME, AMOUNT) VALUES (?, ?, ?)";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, itemId);
            stmt.setString(2, itemStat.getStatType().toString());
            stmt.setInt(3, itemStat.getAmount());
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
     * Insert an item stat into the database
     * @param itemID The ID of the item to insert the stat for
     * @param statType The type of stat to insert
     * @param amount The amount of the stat to insert
     */
    public void insertItemStat(int itemID, ItemStatType statType, int amount) {
        // Use PreparedStatement to insert an item stat
        ItemStats itemStat = new ItemStats(itemID, statType, amount);
        insertItemStat(itemID, itemStat);
    }

    /**
     * Update an item stat in the database
     * @param itemId The ID of the item to update the stat for
     * @param statType The type of stat to update
     * @param amount The new amount of the stat
     */
    public void updateItemStat(int itemId, ItemStatType statType, int amount) {
        // Update item stat using its ID and stat type
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DatabaseConnector.getConnection();
            String query = "UPDATE ITEM_STATS SET AMOUNT = ? WHERE ITEM_ID = ? AND STAT_NAME = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, amount);
            stmt.setInt(2, itemId);
            stmt.setString(3, statType.toString());
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
     * Delete an item stat from the database
     * @param itemID The ID of the item to delete the stat for
     * @param statType The type of stat to delete
     */
    public void deleteItemStat(int itemID, ItemStatType statType) {
        // Remove item stat using its ID and stat type
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DatabaseConnector.getConnection();
            String query = "DELETE FROM ITEM_STATS WHERE ITEM_ID = ? AND STAT_NAME = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, itemID);
            stmt.setString(2, statType.toString());
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
     * Get all item stats for an item
     * @param itemId The ID of the item to get stats for
     * @return A list of all item stats for the item
     */
    public List<ItemStats> getItemStatsforItem(int itemId) {
        List<ItemStats> itemStats = new ArrayList<>();
        // Execute SELECT * FROM ITEM_STATS WHERE ITEM_ID = ?
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DatabaseConnector.getConnection();
            String query = "SELECT * FROM ITEM_STATS WHERE ITEM_ID = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, itemId);
            rs = stmt.executeQuery();
            while (rs.next()) {
                ItemStatType statType = ItemStatType.valueOf(rs.getString("STAT_NAME"));
                int amount = rs.getInt("AMOUNT");
                ItemStats itemStat = new ItemStats(itemId, statType, amount);
                itemStats.add(itemStat);
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
        return itemStats;
    }

    /**
     * Get all item stats from the database
     * @return A list of all item stats
     */
    public List<ItemStats> getAllItemStats() {
        // Execute SELECT * FROM ITEM_STATS
        List<ItemStats> itemStats = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = DatabaseConnector.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM ITEM_STATS");
            while (rs.next()) {
                int id = rs.getInt("ITEM_ID");
                ItemStatType statType = ItemStatType.valueOf(rs.getString("STAT_NAME"));
                int amount = rs.getInt("AMOUNT");
                ItemStats itemStat = new ItemStats(id, statType, amount);
                itemStats.add(itemStat);
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
        return itemStats;
    }
}
