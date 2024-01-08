# Manual de Utilizator pentru UserController folosind Postman

## Introducere

Acest manual de utilizator oferă instrucțiuni despre cum să utilizați endpoint-urile API ale `UserController` folosind Postman. `UserController` gestionează cererile HTTP legate de utilizatori, oferind funcționalități CRUD pentru utilizatori.

### 1. Creare Utilizator

- **Descriere**: Creează un nou utilizator.

### Pași:

1. Deschideți Postman.
2. Setează tipul `POST`.
3. Introduceți URL-ul: `http://localhost:5500/create_user`.
4. Mergeți la fila "Body", selectați "raw", și alegeți "JSON (application/json)".
5. Introduceți obiectul UserDto în format JSON.
6. Apăsați "Send".

### 2. Ștergere Utilizator

- **Descriere**: Șterge un utilizator după ID.

### Pași:

1. Deschideți Postman.
2. Setează tipul `DELETE`.
3. Introduceți URL-ul: `http://localhost:5500/delete_user/{id}` (înlocuiți `{id}` cu ID-ul utilizatorului).
4. Apăsați "Send".

### 3. Listare Utilizatori

- **Descriere**: Recuperează o listă cu toți utilizatorii.

### Pași:

1. Deschideți Postman.
2. Setează tipul `GET`.
3. Introduceți URL-ul: `http://localhost:5500/users`.
4. Apăsați "Send".

### 4. Obținere Utilizator după ID

- **Descriere**: Recuperează un utilizator după ID.

### Pași:

1. Deschideți Postman.
2. Setează tipul `GET`.
3. Introduceți URL-ul: `http://localhost:5500/user/{id}` (înlocuiți `{id}` cu ID-ul utilizatorului).
4. Apăsați "Send".

### 5. Actualizare Utilizator după ID

- **Descriere**: Actualizează un utilizator după ID.

### Pași:

1. Deschideți Postman.
2. Setează tipul `PUT`.
3. Introduceți URL-ul: `http://localhost:5500/update_user/{id}` (înlocuiți `{id}` cu ID-ul utilizatorului).
4. Mergeți la fila "Body", selectați "raw", și alegeți "JSON (application/json)".
5. Introduceți obiectul UserDto cu detaliile actualizate în format JSON.
6. Apăsați "Send".