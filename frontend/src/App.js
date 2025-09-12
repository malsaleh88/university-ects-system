import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import Navbar from "./components/Navbar";
import Bulletins from "./pages/Bulletins";
import Anomalies from "./pages/Anomalies";

function App() {
  return (
    <BrowserRouter>
      <Navbar />
      <Routes>
        <Route path="/bulletins" element={<Bulletins />} />
        <Route path="/anomalies" element={<Anomalies />} />
        <Route path="*" element={<Navigate to="/bulletins" replace />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
