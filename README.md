# 📘 Quiz App (Monolithic Architecture) – Project
A `quiz-app-monolithic` is a single, unified application where all layers of functionality (UI, business logic, and data access) are packaged and deployed together as one unit. Unlike microservices, everything runs inside the same process and codebase.

## 🔑 Key Characteristics
- **Single Codebase**: Frontend, backend, and database interactions are tightly coupled in one project.
- **Unified Deployment**: The entire app is built and deployed as a single artifact (e.g., a `JAR/WAR` in Java).
- **Shared Resources**: All modules share the same memory space and runtime environment.
- **Simpler Setup**: Easier to develop and deploy initially, since there’s no distributed system complexity.

## ⚙️ Typical Features in a Quiz App
- **User Management**: Registration, login, and profile handling.
- **Quiz Management**: Admins can create, update, and delete quizzes/questions.
- **Question Bank**: Stores multiple-choice questions with answers and explanations.
- **Quiz Attempt**: Users can take quizzes, submit answers, and view scores.
- **Result Tracking**: Stores user performance and history.
- **Single Database**: All data (users, quizzes, results) is stored in one database schema.

## 🏗️ Example Tech Stack
- **Backend**: Java Spring Boot (controllers, services, repositories in one project)
- **Frontend**: Thymeleaf templates or React/Angular bundled into the same app
- **Database**: PostgreSQL/MySQL accessed directly via JPA/Hibernate
- **Build/Deploy**: Maven/Gradle → packaged as one JAR/WAR → deployed on Tomcat/Docker

## ✅ Advantages
- Easy to build and understand for small teams.
- Faster initial development and deployment.
- No need for complex orchestration or service discovery.

## ❌ Limitations
- Harder to scale individual components (e.g., quiz engine vs. user service).
- Tight coupling makes maintenance and updates more difficult.
- Larger deployments as the app grows, leading to slower build/start times.

---

## 🏗️ Tech Stack
- **Backend**: Java 17, Spring Boot
- **Database**: PostgreSQL/MySQL with JPA/Hibernate
- **Build Tool**: Maven
---

## 📂 Project Structure
```
    quiz-app-monolithic/
        ├── src/main/java/com/example/quizapp
        │    ├── controller/        # REST Controllers
        │    ├── service/           # Business Logic
        │    ├── repository/        # JPA Repositories
        │    └── model/             # Entities (User, Quiz, Question, Result)
        ├── src/main/resources/
        │    ├── application.properties  # DB & app configs
        │    └── templates/              # Thymeleaf views
        ├── pom.xml
        └── README.md
```
---

## ⚙️ Setup & Quickstart

### 1. Clone the repository
```bash
    git clone https://github.com/sugganabalaji/quiz-app-monolithic.git
    cd quiz-app-monolithic
````

### 2. Configure Database
Update src/main/resources/application.properties:
```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/Dev
    spring.datasource.username=postgres
    spring.datasource.password=root
    spring.jpa.hibernate.ddl-auto=update
```
### 3. Build & Run the Application
```bash
    mvn clean install
    mvn spring-boot:run
```
### 4. Access the App
Open `http://localhost:8080` in your browser.

### 5. Here are the endpoints:
- GET `/questions/all` → get all questions
- GET `/questions/category/{category}` → get questions by category
- POST `/questions/add` → add a new question
- PUT `/questions/update` → update an existing question
- DELETE `/questions/delete/{id}` → delete a question by ID

- POST `/quiz/create` → create a new quiz (with category, number of questions, and title)
- GET `/quiz/get/{id}` → get quiz questions by quiz ID
- POST `/quiz/submit/{id}` → submit quiz answers and calculate score

