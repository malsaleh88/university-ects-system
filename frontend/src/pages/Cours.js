import { useState } from "react";
import { Table, Container, Form, Button } from "react-bootstrap";

export default function Cours() {
  const [mnemonique, setMnemonique] = useState("");
  const [cours, setCours] = useState([]);
  const [error, setError] = useState("");

  // Fetch all courses
  const fetchAll = () => {
    fetch("http://localhost:8080/api/cours")
      .then(res => res.json())
      .then(data => {
        setCours(data);
        setError("");
      })
      .catch(() => setError("Failed to fetch courses."));
  };

  // Fetch course by mnemonique
  const fetchByMnemonique = () => {
    if (!mnemonique) return;
    fetch(`http://localhost:8080/api/cours/${mnemonique}`)
      .then(res => {
        if (!res.ok) throw new Error("Not found");
        return res.json();
      })
      .then(data => {
        setCours([data]); // wrap single course into array for table
        setError("");
      })
      .catch(() => setError("No course found with this mnemonique."));
  };

  return (
    <Container style={{ marginTop: "20px" }}>
      <h1>Cours</h1>

      {/* 🔎 Search Bar */}
      <Form className="d-flex mb-3">
        <Form.Control
          type="text"
          placeholder="Enter mnemonique..."
          value={mnemonique}
          onChange={(e) => setMnemonique(e.target.value)}
        />
        <Button
          variant="primary"
          onClick={fetchByMnemonique}
          style={{ marginLeft: "10px" }}
        >
          Search
        </Button>
        <Button
          variant="secondary"
          onClick={fetchAll}
          style={{ marginLeft: "10px" }}
        >
          Show All
        </Button>
      </Form>

      {/* ⚠️ Error */}
      {error && <p style={{ color: "red" }}>{error}</p>}

      {/* 📋 Courses Table */}
      <Table striped bordered hover>
        <thead>
          <tr>
            <th>Mnemonique</th>
            <th>Intitulé</th>
            <th>Crédits</th>
            <th>Titulaire</th>
          </tr>
        </thead>
        <tbody>
          {cours.map((c, idx) => (
            <tr key={idx}>
              <td>{c.mnemonique}</td>
              <td>{c.intitule}</td>
              <td>{c.credit}</td>
              <td>{c.titulaire}</td>
            </tr>
          ))}
        </tbody>
      </Table>
    </Container>
  );
}
