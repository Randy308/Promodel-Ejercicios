/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.promodel;

/**
 *
 * @author M S I
 */
import java.util.PriorityQueue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

class Evento implements Comparable<Evento> {

    double time;
    int priority;
    String description;

    public Evento(double time, int priority, String description) {
        this.time = time;
        this.priority = priority;
        this.description = description;
    }

    @Override
    public int compareTo(Evento other) {
        
        if (this.time < other.time) {
            return -1;
        } else if (this.time > other.time) {
            return 1;
        } else {
            if (this.priority < other.priority) {
                return -1;
            } else if (this.priority > other.priority) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    public static void main(String[] args) {
        ArrayBlockingQueue<Evento> eventQueue = new ArrayBlockingQueue<>(4);

        Random random = new Random();

        double currentTime = 0;
        int cantidadClientes = 0;
        int cantidadClientesRechazados = 0;
        int N = 3;
        double tiempoSimulacion = 24 * 60; //minutos
        while (tiempoSimulacion > currentTime) {

            if (eventQueue.remainingCapacity() == 0) {
                System.out.println("La cola de eventos ya está llena. No se agregará el nuevo evento.");
                
            } else {
                double timeToNextEvent = random.nextDouble() * 10;
                currentTime += timeToNextEvent;
                eventQueue.add(new Evento(currentTime, 0, "Un evento importante"));
            }


            tiempoSimulacion--;
        }
    }

   
}
