/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Benny
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class MessageReceiver implements Runnable {
    private ServerSocket serverSocket;
    private boolean running;

    public MessageReceiver(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("MessageReceiver escuchando en el puerto " + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        running = true;
        Thread thread = new Thread(this);
        thread.start();
    }

    public void stop() {
        running = false;
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (running) {
            try {
                Socket clientSocket = serverSocket.accept();
                // Manejar la conexión entrante en un hilo separado
                Thread thread = new Thread(() -> handleClientConnection(clientSocket));
                thread.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleClientConnection(Socket clientSocket) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            String message;
            while ((message = reader.readLine()) != null) {
                System.out.println("Mensaje recibido: " + message);
                // Realizar acciones con el mensaje recibido
                // ...
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

