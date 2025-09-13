import { useEffect, useState } from "react";
import { Table, Container } from "react-bootstrap";

export default function Inscriptions() {
  const [inscriptions, setInscriptions] = useState([]);

  useEffect(() => {
    fetch("http://localhost:8080/api/inscriptions")
      .then(res => res.json())
      .then(setInscriptions)
      .catch(err => console.error("Error:", err));
  }, []);

  return (
    <Container style={{ marginTop: "20px" }}>
      <h1>Inscriptions</h1>
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
              <td>{i.anneeEtude}</td>
              <td>{i.coursJson}</td>
            </tr>
          ))}
        </tbody>
      </Table>
    </Container>
  );
}
