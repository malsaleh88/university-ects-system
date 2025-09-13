import { useEffect, useState } from "react";

export default function Anomalies() {
  const [anomalies, setAnomalies] = useState([]);

  useEffect(() => {
    fetch("http://localhost:8080/api/anomalies")
      .then((res) => res.json())
      .then(setAnomalies)
      .catch((err) => console.error("Error:", err));
  }, []);

  return (
    <div className="container my-4">
      <h1 className="mb-4">Anomalies</h1>
      <table className="table table-hover table-bordered">
        <thead className="table-dark">
          <tr>
            <th>Type</th>
            <th>Matricule</th>
            <th>Année</th>
            <th>Détail</th>
          </tr>
        </thead>
        <tbody>
          {anomalies.map((a, i) => (
            <tr key={i}>
              <td>{a.type}</td>
              <td>{a.matricule}</td>
              <td>{a.annee}</td>
              <td>{a.detail}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
