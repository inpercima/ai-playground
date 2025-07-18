# my-ai-springapp

Java Spring Boot REST API mit Ollama llama3 Integration

## Features
- REST-Endpoint `/chat` (POST), der einen Prompt an das lokale Ollama-Modell "llama3" sendet
- Docker Compose Setup für Ollama

## Voraussetzungen
- Java 17+
- Maven
- Docker

## Setup & Start

### 1. Ollama Modell starten
```bash
cd my-ai-springapp
# Modell llama3 wird automatisch geladen
sudo docker compose -f Docker-compose-ollama.yaml up
```

### 2. Spring Boot Anwendung bauen & starten
```bash
mvn clean package
mvn spring-boot:run
```

### 3. Testen des Endpoints
```bash
curl -X POST http://localhost:8080/chat -H "Content-Type: text/plain" -d "Was ist KI?"
```

## Hinweise
- Der Endpoint `/chat` erwartet den Prompt als reinen Text im Body (Content-Type: text/plain).
- Die Antwort ist die Rohantwort des Ollama llama3 Modells.

## Projektstruktur
- `src/main/java/com/example/myaispringapp/` - Spring Boot Code
- `Docker-compose-ollama.yaml` - Docker Compose für Ollama
- `.vscode/tasks.json` - VS Code Tasks
- `.github/copilot-instructions.md` - Copilot Hinweise

## Weiteres
- Für produktive Nutzung sollte die Fehlerbehandlung und das Response-Parsing verbessert werden.
