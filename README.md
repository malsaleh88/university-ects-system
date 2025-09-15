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

## 📊 Business Logic

### 🎓 Bulletin Generation
For each student/year:
- **ects_total_inscrits** → sum of registered course credits  
- **ects_obtenus** → sum of credits of passed courses (note ≥ 10)  
- **moyenne_ponderee** → weighted average of notes by credits  
- **reussite** → true if ects_obtenus ≥ 60 OR all courses graded & average ≥ 10  

### ⚠️ Anomaly Detection
- `NOTE_SANS_INSCRIPTION` → Note exists but not registered  
- `COURS_INCONNU` → Course in inscription but missing in DB  
- `INSCRIPTION_SANS_COURS` → Student without registered courses  
- `DUPLICATA_NOTE` → Multiple notes for same (student, course)  
- `NOTE_SANS_CREDIT` → Course with note but invalid credits  

---



## 🐳 Running with Docker

```bash
# Build & run services
docker-compose up --build
