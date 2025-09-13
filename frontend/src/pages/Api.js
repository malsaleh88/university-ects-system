import { Link } from "react-router-dom";
import { Container, Button } from "react-bootstrap";

export default function Api() {
  return (
    <Container className="text-center" style={{ marginTop: "100px" }}>
      <h1>🌐 API Data</h1>
      <p>Select which API resource you want to explore:</p>

      <div style={{ marginTop: "40px" }}>
        <Link to="/bulletins">
          <Button variant="success" size="lg" style={{ margin: "15px", width: "220px" }}>
            Bulletins
          </Button>
        </Link>

        <Link to="/anomalies">
          <Button variant="danger" size="lg" style={{ margin: "15px", width: "220px" }}>
            Anomalies
          </Button>
        </Link>
      </div>
    </Container>
  );
}
