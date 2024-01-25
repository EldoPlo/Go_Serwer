package com.example.serwer;
import java.io.IOException;
import java.net.ServerSocket;

import java.util.concurrent.TimeUnit;


import com.example.serwer.MessagefromServer.NewGame;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;



@Component
public class Server {

    //private final ExecutorService executorService = Executors.newFixedThreadPool(10); // Adjust the pool size as needed




    @EventListener
    public void prepareToGame(ContextRefreshedEvent event) {
        ServerConnection serverConnection = null;
        ServerSocket socket = null;
        try {
            socket = new ServerSocket(3311);
            serverConnection = new ServerConnection(socket);
            TimeUnit.SECONDS.sleep(1);
            serverConnection.sendMessage(new NewGame(null));

            // Pętla do ciągłego odbierania wiadomości od klienta
            while (true) {
                Object line = serverConnection.getMessage();

                // Tutaj możesz przetwarzać otrzymaną wiadomość
//                if (receivedMessage instanceof NewGame) {
//                    NewGame newGame = (NewGame) receivedMessage;
//                    int[] gamesIdList = newGame.getGamesIdList();
//                }
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            if (serverConnection != null) {
                serverConnection.close();
            }
        }


}}



