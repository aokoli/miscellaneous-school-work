public class Level
{
// FIELDS
//Screen
int screenWidth = 600;
int screenHeight = 600;

//METHODS
// Initial food location
   
   Point FoodLocation()
   {
     Point food = new Point(400, 320);
     return food;
   }

// Coords for Point snake

  List<Point> PSnakeLocation()
  {
    List<Point> PsnakeBody = new ArrayList<Point>();
    
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

   
   
   List<Point> PLevelWalls(int n)  
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
  
  Point PRightEyeLocation() 
  {
    int PxInitialRightEye = 170+40+40+40+19;
    int PyInitialRightEye = 157+40+40+40+15;
    Point PsnakeRightEye = new Point(PxInitialRightEye, PyInitialRightEye);
    return PsnakeRightEye;
  }  
  
  Point PLeftEyeLocation() 
  {
    int PxInitialLeftEye = 152+40+40+40+11;
    int PyInitialLeftEye = 157+40+40+40+15;
    Point PsnakeLeftEye = new Point(PxInitialLeftEye, PyInitialLeftEye);
    return PsnakeLeftEye;
  }

  int PEyeWidth()   
  {return 8;} 
   
  int PEyeHeight()   
  {return 16;} 
  
  int PBodyWidth() 
  {return PPointChange();}  
  
  int PBodyHeight()  
  {return PPointChange();} 

  int PPointChange()
  {return 40;}  // Changes location of Point(x,y) using pixels as rate of change
  
}
