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
    <div className="container my-4">
      <h1 className="mb-4">Bulletins</h1>
      {bulletins.map((b) => (
        <div key={b.matricule} className="card mb-3 shadow-sm">
          <div className="card-body">
            <h4 className="card-title">
              {b.nom} {b.prenom}{" "}
              <small className="text-muted">({b.matricule})</small>
            </h4>
            <p><strong>Année:</strong> {b.anneeEtude}</p>
            <p><strong>ECTS inscrits:</strong> {b.ectsTotalInscrits}</p>
            <p><strong>ECTS obtenus:</strong> {b.ectsObtenus}</p>
            <p>
              <strong>Moyenne:</strong>{" "}
              {b.moyenne !== null && b.moyenne !== undefined
                ? b.moyenne.toFixed(2)
                : "N/A"}
            </p>
            <p>
              <strong>Réussite:</strong>{" "}
              {b.reussite ? (
                <span className="badge bg-success">✅ Oui</span>
              ) : (
                <span className="badge bg-danger">❌ Non</span>
              )}
            </p>

            <h5>Détails des cours</h5>
            <table className="table table-striped table-bordered">
              <thead className="table-dark">
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
        </div>
      ))}
    </div>
  );
}
