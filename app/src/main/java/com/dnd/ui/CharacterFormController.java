package com.dnd.ui;

import com.dnd.model.DNDCharacter;
import com.dnd.dao.CharacterDAO;
import com.dnd.dao.LocationDAO;
import com.dnd.model.Location;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.util.List;

public class CharacterFormController {
    @FXML private TextField charIdField;
    @FXML private TextField charNameField;
    @FXML private ComboBox<String> charRaceBox;
    @FXML private ComboBox<String> charClassBox;
    @FXML private ComboBox<String> charAlignmentBox;
    @FXML private TextField charBackgroundField;
    @FXML private TextField charLevelField;
    @FXML private CheckBox charNpcCheckBox;
    @FXML private TextField charStrengthField;
    @FXML private TextField charDexterityField;
    @FXML private TextField charConstitutionField;
    @FXML private TextField charIntelligenceField;
    @FXML private TextField charWisdomField;
    @FXML private TextField charCharismaField;
    @FXML private ComboBox<Location> charLocationBox; // ✅ Now stores `Location` objects

    private Stage dialogStage;
    private DNDCharacter character;
    private boolean confirmed = false;
    private final CharacterDAO characterDAO = new CharacterDAO();
    private final LocationDAO locationDAO = new LocationDAO();

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setCharacter(DNDCharacter character) {
        this.character = character;

        if (character != null) {
            charIdField.setText(String.valueOf(character.getId()));
            charNameField.setText(character.getName());
            charRaceBox.setValue(character.getRace());
            charClassBox.setValue(character.getCharacterClass());
            charAlignmentBox.setValue(character.getAlignment());
            charBackgroundField.setText(character.getBackground());
            charLevelField.setText(String.valueOf(character.getLevel()));
            charNpcCheckBox.setSelected(character.isNpc());
            charStrengthField.setText(String.valueOf(character.getStrength()));
            charDexterityField.setText(String.valueOf(character.getDexterity()));
            charConstitutionField.setText(String.valueOf(character.getConstitution()));
            charIntelligenceField.setText(String.valueOf(character.getIntelligence()));
            charWisdomField.setText(String.valueOf(character.getWisdom()));
            charCharismaField.setText(String.valueOf(character.getCharisma()));

            // Find and set matching Location object
            for (Location loc : charLocationBox.getItems()) {
                if (loc.getId() == character.getLocation()) {
                    charLocationBox.setValue(loc);
                    break;
                }
            }
        }
    }

    @FXML
    private void initialize() {
        charRaceBox.getItems().addAll("Human", "Elf", "Dwarf", "Orc", "Fairy");
        charClassBox.getItems().addAll("Mage", "Warrior", "Rogue", "Archer");
        charAlignmentBox.getItems().addAll("Lawful Good", "Neutral Good", "Chaotic Good",
                "Lawful Neutral", "True Neutral", "Chaotic Neutral",
                "Lawful Evil", "Neutral Evil", "Chaotic Evil");

        // Fetch list of locations and populate charLocationBox
        List<Location> locations = locationDAO.getAllLocations();
        charLocationBox.getItems().setAll(locations);
    }

    @FXML
    private void handleSave() {
        if (character == null) {
            character = new DNDCharacter(0, "", "Human", "Warrior", "", "", 1, false, 10, 10, 10, 10, 10, 10, 0);
        }

        // 🔹 Capture user inputs
        character.setName(charNameField.getText());
        character.setRace(charRaceBox.getValue());
        character.setCharacterClass(charClassBox.getValue());
        character.setAlignment(charAlignmentBox.getValue());
        character.setBackground(charBackgroundField.getText());
        character.setLevel(Integer.parseInt(charLevelField.getText()));
        character.setNpc(charNpcCheckBox.isSelected());

        character.setStrength(Integer.parseInt(charStrengthField.getText()));
        character.setDexterity(Integer.parseInt(charDexterityField.getText()));
        character.setConstitution(Integer.parseInt(charConstitutionField.getText()));
        character.setIntelligence(Integer.parseInt(charIntelligenceField.getText()));
        character.setWisdom(Integer.parseInt(charWisdomField.getText()));
        character.setCharisma(Integer.parseInt(charCharismaField.getText()));

        // Retrieve selected Location object and set its ID
        Location selectedLocation = charLocationBox.getSelectionModel().getSelectedItem();
        if (selectedLocation != null) {
            character.setLocation(selectedLocation.getId());
        }

        // If character has an ID, update; otherwise, insert a new one
        if (character.getId() > 0) {
            System.out.println("Updating existing character: " + character.getId());
            characterDAO.updateCharacter(character);
        } else {
            int newId = characterDAO.insertCharacter(character);
            character.setId(newId);
            System.out.println("New character added with ID: " + newId);
        }

        confirmed = true;
        dialogStage.close();
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public DNDCharacter getCharacter() {
        return character;
    }
}

