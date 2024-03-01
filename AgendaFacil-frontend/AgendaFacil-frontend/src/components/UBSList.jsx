import React from 'react';
import UBSCard from './UBSCard';

const UBSList = ({ ubsList }) => {
    return (
        <div>
            <h2>Unidade Basicas de Saude da Regiao</h2>
            <div>
                {ubsList.map(ubs => (
                    <UBSCard key={ubs.id} ubs={ubs} />
                ))}
            </div>
        </div>
    );
};

export default UBSList;