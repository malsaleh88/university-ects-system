import { Link } from "react-router-dom";
import { Container, Button } from "react-bootstrap";

export default function Home() {
  return (
    <Container className="text-center" style={{ marginTop: "50px" }}>
      <h1>👋 Welcome to University ECTS System</h1>
      <p>Select which part you want to explore:</p>

      <div style={{ marginTop: "30px" }}>
        <Link to="/sqlite">
          <Button variant="primary" size="lg" style={{ margin: "10px" }}>
            Part 1: SQLite Data
          </Button>
        </Link>

        <Link to="/bulletins">
          <Button variant="success" size="lg" style={{ margin: "10px" }}>
            Part 2: API (Bulletins & Anomalies)
          </Button>
        </Link>
      </div>
    </Container>
  );
}
