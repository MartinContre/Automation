# Battleship Test Automation 🚢

This test automates the gameplay for the Battleship game using automated scripts. It performs a sequence of actions 
in the game, including randomizing ship placements, initiating a game, and making shots on the battlefield.
The test aims to validate the game's outcome based on specific conditions.

## Test Scenario 🎮

1. The test starts on the [Battleship Game](http://ru.battleship-game.org) website.
2. It randomizes ship placements by clicking the "Randomize" button a random number of times (configurable).
3. Click the "Play" button to initiate the game.
4. A battlefield is created with configurable dimensions (width and length).
5. Shots are made on the battlefield until one of the following conditions is met:
    - All ships are sunk (the game ends in victory).
    - The game status changes to indicate a loss (e.g., connection lost).

## Test Data Configuration ⚙️

The test configuration includes the following parameters:
- `numberTimesRandomise`: Number of times to randomize ship placements.
- `fieldWidth` and `fieldLength`: Dimensions of the battlefield.
- `numberShips`: Number of ships in the game.

These parameters are read from configuration files to allow flexibility in testing.

## Test Automation Framework 🤖

The test script is implemented using a suitable test automation framework, such as Selenium WebDriver, to interact with the web-based game.

## Author 👤

Martin Perez
