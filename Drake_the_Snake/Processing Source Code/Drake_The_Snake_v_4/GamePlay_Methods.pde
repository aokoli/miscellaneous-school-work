// NOTE: This isn't actually a seperate class. It just a group of INSTANCE methods for the Main Class


// GAMESTART: This controls game mechanics, snake movement, snake eating, snake dying etc.
void GameStart()    
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
  
  void GameOverPrompt()
  {
    background(0);
    textSize(32);
    text("GAME OVER!", 300, 280);
    text("Press \"R\" to reset to Blank stage.", 160, 320);
    textSize(18);
    text("Did you know there are alternate stages?", 230, 400);
    text("During Gameplay: \nPRESS \"G\" for George Thiruvathukal Stage \nPRESS \"I\" for \"IT\" Stage \nPRESS \"B\" for default Blank Stage ", 230, 440);  
  }
  
  
  
  void ReverseSnake()
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


// SNAKE MOVEMENTS

Point PSnakeHeadMove()  // THIS IS NECESSARY SO CRASHES ARE DETECTED 1 FRAME/BLOCK BEFOREHAND, SO SNAKEHEAD DOESN'T APPEAR INSIDE CRASHED OBJECT
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

List<Point> PSnakeMove()  // This moves snake to an empty location.   
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
   fill(#30c656);  // Makes next shape (snake) green.  
   
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
   fill(#30c656);  // Makes next shape (snake) green.   
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





List<Point> PSnakeEat()  // This moves snake to an empty location.   
   {
   
   // "TURN" THE SNAKE HEAD:
   // Get head location
   Point PsnakeHead = PsnakeBody.get(0); 
   
   // Paint snake body where head is
   stroke(backgroundColor);  // Make borders around the next shape background color
   fill(backgroundColor);  // Make shape (fill) background color
   rect((float) PsnakeHead.getX(), (float) PsnakeHead.getY(), PbodyWidth, PbodyHeight); // Clears snake head
   stroke(0);  // Make borders around next shape black
   fill(#30c656);  // Makes next shape (snake) green.   
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

void LevelDisplay()
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
    
    fill(#30c656);  // Makes next shape (snake) green.  

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
    fill(#e71b35); 
    ellipse((int) food.getX(), (int) food.getY(), PbodyWidth, PbodyHeight);
    noFill();  
}

 
    // FOOD GENERATOR
    Point FoodGenerator()
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
          }
      }  
   
   fill(#e71b35); // Makes food red
   ellipse((int) food.getX(), (int) food.getY(), PbodyWidth, PbodyHeight);   
   return food;
   }
