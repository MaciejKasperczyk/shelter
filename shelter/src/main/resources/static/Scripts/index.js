async function adoptDog(id) {
    try {
        if (id === undefined) {
            console.error('ID psa jest niezdefiniowane.');
            return;
        }
        const response = await fetch(`http://localhost:8090/dogs/delete/${id}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            }
        });
        if (response.ok) {
            // Usunięto psa z sukcesem, odśwież tabelę
            fetchAndRenderDogs();
        } else {
            console.error('Failed to adopt dog:', response.status);
        }
    } catch (error) {
        console.error('Error adopting dog:', error);
    }
}

async function fetchAndRenderDogs() {
    try {
        const response = await fetch('http://localhost:8090/dogs');
        const dogs = await response.json();
        const tableBody = document.getElementById('dogs-table-body');
        tableBody.innerHTML = '';

        for (const dog of dogs) {
            const imagePromise = getRandomDogImage(); // Pobieranie obrazka
            const lastVisitPromise = fetchLastVetVisit(dog.dog_id); // Pobieranie daty ostatniej wizyty
            const [image, lastVisit] = await Promise.all([imagePromise, lastVisitPromise]); // Czekamy na oba wyniki

            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${dog.dog_id}</td>
                <td>${dog.dog_name}</td>
                <td>${dog.dog_race}</td>
                <td>${dog.dog_sex}</td>
                <td>${dog.dog_age}</td>
                <td><img src="${image}" style="width: 200px; height: 200px;" /></td>
                <td>${lastVisit}</td>
                <
            `;
            tableBody.appendChild(row);
        }
    } catch (error) {
        console.error('Error fetching dogs:', error);
    }
}



async function getRandomDogImage() {
    try {
        const response = await fetch('http://localhost:8090/random-dog-image');
        const data = await response.json();
        return data.message; // Zwraca adres URL losowego zdjęcia psa
    } catch (error) {
        console.error('Error fetching random dog image:', error);
        return ''; // Zwraca pusty ciąg w przypadku błędu
    }
}

async function fetchLastVetVisit(dogId) {
    try {
        const response = await fetch(`http://localhost:8090/dogs/${dogId}/last-visit`);
        const data = await response.json();
        return data; // Zakładamy, że API zwraca datę jako string
    } catch (error) {
        console.error(`Error fetching last vet visit for dog ${dogId}:`, error);
        return 'Brak danych'; // Zwraca tę wartość w przypadku błędu
    }
}

window.onload = fetchAndRenderDogs;
