export const apontamentoHorasService = {
    apontarHoras
};

function apontarHoras(tempo, projetoId) {
    let user = JSON.parse(localStorage.getItem('jwt'));
    const userId = user.id;

    const requestOptions = {
        method: 'POST',
        headers: { 
            'Content-Type': 'application/json' 
            // 'Authorization': 'Bearer ' + user.token
        },
        body: JSON.stringify({ tempo, projetoId, userId })
    };

    console.warn('Realizando apontamento de hrs: ', requestOptions.body);

    return fetch(`http://localhost:8080/apontamento-horas`, requestOptions)
        .then(handleResponse).then(ap => console.log('sucesso ', ap));
}

function handleResponse(response) {
    return response.text().then(text => {
        const data = text && JSON.parse(text);
        if (!response.ok) {
            const error = (data && data.message) || response.statusText;
            return Promise.reject(error);
        }

        return data;
    });
}