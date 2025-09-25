# User Management REST API

A simple Spring Boot REST API that performs basic CRUD operations on a User entity.
The unique feature of this API is its Global Exception Handling ⚡ — instead of returning error 500, it gracefully handles validation errors, duplicate entries, and missing users.

---

<h2>✨ Features</h2>

📝 CRUD Operations on User

🔒 Validation for fields

Email must be unique and in Gmail format

Age must be at least 18

🛡️ Global Exception Handling (no HTTP 500 errors)

✅ Responses are clean and user-friendly

---
🗂️ Entity: User
@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@gmail\\.com$", message = "email must be a valid Gmail address")
    @Column(unique = true, nullable = false)
    private String mail;

    @Min(value = 18, message = "Age must be at least 18")
    private int age;
}

---
<h2>📌 Rules:</h2>

mail ➝ must be a Gmail address (@gmail.com) & unique

age ➝ must be 18 or older

name ➝ cannot be blank

---
<h2>🌍 API Endpoints</h2>

Base URL: /api

➕ Create User

POST /api

{<br>
  "name": "Ayush",<br>
  "mail": "ayush@gmail.com",<br>
  "age": 22<br>
}
<h2></h2>
📋 Get All Users

GET /api

🔍 Get User by ID

GET /api/{id}

Example:

GET /api/1
<h2></h2>
✏️ Update User

PUT /api/{id}

{<br>
  "name": "Updated User",<br>
  "mail": "updated@gmail.com",<br>
  "age": 25<br>
}
<h2></h2>

❌ Delete User

DELETE /api/{id}

Response:

deleted successfully

---

<h2>⚡ Global Exception Handling</h2>

Implemented using @RestControllerAdvice 🎯

🔎 Exceptions Covered

UserNotFoundException ➝ returns 404 Not Found if ID doesn’t exist

MethodArgumentNotValidException ➝ handles body validation errors (@Valid)

ConstraintViolationException ➝ handles parameter validation errors (@Validated)

DataIntegrityViolationException ➝ handles duplicate email entries

Exception ➝ catch-all for other unexpected errors
<h2></h2>

🛠️ Example Responses

❌ Invalid email:

Validation failed: mail - email must be a valid Gmail address;
<h2></h2>

❌ Age below 18:

Validation failed: age - Age must be at least 18;
<h2></h2>

❌ Duplicate Gmail:

Error: Duplicate email not allowed
<h2></h2>

❌ User not found:

this unique id 5 is not found

---

<h2>🚀 How to Run</h2>

Clone the repo

Add dependencies in pom.xml:

spring-boot-starter-web

spring-boot-starter-data-jpa

spring-boot-starter-validation

DB driver (e.g., H2 or MySQL)

Run the application:

mvn spring-boot:run


Access API at:
👉 http://localhost:8080/api

<h2>🎯 Summary</h2>

This REST API is simple yet powerful:

Provides CRUD operations on User

Enforces validations ✅

Handles all exceptions globally (no raw 500 errors) 🛡️

---


## ✨ Author
<p align="center">
  <a href="https://linkedin.com/in/ayuseth" target="_blank">
    <img src="https://readme-typing-svg.herokuapp.com?font=Orbitron&size=24&color=00FFAB&center=true&vCenter=true&width=600&lines=👨‍💻+Ayush+Seth;💡+Computer+Science+Engineer;🚀+Java+Developer" alt="Author Typing Effect" />
  </a>
</p>

<p align="center">
  <a href="https://linkedin.com/in/ayuseth" target="_blank">
    <img src="https://capsule-render.vercel.app/api?type=waving&color=00FFAB&height=100&section=footer&text=Ayush+Seth&fontSize=32&animation=twinkling&fontColor=ffffff" />
  </a>
