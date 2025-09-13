import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import Navbar from "./components/Navbar";
import Bulletins from "./pages/Bulletins";
import Anomalies from "./pages/Anomalies";
import Inscriptions from "./pages/Inscriptions";
import Notes from "./pages/Notes";
import Cours from "./pages/Cours";

import About from "./pages/About";
import Home from "./pages/Home";  // ⬅ new
import SQLite from "./pages/SQLite";

function App() {
  return (
    <BrowserRouter>
      <Navbar />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/sqlite" element={<SQLite />} />
        <Route path="/bulletins" element={<Bulletins />} />
        <Route path="/inscriptions" element={<Inscriptions />} />
        <Route path="/anomalies" element={<Anomalies />} />
                <Route path="/cours" element={<Cours />} />

        <Route path="/notes" element={<Notes />} />
        <Route path="/about" element={<About />} />

        <Route path="*" element={<Navigate to="/" replace />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
