#  Robo Rumble Challenge

[![Build Status](https://travis-ci.org/slothsoft/challenge-robo-rumble.svg?branch=master)](https://travis-ci.org/slothsoft/challenge-robo-rumble)

- **Author:** [Stef Schulz](mailto:s.schulz@slothsoft.de)
- **Repository:** <https://github.com/slothsoft/challenge-robo-rumble>
- **Open Issues:** <https://github.com/slothsoft/challenge-robo-rumble/issues>
- **Wiki:** none


In this challenge you give an AI to a little robot that fights for his life against other robots.

![Screenshot](https://raw.githubusercontent.com/slothsoft/challenge-robo-rumble/master/readme/screenshot.png)



## Getting Started

### Prerequisites

You need at least **Java 8** or above to run the code.


### Using the Challenge

1. Clone this repository
2. Import into the IDE of your choice
3. Run `RoboRumble` to see the challenge in action
4. Create your own implementation of `Robot` and put it into `de.slothsoft.roborumble.contrib`, then run `RoboRumble` again to see your robot

```java   

public class MyRobot extends AbstractRobot implements Robot {

	@Override
	public String getDisplayName() {
		return "My Robot";
	}

	@Override
	public void execute(Context context) {
		// TODO: use context to do an action
	}

}
```
   
     

##  Versions


| Version       | 
| ------------- |
| [Java 8](https://github.com/slothsoft/challenge-robo-rumble) |
   

## Features

Optionally, you can override the following methods to customize your robot:

- **`createStats()`** - returns the stats for your robot, in case you want to make it super fast or super strong or super defensive
- **`createRenderer()`** - creates the renderer for your robot; the default implementation `SpriteRobotRenderer` splits an image into sprites

There are some util classes to help you implement your robot, like

- **`GameUtil`**
  - `isOnMap(Map, Point)` - if the point is on the map
  - `isOnWall(Map map, Point point)` - if the point is on the map but blocked by a wall


## License

This project is licensed under the MIT License - see the [MIT license](https://opensource.org/licenses/MIT) for details.
