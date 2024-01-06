import React from 'react';
import "./UBSCard.css";

const UBSCard = ({ ubs }) => {
    return (
        <div className="ubs-card">
            <img src={ubs.image} alt={ubs.nomeUBS}/>
            <p><strong>{ubs.nomeUBS}</strong></p>
            <p>Bairro: {ubs.bairro}</p>
            <p>Rua: {ubs.rua}</p>
        </div>
    );
};

export default UBSCard;
