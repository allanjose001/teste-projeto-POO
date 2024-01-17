import React from 'react';
import "./UBSCard.css";

const UBSCard = ({ ubs }) => {
    return (
        <div className="ubs-card">
            <img src= "https://picsum.photos/200/300"  alt={ubs.nomeUBS}/>
            <div>
                <p><strong>{ubs.nomeUBS}</strong></p>
                <p>Bairro: {ubs.bairro}</p>
                <p>Rua: {ubs.rua}</p>
            </div>
        </div>
    );
};

export default UBSCard;
