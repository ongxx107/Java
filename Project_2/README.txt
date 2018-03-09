Summary: For Project #2, we will write a screen saver application that animates balls bouncing and colliding inside a box. 
This project will require you to use inheritance to extend the Circle class as well as an AnimationFrame class we have written for you, 
and will give you practice applying concepts like composition, arrays, and handling user input.

Part I. Defining and Implementing a Ball class 

The Ball class should extend the Circle class from the last project (or download our version), 
and should maintain state information and implement the physics of each bouncing ball. 
The state information should include the size, color, position and velocity (speed in both X and Y dimensions) of the ball 
and all of the methods necessary to interact with a ball such as checking if two balls have collided and functionality for updating state after a collision.

Methods inherited from Circle:
• Name: setColor; input: color of the shape (type Color); output: none
• Name: setPos; input: x, y position of the center (both doubles) output: none
• Name: setRadius; input: radius (double) output: none
• Name: getColor; input: none; output: color of the shape (type Color);
• Name: getXPos; input: none; output: x position of the center (double)
• Name: getYPos; input: none; output: y position of the center (double)
• Name: getRadius; input: none; output: radius (double)
• Name: calculatePerimeter; input: none; output: perimeter of the circle (type double)
• Name: calculateArea; input: none; output: area of the circle (double)

New methods:
• Name: setSpeedX; input: speed of the ball’s movement along the horizontal axis (type double); output: none
• Name: setSpeedY; input: speed of the ball’s movement along the vertical axis (type double); output: none
• Name: getSpeedX; input: none; output: speed of the ball’s movement along the horizontal axis (type double)
• Name: getSpeedY; input: none; output: speed of the ball’s movement along the vertical axis (type double)
• Name: updatePosition; input: number of time units passed (type double); output: none; This method should update the ball’s 
  position based on its current velocity and the time elapsed.
• Name: intersect; input: another Ball object to check if they have collided (type Ball); output: Boolean; This method 
should check if the passed Ball object overlaps with the current Ball, which will be useful for detecting collisions among Balls.
• Name: collide; input: another Ball object with which the current Ball should collide (type Ball); output: none; 
This method should confirm that the balls have collided and implement the physics of two balls colliding— the state (i.e. X and Y speeds)
 of each ball should be updated accordingly at the end of this method.
• Name: Ball (constructor) ; input: x position (double), y position (double), radius (double); color (Color) output : object of type Ball

Part II. Defining and Implementing a BallScreenSaver class

Your task is to define and implement a class called BallScreenSaver that extends our AnimationFrame class to display 
balls bouncing around inside a box. The methods you will need to implement/override are:
• action(): input: none; output: none; As described above, this method is called periodically (default rate 50 times/sec)
  and actually updates the states of any objects being animated.
• draw(): input: graphics object reference (type Graphics); output: none; As described above, this method actually draws 
  the graphics for each frame after the action method is called to update state.
• processKeyEvent(): input: event object corresponding with a keyboard event (type KeyEvent); output: none; This method is 
  automatically called whenever a key is pressed while the animation is running. You will need to override this method to support the requirements described below.
Hint: all of the above methods should override either existing or abstract methods in the class AnimationFrame, 
      so the method signatures should be identical to the base class.
• BallScreenSaver (constructor): input: window frame width (int), window frame height (int), name of the application window (String), 
  the number of bouncing balls to be animated (int) output : object of type BallScreenSaver

Part III. Implementing a CollisionLogger class

Finally, you will need to finish an implementation of a CollisionLogger class, which will be used to count the number of 
collisions that occurs in each “bucket” of a two-dimensional, discretized grid. We have provided a skeleton version of this 
class for you—you just need to add the appropriate data members and complete the implementation of the methods described below. 
You are required to use a two-dimensional array of integers as a data member of this class.
 
CollisionLogger (constructor): inputs: int screenWidth, int screenHeight, int bucketWidth inputs: the screen width (type int), 
the screen height (type int) and the bucketWidth (type int); output: an instance of the CollisionLogger class.
collide: inputs: two different Ball objects; output: none. This method should log a collision at the position of the two balls. 
You can assume the average X and average Y position of the two balls for the collision location.
getNormalizedHeatMap: inputs: none; output: a two-dimensional array of ints. This should return the current state of the collision
log but rescaled such that the bucket with the largest number of collisions has value 255, and buckets without any collisions have a value of 0.