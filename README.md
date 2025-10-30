# Mongo Calculator

A **console-based calculator** that saves and loads operations using **MongoDB**.
Supports addition, subtraction, multiplication, and division, with persistent storage for previously run operations.

---

## ✨ Features

* Run calculations directly in the console (CLI).
* Supports `+`, `-`, `*`, `/`.
* Each operation is stored as a **MongoDB document**.
* Load and re-run previous operations from the database.
* Simple, lightweight code structure.

---

## 📦 Technologies

* **Java 17+**
* **MongoDB** (as storage backend)
* **Lombok** (for boilerplate reduction)
* **BSON** (MongoDB document format)

---

## 📂 Project Structure

```
me.wabuein.calc
 └── operation
      ├── Operation.java        # Represents a calculation, can be saved/loaded
      ├── OperationType.java    # Enum: ADD, SUBTRACT, MULTIPLY, DIVIDE
      └── OperationStage.java   # Enum for input stages (A, B, Operation type)
```

* **Operation** – Holds operands (`a`, `b`), operation type, and can convert to/from MongoDB `Document`.
* **OperationType** – Enum of supported operations.
* **OperationStage** – Guides user input stages (setting first number, second number, operation).

---

## ⚡ Usage

1. Clone the repository:

   ```bash
   git clone https://github.com/<your-username>/<repo-name>.git
   cd <repo-name>
   ```
2. Ensure you have **MongoDB** running locally (`mongodb://localhost:27017` by default).
3. Build & run the project:

   ```bash
   ./mvnw clean install
   java -jar target/mongo-calculator.jar
   ```
4. Follow console prompts to:

   * Enter number A
   * Enter number B
   * Choose an operation type
5. The result is printed and saved to MongoDB.

---

## 🗂 Example Mongo Document

Each operation is stored like this:

```json
{
  "a": 10,
  "b": 5,
  "type": "ADD"
}
```

## 📄 Configuration File (config.txt)

```
mongo:
  uri-mode: false
  normal:
    host: 127.0.0.1
    port: 27017
    auth:
      enabled: false
      username: ''
      password: ''
  uri:
    database: Calculator
    connection-string: mongodb://127.0.0.1:27017/Calculator
```

---

## 📜 License

MIT – feel free to use and adapt.
