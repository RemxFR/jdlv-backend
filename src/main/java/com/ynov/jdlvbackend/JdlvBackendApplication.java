package com.ynov.jdlvbackend;

import com.ynov.jdlvbackend.dto.service.ChiffrageService;
import com.ynov.jdlvbackend.socket.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.ServerSocket;

@SpringBootApplication
public class JdlvBackendApplication {
    /**
     * Main méthode de l'application qui définie le port du serveur en ce qui concerne la partie socket.
     * Cette méthode démarre également le server.
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        SpringApplication.run(JdlvBackendApplication.class, args);
        ServerSocket serverSocket = new ServerSocket(1234);
        Server server = new Server(serverSocket);
        server.startServer();
        ChiffrageService.getInstance(null);
    }

}
