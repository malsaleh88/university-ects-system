import { useEffect, useState } from "react";

export default function Bulletins() {
  const [bulletins, setBulletins] = useState([]);

  useEffect(() => {
    fetch("http://localhost:8080/api/bulletins")
      .then((res) => res.json())
      .then(setBulletins)
      .catch((err) => console.error("Error:", err));
  }, []);

  return (
    <div style={{ padding: "20px" }}>
      <h1>Bulletins</h1>
      {bulletins.map((b) => (
        <div
          key={b.matricule}
          style={{
            border: "1px solid #ccc",
            margin: "10px 0",
            padding: "15px",
            borderRadius: "6px",
          }}
        >
          <h2>
            {b.nom} {b.prenom} ({b.matricule})
          </h2>
          <p>Année: {b.anneeEtude}</p>
          <p>ECTS inscrits: {b.ectsTotalInscrits}</p>
          <p>ECTS obtenus: {b.ectsObtenus}</p>
          <p>
            Moyenne:{" "}
            {b.moyenne !== null && b.moyenne !== undefined
              ? b.moyenne.toFixed(2)
              : "N/A"}
          </p>
          <p>Réussite: {b.reussite ? "✅ Oui" : "❌ Non"}</p>

          {/* --- Table of courses --- */}
          <h3>Détails des cours</h3>
          <table
            border="1"
            cellPadding="5"
            style={{ borderCollapse: "collapse", width: "100%" }}
          >
            <thead>
              <tr>
                <th>Mnemonique</th>
                <th>Intitulé</th>
                <th>Crédit</th>
                <th>Titulaire</th>
                <th>Note</th>
              </tr>
            </thead>
            <tbody>
              {b.details.map((d, i) => (
                <tr key={i}>
                  <td>{d.mnemonique}</td>
                  <td>{d.intitule}</td>
                  <td>{d.credit}</td>
                  <td>{d.titulaire}</td>
                  <td>{d.note !== null ? d.note : "—"}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      ))}
    </div>
  );
}
