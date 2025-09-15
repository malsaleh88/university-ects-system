# 🎓 University ECTS System

A full-stack project (Spring Boot + React) to manage university **ECTS** (European Credit Transfer System).  
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

## 📜 API Documentation (Swagger)

Swagger UI is enabled for quick API testing.  
Once the backend is running:

👉 [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)  

---

## 🧠 Design Decisions & Algorithm Choices

### 1. Backend Design (Spring Boot + REST API)
- I used **Spring Boot** for the backend because it provides:
  - Built-in support for REST endpoints with simple annotations (`@RestController`, `@GetMapping`).
  - Integration with **Spring Data JPA** for easy SQLite access.
  - Automatic API docs via **Swagger/OpenAPI**.

This kept the backend clean, modular, and easy to extend.

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


### 4. Frontend Design (React + Bootstrap)
- Chose **React** for modular components and quick state updates.  
- Used **React-Bootstrap** for fast responsive UI with minimal CSS.  
- Each resource (Courses, Notes, Bulletins, Anomalies) has its own component → separation of concerns.  
- Tables & search bars improve **UX** (quick filtering).

---

### 5. DevOps Choices
- **Docker** → consistent environment for backend, frontend, and DB.  
- **Jenkins** → automate build, test, and deploy pipeline.  
- **docker-compose** → one command to spin up the whole system.  

---

✅ These choices ensure the system is **modular, efficient (O(n) per student), and easy to deploy**.




## 🐳 Running with Docker

```bash
# Build & run services
docker-compose up --build
