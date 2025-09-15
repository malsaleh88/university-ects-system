# 🎓 University ECTS System

A Spring Boot + React application for managing university ECTS (European Credit Transfer System).  
This project was developed as part of a technical assessment and demonstrates:

- ✅ REST API for accessing student/course data  
- ✅ Automatic **bulletin generation** with averages, credits, and pass/fail detection  
- ✅ **Anomaly detection** (data inconsistencies: missing credits, duplicate notes, etc.)  
- ✅ CI/CD setup with **Jenkins** and **Docker**  
- ✅ Modern frontend (React) to visualize data  

---

## 📂 Project Structure

university-ects-system/
├── frontend/ # React frontend
├── university-ects-system/ # Spring Boot backend
│ ├── config/ # Configuration (e.g., CORS)
│ ├── controller/ # REST Controllers (API endpoints)
│ ├── dto/ # Data Transfer Objects
│ ├── model/ # Entities (Cour, Inscription, Note)
│ ├── repository/ # Spring Data JPA Repositories
│ ├── service/ # Business logic & anomaly/bulletin generation
│ └── UniversityEctsSystemApplication.java
├── docker-compose.yml # Compose setup for backend & frontend
├── Dockerfile # Backend Dockerfile
├── Jenkinsfile # CI/CD pipeline
└── README.md # You are here 🚀


---

## ⚙️ Backend (Spring Boot)

The backend exposes several REST APIs under `/api/**`.

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

## 📊 Business Logic

### 🎓 Bulletin Generation
For each student/year:
- **ects_total_inscrits** → sum of credits of registered courses  
- **ects_obtenus** → sum of credits of passed courses (note ≥ 10)  
- **moyenne_ponderee** → weighted average of notes by credits  
- **reussite** → true if ects_obtenus ≥ 60 OR all courses graded & average ≥ 10  

### ⚠️ Anomaly Detection
Detects:
- `NOTE_SANS_INSCRIPTION` → Note exists but course not in inscription  
- `COURS_INCONNU` → Course in inscription but missing from DB  
- `INSCRIPTION_SANS_COURS` → Student with empty course list  
- `DUPLICATA_NOTE` → Multiple notes for same (student, course)  
- `NOTE_SANS_CREDIT` → Course with note but invalid credits  

---

## 🐳 Running with Docker

```bash
# Build & run services
docker-compose up --build
