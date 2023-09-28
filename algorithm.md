Jorge Jimenez
Professor Andrew Harris
CSCI 24000
4/25/2022
Final Project

Goal: To create a basic "text adventure game" using connected Nodes in a binary tree format. The program should also include a way for players to have thier own personal accounts where their personal game history will be available to review. All of which is written in java.

Input: Users are expected to input thier loggin credentials, and to input their choices based on the given menus.

Outpu: The program will output the appropiate menus for the given user, and will output the appropiate results based on the menu selection made. These selected outputs are a list of player game history and game prompts.

Steps:

  Player class:
    Goal: to house the account number and pin of the player. Also, to hold the "inventory" of the player. "Inventory" relates to whenever or not a player is holding a "beneficial" item. To determine if the player is holding an item a counter can be used, not a static counter, since the number of "beneficial" items will be different for each player. If that counter is ever determine to be more than one, then the method that generates the game level will know that the player is safe if they encounter anything dangerous. Finally, the player will house an array list of strings, which will be the game history of the player.
    Input: The constructor can not be null. As at the creation of all players, a unique account number must be given. There will be setters to set any changes to the data log array list.
    Output: The getters should return the following important information: account number, pin, the "inventory" value, a getter to retrieve all the data log strings, and a player menu method convinience.

    getAccountNumber()
      Goal: return the integer value of the protected account number attribute

    getPin() mehtod
      Goal: return the integer value fo the protected pin attribute
 
    getArrayList() method:
      Goal: to return the data log array list. This way, the Game class can call and read the unique data log each player has whenever the correct player credentials are inputed.

    setArrayList() method:
      Goal: to update the protected data log array list attribute. Whenever the Game class adds to the data log array list, it will use its altered version of the data log array list to update the unique data log array list within the Player class instance.

    addLog() method:
      Goal: to add a new string message to the data log array list

    printLog() method:
      Goal: to print every string within the data log array list using a for loop of the size of the data log array list.

    getPlayerSCP method:
      Goal: to return the integer value of the protected playerSCP attribute. This attribute is the "inventory" counter which always starts at zero and will increase as the player finds "beneficial" items throughout the game.

    reducePlayerSCP method:
      Goal: to subtract one from the player inventory whenever an item is used. This method will need to make sure that the player inventory value never goes into the negative.

    addPlayerSCP method:
      Goal: to add one to the player inventory vlaue whenever an item is discovered.

    resetPlayerSCP method:
      Goal: to reset the player inventory value to zero. This method is used to counter the bug that allows users to carry items through several playlist. It should be called at the end of the game to completely reset the player inventory value.

    playerMenu method:
      Goal: to take user input and list all the major actions the players can take. These actions are to loggout, play the game, or view the player game history.

  Admin class:
    Goal: to hold the account number and pin of the admin, to hold the array list of that houses all the players, and to produce the admin menu.
    Input: the constructor could be null, as there will only ever be one admin. User input is expected for the admin menu, which will be used only within the Game class. A setter class will be used to update the array list of players.
    Output: to use getters to obtain the admin account number, admin pin, and the array list of players. 

    getPin method:
      Goal: to return the integer value of the protected pin attributed.

    getAccountNumber method:
      Goal: to return the integer value of the protected account number attribute.

    getArrayList method:
      Goal: to return the players array list. This is so that the Game class can read the players array list and have access to all the players created.

    setArrayList method: 
      Goal: to set the players array list. Whenever the Game class alteres its version of the players array list, it can update the unique admin array list that will be serialized at the end.

    addPlayer method:
      Goal: to accept two integer parameters to use as the account number and pin for the new user. However, the method wil first check a for loop of the players array list to determine if the account number, which has to be unique, has already been taken. If it has been taken then a boolean attribute within the method can be altered to true. An if statement will determine if the boolean is true or false, if true then the user will be warned that the account number is already in use. If the boolean value is false, then the new player will be made give the inputed account number and pin.

    listAllPlayers method:
      Goal: to iterate through the entire players array list using a for loop. As the for loop iterates the through each player instance within the array list, it will print each player's account number and pin.

    adminMenu method:
      Goal: to print out the actions the admin can do while also taken user input.

  Node class: 
    Goal: to create a recursive node class that will be used to create a binary tree. Each Node will have a title(based on the player choice within the game), a description, a left Node, a forward Node, a right Node, a end message (which is added to the player data log array list), a boolean value to determine if there was anything dangerous within the Node, and a boolean value to determine if there was a "beneficial" item within the Node. Finally, the Node will have a next Node to which is altered to determine the next Node in line.
    Input: The user is expected to input L, F, or R within the displayNode method in order to set the value of the next Node in line.
    Output: getters will be used to obtain the boolean values that determine what's inside the Node, and a getter will be used to return the end message.

    displayNode method:
      Goal: to print out the title, description, and path options to the user. This method also takes a user input to that will be read by an if statement. If the input is L, then the left Node path is choosen to be the next Node. If the input if F or R, then either the forward or right Node path are chosen to be the next Node. The next Node is then loaded into memory using recursion, showing the next title, the next description, and the same path options. 

    setForward, setLeft, setRight method:
      Goal: as all Nodes are made with empty forward, left, and right paths, setters will be used to manually set what the Node will be on the previous Node's left, forward, and right Node attributes (the three corresponding paths).

    getLocalSCP method:
      Goal: to return the boolean value that determines if there is a monster within the Node.

    getBeneficialSCP method:
      Goal: to return the boolean value that determines if there is a beneficial item within the Node.


  Game class: 
    Goal: to construct the entire game, and to allow the admin or player to loggin and use thier corresponding methods based on menu inputs. 
    Input: the users will have to input menu options, and the corresponding admin or player credentials.  
    Output: The corresponding menus should appear, based on given loggin credentials, and the current Node of the game should be displayed.

    Game method and constructor:
      Create a new admin instance and save it to the users.txt file. In subsequent runs of the program, the admin instance will be loaded from the users.txt file at the beginning.
      
      Load in the admin's players array list and then begin showing the Game menu which allows the user to try and log in as either the admin or a player. 

      If the user inputs the correct admin loggin credentials, then the admin menu is printed and the user is allowed to input their desired action as the admin. These actions are to list all players, where the admin's corresponding method is called. The other action is to add a player, where the admin's addPlayer method is called into action to take the inputted account number and pin for the new player. If the admin decides to loggout, then the players array list that was localy created in the Game class using the getter for the admin's players array list will be used as a parameter for the admin's array list setter. In the end, the admin's personal attribute should be updated by the time the user decides to loggout.

     If the user inputs a correct player loggin credentials, then the player menu is printed adn the user is allowed to input their desired action as a player. These actions include printing the player's data log using the corresponding player method, and another action is to call the Game class' level method. When calling the level method, a player instance must be given as a parameter, so the current player instance being logged into by the user should be sent. Taken the returned string from the level method, the string will be added to the player's personal data log of play history. If the player decides to loggout, then the Game class' altered version of the player's data that was loaded into memory using the array list getter will be used in the player's array list setter, so that the player's personal array list attributes is updated. At the end, whenever the player logout of the program, their datalog should be updated.

      When trying to login as a player, a for loop is needed is to compare the inputed credentials to all the players within the players array list.

    level method:
      The level method will create each Node from hand. This made more sense in this personal case, but there may be better ways to do this.
      
      Before the game is played, two Node instances are made for the current Node and the previous Node. Both Nodes will be set to the starting Node of the binary tree before the game runs. 

      When the game runs, using recursion until the current Node reaches a null value, the previous Node will get the current Node value each time the next Node is loaded into the current Node. This way, whenever a dangerous monster is detected to be within the Node, using the boolean value of the current Node, then then the player's inventory value will be loaded. If the inventory value is more than one then the current Node will be set to the previous Node and the previous Node is set to the current Node. Before the loop ends in this instance, the current Node is once again displayed, so that the game doesn't bug and allow the user to possibly get additional beneficial items.

      When the game detects that there is a beneficial item within the Node, the player's inventory value is increased by one.
      When the game loop detects that the current Node is null, then the game is no longered called and the player's inventory is reset to zero.

      At the end of the level method, the program will return the previous Node's end message string, since the current Node is still null and doesn't have a end message attribute. 
