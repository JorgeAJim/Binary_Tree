//Player.java

import java.util.*;
import java.io.*;

public class Player implements Serializable{

  protected int accountNumber;
  protected int pin;
  protected ArrayList<String> dataLog = new ArrayList<String>();
  protected int playerSCP;

  public Player(int accountNumber, int pin){
    this.accountNumber = accountNumber;
    this.pin = pin;
    this.playerSCP = 0;
  }//end construtor

  public int getPin(){
    return this.pin;
  }//end getPin

  public int getAccountNumber(){
    return this.accountNumber;
  }//end getAccountNumber

  public ArrayList<String> getArrayList(){
    return dataLog;
  }//end getArrayList

  public void setArrayList(ArrayList<String> log){
    this.dataLog = log;
  }//end setArrayList

  public void addLog(String message){
    dataLog.add(message);
  }//end addLog

  public void printLog(){
    for(int i = 0; i < dataLog.size(); i++){
      System.out.println(dataLog.get(i));
    }//end for
  }//end printLog

  public int getPlayerSCP(){
    return this.playerSCP;
  }//end getPlayerSCP

  public void reducePlayerSCP(){
    this.playerSCP -= 1;
    if(this.playerSCP < 0){
      this.playerSCP = 0;
    }//end if
  }//end recudePlayerSCP

  public void addPlayerSCP(){
    this.playerSCP += 1;
  }//end addPlayerSCP

  public void resetPlayerSCP(){
    this.playerSCP = 0;
  }//end clearAllSCP

  public String playerMenu(){
    Scanner input = new Scanner(System.in);
    System.out.println("Access: D-Class personal. Choose an option");
    System.out.println("0) Loggout");
    System.out.println("1) Play");
    System.out.println("2) View Player Data Log");
    String choice = input.nextLine();
    return choice;
  }//end playerMenu

}//end class def
