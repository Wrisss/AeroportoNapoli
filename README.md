🛫 Aeroporto di Napoli - Sistema di Gestione
https://img.shields.io/badge/Java-17%252B-orange
https://img.shields.io/badge/PostgreSQL-14%252B-blue
https://img.shields.io/badge/GUI-Swing-lightgrey

Un'applicazione Java completa per la gestione dell'aeroporto di Napoli, sviluppata come progetto universitario di Programmazione Orientata agli Oggetti.

📋 Descrizione del Progetto
Simulazione di un sistema gestionale per l'aeroporto di Napoli che include:

Interfaccia grafica creata con GUI Designer di IntelliJ IDEA e Java Swing

Database PostgreSQL completamente funzionante

Sistema di autenticazione con due ruoli utente

Documentazione completa HTML (JavaDoc) e inline nel codice

Diagrammi delle classi e di sequenza

✨ Funzionalità Principali
🔐 Autenticazione
Registrazione per utenti generici

Login con username e password

Due ruoli: Utente Generico e Amministratore

👤 Utente Generico
Prenotazione voli

Ricerca prenotazioni con filtri

Gestione prenotazioni personali

⚡ Amministratore
Inserimento nuovi voli

Gestione e modifica voli esistenti

Gestione gate aeroportuali

Visualizzazione statistiche

🛠 Requisiti di Sistema
Java: JDK 17 o superiore

Database: PostgreSQL 14+

pgAdmin: 4 v9.0 o superiore

Maven: 3.6+

IDE: IntelliJ IDEA (raccomandato)

🚀 Installazione e Configurazione
1. Configura il Database
Apri pgAdmin e crea un nuovo database chiamato aeroporto_na

Esegui lo script di backup presente nella cartella backup database

2. Configura la Connessione al Database
Modifica il file src/main/java/database/ConnessioneDatabase.java:

private String url = "jdbc:postgresql://localhost:5432/aeroporto_na";
private String user = "postgres";
private String password = "LA_TUA_PASSWORD"; // Sostituisci con la tua password

🎯 Avvio dell'Applicazione
Metodo 1: Tramite Maven (Raccomandato)
mvn clean package
java -jar target/aeroportonapoli.jar

Metodo 2: Da IntelliJ IDEA
Apri il progetto in IntelliJ IDEA

Naviga su AeroportoNapoli → src → main → java → Main

Esegui con Shift + F10

📁 Struttura del Progetto
AeroportoNapoli/
├── src/
│ ├── main/java/
│ │ ├── database/ # Gestione connessione DB
│ │ ├── gui/ # Interfacce grafiche
│ │ ├── model/ # Classi del dominio
│ │ ├── controller/ # Logica applicativa
│ │ └── Main.java # Punto d'ingresso
│ └── resources/ # Risorse e immagini
├── backup database/ # Script di inizializzazione DB
├── documentazione/ # Documentazione JavaDoc
└── diagrammi/ # UML e diagrammi di sequenza

📚 Documentazione
La documentazione completa è disponibile in due formati:

Documentazione HTML: Generata con JavaDoc nella cartella documentazione

Documentazione nel codice: Commenti dettagliati inline

👨‍💻 Autore
Angelo Molaro
Progetto universitario per il corso di Programmazione Orientata agli Oggetti
Università degli Studi di Napoli "Federico II" - Anno Accademico 2024/2025

📄 Licenza
Questo progetto è sviluppato per scopi educativi nell'ambito universitario.

Note: Assicurati che il servizio PostgreSQL sia in esecuzione prima di avviare l'applicazione.

