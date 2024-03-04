package com.ynov.jdlvbackend.socket;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Set;

@Slf4j
public class ClientHandler implements Runnable {

    public static ArrayList<ClientHandler> clientHandlerArrayList = new ArrayList<>();
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String clientUsername;
    private int thread = 0;

    public ClientHandler(Socket socket) {
        this.socket = socket;
        log.info("Opening socket, server side");
        try {
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.clientUsername = bufferedReader.readLine();
            clientHandlerArrayList.add(this);
            broadcastMessage("Server: " + clientUsername + " has entered the tchat !");
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    @Override
    public void run() {
        String messageFromClient;

        while (socket.isConnected()) {
            try {
                messageFromClient = bufferedReader.readLine();
                broadcastMessage(messageFromClient);

            } catch (IOException e) {
                closeEverything(socket, bufferedReader, bufferedWriter);
                break;
            }
        }
    }

    public void broadcastMessage(String message) {
        for (ClientHandler clientHandler : clientHandlerArrayList) {
            try {
                if (!clientHandler.clientUsername.equals(clientUsername)) {
                    if (message != null) {
                        clientHandler.bufferedWriter.write(message);
                        clientHandler.bufferedWriter.newLine();
                        clientHandler.bufferedWriter.flush();
                    } else {
                        this.closeEverything(socket, bufferedReader, bufferedWriter);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
                threadSet.iterator().next();
                closeEverything(socket, bufferedReader, bufferedWriter);
            }
        }
    }

    public void removeClientHandler() {
        clientHandlerArrayList.remove(this);
        broadcastMessage("SERVER: " + clientUsername + " has left the tchat!");
    }

    public void closeEverything(Socket socket, BufferedReader reader, BufferedWriter writer) {
        removeClientHandler();
        try {
            if (reader != null) {
                log.info("Close reader");
                reader.close();
            }
            if (writer != null) {
                log.info("Close writer");
                writer.close();
            }
            if (socket != null) {
                log.info("Close socket");
                socket.close();
            }
        } catch (IOException e) {
            log.error("closeEverything error.");
            e.printStackTrace();
        }
    }
}
