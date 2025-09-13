import { Container, Button } from "react-bootstrap";
import { Link } from "react-router-dom";

export default function SQLite() {
  return (
    <Container className="text-center" style={{ marginTop: "100px" }}>
      <h1>📊 SQLite Data</h1>
      <p>Select which dataset you want to explore:</p>

      <div style={{ marginTop: "40px" }}>
        <Link to="/inscriptions">
          <Button variant="primary" size="lg" style={{ margin: "15px", width: "200px" }}>
            Inscriptions
          </Button>
        </Link>

        <Link to="/notes">
          <Button variant="info" size="lg" style={{ margin: "15px", width: "200px" }}>
            Notes
          </Button>
        </Link>

        <Link to="/cours">
          <Button variant="warning" size="lg" style={{ margin: "15px", width: "200px" }}>
            Cours
          </Button>
        </Link>
      </div>
    </Container>
  );
}
