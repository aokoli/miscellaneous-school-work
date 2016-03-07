/*
DRAKE THE SNAKE (lite)
Version: 4.0
Last update: Wednesday, December 12, 2012 at 12:44 PM CST

Changes from Version 3.0:
- After a screen change, the snake remains stagnant until a button is pressed

*/

import java.awt.Point;

Level game = new Level();  // Getting game level object

//Score/leve default
int score;
int level = 1;

//Game screen/coordinates default
int PpointChange = game.PPointChange(); // NEW

// Snake default
List<Point> PsnakeBody = game.PSnakeLocation(); // NEW
Point PsnakeRightEye = game.PRightEyeLocation(); // NEW
Point PsnakeLeftEye = game.PLeftEyeLocation(); // NEW
int PbodyWidth = game.PBodyWidth();  // NEW
int PbodyHeight = game.PBodyHeight();  // NEW
int PeyeWidth = game.PEyeWidth();   // NEW 
int PeyeHeight = game.PEyeHeight(); // NEW

// Level walls/obstacles default
List<Point> PlevelWalls = game.PLevelWalls(1);   // NEW

// Direction button default
int dir;
int previousDir = dir;

// Initial food location
Point food = game.FoodLocation();

boolean begin = false;
boolean welcomeScreenOff = false;
int backgroundColor = 255;

//AUDIO COMPONENT
import ddf.minim.*;
Minim minim;
AudioPlayer bgSFX_splash;
AudioPlayer bgSFX_game; 
AudioPlayer SFX_bite;
AudioPlayer SFX_burp;

//IMAGE COMPONENT
PImage IMG_splash;

void setup()
{
    size(800,600);
    //frameRate(10);
    background(backgroundColor);  
    
    //Set frame
    frame.setTitle("Drake the Snake | Lite");
  
    //Font
    PFont font = loadFont("DIN-Bold-20.vlw");
    textFont(font);
    
    //Splash image
    IMG_splash = loadImage("splash.png");

    //Background music and SFX
    minim = new Minim(this);
    bgSFX_splash = minim.loadFile("bgSFX_splash.mp3");
    bgSFX_game = minim.loadFile("bgSFX_game.mp3");
    SFX_bite = minim.loadFile("bite.mp3");
    SFX_burp = minim.loadFile("burp.mp3");
    bgSFX_splash.loop();
   
    smooth();  // noSmooth() = More realistic snake?
    ellipseMode(CORNER);  
    
     //Load splash bacgkround image
  image(IMG_splash, 0, 0, width, height);


}

void draw()
{ 
  if (welcomeScreenOff)
  {    
     LevelDisplay();      
     //if (keyCode == UP || keyCode == RIGHT || keyCode == LEFT) {begin = true;}
     
     if (begin)
     {
       GameStart();
     }
     
  }
}



void mousePressed()
{
  //Stop the splash music and start game music
  bgSFX_splash.close();
  bgSFX_game.loop();
  
  //Hide the splash image
  tint(255, 0);
  
  //Send the value to global variable
  welcomeScreenOff = true;
  
  // Direction button default
  dir = 0;
  previousDir = dir;
  begin = false;


}


void keyPressed() 
{
  if (welcomeScreenOff)
  {
  if (keyCode == UP || keyCode == RIGHT || keyCode == LEFT) {begin = true;}
  if (keyCode == 'B' || keyCode == 'b' ) 
  { PlevelWalls = game.PLevelWalls(1); }
  if (keyCode == 'G' || keyCode == 'g' ) 
  { PlevelWalls = game.PLevelWalls(2); }
  if (keyCode == 'I' || keyCode == 'i' ) 
  { PlevelWalls = game.PLevelWalls(3); }
  if (keyCode == 'R'|| keyCode == 'r') {reset();}
  }
}


void reset()
{
  background(backgroundColor);
  
 //Level game = new Level();  // Getting game level object
  
 // Game screen/coordinates default
 PpointChange = game.PPointChange(); // NEW

 // Snake default
 PsnakeBody = game.PSnakeLocation(); // NEW
 PsnakeRightEye = game.PRightEyeLocation(); // NEW
 PsnakeLeftEye = game.PLeftEyeLocation(); // NEW
 PbodyWidth = game.PBodyWidth();  // NEW
 PbodyHeight = game.PBodyHeight();  // NEW
 PeyeWidth = game.PEyeWidth();   // NEW 
 PeyeHeight = game.PEyeHeight(); // NEW

 // Level walls/obstacles default
 PlevelWalls = game.PLevelWalls(1);   // NEW

 // Direction button default
  dir = 0;
  previousDir = dir;
 
 // Initial score/level
 score = 0;
 level = 1;
 
 // Initial food location
 food = game.FoodLocation();
 
  LevelDisplay();
  begin = false;
  loop();

}





