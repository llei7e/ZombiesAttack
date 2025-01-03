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
- [Sound Effects](#sound-effects)  
- [Contribution](#contribution)  


## About

This game project was developed by students coursing System Analisys and Development at Faculdade SENAI Gaspar Ricardo Junior, with 2rpnet support.

Start date: 06/10/2024.

End date: to be defined.


## Requirements

- Java IDE like IntelliJ or Eclipse (used IntelliJ for development),  
- java 22(used jdk 22.0.1),  
- java.fx,

### Steps
- Download the IntelliJ (community edition is free) - [IntelliJ (Community Edition)](https://www.jetbrains.com/idea/download/#section=windows),
- Download git - [git for windows)](https://git-scm.com/download/win),
- Install both IntelliJ and git,
- Clone the project,
- Open it at IntelliJ (wait the dependencies to install),
- Close the project,
- Open it again.

## Game Classes
```mermaid

---
title: Zombies Attack
---

classDiagram
Characters <|-- Player
Characters <|-- Zombies
ImageView <|-- Characters
Application <|-- Game

class Game{
+start(Stage primaryStage)
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
+Characters()
+move(int frame, String weapon)
+move(int frame, int type)
+setSprite(Image img)
+setSprite(Image img, int type)
+setSprite(Image img, String weapon)
+setSprite(Image img, String weapon, boolean isShooting)
}
class Player{
-String name
-int points
-String weapon
-boolean isJumping
-boolean isCooldown
-String direction
+Player()
+playerWeapons()
+attack(Pane pane, ArrayList<Zombies> zombies)
+jump()
+checkHealing(ImageView healing, Pane pane)
+animationEndGame(Scene scene, Pane pane, Stage stage)
+weaponsSfx()
+hit(Zombie zombie)
+takeDamage()
}
class Zombies{
-int type
+Zombies(int positionX, int type)
+chasing(Player player, Zombie zombie, int frame)
+takeHit()
}
class Menu{
+homeScreen(Scene scene, Pane pane, Stage stage, Ranking ranking)
+rankingScreen(Scene scene, Pane pane, Stage stage, Ranking ranking)
+gameKeys(Scene scene, Pane pane, Stage stage)
+gameOver(Scene scene, Pane pane, Stage stage, Player p1)
+game(Scene scene, Pane pane, Stage stage)
+loading(Scene scene, Pane pane, Stage stage)
+rankingName(Scene scene, Pane pane, Stage stage, Player p1)
}
class GameLoop{
-boolean paused
-boolean canRestart
double difficulty
+keyEvent()
+gamePaused(Scene scene, Pane pane, AnimationTimer gameLooping)
}
class Sounds{
+getSoundEffect()
+getRifle(int op)
+getPistol(int op)
+getKnife()
+getKatana()
+getJumping()
+getZombieGrowl()
+getSmallPunch()
+getBigPunch()
+getGameOver()
+getOption()
+getHealing()
+getSpawn()
+getLoading()
+getHome()
}
class Ranking{
-ArrayList<Winner> winners
-File archive
+Ranking()
+getWinnersFromArchive()
+setWinnersToArchive()
+sortWinners()
+saveWinner(String name, int points)
+getWinners()
}
class Winner{
-String name
-int points
+Winner(String name, int points)
+setName()
+setPoints()
+getName()
+getPoints()
}
class Application{
+launch()
}
```

## User Flow

<div align="center"> 
    
### Home  
![Home Screen](https://github.com/Game-JAVA/Clueless/blob/develop/Images/screens/home%20screen.png)
</div>

<div align="center"> 
    
### Ranking 
![Ranking Screen](https://github.com/Game-JAVA/Clueless/blob/develop/Images/screens/ranking%20screen.png)
</div>

<div align="center"> 
    
### Home 
![Home Screen](https://github.com/Game-JAVA/Clueless/blob/develop/Images/screens/home%20screen.png)
</div>

<div align="center"> 
    
### Loading  
![Loading Screen](https://github.com/Game-JAVA/Clueless/blob/develop/Images/screens/loading%20screen.png)
</div>

<div align="center"> 
    
### Controls 
![Controls Screen](https://github.com/Game-JAVA/Clueless/blob/develop/Images/screens/GameKeys%20Screen.png)
</div>

<div align="center"> 
    
### Game 
![Game Screen](https://github.com/Game-JAVA/Clueless/blob/develop/Images/screens/Game%20Screen.png)
</div>

<div align="center"> 
    
### Game Over 
![GameOver Screen](https://github.com/Game-JAVA/Clueless/blob/develop/Images/screens/Game%20Over.png)
</div>

<div align="center"> 
    
### Insert Ranking Name  
![InsertName Screen](https://github.com/Game-JAVA/Clueless/blob/develop/Images/screens/RankingName%20Screen.jpg)
</div>


## Story

During a zombie apocalypse, Indiana Grimes was the only one from his city that survived. Trying to find someone else or at least a single life, he runs the world fighting lots of types of zombies using his knife and wapons that he finds along the way.  

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
