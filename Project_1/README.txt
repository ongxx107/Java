Summary: Project #1 will involve implementing several shape classes in Java and using a drawing class we have implemented for you to create some graphics, 
         including a fractal based on your shapes.


Circle class Methods:
 Name: Circle (constructor); input: x position (double), y position (double), radius (double); output : object of type Circle
 Name: calculatePerimeter; input: none; output: perimeter of the circle (type double)
 Name: calculateArea; input: none; output: area of the circle (double)
 Name: setColor; input: color of the shape (type Color); output: none
 Name: setPos; input: x, y position of the center (both doubles) output: none
 Name: setRadius; input: radius (double) output: none
 Name: getColor; input: none; output: color of the shape (type Color);
 Name: getXPos; input: none; output: x position of the center (double)
 Name: getYPos; input: none; output: y position of the center (double)
 Name: getRadius; input: none; output: radius (double)
Any data members needed to support these methods.
Note: the Color class is implemented in the java.awt library, which you can use by including the following statement at the top 
of your class definition file: import java.awt.*; See the Java API documentation for more details on this class (note the static data fields, e.g. Color.BLUE).

Triangle class Methods:
 Name: Triangle (constructor); input: x position of bottom left corner (double), y position of bottom left corner (double), width (double), height (double); output: object of type Triangle
 Name: calculatePerimeter; input: none; output: perimeter of the triangle (type double)
 Name: calculateArea; input: none; output: area of the triangle (double)
 Name: setColor; input: color of the shape (type Color); output: none
 Name: setPos; input: x, y position of bottom left corner (both doubles) output: none
 Name: setHeight; input: height (double) output: none
 Name: setWidth; input: width (double) output: none
 Name: getColor; input: none; output: color of the shape (type Color);
 Name: getXPos; input: none; output: x position of the bottom left corner (double)
 Name: getYPos; input: none; output: y position of the bottom left corner (double)
 Name: getHeight; input: none; output: height (double)
 Name: getWidth; input: none; output:width (double)

Rectangle class
 Name: Rectangle (constructor); input: x position of bottom left corner (double), y
position of bottom left corner (double), width (double), height (double); output: object of
type Rectangle
 Name: calculatePerimeter; input: none; output: perimeter of the rectangle (type double)
 Name: calculateArea; input: none; output: area of the rectangle (double)
 Name: setColor; input: color of the shape (type Color); output: none
 Name: setPos; input: x, y position of bottom left corner (both doubles) output: none
 Name: setHeight; input: height (double) output: none
 Name: setWidth; input: width (double) output: none
 Name: getColor; input: none; output: color of the shape (type Color);
 Name: getXPos; input: none; output: x position of the bottom left corner (double)
 Name: getYPos; input: none; output: y position of the bottom left corner (double)
 Name: getHeight; input: none; output: height (double)
 Name: getWidth; input: none; output: width (double)
Any data members needed to support these methods.

For this part of the project, we will write a Java program that uses your shape classes to draw a fractal. 

