# POAO Trivia

A multiple-choice and true-or-false trivia game with a Swing graphical
interface, written in Java. This is the final project for the Object Oriented
Programming course (Programacao Orientada a Objetos), first semester of the
second year of the Informatics Engineering degree at the University of Coimbra.

Questions are loaded from a question bank at startup, the player answers a set
of questions across several topics, and the final score can be saved and shown
on a scoreboard.

## Features

- Swing graphical interface for the whole game flow: main menu, question
  windows, and a final results and scoreboard screen.
- Questions spanning several topics: Football, Science, Art, Ski, and Swimming.
- Multiple-choice and true-or-false question types.
- Random question selection that avoids repeating topics within a round.
- Scoring with persistence: results are saved to per-game `.dat` files and can
  be reloaded for the scoreboard.

## Repository structure

```
.
├── src/                Java source files (main project)
│   ├── POOaoTrivia.java   Game logic and entry point
│   ├── UiWindow.java      Swing user interface
│   ├── FileManager.java   Loads questions and reads/writes saved games
│   ├── Question.java      Question model and Option, Person helpers
│   ├── Football.java      Topic classes (Science, Art, Ski, Swimming, ...)
│   └── ...
├── Files/              Saved game data (.dat) and the file index
├── PerguntasPoo.txt    Question bank read at startup
├── javadoc/            Generated API documentation
├── docs/               UML diagrams and the written report (PDF)
└── exercises/          Smaller exercises from the same course
```

## Running the game

The program reads and writes its data files using paths relative to the working
directory, so compile and run it from the repository root so that
`PerguntasPoo.txt` and `Files/` are found:

```
javac -d out src/*.java
java -cp out POOaoTrivia
```

API documentation is available in `javadoc/index.html`, and the UML diagrams
and the written report are in `docs/`.

## Course exercises

Two smaller exercises from the same course live under `exercises/`.

### Exercise 1: URL parsing

A console program that reads a list of URLs, extracts the country code from each
domain, maps it to a country name, and prints how many times each country
appears. Unknown codes are grouped under "Outro(s)".

```
cd exercises/exercise1-url-parsing
javac Exercicio1.java
java Exercicio1
```

### Exercise 2: RPG game

An exercise in inheritance and abstract classes. `Personagens` is an abstract
base class extended by `Guerreiro`, `Mago`, and `Mercenario`, each with its own
level-up logic and traits. `Pow` builds a list of characters and prints their
experience levels.

```
cd exercises/exercise2-rpg-game/src
javac *.java
java Pow
```

## Authors

Miguel Castela, with Miguel Martins on the trivia project.
