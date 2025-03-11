
# Task Tracker Documentation


Sample solution for the task-tracker challenge from roadmap.sh.

## Project Overview

**Task Tracker** is a simple Spring Boot-based application designed for task management. It allows users to add, update, and track tasks through a **command-line interface (CLI)**. Tasks are stored in a local **JSON file** and can be marked with different statuses such as "todo," "in-progress," or "done."

---

## Features

- **Add, update, delete tasks**
- **Mark tasks** as `todo`, `in-progress`, or `done`
- **List tasks** filtered by status
- Data is stored in a local **JSON file**

---

## Prerequisites

Ensure you have the following installed:

- **Java 17** or higher
- **Maven** or **Gradle** for build management
- **Spring Boot** dependencies
- **GraalVM** (for native build)

---

## Installation

### Clone the Repository
```bash
git clone https://github.com/sukrut57/task-tracker-app.git
cd task-tracker-app
```

### Build the Project

#### Using Maven
```bash
./mvnw clean install
```

#### Using Gradle
```bash
./gradlew clean build
```

---

## Usage

Once the application is set up, you can interact with it using various commands.

### Available Commands

1. **Add Task**:
   Adds a new task with a description.
   ```bash
   task-cli add "Task description"
   ```

2. **Update Task**:
   Updates the description of an existing task.
   ```bash
   task-cli update <task_id> "Updated task description"
   ```

3. **Mark Task as In Progress**:
   Marks the specified task as "in-progress".
   ```bash
   task-cli mark-in-progress <task_id>
   ```

4. **Mark Task as Done**:
   Marks the specified task as "done".
   ```bash
   task-cli mark-done <task_id>
   ```

5. **List Tasks**:
   Lists tasks filtered by status.
   ```bash
   task-cli list               # Lists all tasks
   task-cli list done          # Lists completed tasks
   task-cli list todo          # Lists pending tasks
   task-cli list in-progress   # Lists tasks in progress
   ```

---

## Task Model

### Properties:
- **id**: Unique identifier for each task
- **description**: Task description
- **status**: Can be one of `todo`, `in-progress`, or `done`
- **createdAt**: Timestamp of task creation
- **updatedAt**: Timestamp of task's last update

---

## File Structure

```
task-tracker-app/
│
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── tasktracker/
│   │               ├── TaskTrackerApplication.java  # Main application entry point
│   │               ├── commands
│   │               │   └── TaskComponent.java        # Command to add a new task
│   │               └── model                 # Task model
│   │               │   └── Task.java
|   |               └── service
|   |                   └── TaskService.java  # Service for task operations
│   │                   └── FileService.java  # Service for file operations
│   └── resources/
│       └── application.properties                 # Configuration properties
│
├── build.gradle                               # Gradle build configuration
├── pom.xml                                    # Maven build configuration
└── README.md                                  # Project documentation
```

---

## Development

### Running the Application

1. **Run with Spring Boot**:
   ```bash
   ./gradlew bootRun     # Gradle
   ./mvnw spring-boot:run   # Maven
   ```

2. **Run the Native Executable** (if using GraalVM):
   After building the native executable:
   ```bash
   build/native/nativeCompile/task-tracker.exe
   ```

---

## Contributing

Feel free to fork the repository and submit pull requests. For major changes, please open an issue first to discuss what you would like to change.

---

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
