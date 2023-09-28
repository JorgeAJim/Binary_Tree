Game.class: Game.java
	javac -g Game.java

BasicGame.class: BasicGame.java
	javac -g BasicGame.java

Node.class: Node.java
	javac -g Node.java

Player.class: Player.java
	javac -g Player.java

Admin.class: Admin.java
	javac -g Admin.java

clean:
	rm *.class

run: Game.class
	java Game
