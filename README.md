# Clueless Zombies Attack

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
