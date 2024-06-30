# Pac-Man Game

## Overview
This project implements a Pac-Man game focusing on backend algorithms for Pac-Man's movement, ghost avoidance, and game logic using 2D arrays and object-oriented principles.
 The GUI and frontend were provided and integrated, while the backend, algorithms, and game logic were developed as part of this project.

![Pac-Man Game](link-to-screenshot-or-gif)

## Features
- **2D Maze Navigation**: Efficient algorithms to control Pac-Man’s movement.
- **Ghost Avoidance**: Strategies to evade ghosts in the maze.
- **Object-Oriented Design**: Utilizes Java OOP principles.
- **JUnit Testing**: Comprehensive testing using JUnit.

## Getting Started

### Prerequisites
- **Java JDK 11+**: Ensure you have Java Development Kit installed.
- **JUnit 5**: For running tests.

### Installation
1. **Clone the Repository**:
    ```bash
    git clone https://github.com/yourusername/pacman-game.git
    cd pacman-game
    ```
2. **Compile the Java Files**:
    ```bash
    javac -d bin src/main/java/pacman/*.java
    ```
3. **Run the Game**:
    ```bash
    java -cp bin pacman.Main
    ```

### Running Tests
To run the JUnit tests:
```bash
java -cp bin:lib/junit-platform-console-standalone.jar org.junit.runner.JUnitCore pacman.Index2DTest
```

## How to Play
### Manual Game
To play the manual version of the Pac-Man game:
 ```bash
java -jar libs/Ex3_Manual_Game_v0.1.jar
```
Key Instructions:

' ' - Start
'0,1,2,3,4' - Level
'w,a,x,d' - Move up, left, down, right
'c' - Toggle cyclic mode
'r' - Change resolution

### Automated Algorithm
The Pac-Man game includes an automated algorithm to control Pac-Man's movements. The backend, algorithms, and logic for this automation were developed as part of this project.



### Code Structure

```bash
.
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── pacman
│   │   │   │   ├── Index2D.java            # Implementation of Index2D class
│   │   │   │   ├── Pixel2D.java            # Interface for Pixel2D
│   │   │   │   ├── Map2D.java              # Interface for Map2D
│   │   │   │   ├── Map.java                # Implementation of Map class
│   │   │   │   ├── PacmanAlgo.java         # Algorithm for Pacman movement
│   │   │   │   ├── GameInfo.java           # Game information including player ID
│   │   │   │   ├── Ex3Algo.java            # Documentation for your algorithm
│   │   │   │   └── ...
│   └── test
│       ├── java
│       │   ├── pacman
│       │   │   ├── Index2DTest.java        # JUnit tests for Index2D
│       │   │   ├── MapTest.java            # JUnit tests for Map
│       │   │   └── ...
├── docs
│   ├── design_patterns.md                  # Documentation on design patterns used
│   └── ...
├── libs                                    # Libraries (if any)
│   ├── Ex3_Manual_Game_v0.1.jar            # Manual game JAR file
│   └── ...
├── README.md                               # Project README
├── .gitignore                              # Git ignore file
└── Ex3_Results.xlsx                        # Results sheet (if applicable)

```

