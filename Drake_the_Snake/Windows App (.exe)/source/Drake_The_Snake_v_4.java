import processing.core.*; 
import processing.data.*; 
import processing.opengl.*; 

import java.awt.Point; 
import ddf.minim.*; 

import java.applet.*; 
import java.awt.Dimension; 
import java.awt.Frame; 
import java.awt.event.MouseEvent; 
import java.awt.event.KeyEvent; 
import java.awt.event.FocusEvent; 
import java.awt.Image; 
import java.io.*; 
import java.net.*; 
import java.text.*; 
import java.util.*; 
import java.util.zip.*; 
import java.util.regex.*; 

public class Drake_The_Snake_v_4 extends PApplet {

/*
DRAKE THE SNAKE (lite)
Version: 4.0
Last update: Wednesday, December 12, 2012 at 12:44 PM CST

Changes from Version 3.0:
- After a screen change, the snake remains stagnant until a button is pressed

*/



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

Minim minim;
AudioPlayer bgSFX_splash;
AudioPlayer bgSFX_game; 
AudioPlayer SFX_bite;
AudioPlayer SFX_burp;

//IMAGE COMPONENT
PImage IMG_splash;

public void setup()
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

public void draw()
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



public void mousePressed()
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


public void keyPressed() 
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


public void reset()
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





// NOTE: This isn't actually a seperate class. It just a group of INSTANCE methods for the Main Class


// GAMESTART: This controls game mechanics, snake movement, snake eating, snake dying etc.
public void GameStart()    
{    
  if (keyCode == UP) {dir = 0;}
  else if (keyCode == DOWN) {dir = 1;}
  else if (keyCode == RIGHT) {dir = 2;}
  else if (keyCode == LEFT) {dir = 3;}
  
  Point PsnakeMouth = PSnakeHeadMove();
  
  if (dir == 2 && previousDir == 3)
  {
    ReverseSnake();
  }
  else if (dir == 3 && previousDir == 2)
  {
    ReverseSnake();
  } 
  else if (dir == 0 && previousDir == 1)   
  {                               
    ReverseSnake();      
  }
  else if (dir == 1 && previousDir == 0)    
  {                               
    ReverseSnake();      
  }
  else if (PsnakeBody.contains(PsnakeMouth) || PlevelWalls.contains(PsnakeMouth))     
  {
    GameOverPrompt();
    noLoop();  
  }  
  else if (PsnakeMouth.equals(food))
  {
    score += 1 + 10*((PsnakeBody.size() - 1) / 5);
    PSnakeEat();
    food = FoodGenerator();   // Game ends within this function
    
    //Play SFX whenever a food is eaten
    SFX_bite.play();
    SFX_bite.rewind();
  }
  else
  {
    PsnakeBody = PSnakeMove();
  }
  
  previousDir = dir;
  
  
   // SCORE/LEVEL UPDATER 
   //Changes framerate every 5 meals
   frameRate(10 + (PsnakeBody.size() - 1) / 5);   
    
    
   //Computes current level
   level = 1 + ((PsnakeBody.size() - 1) / 10);
    
}
  
  public void GameOverPrompt()
  {
    background(0);
    textSize(32);
    text("GAME OVER!", 300, 280);
    text("Press \"R\" to reset to Blank stage.", 160, 320);
    textSize(18);
    text("Did you know there are alternate stages?", 230, 400);
    text("During Gameplay: \nPRESS \"G\" for George Thiruvathukal Stage \nPRESS \"I\" for \"IT\" Stage \nPRESS \"B\" for default Blank Stage ", 230, 440);  
  }
  
  
  
  public void ReverseSnake()
  {
    dir = previousDir;
    
    Point PsnakeMouth = PSnakeHeadMove();
    if (PsnakeBody.contains(PsnakeMouth) || PlevelWalls.contains(PsnakeMouth))     
  {
    GameOverPrompt();
    noLoop();
  }  
  else if (PsnakeMouth.equals(food))
  {
    score += 1 + 10*((PsnakeBody.size() - 1) / 5);
    PSnakeEat();
    food = FoodGenerator();   // Game ends within this function
  }
  else
  {
    PsnakeBody = PSnakeMove();
  }
  }
  
  
  
  
  
