import React, { useState, useEffect } from 'react'
import { useQuery } from 'react-query';
import BaseLayout from '../../components/BaseLayout';

const fetchData = async () => {
    const response = await fetch('http://localhost:8080/agendamento');
    if (!response.ok) {
      throw new Error('Erro ao buscar UBS');
    }
    return response.json();
};

const App = () => {
    const { data: ubsList } = useQuery('ubsList', fetchData);
  
    return (
      <BaseLayout>
        <div>
          <h1>Agenda Facil</h1>

        </div>
      </BaseLayout>
    );
  };
  
  export default agendamento