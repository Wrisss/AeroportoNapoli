# 🛫 Aeroporto di Napoli - Sistema di Gestione

![Java](https://img.shields.io/badge/Java-17%2B-orange)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-14%2B-blue)
![Swing](https://img.shields.io/badge/GUI-Swing-lightgrey)

Un'applicazione Java completa per la gestione dell'aeroporto di Napoli, sviluppata come progetto universitario di Programmazione Orientata agli Oggetti.

## 📋 Descrizione del Progetto

Simulazione di un sistema gestionale per l'aeroporto di Napoli che include:
- **Interfaccia grafica** creata con GUI Designer di IntelliJ IDEA e Java Swing
- **Database PostgreSQL** completamente funzionante
- **Sistema di autenticazione** con due ruoli utente
- **Documentazione completa** HTML (JavaDoc) e inline nel codice
- **Diagrammi** delle classi e di sequenza

## ✨ Funzionalità Principali

### 🔐 Autenticazione
- Registrazione per utenti generici
- Login con username e password
- Due ruoli: **Utente Generico** e **Amministratore**

### 👤 Utente Generico
- Prenotazione voli
- Ricerca prenotazioni con filtri
- Gestione prenotazioni personali

### ⚡ Amministratore
- Inserimento nuovi voli
- Gestione e modifica voli esistenti
- Gestione gate aeroportuali
- Visualizzazione statistiche

## 🛠 Requisiti di Sistema

- **Java**: JDK 17 o superiore
- **Database**: PostgreSQL 14+
- **pgAdmin**: 4 v9.0 o superiore
- **Maven**: 3.6+
- **IDE**: IntelliJ IDEA (raccomandato)

## 🚀 Installazione e Configurazione

### 1. Configura il Database
1. Apri pgAdmin e crea un nuovo database chiamato `aeroporto_na`
2. Esegui lo script di backup presente nella cartella `backup database/`

### 2. Configura la Connessione al Database

Modifica il file `src/main/java/database/ConnessioneDatabase.java`:

```java
private String url = "jdbc:postgresql://localhost:5432/aeroporto_na";
private String user = "postgres";
private String password = "LA_TUA_PASSWORD"; // Sostituisci con la tua password
