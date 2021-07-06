# Forza4

**Run the application**
To realize the project, it has been used `Java version 8, Update 251 (build 1.8.0_251-b08)`.

In order to compile and execute the code, it may be necessary to 
install this specific version if some errors are found.

The code has been developed using `Eclipse` IDE.
To run the application, you must open **ConnectFour** class, 
the class containing the main method that launches the Graphical User Interface.
You can find this class in *src/playGame*.
**Run ConnectFour class, then you can play!**  

**Run test classes**
It has been developed a series of test classes for each notable class. 
You can find them in *src/testing*.
Every single class has a main method in it, so you can run each class 
and see the tests being done, the expected result and so on.

**Other folders**
In the project, there are three other main folders:
- **doc**, a folder containing the documentation of each class, using the `JavaDoc` standard tool.
- **res**, a folder containing resources used by the application.
- **savedGames**, a folder containing the saved matches.

**Please** do **not** delete any of this folder. 
Deleting any of them will result in a completely different experience 
while playing the game, other than reducing the functionality the application provides.
(This is clearly not the case of the documentation though).

**Additional notes**:

`Windows 10`
The project runs with no additional note on Windows 10 OS.

`Linux`
The project has not been tested in Linux environment, so there could be some issue regarding
the colors or the interface of the application.

`MacOS`
Please note that some 'Java Look & Feel' options enabled by default on macOS
has been shown to create some problems in components rendering, so background
colors did not render in the expected way. 
This problem has been fixed apparently, but it may be the case that some 
visual problems are still not fixed, due to different macOS versions where the
application has not been tested. 
Please be aware of this issue, and refer to the GUI book to see what it is expected 
and what it is not, or, for a full experience, think about using `Windows 10`.
