import React from 'react';
import "./UBSCard.css";

const UBSCard = ({ ubs }) => {
    const history = useHistory();

    const handleClick = () => {

        history.push('/agendamento/${ubs.id}');
    };

    return (

        <div className="ubs-card" onClick = {handleClick}>
            <img src= "https://picsum.photos/200/300"  alt={ubs.nomeUBS}/>
            <div>
                <p><strong>{ubs.nomeUBS}</strong></p>
                <p>Rua: {ubs.endereco.rua}</p>
                <p>Bairro: {ubs.endereco.bairro}</p>
                <p>Cidade: {ubs.endereco.cidade}</p>
                <p>Estado: {ubs.endereco.estado}</p>
            </div>
        </div>
    );
};

export default UBSCard;