  /*
   Point PsnakeMouth = PSnakeHeadMove(); 

   if (PsnakeMouth.equals(food))
   {
     PSnakeEat();
     food = FoodGenerator();
   } 
   else if (PsnakeBody.contains(PsnakeMouth) || PlevelWalls.contains(PsnakeMouth))     
   {
    noLoop();
   } 
   else 
   {
    PsnakeBody = PSnakeMove();
   }
   

*/



// SNAKE MOVEMENTS
// (Point head movement)

public Point PSnakeHeadMove()  // THIS IS NECESSARY SO CRASHES ARE DETECTED 1 FRAME/BLOCK BEFOREHAND, SO SNAKEHEAD DOESN'T APPEAR INSIDE CRASHED OBJECT
{
// "TURN" THE SNAKE HEAD:
   // Get head location
   Point PsnakeHead = PsnakeBody.get(0);    
      
  if (dir == 0)    // Makes snake head move UP
  {
    PsnakeHead = new Point((int) PsnakeHead.getX(), (int) (PsnakeHead.getY() - PpointChange));  

     }  
  
  if (dir == 1)    // Makes snake head move DOWN
  {
    PsnakeHead = new Point((int) PsnakeHead.getX(), (int) (PsnakeHead.getY() + PpointChange));  
     }
  
  if (dir == 2)    // Makes snake head move RIGHT
  {
    PsnakeHead = new Point((int) (PsnakeHead.getX() + PpointChange), (int) PsnakeHead.getY()); 
    
  }

  if (dir == 3)    // Makes snake head move LEFT
  {
   PsnakeHead = new Point((int) (PsnakeHead.getX() - PpointChange), (int) PsnakeHead.getY());  
  }  
  return PsnakeHead;
}

// POINT SNAKE MOVE

public List<Point> PSnakeMove()  // This moves snake to an empty location.   
   {
   // REMOVE SNAKE TAIL:
   // Get tail location
   Point PsnakeTail = PsnakeBody.get(PsnakeBody.size() - 1);
   PsnakeBody.remove(PsnakeBody.size() - 1);  // Deletes snake tail from List of snake coordinates
   
   // Where tail was located, paint background color
   stroke(backgroundColor);  // Paints border, of the next shape, same color as game background (this does a better job than "noStroke()" function which leaves more marks).
   fill(backgroundColor);  // Paints next shape (fill) background color
   rect((float) PsnakeTail.getX(), (float) PsnakeTail.getY(), PbodyWidth, PbodyHeight); // fills previous tail with "block" of background color
   stroke(0);  // Make borders around next shape black
   fill(0xff30c656);  // Makes next shape (snake) green.  
   
   /* PLEASE NOTE: Code directly below does not affect physics logic at all - it is only for display purposes on Processing. Whenever Processing
    * deletes a tail (using the algorithm directly above), the bottom of new tail will look straight (instead of circular) because a straight edge from  
    * the rectangle used to delete the old tail, cuts through the new tail. To solve this problem and make the new tail have a circular look, we simply
    * print the new tail in the NEW spot, using the code immediately following:
   */
   Point PnewSnakeTail = PsnakeBody.get(PsnakeBody.size() - 1);
   ellipse((float) PnewSnakeTail.getX(), (float) PnewSnakeTail.getY(), PbodyWidth, PbodyHeight);        
   
   // "TURN" THE SNAKE HEAD:
   // Get head location
   Point PsnakeHead = PsnakeBody.get(0); 
   
   // Paint snake body where head is
   stroke(backgroundColor);  // Make borders around the next shape background color
   fill(backgroundColor);  // Make shape (fill) background color
   rect((float) PsnakeHead.getX(), (float) PsnakeHead.getY(), PbodyWidth, PbodyHeight); // Clears snake head
   stroke(0);  // Make borders around next shape black
   fill(0xff30c656);  // Makes next shape (snake) green.   
   ellipse((float) PsnakeHead.getX(), (float) PsnakeHead.getY(), PbodyWidth, PbodyHeight);  // Paints snake body in snake head location
   
      
  if (dir == 0)    // Makes snake head move UP
  {
    PsnakeHead = new Point((int) PsnakeHead.getX(), (int) (PsnakeHead.getY() - PpointChange));  // Turns snake head in correct direction
    ellipse((float) PsnakeHead.getX(), (float) PsnakeHead.getY(), PbodyWidth, PbodyHeight);   // Prints new snake head
    
    fill(255); // Ultimately for bottom half of eye ball to be white
    PsnakeRightEye = new Point((int) PsnakeRightEye.getX(), (int) (PsnakeRightEye.getY() - PpointChange));  // Updates coordinate for right eyeball
    ellipse((float) PsnakeRightEye.getX(), (float) PsnakeRightEye.getY(), PeyeWidth, PeyeHeight); // Prints right eyeball inside snake head
    fill(0); // Ultimately for top half of eye ball to be black
    arc((float) PsnakeRightEye.getX(), (float) PsnakeRightEye.getY(), PeyeWidth, PeyeHeight, -PI, 0);  // prints arc shape in bottom half of eyeball (splits eyeball in half)
    
    fill(255); // Ultimately for bottom half of eye ball to be white
    PsnakeLeftEye = new Point((int) PsnakeLeftEye.getX(), (int) ((PsnakeLeftEye.getY() - PpointChange)));  // Updates coordinate for left eye
    ellipse((float) PsnakeLeftEye.getX(), (float) PsnakeLeftEye.getY(), PeyeWidth, PeyeHeight); // Prints left eyeball inside snake head
    fill(0); // Ultimately for top half of eye ball to be black
    arc((float) PsnakeLeftEye.getX(), (float) PsnakeLeftEye.getY(), PeyeWidth, PeyeHeight, -PI, 0); // prints arc shape in botton half of eyeball (splits eyeball in half)
  }  
  
  if (dir == 1)    // Makes snake head move DOWN
  {
    PsnakeHead = new Point((int) PsnakeHead.getX(), (int)(PsnakeHead.getY() + PpointChange)); 
    ellipse((float) PsnakeHead.getX(), (float) PsnakeHead.getY(), PbodyWidth, PbodyHeight);
    
    fill(0);
    PsnakeRightEye = new Point((int) PsnakeRightEye.getX(), (int) ((PsnakeRightEye.getY() + PpointChange)));
    ellipse((float) PsnakeRightEye.getX(), (float) PsnakeRightEye.getY(), (float) PeyeWidth, (float) PeyeHeight);
    fill(255);
    arc((float) PsnakeRightEye.getX(), (float) PsnakeRightEye.getY(), (float) PeyeWidth, (float) PeyeHeight, -PI, 0);
  
    fill(0);
    PsnakeLeftEye = new Point((int) PsnakeLeftEye.getX(), (int) ((PsnakeLeftEye.getY() + PpointChange)));  // Updates coordinate for left eye
    ellipse((float) PsnakeLeftEye.getX(), (float) PsnakeLeftEye.getY(), PeyeWidth, PeyeHeight); // Prints left eye
    fill(255);
    arc((float) PsnakeLeftEye.getX(), (float) PsnakeLeftEye.getY(), PeyeWidth, PeyeHeight,  -PI, 0); //
  }
  
  if (dir == 2)    // Makes snake head move RIGHT
  {
    PsnakeHead = new Point((int) (PsnakeHead.getX() + PpointChange), (int) PsnakeHead.getY()); 
    ellipse((float) PsnakeHead.getX(), (float) PsnakeHead.getY(), PbodyWidth, PbodyHeight);
    
    fill(0);
    PsnakeRightEye = new Point((int) (PsnakeRightEye.getX() + PpointChange),(int) PsnakeRightEye.getY());  // Updates coords for right eye.
    ellipse((float) PsnakeRightEye.getX(), (float) PsnakeRightEye.getY(), PeyeWidth, PeyeHeight); // prints right eye
    fill(255);
    arc((float) PsnakeRightEye.getX(), (float) PsnakeRightEye.getY(), PeyeWidth, PeyeHeight, PI / 2, 3 * PI / 2); // paints eye ball
    
    PsnakeLeftEye = new Point((int) (PsnakeLeftEye.getX() + PpointChange), (int) PsnakeLeftEye.getY());   // Updates coords for left eye, but doesn't print!
  
  }

  if (dir == 3)    // Makes snake head move LEFT
  {
   PsnakeHead = new Point((int) (PsnakeHead.getX() - PpointChange), (int) PsnakeHead.getY()); 
   ellipse((float) PsnakeHead.getX(), (float) PsnakeHead.getY(), PbodyWidth, PbodyHeight);
   
   PsnakeRightEye = new Point((int) (PsnakeRightEye.getX() - PpointChange), (int) PsnakeRightEye.getY()); // Updates coords for right eye, but doesn't print!
  
   fill(255);
   PsnakeLeftEye = new Point((int)(PsnakeLeftEye.getX() - PpointChange), (int) PsnakeLeftEye.getY()); // Updates coords for left eye. 
   ellipse((float) PsnakeLeftEye.getX(), (float) PsnakeLeftEye.getY(), PeyeWidth, PeyeHeight); // prints left eye
   fill(0);
   arc((float) PsnakeLeftEye.getX(), (float) PsnakeLeftEye.getY(), PeyeWidth, PeyeHeight, PI / 2, 3 * PI / 2); // paints eye ball
    
  }
  
  PsnakeBody.add(0, PsnakeHead);
  return PsnakeBody;  // adds new head coordinates to snake's body
}





public List<Point> PSnakeEat()  // This moves snake to an empty location.   
   {
   
   // "TURN" THE SNAKE HEAD:
   // Get head location
   Point PsnakeHead = PsnakeBody.get(0); 
   
   // Paint snake body where head is
   stroke(backgroundColor);  // Make borders around the next shape background color
   fill(backgroundColor);  // Make shape (fill) background color
   rect((float) PsnakeHead.getX(), (float) PsnakeHead.getY(), PbodyWidth, PbodyHeight); // Clears snake head
   stroke(0);  // Make borders around next shape black
   fill(0xff30c656);  // Makes next shape (snake) green.   
   ellipse((float) PsnakeHead.getX(), (float) PsnakeHead.getY(), PbodyWidth, PbodyHeight);  // Paints snake body in snake head location
       
      
  if (dir == 0)    // Makes snake head move UP
  {
    PsnakeHead = new Point((int) PsnakeHead.getX(), (int) (PsnakeHead.getY() - PpointChange));  // Turns snake head in correct direction
    ellipse((float) PsnakeHead.getX(), (float) PsnakeHead.getY(), PbodyWidth, PbodyHeight);   // Prints new snake head
    
    fill(255); // Ultimately for bottom half of eye ball to be white
    PsnakeRightEye = new Point((int) PsnakeRightEye.getX(), (int) (PsnakeRightEye.getY() - PpointChange));  // Updates coordinate for right eyeball
    ellipse((float) PsnakeRightEye.getX(), (float) PsnakeRightEye.getY(), PeyeWidth, PeyeHeight); // Prints right eyeball inside snake head
    fill(0); // Ultimately for top half of eye ball to be black
    arc((float) PsnakeRightEye.getX(), (float) PsnakeRightEye.getY(), PeyeWidth, PeyeHeight, -PI, 0);  // prints arc shape in bottom half of eyeball (splits eyeball in half)
    
    fill(255); // Ultimately for bottom half of eye ball to be white
    PsnakeLeftEye = new Point((int) PsnakeLeftEye.getX(), (int) ((PsnakeLeftEye.getY() - PpointChange)));  // Updates coordinate for left eye
    ellipse((float) PsnakeLeftEye.getX(), (float) PsnakeLeftEye.getY(), PeyeWidth, PeyeHeight); // Prints left eyeball inside snake head
    fill(0); // Ultimately for top half of eye ball to be black
    arc((float) PsnakeLeftEye.getX(), (float) PsnakeLeftEye.getY(), PeyeWidth, PeyeHeight, -PI, 0); // prints arc shape in botton half of eyeball (splits eyeball in half)
  }  
  
  if (dir == 1)    // Makes snake head move DOWN
  {
    PsnakeHead = new Point((int) PsnakeHead.getX(), (int)(PsnakeHead.getY() + PpointChange)); 
    ellipse((float) PsnakeHead.getX(), (float) PsnakeHead.getY(), PbodyWidth, PbodyHeight);
    
    fill(0);
    PsnakeRightEye = new Point((int) PsnakeRightEye.getX(), (int) ((PsnakeRightEye.getY() + PpointChange)));
    ellipse((float) PsnakeRightEye.getX(), (float) PsnakeRightEye.getY(), (float) PeyeWidth, (float) PeyeHeight);
    fill(255);
    arc((float) PsnakeRightEye.getX(), (float) PsnakeRightEye.getY(), (float) PeyeWidth, (float) PeyeHeight, -PI, 0);
  
    fill(0);
    PsnakeLeftEye = new Point((int) PsnakeLeftEye.getX(), (int) ((PsnakeLeftEye.getY() + PpointChange)));  // Updates coordinate for left eye
    ellipse((float) PsnakeLeftEye.getX(), (float) PsnakeLeftEye.getY(), PeyeWidth, PeyeHeight); // Prints left eye
    fill(255);
    arc((float) PsnakeLeftEye.getX(), (float) PsnakeLeftEye.getY(), PeyeWidth, PeyeHeight,  -PI, 0); //
  }
  
  if (dir == 2)    // Makes snake head move RIGHT
  {
    PsnakeHead = new Point((int) (PsnakeHead.getX() + PpointChange), (int) PsnakeHead.getY()); 
    ellipse((float) PsnakeHead.getX(), (float) PsnakeHead.getY(), PbodyWidth, PbodyHeight);
    
    fill(0);
    PsnakeRightEye = new Point((int) (PsnakeRightEye.getX() + PpointChange),(int) PsnakeRightEye.getY());  // Updates coords for right eye.
    ellipse((float) PsnakeRightEye.getX(), (float) PsnakeRightEye.getY(), PeyeWidth, PeyeHeight); // prints right eye
    fill(255);
    arc((float) PsnakeRightEye.getX(), (float) PsnakeRightEye.getY(), PeyeWidth, PeyeHeight, PI / 2, 3 * PI / 2); // paints eye ball
    
    PsnakeLeftEye = new Point((int) (PsnakeLeftEye.getX() + PpointChange), (int) PsnakeLeftEye.getY());   // Updates coords for left eye, but doesn't print!
  
  }

  if (dir == 3)    // Makes snake head move LEFT
  {
   PsnakeHead = new Point((int) (PsnakeHead.getX() - PpointChange), (int) PsnakeHead.getY()); 
   ellipse((float) PsnakeHead.getX(), (float) PsnakeHead.getY(), PbodyWidth, PbodyHeight);
   
   PsnakeRightEye = new Point((int) (PsnakeRightEye.getX() - PpointChange), (int) PsnakeRightEye.getY()); // Updates coords for right eye, but doesn't print!
  
   fill(255);
   PsnakeLeftEye = new Point((int)(PsnakeLeftEye.getX() - PpointChange), (int) PsnakeLeftEye.getY()); // Updates coords for left eye. 
   ellipse((float) PsnakeLeftEye.getX(), (float) PsnakeLeftEye.getY(), PeyeWidth, PeyeHeight); // prints left eye
   fill(0);
   arc((float) PsnakeLeftEye.getX(), (float) PsnakeLeftEye.getY(), PeyeWidth, PeyeHeight, PI / 2, 3 * PI / 2); // paints eye ball
    
  }
  
  PsnakeBody.add(0, PsnakeHead);
  return PsnakeBody;  // adds new head coordinates to snake's body
}














// LEVEL PRINTER

public void LevelDisplay()
{
     // WALLS/OBSTACLES
    background(backgroundColor);
    for (int i = 0; i < PlevelWalls.size(); i++)
    {      
      int xWallFloat = (int) (PlevelWalls.get(i).getX());             
      int yWallFloat = (int) (PlevelWalls.get(i).getY());  
      
      fill (175);  // Makes walls grey
      rectMode(CORNER);
      rect(xWallFloat,yWallFloat,PbodyWidth,PbodyHeight); // Prints big wall blocks
      rect(xWallFloat, yWallFloat, (PbodyWidth/2), (PbodyHeight/2)); // Prints smaller blocks inside big wall blocks   
      fill(backgroundColor);     
      
    }      
    
    
    // SNAKE
    
    fill(0xff30c656);  // Makes next shape (snake) green.  

    for (int i = 0; i < PsnakeBody.size(); i++)
    {
      if (i == 0)
      {
        ellipse((float) PsnakeBody.get(i).getX(), (float) PsnakeBody.get(i).getY(), PbodyWidth, PbodyHeight);  // NEW! Prints snake head 
        ellipse((float) PsnakeRightEye.getX(), (float) PsnakeRightEye.getY(), (float) PeyeWidth, PeyeHeight); // Prints right eye
      ellipse((float) PsnakeLeftEye.getX(), (float) PsnakeLeftEye.getY(), PeyeWidth, PeyeHeight);  

    }
      else
      {
        ellipse((float) PsnakeBody.get(i).getX(), (float) PsnakeBody.get(i).getY(), PbodyWidth, PbodyHeight);  // NEW! Prints snake body 
 
      }
    }  
    
    
   //SCORE/LEVEL DISPLAY 
   //Builds rectangle for score/level display
   fill(255);
   rect(5, height-24, 160, 18);

   fill(0);
   textSize(12);
   text("LEVEL: " + level + "   SCORE: " + score, 10, height-10);
    
    // FOOD
    fill(0xffe71b35); 
    ellipse((int) food.getX(), (int) food.getY(), PbodyWidth, PbodyHeight);
    noFill();  
}

 
    // FOOD GENERATOR
    public Point FoodGenerator()
    {
      // Code below generates new random food that doesn't land on snakebody or on any obstacle/wall
     Random r = new Random();
     
     int foodLimitX = 720;  // The food can't be generated past this width
     int foodLimitY = 520; // The food can't be generated past this height
   
   int foodLocationX = (r.nextInt((foodLimitX/PpointChange)) * PpointChange) + PpointChange;  // Min: 40 Max: 720
   int foodLocationY = (r.nextInt((foodLimitY/PpointChange)) * PpointChange) + PpointChange;  // Min: 40 Max: 520
   
   Point food = new Point(foodLocationX, foodLocationY);   // Assigns food to new location
   
   /*  CODE FOR GAME ENDING AND FOR FOOD TO NOT APPEAR ON SNAKE BODY/WALLS
   * Code below makes sure food does not respawn/appear on snake or walls! If food does appear on snake or walls
   * the program keeps assigning a new coordinate to the foodLocation UNTIL the snakeBody and walls don't
   * contain the foodLocation. If there is no new location to put food i.e. After every location in the game 
   * has been checked, then the player has won the game.
   */
   
   
   
   while (PsnakeBody.contains(food) || PlevelWalls.contains(food))
      {
          for (int h = PpointChange; h < (foodLimitY + PpointChange); h += PpointChange)  // Checks every cell within food respawnable area (Less work on PC: (int h = PpointChange; h < (foodLimitY + PpointChange); h += PpointChange) 
          {
              for (int w = PpointChange; w < (foodLimitX + PpointChange); w += PpointChange)
              {
                  food = new Point(w, h);
                  if (!PsnakeBody.contains(food) && !PlevelWalls.contains(food))
                  {
                      break;
                  }
              }
              if (!PsnakeBody.contains(food) && !PlevelWalls.contains(food))
              {
                  break;
              }
          }
          if (!PsnakeBody.contains(food) && !PlevelWalls.contains(food))
          {
              break;
          }
          else
          {
             background(0);
             textSize(32);
             text("YOU WIN!", 280, 280);
             text("Press \"R\" to reset level.", 200, 320);
             noLoop();
            // TO TEST GAME WIN SCREEN!!!!!!!!!!!!!!

          }
      }  
   
   fill(0xffe71b35); // Makes food red
   ellipse((int) food.getX(), (int) food.getY(), PbodyWidth, PbodyHeight);   
   return food;
   }
public class Level
{
// FIELDS
//Screen
int screenWidth = 600;
int screenHeight = 600;

//METHODS
// Initial food location
   
