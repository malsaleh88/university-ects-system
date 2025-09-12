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
          style={{ border: "1px solid #ccc", margin: "10px", padding: "10px" }}
        >
          <h2>
            {b.nom} {b.prenom} ({b.matricule})
          </h2>
          <p>Année: {b.anneeEtude}</p>
          <p>Moyenne: {b.moyenne.toFixed(2)}</p>
          <p>Réussite: {b.reussite ? "✅ Oui" : "❌ Non"}</p>
        </div>
      ))}
    </div>
  );
}
