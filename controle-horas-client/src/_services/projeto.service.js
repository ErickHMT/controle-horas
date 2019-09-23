import { authHeader } from '../_helpers';

export const projetoService = {
    getProjetos
};

function getProjetos() {
    const requestOptions = {
        method: 'GET',
        // headers: authHeader()
    };

    return fetch(`http://localhost:8080/projetos`, requestOptions).then(handleResponse);
}

function handleResponse(response) {
    return response.text().then(text => {
        const data = text && JSON.parse(text);
        if (!response.ok) {
            // if (response.status === 401 ) {
            //     logout();
            // }

            const error = (data && data.message) || response.statusText;
            return Promise.reject(error);
        }

        return data;
    });
}