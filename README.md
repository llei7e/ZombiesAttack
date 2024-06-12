# Clueless - Zombies Attack

*(Breve descrição do projeto aqui.)*
## Index
- [About](#about)
    - [Game Classes](#game-classes)
    - [Sound Effects](#sound-effects)
- [Requirements](#requirements)
- [Contribution](#Contribution)


## Game Classes

```mermaid
---
title: Survivor Zombies
---
classDiagram
    Characters <|-- Player
    Characters <|-- Zombies
    Guns <|-- Player
    Sounds <|-- Characters
    Sounds <|-- Scenario
    Sounds <|-- Guns
    class Characters{
        -int positionX
        -int positionY
        -int life
        -int speed
        -int strength
        +move(int x, int y)
        +attack()
        +jump()
    }
    class Player{
        -String name
        -int points
        -int timeSurvived
        +dash()
    }
    class Zombies{
        -int type
        +followPlayer(int x, int y)
    }
    class Guns{
        -int knife
        -int sword
    }
    class Scenario{
        -Object plataform
        -Object background
        -Object flyingPlataform
    }
    class Sounds{
        +noise()
    }

```
## Sound Effects

In this project we use some sound effects to enrich the player's experience when interacting with the objects of game.

Sources used to obtain the files:

- [Pixabay](#https://pixabay.com/pt/sound-effects/)
- [Mixkit](#https://mixkit.co/free-sound-effects/)