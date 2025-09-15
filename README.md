# 🎓 University ECTS System

A full-stack project (Spring Boot + React) to manage university **ECTS**.  
This project was developed as part of a technical assessment and demonstrates:

- ✅ REST API (Spring Boot) for accessing student/course data  
- ✅ Automatic **bulletin generation** (ECTS, averages, pass/fail detection)  
- ✅ **Anomaly detection** (data inconsistencies like missing credits, duplicate notes, etc.)  
- ✅ CI/CD setup with **Jenkins** and **Docker**  
- ✅ Responsive frontend (React + Bootstrap) to visualize data  

---

## 📂 Project Structure

<img width="812" height="635" alt="Screenshot 2025-09-15 134125" src="https://github.com/user-attachments/assets/58377151-c0f3-4445-b41d-06e372ffa145" />


---

## ⚙️ Backend (Spring Boot)

### 🔹 Endpoints

#### Courses
- `GET /api/cours` → Get all courses  
- `GET /api/cours/{id}` → Get course by mnemonique  

#### Inscriptions
- `GET /api/inscriptions` → Get all inscriptions  
- `GET /api/inscriptions/{matricule}` → Get inscriptions for a student  

#### Notes
- `GET /api/notes` → Get all notes  
- `GET /api/notes/student/{matricule}` → Get notes by student  
- `GET /api/notes/course/{mnemonique}` → Get notes by course  
- `GET /api/notes/{matricule}/{mnemonique}` → Get specific note  

#### Bulletins
- `GET /api/bulletins` → Generate bulletins per student/year  

#### Anomalies
- `GET /api/anomalies` → Generate anomaly report  

---
## 🧠 Design Decisions & Algorithm Choices

### 1. Backend Design (Spring Boot + REST API)
I used **Spring Boot** for the backend because it provides:
- Built-in support for REST endpoints with simple annotations (`@RestController`, `@GetMapping`).  
- Integration with **Spring Data JPA** for easy SQLite access.  
- Automatic API docs via **Swagger/OpenAPI**.  

👉 This kept the backend **clean, modular, and easy to extend**.

---

### 2. Bulletin Generation
- Compute per-student totals, averages, and success.  
- For each course: add credits, count passed ones, include note in weighted average.  
- Success if `ects_obtenus ≥ 60` or all graded with average ≥ 10.  
- **Efficiency:** O(n) per student, with O(1) lookups via maps.  

---

### 3. Anomaly Detection
- Detects:  
  - note without registration  
  - course not in DB  
  - empty course list  
  - duplicate notes  
  - invalid credits  
- Uses maps for quick lookups, single-pass validation.  
- **Efficiency:** O(n), scalable without nested scans.  

---

### 4. DTOs vs Models
- **Models** (`Cour`, `Inscription`, `Note`):  
  - Direct mapping to SQLite database tables.  
  - Used for CRUD operations via Spring Data JPA.  

- **DTOs** (`BulletinDto`, `AnomalyDto`, `NoteDto`, etc.):  
  - Used to **shape the API responses** (bulletins & anomalies).  
  - Keeps API payloads clean and separate from database entities.  
  - Allows flexibility if the database schema changes without breaking the API.  

👉 This separation follows **best practices (Separation of Concerns)** and makes the codebase easier to maintain.

---

### 5. Layered Architecture: Controller, Service, Repository
- **Controller Layer**:  
  - Handles HTTP requests/responses.  
  - Exposes clean REST endpoints for the frontend and API clients.  
  - Does **not** contain business logic → keeps code testable.  

- **Service Layer**:  
  - Contains business logic (bulletin calculation, anomaly detection).  
  - Reusable and independent of web layer → easier to test and extend.  

- **Repository Layer**:  
  - Manages persistence with **Spring Data JPA**.  
  - Abstracts SQL/SQLite queries → developers only work with Java objects.  

👉 This structure enforces **Separation of Concerns**:  
- Controllers = API interface  
- Services = Business rules  
- Repositories = Database access  

---

### 6. Frontend Design (React + Bootstrap)
- Chose **React** for modular components and quick state updates.  
- Used **React-Bootstrap** for fast responsive UI with minimal CSS.  
- Each resource (Courses, Notes, Bulletins, Anomalies) has its own component → **separation of concerns**.  
- Tables & search bars improve **UX** (quick filtering).  

---

### 7. DevOps Choices
- **Docker** → consistent environment for backend, frontend, and DB.  
- **Jenkins** → automate build, test, and deploy pipeline.  
- **docker-compose** → one command to spin up the whole system.  


✅ These choices ensure the system is **modular, efficient (O(n) per student), and easy to deploy**.



````markdown
# 🚀 How to Run the Project

The easiest way to run everything is with **Docker Compose**:

---

## ▶ Run with Docker

1. Make sure you have **Docker** installed:  
   👉 [Get Docker](https://www.docker.com/get-started/)

2. Clone this repository and navigate into the folder:
   ```bash
   git clone https://github.com/malsaleh88/university-ects-system.git
   cd university-ects-system
````

3. Start the application:

   ```bash
   docker-compose up --build
   ```

4. Open your browser:

   ```bash
   # Backend API Docs → http://localhost:8080/swagger-ui.html
   # Frontend App → http://localhost:3000
   ```

---

## 🛠 Alternative (Run without Docker)

### Backend (Spring Boot)

```bash
cd university-ects-system
./mvnw spring-boot:run
# Open http://localhost:8080
# Swagger UI: http://localhost:8080/swagger-ui.html
```

### Frontend (React)

```bash
cd frontend
npm install
npm start
# Open http://localhost:3000
```

## 🎥 Demo Video

https://github.com/user-attachments/assets/9ab78b21-8edd-4b7e-b446-dd475db838aa

---

## 🛠 Jenkins Build Example

<img width="1912" height="880" alt="jenkiiii" src="https://github.com/user-attachments/assets/2c1c7c8d-f36f-4010-a1b5-a33c2247eadc" />


---

## 🙏 Conclusion

This project demonstrates:  
- Building a **full-stack system** (Spring Boot + React)  
- Designing efficient **algorithms** for bulletins & anomaly detection  
- Using **Docker & Jenkins** for reproducibility and CI/CD  
- Delivering a clean **frontend UI** for easy interaction  

⚡ Thank you for reviewing my work!  

👤 **Author:** Mohammad Alsaleh  


