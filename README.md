# 🍲 Full Stack Recipe Finder Application

This is a full-stack application that fetches and displays recipes using a Spring Boot backend and a ReactJS frontend. Users can search recipes using a typeahead/autpletion UI and view full recipe details with image and ingredients.

---

## 🧰 Tech Stack

### 🔧 Backend

* Java 17
* Spring Boot 3.3.x
* Spring Web, Spring Data JPA
* H2 In-Memory Database
* Spring Retry (`@Retryable`) for resilience
* Swagger/OpenAPI
* JUnit 5 + Mockito

### 💅 Frontend

* React 18
* Tailwind CSS
* Axios
* React Router DOM
* React Testing Library (Jest)

---

## 📦 Prerequisites

Make sure you have the following installed:

* [Java 17+](https://adoptium.net/)
* [Maven](https://maven.apache.org/)
* [Node.js and npm](https://nodejs.org/)
* [Git](https://git-scm.com/)

---

## 📁 Folder Structure

```
recipes-fullstack/
├── recipe-backend/               <-- Spring Boot project
└── recipe-frontend/              <-- React + Tailwind project
```

---

## 🚀 Getting Started

### 1️⃣ Clone the Repository

```bash
git clone https://github.com/Rocky17292/recipes_fullstack/
cd recipes-fullstack
```

---

## 🔀 Backend Setup (Spring Boot)

### 📍 Navigate to Backend Folder

```bash
cd backend
```

### 🚀 Run the Backend

```bash
./mvnw spring-boot:run
# or
mvn spring-boot:run
```

### 🧪 Test the Backend APIs

| Method | Endpoint                      | Description                                    |
| ------ | ----------------------------- | ---------------------------------------------- |
| POST   | `/recipes/load`               | Loads recipes from dummyjson into in-memory DB |
| GET    | `/recipes/search?query=pizza` | Search recipes by name/cuisine                 |
| GET    | `/recipes/{id}`               | Get full recipe detail by ID                   |

### 🔍 Developer Tools

* Swagger UI → [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
* H2 Console → [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
  JDBC URL: `jdbc:h2:mem:recipesdb`
  Username: `sa` (no password)

---

## 🎨 Frontend Setup (React + Tailwind)

### 📍 Navigate to Frontend Folder

```bash
cd ../frontend
```

### 📦 Install Dependencies

```bash
npm install
```

### 🚀 Start the Frontend App

```bash
npm run dev
```

Visit the app in your browser at:
🔗 [http://localhost:5173](http://localhost:5173)

---

## ✨ Features Covered

### ✅ Backend

* Loads and stores recipes from [https://dummyjson.com/recipes](https://dummyjson.com/recipes)
* Free-text search on name and cuisine (contains match)
* Resilient API calls using `@Retryable`
* Exception handling with `@ControllerAdvice`
* RESTful API design with DTOs (optional)
* Swagger documentation
* Unit tests for controller and service

### ✅ Frontend

* Typeahead/autocomplete search bar with 3+ char trigger
* Responsive dropdown with `name`, `cuisine`, `id`
* Navigate to `/recipe/:id` to view full details
* Display recipe image and ingredients
* Lazy loading of images
* Styled with Tailwind CSS
* Responsive design across devices
* Back to search button from details page
* Frontend unit test using React Testing Library

---

## 🧪 How to Run Tests

### 🧪 Backend Tests (JUnit + Mockito)

```bash
cd backend
mvn test
```

### 🧪 Frontend Tests (React Testing Library + Jest)

```bash
cd frontend
npm run test
```

---

## 📅 Project Enhancements / TODOs

* [ ] Use Hibernate Search for indexed search
* [ ] Add edit/delete functionality (CRUD)
* [ ] Implement search filters (by ingredient, cuisine, etc.)
* [ ] Use Redis for caching external data
* [ ] Add pagination to results
* [ ] Deploy frontend and backend with Docker

---

## 💼 Author

Built with ❤️ by \[Rushikesh]
📧 Email: rushikesh.shinde.java@gmail.com
🔗 LinkedIn: https://www.linkedin.com/in/rushikesh-shinde-8a2b72222/
- portfolio - http://rocky17292.github.io/MyPortfolio_React/

---

