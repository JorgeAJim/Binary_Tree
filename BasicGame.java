//BasicGame.java


import java.util.*;

public class BasicGame{
  Player tester = new Player(00000, 12345);

  public static void main(String[] args){
    Node.counter = 0;
    BasicGame bg = new BasicGame();
    bg.sampleGame();
  }//end main

  public void sampleGame(){
    Node begin = new Node("Begin", "Go left, go forward to lose, and go right to get lost", null, null, null, "", false, false, false);
    Node left = new Node("Win", "You won the game. Go forward to retry, go left or right to exit", null, begin, null, "You managed to survive", false, false, false);
    Node forward = new Node("Lose", "You died. Go forward to retry, go left or right to exit", null, begin, null, "You died to SCP-682, how unlucky", true, false, false);
    Node right = new Node("You found a beneficial SCP", "You hear stone being dragged on stone nearby", null, null, null, "", false, true, false);
    Node survive = new Node("You found the exit", "Lady Luck shined upon you. Go forward to retry, or go left or right to exit", null, begin, null, "You survived", false, false, false);
    Node stoneMonster = new Node("You ran into SCP-173", "The cause of this disaster stands before you, how long can you go without blinking?", null, null, null, "You died to SCP-172", true, false, false);

    begin.setLeft(left);
    begin.setForward(forward);
    begin.setRight(right);

    right.setLeft(survive);
    right.setForward(stoneMonster);
    right.setRight(survive);

    //play the game
    Node currentNode = begin;
    Node previousNode = begin;
    while(currentNode != null){
      previousNode = currentNode;
      currentNode = currentNode.displayNode();
  
      if(currentNode != null){
        if(currentNode.getLocalSCP() == true){
          if(tester.getPlayerSCP() >= 1){
            System.out.println("You walked into a room with a dangerous SCP. Thankfully you had a beneficial SCP on your person, so you managed to get to the previous room safely");
            tester.reducePlayerSCP();
            currentNode = previousNode;
            previousNode = currentNode;
            currentNode = currentNode.displayNode();
          }//end if
        }//end if

        if(currentNode.getBeneficialSCP() == true){
          System.out.println("You take the beneficial SCP with you");
          tester.addPlayerSCP();
        }//end if
      }//end if
    }//end while

    //at the end show the endMessage and boolean value of the node previous to the null
    System.out.println("End Message: " + previousNode.getEndMessage());
    System.out.println("Was there a dangerous SCP? " + previousNode.getLocalSCP());
    System.out.println("Was there a beneficial SCP? " + previousNode.getBeneficialSCP());

  }//end sampleGame

  
  

}//end class def
