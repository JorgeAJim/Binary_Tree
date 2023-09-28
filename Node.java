//Node.java

import java.util.*;
import java.io.*;

public class Node implements Serializable{
  protected static int counter;
  protected int id;
  protected String title;
  protected String description;
  //protected String forwardPath;
  protected Node left;
  //protected String leftPath;
  protected Node forward;
  //protected String rightPath;
  protected Node right;
  protected String endMessage;
  protected boolean localSCP;
  protected boolean beneficialSCP;
  protected boolean hasTrap;

  protected Node next;

  public Node(String title, String description, Node left, Node forward, Node right, String endMessage, boolean localSCP, boolean beneficialSCP, boolean hasTrap){
    this.calcID();
    this.title = title;
    this.description = description;
    this.left = left;
    this.forward = forward;
    this.right = right;
    this.endMessage = endMessage;
    this.localSCP = localSCP;
    this.beneficialSCP = beneficialSCP;
    this.hasTrap = hasTrap;
    this.next = null;
  }//end constructor

  public void calcID(){
    counter++;
    this.id = counter;
  }//end calcID

  public int getID(){
    return this.id;
  }//end getID

  public Node displayNode(){
    Scanner input = new Scanner(System.in);
    //get the current next Node
    Node next = this;

    System.out.println(this.title);
    System.out.println(this.description);
    System.out.println("Go (L)eft");
    System.out.println("Go (F)orward");
    System.out.println("Go (R)ight");
    String choice = input.nextLine();
    
    if(choice.equalsIgnoreCase("L")){
      next = left;
    } else if(choice.equalsIgnoreCase("F")){
      next = forward;
    } else if(choice.equalsIgnoreCase("R")){
      next = right;
    } else{
      System.out.println("Please choose L, F, or R");
    }//end if

    return next;
  }//end displayNode

  public void setForward(Node forward){
    this.forward = forward;
  }//end setForward

  public void setLeft(Node left){
    this.left = left;
  }//end setLeft

  public void setRight(Node right){
    this.right = right;
  }//end setRight

  public String getEndMessage(){
    return this.endMessage;
  }//end getEndMessage

  public boolean getLocalSCP(){
    return this.localSCP;
  }//end localSCP

  public boolean getBeneficialSCP(){
    return this.beneficialSCP;
  }//end getBeneficialSCP
  
  public boolean getHasTrap(){
    return this.hasTrap;
  }//end getHasTrap

  public Node getNext(){
    return this.next;
  }//end getNext

  public void setNext(Node next){
    this.next = next;
  }//end setNext

}//end class def
