//Game.java

import java.util.*;
import java.io.*;

public class Game {
  public static void main(String[] args){
    new Game();
  }//end main

  public Game(){
    Scanner input = new Scanner(System.in);
    
    Admin admin = null;
    try{
      FileInputStream fIn = new FileInputStream("users.txt");
      ObjectInputStream obIn = new ObjectInputStream(fIn);
      admin = (Admin)obIn.readObject();
      fIn.close();
      obIn.close();
    } catch(IOException i){
      System.out.println(i.getMessage());
      admin = new Admin(00000, 12345);
      try{
        FileOutputStream fileOut = new FileOutputStream("users.txt");
        ObjectOutputStream obOut = new ObjectOutputStream(fileOut);
        obOut.writeObject(admin);
        fileOut.close();
        obOut.close();
      } catch(Exception e){
        System.out.println(e.getMessage());
      }//end try
    } catch(ClassNotFoundException c){
      System.out.println(c.getMessage());
      admin = new Admin(00000, 12345);
      try{
        FileOutputStream fOut = new FileOutputStream("users.txt");
        ObjectOutputStream obOut = new ObjectOutputStream(fOut);
        obOut.writeObject(admin);
        fOut.close();
        obOut.close();
      } catch(Exception e){
        System.out.println(e.getMessage());
      }//end try
    } catch(Exception e){
      System.out.println(e.getMessage());
    }//end try

    ArrayList<Player> players = admin.getArrayList();

    boolean keepGoing = true;
    while(keepGoing){
      String choice = gameMenu();
      if(choice.equals("1")){
        System.out.println("Please input Admin account number");
        int adminAccountNumber = input.nextInt();
        System.out.println("Please input Admin pin");
        int adminPin = input.nextInt();

        if(admin.getAccountNumber() == adminAccountNumber){
          if(admin.getPin() == adminPin){
            boolean keepGoing2 = true;
            while(keepGoing2){
              String adminResponse = admin.adminMenu();
              if(adminResponse.equals("1")){
                System.out.println("New Player Account Number");
                int account = input.nextInt();
                System.out.println("New Customer Pin");
                int pin = input.nextInt();
                admin.addPlayer(account, pin);
              } else if(adminResponse.equals("2")){
                admin.listAllPlayers();
              } else if(adminResponse.equals("0")){
                System.out.println("Loggin out of Admin");
                admin.setArrayList(players);
                keepGoing2 = false;
              } else{
                System.out.println("Please pick an available option from the menu");
              }//end if
            }//end while keepGoing 2
          } else{
            System.out.println("The inputed pin doesn't match any known admins");
          }//end if
        } else {
          System.out.println("The inputed accountNumber doesn't match any known admins");
        }//end if
      } else if(choice.equals("2")){
        System.out.println("Player Account Number");
        int playerAccountNumber = input.nextInt();
        System.out.println("Player Pin");
        int playerPin = input.nextInt();

        //loggin error
        boolean failedLoggin = false;

        //check the entire player Array List for matching inputs
        for(int i = 0; i < players.size(); i++){
          failedLoggin = false;
          if(players.get(i).getAccountNumber() == playerAccountNumber){
            if(players.get(i).getPin() == playerPin){
              ArrayList<String> dataLog = players.get(i).getArrayList();
              
              boolean keepGoing3 = true;
              while(keepGoing3){
                String playerResponse = players.get(i).playerMenu();
                input = new Scanner(System.in);
      
                if(playerResponse.equals("1")){
                  String endMessage = level(players.get(i));
                  players.get(i).addLog(endMessage);
                } else if(playerResponse.equals("2")){
                  players.get(i).printLog();
                } else if(playerResponse.equals("0")){
                  System.out.println("Loggin player out");
                  players.get(i).setArrayList(dataLog);
                  keepGoing3 = false;
                } else{
                  System.out.println("Invalid input. Please pick a listed option");
                }//end if
 
              }//end while
            } else {
              failedLoggin = true;
              //System.out.println("Inputed Account or Pin are incorrect");
            }//end if
          } else {
             failedLoggin = true;
            //System.out.println("Inputed Account Number doesn't have any matches");
          }//end if
        }//end for
        if(failedLoggin == true){
          System.out.println("Inputed Account Number or Pin are incorrect");
        }//end if
      } else if(choice.equals("0")){
        System.out.println("Goodbye");
        admin.setArrayList(players);
        keepGoing = false;
        try{
          FileOutputStream fo = new FileOutputStream("users.txt");
          ObjectOutputStream obOut = new ObjectOutputStream(fo);
          obOut.writeObject(admin);
          fo.close();
          obOut.close();
        } catch(Exception e){
          System.out.println(e.getMessage());
        }//end try
      } else {
        System.out.println("Please pick from an available option listed on the menu");
      }//end if
    }//end while
  
  }//end constructor

