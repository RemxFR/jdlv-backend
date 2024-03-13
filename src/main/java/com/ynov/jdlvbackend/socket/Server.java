package com.ynov.jdlvbackend.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Classe qui gère la partie Socket pour se connecter avec la Socket du front.
 */
public class Server {
    /**
     * ServerSocket pour se connecter via la technologie des sockets.
     */
    private ServerSocket serverSocket;

    /**
     * Constructeur qui déclare le serverSocket utilisé.
     *
     * @param serverSocket
     */
    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    /**
     * Méthode qui démarre le server et crée un nouveau thread pour chaque nouvel utilisateur connecté.
     */
    public void startServer() {

        try {
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(socket);

                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Méthode pour fermer le serveurSocket.
     */
    public void closeServerSocket() {
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
