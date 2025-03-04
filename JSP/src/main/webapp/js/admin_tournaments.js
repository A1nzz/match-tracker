

// Функция для открытия модального окна
function openModal(action, tournament) {
    const modal = document.getElementById('modal');
    modal.style.display = 'block';

    // Обновление заголовка модального окна
    const modalTitle = document.getElementById('modalTitle');
    modalTitle.textContent = action === 'edit' ? 'Edit Tournament' : 'Add Tournament';

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
/*
function submitForm() {

    const tournamentId = document.getElementById('tournamentId').value;
    const action = tournamentId ? 'edit' : 'add'; // Определяем действие
    const tournamentData = {
        id: tournamentId,
        name: document.getElementById('tournamentName').value,
        startDate: document.getElementById('tournamentStartDate').value,
        endDate: document.getElementById('tournamentEndDate').value,
        prizePool: document.getElementById('tournamentPrizePool').value,
        organizer: document.getElementById('tournamentOrganizer').value,
        action: action // Добавляем параметр action
    };

    fetch('JSP_war_exploded/tournaments_admin', {
        method: 'PUT',
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

function deleteTournament(tournamentId) {
    if (confirm("Are you sure you want to delete this tournament?")) {
        const tournamentData = {
            id: tournamentId,
            action: 'delete' // Указываем действие
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
                    location.reload(); // Перезагрузить страницу после успешного удаления
                } else {
                    alert('Error while deleting tournament');
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('Error while deleting tournament');
            });
    }
}
*/