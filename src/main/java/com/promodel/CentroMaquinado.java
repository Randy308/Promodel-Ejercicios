/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.promodel;

import java.util.Random;

/**
 *
 * @author User
 */
public class CentroMaquinado {

    public static void main(String[] args) {
        int clientsArrived = 0;
        int clientsInQueue = 0;
        int maxClientsInQueue = 3;
        double timeWaiting = 0;
        double revenue = 0;
        double timeUsed = 0;
        Random random = new Random();

        for (int hour = 0; hour < 40; hour++) {
            // New client arrives
            if (random.nextDouble() < 1.0 / 300) { // A client arrives each 5 minutes on average
                clientsArrived++;

                // Queue management
                if (clientsInQueue < maxClientsInQueue) {
                    clientsInQueue++;
                } else {
                    // Send client to another copy center
                    clientsArrived--;
                }
            }

            // Operator service
            if (clientsInQueue > 0 && random.nextDouble() < 1.0 / 600) { // An operator can serve a client each 6 minutes on average
                clientsInQueue--;
                timeWaiting += 5; // Each client waits 5 minutes on average
                timeUsed += 6; // Each client is served in 6 minutes on average
                revenue -= 5; // Each client spends $5
            }
        }

        double averageClientsInQueue = (double) timeWaiting / clientsArrived;
        double utilization = (double) timeUsed / (40 * 60);
        double expectedLoss = (double) revenue / clientsArrived;

        System.out.println("Average number of clients that wait in the queue: " + averageClientsInQueue);
        System.out.println("Utilization of the copy center: " + utilization);
        System.out.println("Expected loss: $" + expectedLoss);
    }
}