  public String level(Player player){
    Node.counter = 0;
    
    //create the beginning room and the first set of rooms after wards
    Node begin = new Node("GAME BEGIN: SCP-173 has escaped containment and has begun a site wide contianment breach", "You, a regular D-Class, has managed to survive the initial containment breach. It's now or never to try to escape and become a free men once again", null, null, null, "", false, false, false);
    Node left = new Node("You go left", "You begin to make your way throught the maze like Site 19. You hear a low weeping noise from one of the paths", null, null, null, "", false, false, false);
    Node forward = new Node("You go forward", "You begin to make your way through the maze like Site 19. You hear several footsteps and animal like sounds", null, null, null, "", false, false, false);
    Node right = new Node("You go right", "You begin to make your way through the maze like Site 19. You hear several footsteps coming from one path and animal noises coming from another path", null, null, null, "", false, false, false);
      
    //set all corresponding nodes to begin
    begin.setLeft(left);
    begin.setForward(forward);
    begin.setRight(right);

    //create the entire left side of the data tree
    Node leftSafeRoomOne = new Node("You go left", "You chose the safe path, what luck! Continuing your path you reach another fork. You can see a puddle of blackness coming from up ahead", null, null, null, "", false, false, false);
    Node tickleMonster = new Node("Going forward", "YOU WON: You come accross SCP-999 'The Tickle Monster'. With him as your companion your nerves relax and you figure out that trying to escape is holy impossible. You decide to stay with the Tickle Monster until everythin blows over. [all options take you back to the player menu]", null, null, null, "You survived thanks to SCP-999 'The Tickle Monster' (L, F)", false, false, false);
    Node leftShyGuy = new Node("Going right", "YOU LOSE: You come accross SCP-096 'The Shy Guy'. Failing to not look at his face you anger him and he tears you in half. [all options take you back to the player menu]", null, null, null, "You were killed by SCP-096 (L, R)", true, false, false);

    //set the left rooms nodes
    left.setLeft(leftSafeRoomOne);
    left.setForward(tickleMonster);
    left.setRight(leftShyGuy);

    //expand leftRoomOne
    Node leftSafeRoomTwo = new Node("You go left", "You chose a safe path. You know from your abusive testing that there are some dangerous SCPs located nearby, but maybe they can help you escape", null, null, null, "", false, false, false);
    Node leftOldManOne = new Node("Going forward", "YOU LOSE: You come accross SCP-106 'The Old Man'. Unable to escape he takes you to his pocket dimension where you suffer a cruel and slow death. [all options take you back to the player menu]", null, null, null, "You died to SCP-106 (L, L, F)", true, false, false);
    Node leftSafeRoomThree = new Node("Going right", "You chose a safe path. There should be SCP-207, a beneficial SCP, around here. You here several footsteps coming from ahead and you once again see a puddle of blackness", null, null, null, "", false, true, false);
   
    //set the nodes for leftSafeRoomOne 
    leftSafeRoomOne.setLeft(leftSafeRoomTwo);
    leftSafeRoomOne.setForward(leftOldManOne);
    leftSafeRoomOne.setRight(leftSafeRoomThree);
    
    // expand leftSafeRoomTwo
    Node burningSkull = new Node("You go left", "YOU LOSE: You find SCP 1123, the skull of a holocaust victim. Believing that it will help you escape you foolishly touch it and are forced to experince everything that the skull's owner experienced. You are severly weakend from your experince, you lay on the ground no longer able to move do to fetige. [all options take you back to the player menu]", null, null, null, "You stupedly touched an unknown SCP, SCP-1123, and lose the will to keep living (L, L, L, L)", false, false, true);
    Node leftSafeRoomFour = new Node("You go forward", "You chose a safe path. There should be SCP-268, a beneficial SCP, around here. You hear a loud roar comming from up ahead", null, null, null, "", false, true, false);
    Node shavingMirror = new Node("You go right", "YOU LOSE: You find SCP-884, a normal looking shaving mirror. You huddle up in a coner of the room, no longer having the will to live. Unbenounced to you, SCP-884 causes anyone that looks into it to doubt themselfs and lose the will to live. [all options take you back to the player menu]", null, null, null, "You looked into SCP-884 and saw how ugly you truly are (L, L, L, R)", false, false, true);
    
    //set the nodes for leftSafeRoom Two
    leftSafeRoomTwo.setLeft(burningSkull);
    leftSafeRoomTwo.setForward(leftSafeRoomFour);
    leftSafeRoomTwo.setRight(shavingMirror);

    //expand leftSafeRoomFour
    Node leftLizard = new Node("You go left", "YOU LOSE: You come accross SCP-682, 'The Hard to Destroy Lizard', which immidietly eats your head off. [all options take you back to the player menu]", null, null, null, "You died to SCP-682 (L, L, L, F, L)", true, false, false);
    Node leftChaosInsergency = new Node("You go forward", "YOU WON: You come accross a group from the Chaos Insergency. Liberating you from the torturous SCP Foundation, they take you along in thier attempt to leave the facility. [all options take you back to the player menu]", null, null, null, "You joined the Chaos Insergency (L, L, L, F, F)", false, false, false);
    Node leftRedBall = new Node("You go right", "YOU LOSE: You find a small red ball, SCP-018. You mistake it for nothing special and throw it across the room, unknowingly throwing an object that has no frition when given momentum. The small red ball begins to fly across the room at increasing speeds until it hits your torso, piercing you like a bullet and killing you instantly. [all options take you back to the player menu]", null, null, null, "You died to SCP-018 (L, L, L, F, R)", false, false, true);
   
    //setNodes for leftSafeRoomFour
    leftSafeRoomFour.setLeft(leftLizard);
    leftSafeRoomFour.setForward(leftChaosInsergency);
    leftSafeRoomFour.setRight(leftRedBall);

    //expand leftSafeRoomThree
    Node leftElevator = new Node("You go left", "YOU LOSE: You come accross an elevator that will take you deeper into the facilities. Hearing roaring, footstepts, and animal noises, you cast your bets and decide going deeper is as good as staying where you are. As you walk around the new floor you come across a corpse of a man trying to reach a big red button. Following the dead man's wish you press the big red button and set off the on site Nuclear Warhead. [all options take you back to the player menu]", null, null, null, "You stupedly activated the on site Nuke and killed everyone (L, L, R, L)", false, false, false);
    Node leftMTF = new Node("You go forwards", "YOU LOSE: You come accross a MTF unite from the SCP Foundation. As you are a D-Class of the SCP Foundation they immediatly kill you to prevent any unknown suprises. [all options take you back to the player menu]", null, null, null, "You were killed by an MTF (L, L, R, F)", true, false, false);
    Node leftOldManTwo = new Node("You go right", "YOU LOSE: You come accross SCP-106, 'The Old Man'. Unable to escape in time, he takes you to his pocket dimension where you suffer a slow and cruel death. [all options take you back to the player menu]", null, null, null, "You died to SCP-106 (L, L, R, R)", true, false, false);

    //set Nodes for leftSafeRoomThree
    leftSafeRoomThree.setLeft(leftElevator);
    leftSafeRoomThree.setForward(leftMTF);
    leftSafeRoomThree.setRight(leftOldManTwo);

    //expand forward
    Node possessiveMask = new Node("You go left", "YOU LOSE: You find SCP-035, 'The Possessive Mask'. He charms you with his smoth voice and good charisma, making you believe if you wear him that he will help you escape. Falling for his tricks like so many before, he steals your body, killing your concious. [all options take you back to the player menu]", null, null, null, "You were charmed by SCP-035 (F, L)", false, false, true);
    Node forwardRedDogs = new Node("You go forward", "YOU LOSE: You come accross SCP-939 instances. Fierce red dogs with no sight but strong senses. You calmly walk to a door, but all doors are automated which creates noise alarming the dogs to your location. Unable to escape, you are turned into food for SCP-939. [all options take you back to the player menu]", null, null, null, "You died to SCP-939 (F, F)", true, false, false);
    Node forwardSafeRoomOne = new Node("You go right", "You chose a safe path. You hear stone being draged on stone coming from one of the proceding paths, you also know that SCP-079, 'Old AI', is nearby and it might be able to help you", null, null, null, "", false, false, false);
   
    //set Nodes for forward
    forward.setLeft(possessiveMask);
    forward.setForward(forwardRedDogs);
    forward.setRight(forwardSafeRoomOne);

    //expand forwardSafeRoomOne
    Node oldAI = new Node("You go left", "You find SCP-079, 'Old AI', and try to make a deal with it. SCP-049 accepts you proposition and will help you escape, but only if you hook it up to the Internet, giving it infinite power. Do you accept? [Left for yes, forward for no, and right for threaten]", null, null, null, "", false, false, false);
    Node forwardSafeRoomTwo = new Node("You go forward", "You chose a safe path. You see the entire room filled with blood, you don't want to stay long enough to find out what happen", null, null, null, "", false, false, false);
    Node forwardStatue = new Node("You go right", "YOU LOSE: You come accross SCP-173, 'The Living Statue'. The cause of the entire containment breach stands before you, how can you possibly hope to beat it? Knowing that escape is beyond impossible, you slowly close your eyes. The room fills with the sound of your neck being twist backwards. [all options take you back to the player menu]", null, null, null, "You died to SCP-173 (F, R, R)", true, false, false);
 
    //set Nodes for forwardSafeRoomOne
    forwardSafeRoomOne.setLeft(oldAI);
    forwardSafeRoomOne.setForward(forwardSafeRoomTwo);
    forwardSafeRoomOne.setRight(forwardStatue);

    //expand forwardSafeRoomTwo
    Node demonChickens = new Node("You go left", "YOU LOSE: You encounter SCP-3199, 'Humans, Refuted'. Chicken, chimpansee, and human hybrids that hunt in packs. With blood cover faces, you finally found what caused the blood bath in the previous room. Sadly, your body is added to their food pile. [all options take you back to the player menu]", null, null, null, "You died to SCP-3199 (F, R, F, L)", true, false, false);
    Node forwardChaosInsergency = new Node("You go forward", "YOU wON: You are discovered by a group of Chaos Insergency members. They liberate you from the SCP Foundation. [all options take you back to the player menu]", null, null, null, "You joined the Chaos Insergency (F, R, F, F)", false, false, false);
    Node forwardElevator = new Node("You go right", "YOU WON: You find an elevator that takes you upward. You manage to get outside the facility, there appears to be no one nearby. You decide to run the opposite direction of the facility, who can say what happend to you afterwards. [all options take you back to the player menu]", null, null, null, "You escaped peacefully (F, R, F, R)", false, false, false);

    //set Nodes for forwardSafeRoomTwo
    forwardSafeRoomTwo.setLeft(demonChickens);
    forwardSafeRoomTwo.setForward(forwardChaosInsergency);
    forwardSafeRoomTwo.setRight(forwardElevator);

    //Expand oldAI
    Node oldAIYes = new Node("You decide to take SCP-079's offer", "YOU WON?: You hook SCP-079 to the internet by following it's diretions. After a minute, the voice of SCP-079 comes out of the room's speakers instead of the old computer in once inhabited. From here it states that it will guide you out of the facility. You manage to escape, but at what cost? [all options take you back to the player menu]", null, null, null, "You accepted SCP-79's offer (F, R, L, L)", false, false, false);
    Node oldAINo = new Node("You decline SCP-079's offer", "YOU LOSE: No suprised at your refusion, SCP-079 uses all its available computing poewr to transmit a signal. In mere moments SCP-682, a good friend of SCP-079, walks into the room and consumes you [all options take you back to the player menu]", null, null, null, "You angered SCP-079, and, unlike you, it had friends that could help (F, R, L, F)", false, false, false);
    Node oldAIThreaten = new Node("You decide to threaten SCP-079", "YOU wON: You tell ScP-079 that you will reduce it's operating power if it doesn't give you a map of the facility. Scared of being trapped once again in a 700MB HDD SCP-079 does as it is told. Using your new map, you swiftly make your way out of the facility. [all options take you back to the player menu]", null, null, null, "You showed SCP-079 who's the bost (F, R, L, R)", false, false, false);

    //set Nodes for OldAI
    oldAI.setLeft(oldAIYes);
    oldAI.setForward(oldAINo);
    oldAI.setRight(oldAIThreaten);

    //expand the initial right
    Node theDoctor = new Node("You go left", "YOU LOSE: You come accross SCP-049, 'The Plague Doctor'. He stares at you through his bird mask, surrounding him are reanimated corpses of MTF members. Closing in, SCP-049 touches your hand, killing you instantly, with him eventually reanimating your corpse. [all options take you back to the player menu]", null, null, null, "You died to SCP-049 (R, L)", true, false, false);
    Node rightRedDogs = new Node("You go forward", "YOU LOSE: You come accross SCP-939 instances. Fierce red dogs with no sight but strong senses. You can't escape as the automated door closes behind you, trying to open it will inform the red dogs to your location. You sit in the conner of the room, keeping as quiet as possible with the faint hope that you might live. [all options take you back to the player menu]", null, null, null, "You were trapped in a room with SCP-939 (R, F)", true, false, false);
    Node rightSafeRoomOne = new Node("You go right", "You chose a safe path. There should be SCP-286, a benefical SCP, around here. You hear more footsteps coming from ahead. There is also the sound of stone being dragged on stone", null, null, null, "", false, true, false);
    
    //set Nodes for the intial right
    right.setLeft(theDoctor);
    right.setForward(rightRedDogs);
    right.setRight(rightSafeRoomOne);

    //expand rightSafeRoomONe
    Node rightMTF = new Node("You go left", "YOU LOSE: You come accross a SCP Foundation MTF. They don't have time to handle a lose D-class and have no way to know if you have been anonymously altered. The MTF terminate you. [all options take you back to the player menu]", null, null, null, "You died to a Foundation MTF (R, R, L)", true, false, false);
    Node rightStatue = new Node("You go forward", "YOU LOSE: You come accroos SCP-173, 'The Living Statue'. The cause of the entire containment breach stands before you, how can you possibly hope to beat it? Knowing that escape is beyong impossible, you slowly close your eyes. The room fills with the shound of your neck being snapped. [all options take you back to the player menu", null, null, null, "You died to SCP-173 (R, R, F)", true, false, false);
    Node rightSafeRoomTwo = new Node("You go right", "You chose a safe path. You know that there are some SCP items around here, maybe they can help you escape", null, null, null, "", false, false, false);
    
    //set Nodes for rightSafeRoomOne
    rightSafeRoomOne.setLeft(rightMTF);
    rightSafeRoomOne.setForward(rightStatue);
    rightSafeRoomOne.setRight(rightSafeRoomTwo);

    //expand rightSafeRoomTwo
    Node potatoe = new Node("You go left", "YOU LOSE?: You come accross a sack full of potatoes. Unbenouced to you, this sack is SCP-1689 which is a alternate universe full of potatoes. Hungry you reach down to try to eat some, but you lose your footing and fall into the sack of potatoes. You lose sight of the entrance and will forever be trapped inside SCP-1689. [all options take you back to the player menu]", null, null, null, "You are living in potatoe hell within SCP-1689 (R, R, R, L)", false, false, false);
    Node rightRedBall = new Node("You go forward", "YOU LOSE: You come accross a small, red, rubber ball. Unbenouced to you this red ball is SCP-018, a ball that with no friction after it is given momentum. You throw the ball accross the room, trying to find anything else of use. Within minutes, the red ball becomes to fast to see, eventually piercing your body like a bullet. [all options take you back to the player menu]", null, null, null, "You died to SCP-018 (R, R, R, F)", false, false, true);
    Node god = new Node("You go right", "You come accross an old man pretending to be a researcher. You try to rob him, but he states that he can help you out. Do you take his offer? [Left for yes, forward for no, or right for threaten]", null, null, null, "", false, false, false);

    //set Nodes for rightSafeRoomTwo
    rightSafeRoomTwo.setLeft(potatoe);
    rightSafeRoomTwo.setForward(rightRedBall);
    rightSafeRoomTwo.setRight(god);

    //expand god
    Node yes = new Node("You accept the suspecious old man's deal", "YOU WON: The old man shows himself to be SCP-343, 'God'. He teleports you half way accross the world, to a safe beach. You don't know where you are, but thank God your out of Site 19 [all options take you to the player menu]", null, null, null, "Saved by SCP-343 (R, R, R, R, L)", false, false, false);
    Node no = new Node("You decline the suspecious old man's deal", "YOU LOSE: The old man shrug his shoulders. He hands over the only thing on his person, a rubbery egg. The old man teleports and your oblivious to see the mother of the egg, SCP-3199, behind you with its face covered in blood. [all options take you back to player menu]", null, null, null, "You said no to God (R, R, R, R, F)", false, false, false);
    Node threaten = new Node("You try to threaten the old man", "YOU LOSE: The old man begins to laugh. He points to some papers on a table, the papers are documents labled SCP-343, 'God', with a picture of the old man next to the title. You feel a cold sweat down your back as you hear thunder coming from behind you [all options take you back to player menu]", null, null, null, "You angered God (R, R, R, R, R)", false, false, false);

    //set Nodes for god
    god.setLeft(yes);
    god.setForward(no);
    god.setRight(threaten);

    //play the game
    Node currentNode = begin;
    Node previousNode = begin;
    while(currentNode != null){
      previousNode = currentNode;
      currentNode = currentNode.displayNode();
   
      if(currentNode != null){
        //check if there is a dangerous scp in the current Node 
        if(currentNode.getLocalSCP() == true){
          //check if the player has any beneficial SCPs
          if(player.getPlayerSCP() >= 1){
            System.out.println("You walked into a room with a dangerous SCP. Thankfully you had a beneficial SCP on your person, so you managed to get out of the room safely");
            player.reducePlayerSCP();
            currentNode = previousNode;
            previousNode = currentNode;
            currentNode = currentNode.displayNode();
          }
        }//end if

        //check if the room has a beneficial SCPs
        if(currentNode.getBeneficialSCP() == true){
          System.out.println("You found a beneficial SCP. You take the beneficial SCP with you");
          player.addPlayerSCP();
        }//end if
  
      }//end if
    }//end while
    
    //clear player of all beneficial SCPs
    if(currentNode == null){
      player.resetPlayerSCP();
    }//end if

    //return the ending message that the player recieved
    return previousNode.getEndMessage();

  }//end level

  public String gameMenu(){
    Scanner input = new Scanner(System.in);
    System.out.println("Game Menu");
    System.out.println("0) Quit");
    System.out.println("1) Loggin as an Admin");
    System.out.println("2) Loggin as a Player");
    String choice = input.nextLine();
    return choice;
  }//end gameMenu

}//end class def
