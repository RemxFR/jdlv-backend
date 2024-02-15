package com.ynov.jdlvbackend;

import com.ynov.jdlvbackend.socket.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.ServerSocket;

@SpringBootApplication
public class JdlvBackendApplication {

	public static void main(String[] args) throws IOException {

		SpringApplication.run(JdlvBackendApplication.class, args);
		ServerSocket serverSocket = new ServerSocket(1234);
		Server server = new Server(serverSocket);
		server.startServer();
	}

}
