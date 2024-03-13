package com.ynov.jdlvbackend.socket;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Set;

/**
 * Classe qui gère la réception des messages venus du tchat et qui les renvoie aux autres utilisateurs.
 */
@Slf4j
public class ClientHandler implements Runnable {
    /**
     * Liste des utilisateur connectés.
     */
    public static ArrayList<ClientHandler> clientHandlerArrayList = new ArrayList<>();
    /**
     * Constante pour afficher qu'un utilisateur est connecté au tchat.
     */
    private final String HAS_ENTER_THE_TCHAT = " has entered the tchat !";
    /**
     * Constante pour afficher qu'un utilisateur a quitté le tchat.
     */
    private final String HAS_LEFT_TCHAT = " has left the tchat!";
    /**
     * Socket
     */
    private Socket socket;
    /**
     * BufferedReader pour lire les bytes entrants.
     */
    private BufferedReader bufferedReader;
    /**
     * BufferedWriter pour écrire sous forme de bytes le message à envoyé vers le front.
     */
    private BufferedWriter bufferedWriter;
    /**
     * Login de l'utilisateur qui a écrit le message, qui est entré ou qui a quitté le tchat.
     */
    private String clientUsername;

    /**
     * Constructeur qui intitialise la Socket et qui ouvre les thread de lecture et d'écriture des messages.
     *
     * @param socket
     */
    public ClientHandler(Socket socket) {
        this.socket = socket;
        log.info("Opening socket, server side");
        try {
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.clientUsername = bufferedReader.readLine();
            clientHandlerArrayList.add(this);
            broadcastMessage(clientUsername + HAS_ENTER_THE_TCHAT);
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    /**
     * Méthode de Runnable qui permet dans un nouveau Trhead de lire et de renvoyer vers les autres utilisateurs du
     * tchat, un message.
     */
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

    /**
     * Méthode qui permet de renvoyer le message reçu aux utilisateurs autres que celui qui l'a envoyé, en se basant
     * sur le login utilisateur.
     *
     * @param message
     */
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
            } catch (Exception e) {
                e.printStackTrace();
                Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
                threadSet.iterator().next();
                closeEverything(socket, bufferedReader, bufferedWriter);
            }
        }
    }

    /**
     * Méthode pour supprimer un utilisateur de la liste des utilisateurs lors de sa déconnexion
     * et d'afficher un message un message dans le tchat qui prévient qu'il a quitté le tchat.
     */
    public void removeClientHandler() {
        clientHandlerArrayList.remove(this);
        broadcastMessage(clientUsername + HAS_LEFT_TCHAT);
    }

    /**
     * Méthode pour fermer tous les Threads de socket, de reader et de writer.
     *
     * @param socket
     * @param reader
     * @param writer
     */
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
