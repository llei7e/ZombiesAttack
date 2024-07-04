<div align="center">
    
# Zombies Attack

</div>  

<div align="center">
    
![image](https://github.com/Game-JAVA/Clueless/blob/develop/Images/gifs%20png/rickgrimes%20png.gif)
![image](https://github.com/Game-JAVA/Clueless/blob/develop/Images/gifs%20png/zombieP%20png.gif)
![image](https://github.com/Game-JAVA/Clueless/blob/develop/Images/gifs%20png/zombieP%20png.gif)
![image](https://github.com/Game-JAVA/Clueless/blob/develop/Images/gifs%20png/zombieM%20png.gif)
![image](https://github.com/Game-JAVA/Clueless/blob/develop/Images/gifs%20png/zombiG%20png%20.gif)
    
</div>

## Index
- [About](#about)  
- [Requirements](#requirements)
- [Game Classes](#game-classes)
- [User Flow](#user-flow) 
- [Story](#story)  
- [Game Objective](#game-objective)  
- [Commands](#commands)  
- [Sound Effects](#sound-effects)  
- [Contribution](#contribution)  


## Requirements

- Java IDE like IntelliJ or Eclipse (used IntelliJ for development),  
- java 22(jdk 22.0.1),  
- java.fx,  
- Libraries:  
    - javafx.application.Application;  
    - javafx.scene.Scene;  
    - javafx.scene.layout.Pane;  
    - javafx.stage.Stage;  
    - javafx.animation.*;  
    - javafx.scene.image.Image;  
    - javafx.scene.image.ImageView;  
    - javafx.util.Duration;  
    - java.util.ArrayList;  
    - java.util.Objects;  
    - import javafx.animation.KeyFrame;  
    - javafx.animation.PauseTransition;  
    - javafx.geometry.Pos;  
    - javafx.scene.control.Button;  
    - javafx.scene.layout.HBox;  
    - javafx.scene.layout.VBox;  
    - javafx.scene.text.Text;  
    - javafx.scene.input.KeyCode;  


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
-int speed
-int strength
-ImageView sprite
-Image[] walking
+move(int x, int y)
}
class Player{
-String name
-int points
-int timeSurvived
-String weapon
-boolean isJumping
+dash()
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
}

class KeyEvent{
-boolean right
-boolean left
-boolean shooting
-String direction
+keyEvent()
}
```

## User Flow
<div align="center"> 
    
### Home Screen  
![Home Screen2](https://github.com/Game-JAVA/Clueless/assets/148909506/8415a2df-daec-4dee-9848-8e68d29f439c)
</div>




## About

This game project was developed by students coursing System Analisys and Development at Faculdade SENAI Gaspar Ricardo Junior, with 2rpnet support.

Start date: 06/10/2024.

End date: to be defined.


## Story

During a zombie apocalypse, Indiana Grimes was the only one from his city that survived. Trying to find someone else or at least a single life, 
he runs the world fighting lots of types of zombies using his knife and wapons that he finds along the way.  

Zombies:  
    - Short zombies are fast, have low life and a medium hit,
    ![image](https://github.com/Game-JAVA/Clueless/blob/develop/Images/gifs%20png/zombieP%20png.gif)  
    - Medium zombies have a medium speed, medium life and a weak hit,
    ![image](https://github.com/Game-JAVA/Clueless/blob/develop/Images/gifs%20png/zombieM%20png.gif)  
    - Tall zombies are the slowest one, have the biggest life and the strongest hit.
    ![image](https://github.com/Game-JAVA/Clueless/blob/develop/Images/gifs%20png/zombiG%20png%20.gif)  


Indiana Grimes:
![image](https://github.com/Game-JAVA/Clueless/blob/develop/Images/gifs%20png/rickgrimes%20png.gif)  
    - Inspired by Rick Grimes from The Walking Dead and Indiana Jones. Indiana Grimes fights to survive the zombie apocalypse using a variety of weapons.
    


## Game Objective

The game works in rounds, at start the survivor has a knife to fight, by killing zombies he gets more points that allows him to use better weapons. Only the five best that reach more points can appear at the ranking.

Ranking:  
    - Each zombie has different point rewards from killing them,  
    - Player weapon change by reaching especific points,  
    - Indiana Grimes has 10 hits of life, if it turns 0 = GAME OVER,  
    - After GAME OVER screen, player can send his name to ranking, only the top 5 appears at ranking screen.


## Commands
    - A = LEFT, and UP at menu,
    - D = RIGHT, and DOWN at menu,

    - F = ATTACK, and CONFIRM at menu.    
<div align="center">
    
![commands IMAGE](https://github.com/Game-JAVA/Clueless/blob/develop/Images/screens/game%20controls%20screen.png)
</div>

## Sound Effects

In this project we use some sound effects to enrich the player's experience when interacting with the objects of game.

Sources used to obtain the files:

- [Pixabay](#https://pixabay.com/pt/sound-effects/)
- [Mixkit](#https://mixkit.co/free-sound-effects/)


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
