# Scrabble Game  
[![jdk](https://img.shields.io/badge/jdk-1.8-brightgreen.svg)](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
[![maven](https://img.shields.io/badge/maven-3.5%2B-brightgreen.svg)](https://maven.apache.org/download.cgi)  

## Introduction  
Scrabble is a very popular crossword puzzle, multiple players place letters on one board to form a word to earn points, and the player with the highest total score will win the game. The purpose of this project is to develop a multiplayer online Scrabble game include the server and client by using Java programming language. The player opens the client to connect server and plays the game with other online players. Our design allowed multiplayer play multi-game at the same time. The system will automatically handle the player's actions and push the game's progress.  
The application was developed by using the concept of sockets for communication between the different clients and the game server. TCP Sockets are used for communication. The concurrency in the game is achieved through multi-threading. The client has two thread and the server has multi-thread which is the thread-per-connection structure. Threads help in executing one or more tasks concurrently in a java program. The message passing between different clients and the game server is IO objects stream, implemented by sending objects through sockets using the concept of serialization. The error handling is implemented through Exception handling in Java. And the Graphical User Interface is implemented using JavaFX and Cascading Style Sheets.  

## Architecture  
The system architecture is Client-Server architecture and used the classic two-tier model.  The Client has two thread one thread is for GUI and catches user’s operation then send to the Server, the other one is listening to the response from the Server and represent on the GUI. The Server used multi-thread, the server threading architectures is thread-per-connection. The main thread will always listen for the connection request, once the client connects to the server, the server will create a new thread for the client. The client will interact with the thread for it on the server. The broadcast will be used on Server to transfer message to Client.  

## Contribution
* [Shailee]
* [Ethan]
* [Jun]
* [Eric]
* [James]
