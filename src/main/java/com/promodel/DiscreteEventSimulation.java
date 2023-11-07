/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.promodel;
import java.util.PriorityQueue;

class Event implements Comparable<Event> {
    double time;
    EventType type;

    public Event(double time, EventType type) {
        this.time = time;
        this.type = type;
    }

    @Override
    public int compareTo(Event other) {
        return Double.compare(this.time, other.time);
    }
}

enum EventType {
    ARRIVAL, DEPARTURE
}

public class DiscreteEventSimulation {

    public static void main(String[] args) {
        PriorityQueue<Event> eventQueue = new PriorityQueue<>();
        double clock = 0.0;
        int customersInSystem = 0;
        double totalWaitingTime = 0.0;
        int maxQueueSize = 3; // Capacidad máxima de la cola

        double simulationTime = 60.0; // Tiempo total de simulación en minutos
        double lambda = 2.0; // Tasa de llegada (clientes por minuto)
        double mu = 3.0;    // Tasa de servicio (clientes por minuto)

        while (clock < simulationTime) {
            double arrivalTime = clock + exponentialRandom(lambda);
            System.out.println(customersInSystem);
            if (customersInSystem < maxQueueSize) {
                eventQueue.add(new Event(arrivalTime, EventType.ARRIVAL));
                clock = arrivalTime;
                customersInSystem++;
            } else {
                System.out.println("No hay espacio en la cola para la llegada en el tiempo " + arrivalTime);
            }

            if (customersInSystem == 1) {
                double serviceTime = exponentialRandom(mu);
                eventQueue.add(new Event(clock + serviceTime, EventType.DEPARTURE));
            }

            Event event = eventQueue.poll();

            if (event.type == EventType.ARRIVAL) {
                customersInSystem--;
            } else if (event.type == EventType.DEPARTURE) {
                customersInSystem--;
                if (customersInSystem > 0) {
                    double serviceTime = exponentialRandom(mu);
                    eventQueue.add(new Event(clock + serviceTime, EventType.DEPARTURE));
                }
                totalWaitingTime += clock - event.time;
            }
        }

        double averageWaitingTime = totalWaitingTime / (maxQueueSize + 1); // Considerando el cliente en servicio
        System.out.println("Average Waiting Time: " + averageWaitingTime + " minutes");
    }

    public static double exponentialRandom(double rate) {
        return -Math.log(1 - Math.random()) / rate;
    }
}
