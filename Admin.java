//Admin.java

import java.util.*;
import java.io.*;

public class Admin implements Serializable{

  protected int accountNumber;
  protected int pin;
  protected ArrayList<Player> players = new ArrayList<Player>();

  public Admin(int accountNumber, int pin){
    this.accountNumber = accountNumber;
    this.pin = pin;
  }//end constructor

  public int getPin(){
    return this.pin;
  }//end getPin

  public int getAccountNumber(){
    return this.accountNumber;
  }//end getAccountNumber

  public ArrayList<Player> getArrayList(){
    return this.players;
  }//end getArrayList

  public void setArrayList(ArrayList<Player> players){
    this.players = players;
  }//end setArrayList

  public void addPlayer(int accountNumber, int pin){
    boolean used = false;
    for(int i = 0; i < players.size(); i++){
      if(players.get(i).getAccountNumber() == accountNumber){
        used = true;
      }//end if
    }//end for

    if(used == true){
      System.out.println("Account Number is already taken");
    } else {
      Player newPlayer = new Player(accountNumber, pin);
      players.add(newPlayer);
    }//end if
  }//end addUser

  public void listAllPlayers(){
    for(int i = 0; i < players.size(); i++){
      System.out.println("Account Number: " + players.get(i).getAccountNumber() + " Pin: " + players.get(i).getPin());
    }//end for
  }//end listAllPlayers

  public String adminMenu(){
    Scanner input = new Scanner(System.in);
    System.out.println("Access: Adminostrator. Please select a menu option");
    System.out.println("0) Loggout");
    System.out.println("1) Add player");
    System.out.println("2) List all players");
    String choice = input.nextLine();
    return choice;
  }//end adminMenu

}//end class def
