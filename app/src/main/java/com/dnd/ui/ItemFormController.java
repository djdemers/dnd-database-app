package com.dnd.ui;

import com.dnd.dao.ItemDAO;
import com.dnd.model.Item;
import com.dnd.model.ItemStatType;
import com.dnd.model.ItemStats;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.List;

public class ItemFormController {

    @FXML private TextField itemIdField;
    @FXML private TextField itemNameField;
    @FXML private TextArea itemInfoTextField;
    @FXML private ComboBox<String> itemRarityBox;
    @FXML private ComboBox<String> itemTypeBox;

    // Item Stats
    @FXML private ComboBox<ItemStatType> statTypeBox;
    @FXML private TextField statAmountField;
    @FXML private TableView<ItemStats> itemStatsTable;
    @FXML private TableColumn<ItemStats, String> statTypeColumn;
    @FXML private TableColumn<ItemStats, Integer> statAmountColumn;
    @FXML private Button addStatButton;

    private Stage dialogStage;
    private Item item;
    private boolean confirmed = false;
    private final ItemDAO itemDAO = new ItemDAO();
    private final ObservableList<ItemStats> itemStatsList = FXCollections.observableArrayList();

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setItem(Item item) {
        this.item = item;

        if (item != null) {
            itemIdField.setText(String.valueOf(item.getId()));
            itemNameField.setText(item.getItemName());
            itemInfoTextField.setText(item.getInfoText());
            itemRarityBox.setValue(item.getRarity());
            itemTypeBox.setValue(item.getType());

            // Load stats for this item
            List<ItemStats> stats = itemDAO.getItemStatsforItem(item.getId());
            itemStatsList.setAll(stats);
        }
    }

    @FXML
    private void initialize() {
        // Disable item ID field (auto-generated)
        itemIdField.setDisable(true);

        // Populate rarity and type options
        itemRarityBox.getItems().addAll("Common", "Uncommon", "Rare", "Epic", "Legendary");
        itemTypeBox.getItems().addAll("Weapon", "Armor", "Potion", "Scroll", "Misc");

        // Populate stat type ComboBox with enum values
        statTypeBox.getItems().addAll(ItemStatType.values());

        // Set up TableView columns
        statTypeColumn.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getStatType().name())
        );
        statAmountColumn.setCellValueFactory(cellData -> cellData.getValue().amountProperty().asObject());

        itemStatsTable.setItems(itemStatsList);
    }

    @FXML
    private void handleSave() {
        if (item == null) {
            item = new Item(0, "", "", "", "");
        }

        // Capture user inputs
        item.setItemName(itemNameField.getText());
        item.setInfoText(itemInfoTextField.getText());
        item.setRarity(itemRarityBox.getValue());
        item.setType(itemTypeBox.getValue());

        // 🔍 **Check if item already exists**
        if (item.getId() > 0) {
            System.out.println("Updating existing item: " + item.getId());
            itemDAO.updateItem(item);
        } else {
            int newId = itemDAO.insertItem(item);
            item.setId(newId);
            System.out.println("New item added with ID: " + newId);
        }


        for (ItemStats stat : itemStatsList) {
            itemDAO.insertItemStat(item.getId(), stat);
        }

        confirmed = true;
        dialogStage.close();
    }



    @FXML
    private void handleAddStat() {
        ItemStatType selectedStat = statTypeBox.getValue();
        String amountText = statAmountField.getText();

        if (selectedStat != null && !amountText.isEmpty()) {
            try {
                int amount = Integer.parseInt(amountText);

                int validItemId = item.getId() > 0 ? item.getId() : 0;

                ItemStats newStat = new ItemStats(validItemId, selectedStat, amount);
                itemStatsList.add(newStat);
            } catch (NumberFormatException e) {
                showAlert("Invalid Input", "Stat amount must be a valid number.");
            }
        } else {
            showAlert("Missing Data", "Please select a stat type and enter a valid amount.");
        }
    }


    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public Item getItem() {
        return item;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}



