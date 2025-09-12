import { Link } from "react-router-dom";

export default function Navbar() {
  return (
    <nav style={{ padding: "10px", background: "#282c34" }}>
      <Link to="/bulletins" style={{ color: "white", marginRight: "15px" }}>
        Bulletins
      </Link>
      <Link to="/anomalies" style={{ color: "white" }}>
        Anomalies
      </Link>
    </nav>
  );
}
