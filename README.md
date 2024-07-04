<div align="center">
    
![image](https://github.com/Game-JAVA/Clueless/blob/develop/Images/screens/home%20screen.png)
</div>

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
It was developed using *java 22 and java.fx.*

Start date: 06/10/2024.

End date: to be defined.


### - History

During a zombie apocalypse, Indiana Grimes was the only one from his city that survived. Trying to find someone else or at least a single life, 
he runs the world fighting lots of types of zombies using his knife and wapons that he finds along the way.  

Zombies:  
    - Short zombies are fast, have low life and a medium hit,
    ![image](https://github.com/Game-JAVA/Clueless/blob/develop/Images/sprites/gifs%20png/zombieP%20png.gif)  
    - Medium zombies have a medium speed, medium life and a weak hit,
    ![image](https://github.com/Game-JAVA/Clueless/blob/develop/Images/sprites/gifs%20png/zombieM%20png.gif)  
    - Tall zombies are the slowest one, have the biggest life and the strongest hit.
    ![image](https://github.com/Game-JAVA/Clueless/blob/develop/Images/sprites/gifs%20png/zombiG%20png%20.gif)  


Indiana Grimes:
![image](https://github.com/Game-JAVA/Clueless/blob/develop/Images/sprites/gifs%20png/rickgrimes%20png.gif)  
    - Inspired by Rick Grimes from The Walking Dead and Indiana Jones. Indiana Grimes fights to survive the zombie apocalypse using a variety of weapons.
    


### - Game Objective

The game works in rounds, at start the survivor have a knife to fight, he can get better weapons with the points he gets by killing zombies, the survivor score points by killing zombies, to get a better place at the ranking.

Ranking:
    - Each zombie has different point rewards from killing them,
    - Player can buy weapons each 5 rounds based on your points,
    - Indiana Grimes has 10 hits of life, if it turns 0 = GAME OVER,
    - After GAME OVER screen, player can send his name to ranking, only the top 5 appears at ranking screen.


### - Commands
    - A = LEFT, and UP at menu,
    - D = RIGHT, and DOWN at menu,

    - F = ATTACK, and CONFIRM at menu.    
<div align="center">
    
![commands IMAGE](https://github.com/Game-JAVA/Clueless/blob/develop/Images/screens/game%20controls%20screen.png)
</div>
## Game Classes

```mermaid

---
title: Zombies Attack
---
classDiagram
Characters <|-- Player
Characters <|-- Zombies
ImageView <|-- Characters
ImageView <|-- Scenario

class Game{
-int width
-int height
+start()
+main(string[]  args)
}
class ImageView{
+ImageView()
}
class Characters{
-int life
-boolean right
-boolean left
-int speed
-int strength
-ImageView sprite
-Image[] walking
+move(int frame, String weapon)
+move(int frame, int type)
}
class Player{
-String name
-int points
-int timeSurvived
-String weapon
-boolean isJumping
-boolean isWalking
-boolean isAttacking
-String direction
-Image[] shooting
+dash()
+playerWeapons()
+checkHealing()
+attack()
+jump()
}
class Zombies{
-int type
+chasing(int x, int y)
}
class Scenario{
-ImageView background
-Object plataform
}
class Menu{
+homeScreen()
+rankingScreen()
+gameKeys()
+gameOver()
+game()
+buyScreen()
+loading()
}

class KeyEvent{
-boolean paused
+keyEvent()
-gamePaused()
}

class Sounds{
-MediaPlayer sound
+getSoundEffect()
+getRifle(int op)
+getPistol(int op)
+getKnife()
+getKatana()
+getWalking()
+getJumping()
+getZombieGrowl()
+getSmallPunch()
+getBigPunch()
+getGameOver()
+getOption()
+getHealing()
}
```


## Sound Effects

In this project we use some sound effects to enrich the player's experience when interacting with the objects of game.

Sources used to obtain the files:

- [Pixabay](#https://pixabay.com/pt/sound-effects/)
- [Mixkit](#https://mixkit.co/free-sound-effects/)


## Requirements

java 22,

java.fx.


## Contribution

<div align="center">
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
</div>
