# Hangman Java Game

Welcome to the Hangman Java game repository! This project implements the classic Hangman game using Java.

## Features
- **File Input:** Reads a word list from a file.
- **Interactive Gameplay:** User-friendly console-based gameplay.
- **Dynamic Word Selection:** Randomly selects a word from the word list.
- **Error Handling:** Handles invalid input gracefully.
- **ASCII Art Stages:** Visual representation of the hangman stages.

---

## Prerequisites

- **Java Development Kit (JDK):** Version 8 or higher.
- **Text File:** A `words.txt` file containing one word per line.

---

## Installation and Setup

1. Clone the Repository:
   ```bash
   git clone https://github.com/antoineafonin/hangman-java.git
   cd hangman-java
   ```

2. Prepare the `words.txt` File:
   - Create a text file named `words.txt` in the `src` directory.
   - Add words to the file, one word per line.

3. Compile the Program:
   ```bash
   javac Main.java
   ```

4. Run the Game:
   ```bash
   java Main
   ```

---

## How to Play

1. When the game starts, a random word is selected from the `words.txt` file.
2. Guess one letter at a time by entering it in the console.
3. You have 7 attempts to guess the word correctly before the game ends.
4. The hangman ASCII art will display your progress.

---

## File Structure

```
hangman-java/
├── src/
│   └── Main.java       # Main game logic
│   └── words.txt      # List of words (one per line)
└── README.md          # Documentation (this file)
```

---

