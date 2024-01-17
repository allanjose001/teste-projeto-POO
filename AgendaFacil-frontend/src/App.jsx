import React, { useState, useEffect } from 'react'
import { useQuery } from 'react-query';
import BaseLayout from './components/BaseLayout';
import UBSList from './components/UBSList';
import './App.css'

//verifica se os dados estão chegando do back-end
const fetchData = async () => {
  const response = await fetch('http://localhost:8080/agenda-facil');
  if (!response.ok) {
    throw new Error('Erro ao buscar UBS');
  }
  return response.json();
};

const App = () => {
  const { data: ubsList, error } = useQuery('ubsList', fetchData);

  if (error) {
    console.error('Erro ao buscar UBS:', error);
  }

  return (
    <BaseLayout>
      <div>
        <h1>Agenda Facil</h1>
        {ubsList ? <UBSList ubsList={ubsList} /> : <p>Carregando...</p>}
      </div>
    </BaseLayout>
  );
};

export default App