

function deleteTournament(tournamentId) {
    if (confirm("Are you sure you want to delete this tournament?")) {
        const contextPath = window.location.pathname.split('/')[1]; // Получает имя контекста
        let url = '/{contextPath}/tournaments_admin/{tournamentId}';
        url = url.replace("{tournamentId}", tournamentId);
        url = url.replace("{contextPath}", contextPath);

        fetch(url, {
            method: 'DELETE',
        })
            .then(response => {
                if (response.ok) {
                    location.reload();
                    console.log('Tournament deleted successfully'); // Успешное удаление
                } else if (response.status === 404) {
                    console.error('Error: Tournament not found'); // Турнир не найден
                } else {
                    console.error('Error deleting tournament:', response.statusText); // Другие ошибки
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('Error while deleting tournament');
            });
    }
}


// Функция для открытия модального окна
function openModal(action, tournament) {
    const modal = document.getElementById('modal');
    modal.style.display = 'block';

    // Обновление заголовка модального окна
    const modalTitle = document.getElementById('modalTitle');
    modalTitle.textContent = action === 'edit' ? 'Edit Tournament' : 'Add Tournament';
    const tournamentForm = document.getElementById('tournamentForm');
    tournamentForm.action = action === 'edit' ? 'tournaments_admin/?action=edit' : 'tournaments_admin/?action=create';

    if (action === 'edit') {
        // Заполнение формы данными турнира
        document.getElementById('tournamentId').value = tournament.id;
        document.getElementById('tournamentName').value = tournament.name;
        document.getElementById('tournamentStartDate').value = tournament.startDate;
        document.getElementById('tournamentEndDate').value = tournament.endDate;
        document.getElementById('tournamentPrizePool').value = tournament.prizePool;
        document.getElementById('tournamentOrganizer').value = tournament.organizer;
    } else {
        // Очистка формы для добавления нового турнира
        document.getElementById('tournamentForm').reset();
    }
}

// Функция для закрытия модального окна
function closeModal() {
    document.getElementById('modal').style.display = 'none';
}


/// Функция для отправки формы (добавление и редактирование турнира)

/*function submitForm(event) {
    event.preventDefault();
    const tournamentId = document.getElementById('tournamentId').value;

    if(tournamentId) {
        console.log('put')
        let url = '/JSP_war_exploded/tournaments_admin/{tournamentId}';
        url = url.replace("{tournamentId}", tournamentId);
        const tournamentData = {
            id: tournamentId,
            name: document.getElementById('tournamentName').value,
            startDate: document.getElementById('tournamentStartDate').value,
            endDate: document.getElementById('tournamentEndDate').value,
            prizePool: document.getElementById('tournamentPrizePool').value,
            organizer: document.getElementById('tournamentOrganizer').value,
        };
        fetch(url, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
            body: JSON.stringify(tournamentData)
        })
        .then(response => {
            if (response.ok) {
                //closeModal();
                //location.reload(); // Перезагрузить страницу после успешного выполнения
                console.log('Tournament updated successfully'); // Успешное обновление
            } else if (response.status === 404) {
                console.error('Error: Tournament not found'); // Турнир не найден
            } else {
                console.error('Error updating tournament:', response.statusText); // Другие ошибки
            }
        })
        .catch(error => {
            console.error('Fetch error:', error); // Ошибка сети
        });
    } else {
        const tournamentData = {
            name: document.getElementById('tournamentName').value,
            startDate: document.getElementById('tournamentStartDate').value,
            endDate: document.getElementById('tournamentEndDate').value,
            prizePool: document.getElementById('tournamentPrizePool').value,
            organizer: document.getElementById('tournamentOrganizer').value,
        };
        fetch('/JSP_war_exploded/tournaments_admin', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(tournamentData)
        })
            .then(response => {
                if (response.ok) {
                    closeModal();
                    location.reload(); // Перезагрузить страницу после успешного выполнения
                } else {
                    console.error('Error:', response.statusText);
                    alert('Error while processing tournament');
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('Error while processing tournament');
            });
    }




}*/



