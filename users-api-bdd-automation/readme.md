# 🧪 User API Automation Framework

This project is built to automate REST API testing for the **User Management API** as part of an **SDET Role-Based Activity**.

The framework validates all CRUD (Create, Read, Update, Delete) operations for system users using a modular and maintainable test automation setup.

---

## 🎯 **Objective**

As part of the SDET task, the goal is to:
- Test the **User API** endpoints from [FakeStore API Documentation](https://fakestoreapi.com/docs#tag/Users)
- Automate the following endpoints:
    - ➕ Create a new user
    - 📋 Get all users
    - 🔍 Get a single user
    - ✏️ Update a user
    - ❌ Delete a user
- Compile a test plan, automate a sample test, and present test execution and reporting.

---

## ⚙️ **Tools & Libraries Used**

| Library | Purpose |
|----------|----------|
| **Cucumber** | BDD framework for feature-driven test execution |
| **TestNG** | Test execution management and assertions |
| **RestAssured** | API testing and HTTP request handling |
| **Faker** | Dynamic test data generation |
| **FasterXML (Jackson)** | JSON serialization/deserialization |
| **Owner** | External configuration management |
| **Extent Reports** | HTML test execution reporting |
| **SLF4J + Logback** | Logging framework |
| **Maven** | Build automation and dependency management |

---

## 🧱 **Framework Architecture**

The framework follows a clean, modular design with clear separation of concerns:

- **Feature files** → Define test scenarios in Gherkin syntax
- **Step Definitions** → Contain implementation logic using RestAssured
- **Utils** → Common reusable helper methods (API, JSON, Validation)
- **Data Generators** → Generate dynamic test data with Java Faker
- **Models** → POJOs mapped to API request/response schema
- **Config Layer** → Handles environment properties via Owner library
- **Reports & Logs** → Captures detailed execution reports and logs

---

## 🏗️ **Project Structure**

```bash
src
├── main
│   ├── java
│   │   └── com.api
│   │       ├── config
│   │       │   ├── ConfigFactoryManager.java
│   │       │   └── FrameworkConfig.java
│   │       ├── datagenerator
│   │       │   └── UserDataGenerator.java
│   │       ├── models
│   │       │   ├── Address.java
│   │       │   ├── Geolocation.java
│   │       │   ├── Name.java
│   │       │   └── User.java
│   │       └── utils
│   │           ├── ApiUtils.java
│   │           ├── JsonUtils.java
│   │           └── ValidationUtils.java
│   └── resources
│       ├── frameworkconfig.properties
│       └── logback.xml
│
└── test
    ├── java
    │   └── com.api
    │       ├── hooks
    │       │   └── Hooks.java
    │       ├── runner
    │       │   └── TestRunner.java
    │       └── stepDefinations
    │           └── UsersSteps.java
    └── resources
        └── features
            └── Users.feature
```

---

## 🧰 **Project Setup on Local Machine**

Follow these steps to set up the project locally:

### 1️⃣ **Clone the Repository**
```bash
git clone <your-repo-url>
```

### 2️⃣ **Open the Project in IntelliJ IDEA Community**
- Open **IntelliJ IDEA Community Edition**
- Click **File → Open**, and select the cloned project folder

### 3️⃣ **Install Gherkin Plugin (for IntelliJ Community)**
1. Go to **File → Settings → Plugins**
2. Search for **Gherkin**
3. Click **Install**, then **Restart IDE**

### 4️⃣ **Ensure Java & Maven Are Installed**
Verify Java version:
```bash
java -version
```

Verify Maven version:
```bash
mvn -version
```

### 5️⃣ **Build the Project**
```bash
mvn clean install
```

This will download dependencies and compile the project.

---

## ▶️ **How to Execute Particular Scenario**

You can execute **specific scenarios** or entire feature files using Cucumber tags or line numbers.

### ✅ **Option 1: Run by Tag**
Add a unique tag above the scenario in your `Users.feature` file:
```gherkin
@CreateUser
Scenario: I create a new user with valid data
```

Then run the scenario using Maven with the tag:
```bash
mvn test -Dcucumber.filter.tags="@CreateUser"
```

### ✅ **Option 2: Run by Line Number**
You can also run by specifying the feature file and the **line number** of the scenario:
```bash
mvn test -Dcucumber.options="src/test/resources/features/Users.feature:15"
```

### ✅ **Option 3: Run from IntelliJ**
- Right-click on the **scenario** or **feature file**
- Select **Run 'Scenario: ...'**  
  (IntelliJ will use the `TestRunner.java` configuration)

---

## 📊 **Test Reports**

After execution, an HTML report is generated using **Extent Reports**.  
The report can be found in the following directory:

```
/test-output/ExtentReports/
```

Open the `.html` file in any browser to view the detailed test results.

---

## 📝 **Additional Notes**

- Environment variables and API base URLs can be configured in `frameworkconfig.properties`.
- Logs are stored in the `/logs` folder with timestamps for better traceability.
- Make sure the API under test is reachable before running tests.

---

✨ **You're all set to run your BDD API automation tests locally!**