   public Point FoodLocation()
   {
     Point food = new Point(400, 320);
     return food;
   }

// NEW!! Coords for Point snake

  public List<Point> PSnakeLocation()
  {
    List<Point> PsnakeBody = new ArrayList<Point>(); // NEW!
    
    //int PyBody = PyInitial;   
    //int PxBody = PxInitial;  
    
    int PyBody = 280;   
    int PxBody = 280;  
    
    int numberOfSnakeParts = 2;  // Minimum of 2. (For head and tail).
    
    for (int i = 0; i < numberOfSnakeParts; i++)    
    { 
      PsnakeBody.add(new Point(PxBody,PyBody));
   
        PyBody += PpointChange;  
    }
    return PsnakeBody;
  }

   
   
   public List<Point> PLevelWalls(int n)     // NEW!!!!
   {    
     // SETS WALL COORDINATES
     List<Point> PlevelWalls = new ArrayList<Point>();  
     
     if (n == 1)
     {
     // GAME WALLS (BORDERS)
     int wallWidth = 800;
     int wallHeight = 600;
     for (int h = 0; h < wallHeight; h += PpointChange)  // Checks every cell within food respawnable area (Less work on PC: (int h = PpointChange; h < (foodLimitY + PpointChange); h += PpointChange) 
          {
              for (int w = 0; w < wallWidth; w += PpointChange)
              {
                if (h == 0 || h == wallHeight - PpointChange || w == 0 || w == wallWidth - PpointChange)
                {
                PlevelWalls.add(new Point(w,h));
                }
              }
          }    
         
     }
     else if (n == 2)
     {      
     // WALLS/BORDERS
     int wallWidth = 800;
     int wallHeight = 600;
     for (int h = 0; h < wallHeight; h += PpointChange)  // Checks every cell within food respawnable area (Less work on PC: (int h = PpointChange; h < (foodLimitY + PpointChange); h += PpointChange) 
          {
              for (int w = 0; w < wallWidth; w += PpointChange)
              {
                if (h == 0 || h == wallHeight - PpointChange || w == 0 || w == wallWidth - PpointChange)
                {
                PlevelWalls.add(new Point(w,h));
                }
              }
          }    
          
          
          
    // EXTRA WALLS (Mid-screen obstacles "G" and "T")
    
    int wallSize;
    int yWall;
    int xWall;
    
    wallSize = 160;  // Initial y value
    for (int i = 0; i < 200; i += PpointChange)
    {
      wallSize += PpointChange;
      yWall = wallSize;
      xWall = 160;
      PlevelWalls.add(new Point(xWall,yWall));
    }
    
    PlevelWalls.add(new Point(200,160));
    PlevelWalls.add(new Point(240,160));
    PlevelWalls.add(new Point(280,160));
    PlevelWalls.add(new Point(320,200));
    
    PlevelWalls.add(new Point(200,400));
    PlevelWalls.add(new Point(240,400));
    PlevelWalls.add(new Point(280,400));
    PlevelWalls.add(new Point(320,320));
    PlevelWalls.add(new Point(320,360));
    PlevelWalls.add(new Point(320,400));
    PlevelWalls.add(new Point(320,440));
    
    
    wallSize = 440;  // Initial x value
    for (int i = 0; i < 200; i += PpointChange)
    {
      wallSize += PpointChange;
      xWall = wallSize;
      yWall = 160;
      PlevelWalls.add(new Point(xWall,yWall));
    }
    
    
    wallSize = 120;  // Initial y value
    for (int i = 0; i < 320; i += PpointChange)
    {
      wallSize += PpointChange;
      yWall = wallSize;
      xWall = 560;
      PlevelWalls.add(new Point(xWall,yWall));
    }
  }
    
    else if (n == 3)
    {
      // WALLS/BORDERS
     int wallWidth = 800;
     int wallHeight = 600;
     for (int h = 0; h < wallHeight; h += PpointChange)  // Checks every cell within food respawnable area (Less work on PC: (int h = PpointChange; h < (foodLimitY + PpointChange); h += PpointChange) 
          {
              for (int w = 0; w < wallWidth; w += PpointChange)
              {
                if (h == 0 || h == wallHeight - PpointChange || w == 0 || w == wallWidth - PpointChange)
                {
                PlevelWalls.add(new Point(w,h));
                }
              }
          }    
          
    
    // EXTRA WALLS (Mid-screen obstacles "IT")
    
    PlevelWalls.add(new Point(160, 80));
    int wallSize;
    int yWall;
    int xWall;
  
    wallSize = 120;  // Initial y value
    for (int i = 0; i < 280; i += PpointChange)
    {
      wallSize += PpointChange;
      yWall = wallSize;
      xWall = 160;
      PlevelWalls.add(new Point(xWall,yWall));
    }

    wallSize = 320;  // Initial x value
    for (int i = 0; i < 280; i += PpointChange)
    {
      wallSize += PpointChange;
      xWall = wallSize;
      yWall = 80;
      PlevelWalls.add(new Point(xWall,yWall));
    }
    
    wallSize = 120;  // Initial y value
    for (int i = 0; i < 280; i += PpointChange)
    {
      wallSize += PpointChange;
      yWall = wallSize;
      xWall = 480;
      PlevelWalls.add(new Point(xWall,yWall));
    }   

  }

    return PlevelWalls;
   }
   
     
  
  // GETTERS  
  
  public Point PRightEyeLocation() // NEW
  {
    int PxInitialRightEye = 170+40+40+40+19;
    int PyInitialRightEye = 157+40+40+40+15;
    Point PsnakeRightEye = new Point(PxInitialRightEye, PyInitialRightEye);
    return PsnakeRightEye;
  }  
  
  public Point PLeftEyeLocation() // NEW
  {
    int PxInitialLeftEye = 152+40+40+40+11;
    int PyInitialLeftEye = 157+40+40+40+15;
    Point PsnakeLeftEye = new Point(PxInitialLeftEye, PyInitialLeftEye);
    return PsnakeLeftEye;
  }

  public int PEyeWidth()   // NEW
  {return 8;} 
   
  public int PEyeHeight()   // NEW
  {return 16;} 
  
  public int PBodyWidth() // NEW
  {return PPointChange();}  
  
  public int PBodyHeight()  // NEW 
  {return PPointChange();} 

  public int PPointChange()
  {return 40;}  // Changes location of Point(x,y) using pixels as rate of change
  
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Drake_The_Snake_v_4" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
