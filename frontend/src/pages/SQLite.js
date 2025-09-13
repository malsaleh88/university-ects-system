import { useState } from "react";
import Button from "react-bootstrap/Button";
import Container from "react-bootstrap/Container";

export default function SQLite() {
  const [data, setData] = useState([]);

  const fetchData = (endpoint) => {
    fetch(`http://localhost:8080/api/${endpoint}`)
      .then((res) => res.json())
      .then(setData)
      .catch((err) => console.error("Error:", err));
  };

  return (
    <Container style={{ marginTop: "20px" }}>
      <h1>SQLite Data</h1>
      <p>Explore raw data from the SQLite database:</p>

      <div style={{ marginBottom: "20px" }}>
        <Button variant="primary" onClick={() => fetchData("inscriptions")} style={{ margin: "5px" }}>
          Get Inscriptions
        </Button>
        <Button variant="info" onClick={() => fetchData("notes")} style={{ margin: "5px" }}>
          Get Notes
        </Button>
        <Button variant="warning" onClick={() => fetchData("cours")} style={{ margin: "5px" }}>
          Get Cours
        </Button>
      </div>

      <pre>{JSON.stringify(data, null, 2)}</pre>
    </Container>
  );
}
