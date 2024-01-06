import React, { useState, useEffect } from 'react'
import UBSList from './components/UBSList';
import './App.css'

const App = () => {
  const [ubsList, setUbsList] = useState([]);

  useEffect(() => {
    fetch('http://localhost:8080/agenda-facil')
      .then(response => response.json())
      .then(data => setUbsList(data))
      .catch(error => console.error('Erro ao buscar UBS:', error));
  }, []);

  return (
    <div>
      <h1>Agenda Facil</h1>
      <UBSList ubsList={ubsList} />
    </div>
  );
};

export default App
