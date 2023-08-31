# 3D Render Engine
## Overview
**Starting Date:** 29th May, 2023
**Project Completion:** 27th July, 2022
**Motivation:** Self-motivated, as a challenge
**Status:** Paused indefinitely

This is a challenge I took on to develop a 3 dimensional rendering engine from scratch, in Java. In was developed sporadically over two months.

![enter image description here](https://onedrive.live.com/embed?resid=BE406011F5E2A3C1!488971&authkey=!AJNywH0mFNZKeMM&width=400)

## Context

After using 3D programs such as Blender, Unity3D, and SolidWorks I was curious to understand exactly how 3D works. I wanted to use the knowledge I had gained from my Engineering Mathematics (ENG1005, ENG2005), particularly regarding vectors, to develop a simple rending engine on my own from scratch.

I set the following restrictions on myself for this task:

- All vectors, geometry primitives, and other frameworks needed to be written as classes.
- The only complex inbuilt maths resources I could use was trigonometric functions like _cos_ and  _sin^-1_.
- The only displaying techniques I could use was a _setPixel()_ function  I wrote which takes the position on the screen and a rgb colour,  and the _clear()_ function to remove all pixels.
- The keyboard input is the final library I used in this project.

![enter image description here](https://onedrive.live.com/embed?resid=BE406011F5E2A3C1!488969&authkey=!AKF05zNCO7R7_vg&width=400&height=400)
 

## Technical Description

### The Framework Classes
For this project I started by developing the framework of classes I would need. I knew that the project would get too complex without using Object Oriented Programming to help manage it. The classes I developed originally were:

- *Rgb*
	- stores red, green, blue and alpha ints from 0 to 255;
- *Vector3*
	- Stores a x, y, z double
- *VectorMath*
	- Contains functions for adding, getting the unit vector, the cross product and other useful functions
- *ScreenCoord*
	- Contains a x, y integer for locations on the screen
- *Vertex*
	- Stores a *Vector3* position
- *Edge*
	- Stores 2 vertices
- *Face*
	- Stores 3 *Edges* that form a triangle
	- Stores a *Vector3* normal
- *Camera*
	- Stores a *Vector3* position
	- Instead of using quaternion or Euler angles to define the camera rotation, I chose to simplify the process by using a forward *Vector3* and a Upward *Vector3*.
	- The field of view was also stored, which was set to 45 degrees or Pi/4
- *Cube*
	- This primative shape could be defined by a width and a *Vector3* centre location
	- All the required *vertices*, *edges*, *faces*, and normals were manually defined relative to these input values
- *DirectionalLight*
	- This class was added later.
	- It stores a direction for the light to be going.
- *SphericalCoords*
	- This class was added later, as will be discussed below.
	- It stores the r, theta, and phi values
	- It also includes the functions to convert between Cartesian and Spherical coordinates.

### The Controllers
Following this, I started creating controllers for each function. Each one uses the Singleton pattern as there should only be one at a time.

- *GeometryManager*
	- This class stores all the *Vertices*, *Edges*, and *Faces* in hash maps, and allows individual ones to be accessed using individual attributes as the keys to access them. For example, a *Vertex* can be accessed using its location as the key.
	- This was also intended to allow the vertexes, faces, and edges to be iterated over, which is used in the *RenderEngine*
- *CameraManager*
	- Stores the active *Camera* instance, rotation speed, and *Vector3* that the camera rotates around.
	- This also contains the functionality to rotate and zoom in/out. I struggled for a while trying to work out how to rotate vectors around an arbitrary axis, before realising that converting the *Camera* location to spherical coordinates made the rotations really simple. Just changing the  psi and theta directly rotated the cameras as I wanted, and converting them back was easy. This wasn't planned at all, but really helped me appreciate the advantages of spherical coordinates.
- *LightManager*
	- Stores the *DirectionalLight* instance.
	- Has a function that tells the *SimpleRenderer* how bright a face should be by finding the angle between the directions of the face tangent and the directional light.


### The Render Engine
The final component is the actual render engine, which took the most amount of time to develop. It also uses a singleton pattern.

- SimpleRenderer
	- The largest function of the renderer is done by the getVertexScreenCoordinates() function, which takes a *Vertex* with a XYZ location, and works out where it would be visible on the screen.
	- I've uploaded a detailed version of this calculation onto a Google Collab [here](https://colab.research.google.com/drive/1gyfaAoTMASUxCD5GNEqldXt_A_CdP2EZ?usp=sharing), which I recommend looking into if you're interested.
	- This class also has a function to loop through each face, calculate where each of the vertices should be on the screen, and fill in the pixels between these points with the right brightness from the *LightManager*. This method is called each frame to update the screen.
	- The fill function to get all the pixels between three XY  coordinates was implemented with a recursive method, however integer rounding seems to have led to a strange pattern being formed. The following image shows two triangles forming a square, and the shading that forms from this algorithm.
![enter image description here](https://onedrive.live.com/embed?resid=BE406011F5E2A3C1!488966&authkey=!ACuACdMtsAoqV4Q&width=336&height=314)

## Results and Potential Improvements
Overall I  am extremely satisfied with this project, and I have a working program that fulfils the requirements I set for myself. Furthermore, I understand the concept of 3D rendering must better than before.

However, there are several issues that I would like to fix sometime in the future when I have more time. The most obvious of these is the shading issue seen above, but there's also no culling of objects behind others currently which is obvious when another smaller cube is added.

In addition, I would like to make the system for defining primitive shapes much easier, as even a simple cube took me a long time.

![enter image description here](https://onedrive.live.com/embed?resid=BE406011F5E2A3C1!488974&authkey=!AO4kDaqXxBX_z58&width=400)


## Running the Program

Run the Application.java file to test the program.
The Render type can be modified in the Application.java file, and addition cubes can be added and/or scaled.

Please note that the window cannot be resized without crashing, but it can be moved.

To zoom in/out, use E and Q keys.
To rotate the camera, use WASD keys.
