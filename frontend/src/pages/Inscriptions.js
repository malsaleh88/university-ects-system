import { useState } from "react";
import { Table, Container, Form, Button } from "react-bootstrap";

export default function Inscriptions() {
  const [matricule, setMatricule] = useState("");
  const [inscriptions, setInscriptions] = useState([]);
  const [error, setError] = useState("");

  const fetchAll = () => {
    fetch("http://localhost:8080/api/inscriptions")
      .then(res => res.json())
      .then(data => {
        setInscriptions(data);
        setError("");
      })
      .catch(() => setError("Failed to fetch inscriptions."));
  };

  const fetchByMatricule = () => {
    if (!matricule) return;
    fetch(`http://localhost:8080/api/inscriptions/${matricule}`)
      .then(res => {
        if (!res.ok) throw new Error("Not found");
        return res.json();
      })
      .then(data => {
        setInscriptions(data);
        setError("");
      })
      .catch(() => setError("No inscriptions found for this matricule."));
  };

  const formatCours = (cours) => {
    try {
      if (!cours) return "—";
      const parsed = JSON.parse(cours); // cours_json is a JSON string
      if (Array.isArray(parsed)) return parsed.join(", ");
      return cours;
    } catch {
      return cours;
    }
  };

  return (
    <Container style={{ marginTop: "20px" }}>
      <h1>Inscriptions</h1>

      <Form className="d-flex mb-3">
        <Form.Control
          type="text"
          placeholder="Enter matricule..."
          value={matricule}
          onChange={(e) => setMatricule(e.target.value)}
        />
        <Button
          variant="primary"
          onClick={fetchByMatricule}
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

      {error && <p style={{ color: "red" }}>{error}</p>}

      <Table striped bordered hover>
        <thead>
          <tr>
            <th>Matricule</th>
            <th>Nom</th>
            <th>Prénom</th>
            <th>Année</th>
            <th>Cours</th>
          </tr>
        </thead>
        <tbody>
          {inscriptions.map((i, idx) => (
            <tr key={idx}>
              <td>{i.matricule}</td>
              <td>{i.nom}</td>
              <td>{i.prenom}</td>
              <td>{i.annee_etude}</td>
              <td>{formatCours(i.cours_json)}</td>
            </tr>
          ))}
        </tbody>
      </Table>
    </Container>
  );
}
