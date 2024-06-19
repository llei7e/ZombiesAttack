# Clueless - Zombies Attack

[game Image]


## Index
- [About](#about)
    - [Story](#-_story)
    - [Game Objective](#-_game-objective)
    - [Commands](#-_commands)
- [Game Classes](#game-classes)
- [Sound Effects](#sound-effects)
- [Requirements](#requirements)
- [Contribution](#contribution)


## About

This game project was developed from students coursing System Analisys and Development at Faculdade SENAI Gaspar Ricardo Junior, with 2rpnet support.
It was developed using *java 22, java.fx, java.fxgl, java.bootstrap, java.controls managed with maven.*

Start date: 06/10/2024.

End date: to be defined.


### - History

During a zombie apocalypse, Indiana Grimes was the only one from his city that survived. Trying to find someone else or at least a single life, 
he runs the world fighting lots of types of zombies using his knife and wapons that he finds along the way.


### - Game Objective

The game works in rounds, at start the survivor have a knife to fight, he can get better weapons with the points he gets by killing zombies.

Zombies:
    - Short zombies are fast, have low life and a medium hit,
    - Medium zombies have a medium speed, medium life and a weak hit,
    - Short zombies are low, have higher life and a strong hit.

Ranking:
    - Each zombie has different point rewards from killing them,
    - Player can buy weapons each 5 rounds based on your points,
    - Indiana Grimes has 10 hits of life, if it turns 0 = GAME OVER,
    - After GAME OVER screen, player can send his name to ranking, only the top 5 appears at ranking screen.


### - Commands
    - A = LEFT, and UP at menu,
    - D = RIGHT, and DOWN at menu,

    - F = ATTACK, and CONFIRM at menu.    

    [commands IMAGE]

## Game Classes

```mermaid
---
title: Survivor Zombies
---
classDiagram
Characters <|-- Player
Characters <|-- Zombies
ImageView <|-- Characters

class Main{
    +initSettings()
}
class ImageView{
    +setSprite()
}
class Characters{
    -int height
    -int width
    -int positionX
    -int positionY
    -int life
    -int speed
    -int strength
    +move(int x, int y)
    +jump()
}
class Player{
    -String name
    -int points
    -int timeSurvived
    -String weapon
    +dash()
    +attack()
}
class Zombies{
    -int type
    +chasing(int x, int y)
}    
```


## Sound Effects

In this project we use some sound effects to enrich the player's experience when interacting with the objects of game.

Sources used to obtain the files:

- [Pixabay](#https://pixabay.com/pt/sound-effects/)
- [Mixkit](#https://mixkit.co/free-sound-effects/)


## Requirements

java 22,

java.fx,

controls.fx,

bootstrap,

fxgl;


## Contribution

<table>
  <tr>
    <td align="center">
      <a href="https://github.com/llei7e">
        <img src="https://avatars.githubusercontent.com/u/148909506?v=4" width="100px;" alt="Foto do perfil do github lucas"/><br>
        <sub>
          <b>Lucas Leite</b>
        </sub>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/Giovani-RodriguesS">
        <img src="https://avatars.githubusercontent.com/u/121878338?v=4" width="100px;" alt="Foto do perfil do github giovani"/><br>
        <sub>
          <b>Giovani Rodrigues</b>
        </sub>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/mwlaofr">
        <img src="https://avatars.githubusercontent.com/u/148801384?v=4" width="100px;" alt="Foto do perfil do github millena"/><br>
        <sub>
          <b>Millena França</b>
        </sub>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/DarkBytess">
        <img src="https://avatars.githubusercontent.com/u/152932607?v=4" width="100px;" alt="Foto do perfil do github luis"/><br>
        <sub>
          <b>Luis Henrique</b>
        </sub>
      </a>
    </td>
  </tr>
</table>
