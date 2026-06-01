# Daily Standup API

A lightweight REST API for logging and retrieving daily standup entries, built with Spring Boot 3, Java 17, and Spring Data JPA. Ideal as a reference project for Spring Boot REST API patterns with in-memory H2 persistence.

![Java](https://img.shields.io/badge/Java-17-orange) ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.5-brightgreen) ![License](https://img.shields.io/badge/license-MIT-blue)

## Tech Stack

- Java 17
- Spring Boot 3.4.5
- Spring Data JPA
- Spring Security
- H2 in-memory database
- Lombok

## Getting Started

```bash
./gradlew bootRun
```

The app starts on `http://localhost:8080`.

## API Endpoints

| Method | Path | Description |
|--------|------|-------------|
| `POST` | `/standup` | Submit a standup entry |
| `GET` | `/standup/today` | Get today's most recent standup |
| `GET` | `/standup/history` | Get all standups (newest first) |
| `GET` | `/standup/{id}` | Get a standup by ID |

### Submit a Standup

```http
POST /standup
Content-Type: application/json

{
  "yesterday": "Finished the auth module",
  "today": "Working on standup API",
  "blockers": "None",
  "standupDate": "2026-05-31"  // optional, defaults to today
}
```

## H2 Console

Available at `http://localhost:8080/h2-console` while the app is running.

- **JDBC URL:** `jdbc:h2:mem:standupdb`
- **Username:** `sa`
- **Password:** *(empty)*

> Note: Data is stored in-memory and resets on each restart.

## Running Tests

```bash
./gradlew test
```
