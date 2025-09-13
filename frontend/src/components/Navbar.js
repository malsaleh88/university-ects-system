import { Link } from "react-router-dom";

export default function Navbar() {
  return (
    <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
      <div className="container-fluid">
        <Link className="navbar-brand" to="/bulletins">University ECTS</Link>
        <div className="collapse navbar-collapse">
          <ul className="navbar-nav me-auto">
            <li className="nav-item">
              <Link className="nav-link" to="/bulletins">Bulletins</Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/anomalies">Anomalies</Link>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  );
}
