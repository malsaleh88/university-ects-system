import { useState } from "react";
import { Table, Container, Form, Button, Row, Col } from "react-bootstrap";

export default function Notes() {
  const [matricule, setMatricule] = useState("");
  const [mnemonique, setMnemonique] = useState("");
  const [notes, setNotes] = useState([]);
  const [error, setError] = useState("");

  // Fetch all notes
  const fetchAll = () => {
    fetch("http://localhost:8080/api/notes")
      .then(res => res.json())
      .then(data => {
        setNotes(data);
        setError("");
      })
      .catch(() => setError("Failed to fetch notes."));
  };

  // Fetch by student (matricule)
  const fetchByMatricule = () => {
    if (!matricule) return;
    fetch(`http://localhost:8080/api/notes/student/${matricule}`)
      .then(res => {
        if (!res.ok) throw new Error("Not found");
        return res.json();
      })
      .then(data => {
        setNotes(data);
        setError("");
      })
      .catch(() => setError("No notes found for this matricule."));
  };

  // Fetch by course (mnemonique)
  const fetchByCourse = () => {
    if (!mnemonique) return;
    fetch(`http://localhost:8080/api/notes/course/${mnemonique}`)
      .then(res => {
        if (!res.ok) throw new Error("Not found");
        return res.json();
      })
      .then(data => {
        setNotes(data);
        setError("");
      })
      .catch(() => setError("No notes found for this course."));
  };

  // Fetch by matricule + mnemonique
  const fetchByMatriculeAndCourse = () => {
    if (!matricule || !mnemonique) return;
    fetch(`http://localhost:8080/api/notes/${matricule}/${mnemonique}`)
      .then(res => {
        if (!res.ok) throw new Error("Not found");
        return res.json();
      })
      .then(data => {
        setNotes(data);
        setError("");
      })
      .catch(() => setError("No note found for this matricule and course."));
  };

  return (
    <Container style={{ marginTop: "20px" }}>
      <h1>Notes</h1>

      {/* 🔎 Search Inputs */}
      <Row className="mb-3">
        <Col>
          <Form.Control
            type="text"
            placeholder="Enter matricule..."
            value={matricule}
            onChange={(e) => setMatricule(e.target.value)}
          />
        </Col>
        <Col>
          <Form.Control
            type="text"
            placeholder="Enter course (mnemonique)..."
            value={mnemonique}
            onChange={(e) => setMnemonique(e.target.value)}
          />
        </Col>
      </Row>

      {/* Buttons */}
      <div className="mb-3">
        <Button variant="primary" onClick={fetchByMatricule} style={{ marginRight: "10px" }}>
          Search by Matricule
        </Button>
        <Button variant="info" onClick={fetchByCourse} style={{ marginRight: "10px" }}>
          Search by Course
        </Button>
        <Button variant="warning" onClick={fetchByMatriculeAndCourse} style={{ marginRight: "10px" }}>
          Search Both
        </Button>
        <Button variant="secondary" onClick={fetchAll}>
          Show All
        </Button>
      </div>

      {/* ⚠️ Error */}
      {error && <p style={{ color: "red" }}>{error}</p>}

      {/* 📋 Notes Table */}
      <Table striped bordered hover>
        <thead>
          <tr>
            <th>ID</th>
            <th>Matricule</th>
            <th>Course (Mnemonique)</th>
            <th>Note</th>
          </tr>
        </thead>
        <tbody>
          {notes.map((n, idx) => (
            <tr key={idx}>
              <td>{n.id}</td>
              <td>{n.matricule}</td>
              <td>{n.mnemonique}</td>
              <td>{n.note}</td>
            </tr>
          ))}
        </tbody>
      </Table>
    </Container>
  );
}
