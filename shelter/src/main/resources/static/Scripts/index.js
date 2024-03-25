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
        dogs.forEach(async (dog) => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${dog.dog_id}</td>
                <td>${dog.dog_name}</td>
                <td>${dog.dog_race}</td>
                <td>${dog.dog_sex}</td>
                <td>${dog.dog_age}</td>
                <td><img src="${await getRandomDogImage()}" style="width: 200px; height: 200px;" /></td>
                <td><button class="btn btn-danger" onclick="adoptDog(${dog.dog_id})">Zaadoptuj</button></td>
                
            `;
            tableBody.appendChild(row);
        });
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

window.onload = fetchAndRenderDogs;