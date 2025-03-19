import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Modal from '../components/Modal';
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';
import '../styles/adminPanel.scss';

function AdminPanel() {
  const [entities, setEntities] = useState([]);
  const [selectedEntity, setSelectedEntity] = useState('heroes');
  const [editingItem, setEditingItem] = useState(null);
  const [loading, setLoading] = useState(false);
  const [teams, setTeams] = useState([]);
  const [tournaments, setTournaments] = useState([]);
  const [matches, setMatches] = useState([]);
  const [matchTypes, setMatchTypes] = useState([]);
  const [games, setGames] = useState([]);
  const [playerHeroes, setPlayerHeroes] = useState([]);
  const [players, setPlayers] = useState([]);
  const [heroes, setHeroes] = useState([]);
  const [gameStats, setGameStats] = useState([]);
  const [items, setItems] = useState([]);
  const [errors, setErrors] = useState({});

  const host = 'http://localhost:8080/';

  useEffect(() => {
    fetchEntities(selectedEntity);
    if (selectedEntity === 'players') {
      fetchTeams();
    }
    if (selectedEntity === 'matches') {
      fetchTournaments();
      fetchTeams();
      fetchMatchTypes();
    }
    if (selectedEntity === 'games') {
      fetchMatches();
    }
    if (selectedEntity === 'game-stats') {
      fetchGames();
      fetchPlayerHeroes();
    }
    if (selectedEntity === 'player-heroes') {
      fetchPlayers();
      fetchHeroes();
    }
    if (selectedEntity === 'game-item-stats') {
      fetchGameStats();
      fetchItems();
    }
  }, [selectedEntity]);

  const fetchEntities = async (entity) => {
    setLoading(true);
    try {
      const response = await axios.get(`${host}admin/${entity}`);
      setEntities(response.data);
    } catch (error) {
      console.error(`Ошибка загрузки ${entity}:`, error);
    } finally {
      setLoading(false);
    }
  };

  const fetchTournaments = async () => {
    try {
      const response = await axios.get(`${host}admin/tournaments`);
      setTournaments(response.data);
    } catch (error) {
      console.error('Ошибка загрузки турниров:', error);
    }
  };

  const fetchTeams = async () => {
    try {
      const response = await axios.get(`${host}admin/teams`);
      setTeams(response.data);
    } catch (error) {
      console.error('Не удалось загрузить команды:', error);
    }
  };

  const fetchMatches = async () => {
    try {
      const response = await axios.get(`${host}admin/matches`);
      setMatches(response.data);
    } catch (error) {
      console.error('Не удалось загрузить матчи:', error);
    }
  };

  const fetchMatchTypes = async () => {
    try {
      const response = await axios.get(`${host}admin/match-types`);
      setMatchTypes(response.data);
    } catch (error) {
      console.error('Не удалось загрузить типы матчей:', error);
    }
  };

  const fetchGames = async () => {
    try {
      const response = await axios.get(`${host}admin/games`);
      setGames(response.data);
    } catch (error) {
      console.error('Не удалось загрузить игры:', error);
    }
  };

  const fetchPlayerHeroes = async () => {
    try {
      const response = await axios.get(`${host}admin/player-heroes`);
      setPlayerHeroes(response.data);
    } catch (error) {
      console.error('Не удалось загрузить игроков - героев:', error);
    }
  };

  const fetchPlayers = async () => {
    try {
      const response = await axios.get(`${host}admin/players`);
      setPlayers(response.data);
    } catch (error) {
      console.error('Не удалось загрузить Игроков:', error);
    }
  };

  const fetchHeroes = async () => {
    try {
      const response = await axios.get(`${host}admin/heroes`);
      setHeroes(response.data);
    } catch (error) {
      console.error('Не удалось загрузить героев:', error);
    }
  };

  const fetchGameStats = async () => {
    try {
      const response = await axios.get(`${host}admin/game-stats`);
      setGameStats(response.data);
    } catch (error) {
      console.error('Не удалось загрузить статистику игры:', error);
    }
  };

  const fetchItems = async () => {
    try {
      const response = await axios.get(`${host}admin/items`);
      setItems(response.data);
    } catch (error) {
      console.error('Не удалось загрузить предметы:', error);
    }
  };

  const validateFields = () => {
    const newErrors = {};
  
    // Проверка на обязательные поля
    if (!editingItem.name) {
      newErrors.name = 'Название обязательно';
    }
  
    // Проверка на отрицательные числа для числовых полей
    const numericFields = ['prizePool', 'gamesPlayed', 'quantity', 'kills', 'deaths', 'assists', 'gold', 'xp', 'cost'];
    numericFields.forEach((field) => {
      if (editingItem[field] !== undefined && editingItem[field] < 0) {
        newErrors[field] = 'Значение не может быть отрицательным';
      }
    });
  
    // Проверка дат
    if (editingItem.startDate && editingItem.endDate && new Date(editingItem.startDate) > new Date(editingItem.endDate)) {
      newErrors.endDate = '\nДата окончания не может быть раньше даты начала';
    }
  
    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const closeModal = () => {
    setEditingItem(null); // Закрываем модальное окно
    setErrors({}); // Сбрасываем ошибки
  };

  const saveEntity = async (item) => {
    if (!validateFields()) return;

    try {
      let response;
      if (item.id) {
        // Обновление существующей сущности
        response = await axios.put(`${host}admin/${selectedEntity}/${item.id}`, item);
        setEntities((prevEntities) =>
          prevEntities.map((entity) => (entity.id === item.id ? response.data : entity))
        );
      } else {
        // Добавление новой сущности
        response = await axios.post(`${host}admin/${selectedEntity}`, item);
        setEntities((prevEntities) => [...prevEntities, response.data]);
      }
      setEditingItem(null);
    } catch (error) {
      console.error(`Ошибка сохранения:`, error);
    }
  };

  const deleteEntity = async (id) => {
    try {
      await axios.delete(`${host}admin/${selectedEntity}/${id}`);
      setEntities((prevEntities) => prevEntities.filter((entity) => entity.id !== id));
    } catch (error) {
      console.error(`Ошибка удаления:`, error);
    }
  };

  const addEntity = () => {
    const emptyEntity = entities.length > 0 ? { ...entities[0] } : {};
    Object.keys(emptyEntity).forEach((key) => (emptyEntity[key] = ''));
    setEditingItem(emptyEntity);
  };

  const renderDatePicker = (key, label, dateFormat, showTimeSelect = false) => {
    return (
      <div className="form-group" key={key}>
        <label>{label}:</label>
        <DatePicker
          selected={editingItem[key] ? new Date(editingItem[key]) : null}
          onChange={(date) =>
            setEditingItem({ ...editingItem, [key]: date.toISOString() })
          }
          showTimeSelect={showTimeSelect}
          timeFormat={showTimeSelect ? "HH:mm" : undefined}
          timeIntervals={showTimeSelect ? 15 : undefined}
          dateFormat={dateFormat}
          placeholderText={`Выберите ${label.toLowerCase()}`}
        />
        {errors[key] && <div className="error">{errors[key]}</div>}
      </div>
    );
  };
  
  const renderSelect = (key, label, options, optionLabelKey = 'name') => {
    return (
      <div className="form-group" key={key}>
        <label>{label}:</label>
        <select
          value={editingItem[key]?.id || ''}
          onChange={(e) =>
            setEditingItem({
              ...editingItem,
              [key]: { id: e.target.value, [optionLabelKey]: e.target.selectedOptions[0].text },
            })
          }
        >
          <option value="">Выберите {label.toLowerCase()}</option>
          {options.map((option) => (
            <option key={option.id} value={option.id}>
              {option[optionLabelKey]}
            </option>
          ))}
        </select>
      </div>
    );
  };
  
  const renderInput = (key, label, type = 'text') => {
    return (
      <div className="form-group" key={key}>
        <label>{label}:</label>
        <input
          required
          type={type}
          value={editingItem[key] || ''}
          onChange={(e) => setEditingItem({ ...editingItem, [key]: e.target.value })}
        />
        {errors[key] && <span className="error">{errors[key]}</span>}
      </div>
    );
  };

  const getEntityLabel = (item, index) => {
    if (item.tournament?.name && item.teamRadiant?.name && item.teamDire?.name) {
      return `${item.tournament.name} | ${item.teamRadiant.name} vs ${item.teamDire.name}`;
    }
  
    if (item.match?.tournament?.name && item.match?.teamRadiant?.name && item.match?.teamDire?.name) {
      return `MatchId ${item.match.id} Игра ${index + 1} ${item.match.tournament.name} | ${item.match.teamRadiant.name} vs ${item.match.teamDire.name}`;
    }
  
    if (item.game && item.playerHero) {
      return `MatchId ${item.game.match.id} Игра ${index + 1} ${item.game.match.tournament.name} | ${item.game.match.teamRadiant.name} vs ${item.game.match.teamDire.name} ${item.playerHero.player.nickname} ${item.playerHero.hero.name}`;
    }
  
    if (item.player && item.hero) {
      return `${item.player.nickname} - ${item.hero.name}`;
    }
  
    if (item.gameStats && item.item) {
      return `(${item.gameStats.id}) ${item.item.name}`;
    }
  
    return item.name || item.nickname || item.typeName;
  };

  const renderEditFields = () => {
    if (!editingItem) return null;
  
    return Object.keys(editingItem).map((key) => {
      if (key === 'id') return null;
  
      switch (key) {
        case 'startTime':
          return renderDatePicker(key, 'Время начала', 'yyyy-MM-dd HH:mm', true);
  
        case 'matchDate':
        case 'startDate':
        case 'endDate':
          return renderDatePicker(key, key.charAt(0).toUpperCase() + key.slice(1), 'yyyy-MM-dd');
  
        case 'team':
        case 'teamDire':
        case 'teamRadiant':
          return renderSelect(
            key,
            key === 'teamRadiant' ? 'Команда Radiant' : key === 'teamDire' ? 'Команда Dire' : 'Команда',
            teams
          );
  
        case 'tournament':
          return renderSelect(key, 'Турнир', tournaments);
  
        case 'winner':
          return (
            <div className="form-group" key={key}>
              <label>Winner:</label>
              <select
                value={editingItem[key] || ''}
                onChange={(e) => setEditingItem({ ...editingItem, [key]: e.target.value })}
              >
                <option value="">Выберите победителя</option>
                <option value="Radiant">Radiant</option>
                <option value="Dire">Dire</option>
              </select>
            </div>
          );
  
        case 'match':
          return renderSelect(key, 'Матч', matches, 'name');
  
        case 'player':
          return renderSelect(key, 'Игрок', players, 'nickname');
  
        case 'hero':
          return renderSelect(key, 'Герой', heroes);
  
        case 'matchType':
          return renderSelect(key, 'Тип матча', matchTypes, 'typeName');
  
        case 'game':
          return renderSelect(key, 'Игра', games, 'name');
  
        case 'playerHero':
          return renderSelect(key, 'Игрок-Герой', playerHeroes, 'name');
  
        case 'gameStats':
          return renderSelect(key, 'Игровая статистика', gameStats, 'name');
  
        case 'item':
          return renderSelect(key, 'Предмет', items);
  
        default:
          return renderInput(key, key.charAt(0).toUpperCase() + key.slice(1));
      }
    });
  };

  return (
    <div className="admin-panel">
      {/* Левое меню */}
      <div className="sidebar">
        <h2>Сущности</h2>
        <ul>
          {['heroes', 'items', 'teams', 'tournaments', 'players', 'matches', 'games', 'match-types', 'game-stats', 'player-heroes', 'game-item-stats'].map((entity) => (
            <li
              key={entity}
              className={selectedEntity === entity ? 'active' : ''}
              onClick={() => setSelectedEntity(entity)}
            >
              {entity.charAt(0).toUpperCase() + entity.slice(1)}
            </li>
          ))}
        </ul>
      </div>

      {/* Основной контент */}
      <div className="content">
        <div className="content-header">
          <h2>{selectedEntity.charAt(0).toUpperCase() + selectedEntity.slice(1)}</h2>
          <button className="button" onClick={addEntity}>
            Добавить
          </button>
        </div>

        {/* Список сущностей */}
        {loading ? (
          <p>Загрузка...</p>
        ) : (
          <ul className="entity-list">
          {entities.map((item, index) => (
            <li key={item.id} className="entity-card">
              <span>{getEntityLabel(item, index)}</span>
              <div className="actions">
                <button onClick={() => setEditingItem(item)}>Редактировать</button>
                <button className="delete" onClick={() => deleteEntity(item.id)}>
                  Удалить
                </button>
              </div>
            </li>
            ))}
          </ul>
        )}

        {/* Модальное окно для редактирования */}
        <Modal isOpen={!!editingItem} onClose={closeModal}>
          <h3>{editingItem?.id ? 'Редактирование' : 'Добавление'}</h3>
          <form
            onSubmit={(e) => {
              e.preventDefault();
              saveEntity(editingItem);
            }}
          >
            {renderEditFields()}
            <div className="form-actions">
              <button type="submit">Сохранить</button>
              <button type="button" onClick={() => setEditingItem(null)}>
                Отмена
              </button>
            </div>
          </form>
        </Modal>
      </div>
    </div>
  );
}

export default AdminPanel;