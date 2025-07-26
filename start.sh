#!/bin/bash

# Startet den Ollama-Server im Hintergrund
ollama serve &

# Warte, bis der Server antwortet (per ollama list statt curl)
until ollama list > /dev/null 2>&1; do
  echo "Warte auf Ollama-Server..."
  sleep 1
done

# Modell laden
echo "Modell wird geladen..."
ollama pull llama3

# Warte auf den Ollama-Serverprozess, damit der Container nicht stoppt
wait
