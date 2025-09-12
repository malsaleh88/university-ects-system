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
    <div style={{ padding: "20px" }}>
      <h1>Anomalies</h1>
      <table border="1" cellPadding="5">
        <thead>
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
