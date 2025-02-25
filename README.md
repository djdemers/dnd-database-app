

# D&D Database Manager

A **Java-based GUI application** using **JavaFX** to manage a Dungeons & Dragons campaign database. The application allows users to interact with the database through a user-friendly interface and perform essential operations like inserting, updating, deleting, and querying characters, quests, items, and locations.

---

## 📂 **Project Structure**

```
📦 dnd-database-app
 ┣ 📂 app
 ┃ ┣ 📂 src
 ┃ ┃ ┣ 📂 main
 ┃ ┃ ┃ ┣ 📂 java
 ┃ ┃ ┃ ┃ ┣ 📂 com
 ┃ ┃ ┃ ┃ ┃ ┣ 📂 dnd
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂 app - Application entry point (MainApp.java)
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂 model - Data models for entities (Character, Quest, Item, etc.)
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂 dao - Data Access Objects for CRUD operations
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂 ui - JavaFX controllers and UI logic
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂 utils - Utility classes (Database connection)
 ┃ ┃ ┃ ┣ 📂 resources
 ┃ ┃ ┃ ┃ ┣ 📂 com
 ┃ ┃ ┃ ┃ ┃ ┣ 📂 dnd
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📂 ui - FXML files for UI design (Login screen, menu, etc.)
 ┣ 📜 build.gradle - Gradle configuration file
 ┣ 📜 settings.gradle - Gradle settings
 ┣ 📜 README.md 
 ┣ 📜 .gitignore - Git ignore rules
```

---

##  **Technologies Used**

- **Java 17+** — Core programming language
- **JavaFX** — GUI framework
- **MySQL** — Relational database
- **Gradle** — Build automation tool
- **JUnit 4** — Unit testing framework (planned)

---

## **How to Set Up and Run the Project**

###  **Prerequisites**
- [Java 17+](https://jdk.java.net/)
- [MySQL](https://dev.mysql.com/)
- [Gradle](https://gradle.org/install/)
- [JavaFX SDK](https://gluonhq.com/products/javafx/)

### **Clone the Repository**


### **Set Up the Database**


### **Configure JavaFX SDK**



### **Run the Application**

From the terminal:

```bash
./gradlew run
```

---

##  **How to Test the Initial Login**

1. Launch the application:
    - A **login window** will prompt you for:
        - **Database URL** (`jdbc:mysql://localhost:3306/<database_name>`)
        - **Username** (your MySQL username)
        - **Password** (your MySQL password)

2. The **Main Menu (coming soon)** will allow you to:
    - Add characters, quests, items, locations.
    - Update existing data.
    - Delete records.
    - Query data for reports.

---

##  **What’s Implemented So Far**

-  **Database connection UI:** Allows users to input custom database URL, username, and password.
-  **Database schema** using MySQL, including tables like `DND_CHARACTER`, `QUEST`, `LOCATION`, `ITEM`, and relationships between them.
-  **Gradle setup** for JavaFX, MySQL connector, and project dependencies.

---

##  **Next Steps**

### 🔹 **1️⃣ Complete Data Model Classes**
- [ ] Implement model classes with attributes (e.g., `DNDCharacter.java`, `Quest.java`).

### 🔹 **2️⃣ Develop DAO Classes (CRUD Operations)**
- [ ] Insert, update, delete, and retrieve operations for:
    - [ ] Characters
    - [ ] Quests
    - [ ] Items
    - [ ] Locations

### 🔹 **3️⃣ Build Main Menu UI (JavaFX)**
- [ ] Implement a dashboard for easy navigation between:
    - Character management
    - Quest management
    - Item inventory
    - Location browser

### 🔹 **4️⃣ Add Unit Tests (JUnit 5)**
- [ ] Write tests for DAO operations to ensure database integrity.

### 🔹 **5️⃣ Improve Error Handling & Validation**
- [ ] Add user-friendly error messages in the UI.
- [ ] Validate form fields for adding/updating entries.



