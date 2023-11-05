/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.promodel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import java.util.Random;

public class ProblemaMaquinado {

    // Tasas de llegada (piezas por minuto)
    double lambda_1 = 1 / 30.0; // Una pieza cada 30 minutos en promedio
    double lambda_2 = 1 / 15.0; // Una pieza cada 15 minutos en promedio
    double lambda_3 = 1 / 30.0; // Una pieza cada 30 minutos en promedio

    // Tiempos de atención (minutos por pieza)
    double mu_1 = 3.0; // Tiempo de atención promedio para la pieza 1
    double mu_2 = 5.0; // Tiempo de atención promedio para la pieza 2
    double mu_3 = 10.0; // Tiempo de atención promedio para la pieza 3

    public void simulacion(int horasSimulacionTotal) {
        double clock = 0.0;
        double minutos = 0;
        Queue<TipoPieza> cola = new LinkedList<>();
        while (horasSimulacionTotal > clock) {
            // Calcular el tiempo de llegada de las tres piezas
            ArrayList<Double> lista = new ArrayList<>();
            if (minutos % 30 == 0) {
                double arriboPieza1 = clock + exponencial(lambda_1);

                double arriboPieza3 = clock + exponencial(lambda_3);
                lista.add(arriboPieza1);

                lista.add(arriboPieza3);
            }
            if (minutos % 15 == 0) {
                double arriboPieza2 = clock + exponencial(lambda_2);
                lista.add(arriboPieza2);
            }

            Collections.sort(lista);
            // Determinar cuál pieza llega primero
            //double maxArribo = Math.max(arriboPieza1, Math.max(arriboPieza2, arriboPieza3));
/*
            while (!lista.isEmpty()) {
                var tiempoEntrada = lista.remove(0);
                if (tiempoEntrada == arriboPieza1) {
                    double tiempoAtencion = exponencial(mu_1);
                    System.out.println("Pieza 1 llega en " + tiempoEntrada + " minutos");
                    cola.add(new TipoPieza(tiempoAtencion, tiempoEntrada, 1));
                } else if (tiempoEntrada == arriboPieza2) {
                    double tiempoAtencion = exponencial(mu_2);
                    System.out.println("Pieza 2 llega en " + tiempoEntrada + " minutos");
                    cola.add(new TipoPieza(tiempoAtencion, tiempoEntrada, 2));
                } else {
                    double tiempoAtencion = exponencial(mu_3);
                    System.out.println("Pieza 3 llega en " + tiempoEntrada + " minutos");
                    cola.add(new TipoPieza(tiempoAtencion, tiempoEntrada, 3));
                }

            }*/
            clock ++;

        }
        System.out.println("Piezas totales recibidas " + cola.size());
        for (TipoPieza tipoPieza : cola) {
            System.out.println("Pieza " + tipoPieza.tipoPieza + " atencion total " + (tipoPieza.tiempoMaquinado + tipoPieza.tiempoEntrada));
        }
    }

    public double exponencial(double lambda) {
        return -Math.log(1 - new Random().nextDouble()) * lambda;
    }

    public static void main(String[] args) {
        ProblemaMaquinado simulacion = new ProblemaMaquinado();
        simulacion.simulacion(6000); // Simular durante 100 horas (6000 minutos)
    }
}
