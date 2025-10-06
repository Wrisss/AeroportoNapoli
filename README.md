# Aeroporto di Napoli - Sistema di Gestione

**Progetto di Programmazione Orientata agli Oggetti**  
Università degli Studi di Napoli Federico II - A.A. 2024/2025  
**Autore:** Angelo Molaro

## Descrizione del Progetto

Applicazione Java completa per la gestione dell'Aeroporto di Napoli, sviluppata con architettura a oggetti e interfaccia grafica realizzata con Java Swing e GUI Designer di IntelliJ IDEA.

Il sistema integra:
- Interfaccia grafica intuitiva e user-friendly
- Database PostgreSQL completamente funzionante
- Documentazione completa (JavaDoc HTML + commenti in-line)
- Diagrammi UML (classi e sequenza)
- Risorse grafiche e descrizione del dominio

## Funzionalità Principali

### Sistema di Autenticazione
- **Registrazione** nuovi utenti (solo per utenti generici, per motivi di sicurezza)
- **Login** con credenziali e gestione ruoli
- **Due livelli di accesso:**
  - Utente Generico
  - Amministratore

### Funzionalità Utente Generico
- Prenotazione voli in tempo reale
- Ricerca prenotazioni con filtri avanzati
- Visualizzazione dettagli prenotazioni

### Funzionalità Amministratore
- Inserimento nuovi voli nel sistema
- Gestione e modifica voli esistenti
- Assegnazione gate ai voli
- Monitoraggio completo delle operazioni aeroportuali

## Requisiti di Sistema

- **Java:** 14 o superiore
- **JDK:** 23
- **pgAdmin:** 4 v.9.0
- **Maven:** 3.x
- **Sistema Operativo:** Windows / macOS / Linux

## Installazione

### 1. Clonare il Repository

```bash
git clone https://github.com/Wrisss/AeroportoNapoli.git
cd Applicativo_AeroportoNapoli
```

### 2. Configurare il Database PostgreSQL

1. Apri **pgAdmin 4**
2. Crea un nuovo database chiamato `aeroporto_na`
3. Esegui lo script di backup presente nella cartella `backup database`

### 3. Configurare la Connessione al Database

Apri il file:
```
src/main/java/database/ConnessioneDatabase.java
```

Modifica i parametri di connessione con le tue credenziali:

```java
private String url = "jdbc:postgresql://localhost:5432/aeroporto_na";
private String user = "postgres";
private String password = "<LA_TUA_PASSWORD>";  // Inserisci la tua password
```

### 4. Compilare il Progetto con Maven

Apri IntelliJ IDEA e segui questi passaggi:

1. Vai su **Maven** (pannello laterale)
2. Esegui in sequenza:
   - `clean`
   - `package`

## Avvio dell'Applicazione

### Metodo 1: Tramite JAR (Consigliato)

Apri il terminale di IntelliJ IDEA (`Alt+F12`) ed esegui:

```bash
java -jar target/aeroportonapoli.jar
```

### Metodo 2: Esecuzione Diretta da IntelliJ

1. Naviga su: `AeroportoNapoli` → `src` → `main` → `java` → `main`
2. Premi `Shift+F10` per avviare l'applicazione

## Documentazione

Il progetto include:

- **JavaDoc HTML** - Documentazione completa delle classi e metodi
- **Commenti in-line** - Descrizione dettagliata del comportamento del codice
- **Diagrammi UML:**
  - Diagramma delle classi
  - Diagrammi di sequenza
- **Descrizione del dominio** con immagini esplicative

## Struttura del Progetto

```
AeroportoNapoli/
├── src/
│   └── main/
│       └── java/
│           ├── main/              # Entry point dell'applicazione
│           ├── database/          # Gestione connessione DB
│           ├── models/            # Classi del dominio
│           ├── controllers/       # Logica applicativa
│           └── views/             # Interfacce grafiche (Swing)
├── backup database/               # Script SQL per il database
├── docs/                          # Documentazione JavaDoc
├── diagrams/                      # Diagrammi UML
├── pom.xml                        # Configurazione Maven
└── README.md
```

## Informazioni Accademiche

**Corso:** Programmazione Orientata agli Oggetti  
**Università:** Università degli Studi di Napoli Federico II  
**Anno Accademico:** 2024/2025  
**Studente:** Angelo Molaro

## Contributi

Questo è un progetto accademico. Per segnalazioni o suggerimenti, apri una issue sul repository GitHub.

## Contatti

Per domande o chiarimenti sul progetto:
- Repository: [github.com/Wrisss/AeroportoNapoli](https://github.com/Wrisss/AeroportoNapoli)

## Licenza

Progetto sviluppato per scopi didattici nell'ambito del corso di Programmazione Orientata agli Oggetti presso l'Università Federico II di Napoli.
