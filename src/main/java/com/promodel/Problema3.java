/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.promodel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import java.util.Random;

public class Problema3 {

    public void simulacion(int horasSimulacionTotal, double lambda_1, double lambda_2, double lambda_3, double mu_1, double mu_2, double mu_3) {
        double clock = 0.0;
        Queue<TipoPieza> cola = new LinkedList<>();
        while (horasSimulacionTotal > clock) {
            // Calcular el tiempo de llegada de las tres piezas
            ArrayList<Double> lista = new ArrayList<>();

            double arriboPieza1 = clock + exponencial(lambda_1);
            double arriboPieza2 = clock + exponencial(lambda_2);
            double arriboPieza3 = clock + exponencial(lambda_3);
            lista.add(arriboPieza1);
            lista.add(arriboPieza3);
            lista.add(arriboPieza2);
            Collections.sort(lista);
            // Determinar cuál pieza llega primero
            double minArribo = Math.min(arriboPieza1, Math.min(arriboPieza2, arriboPieza3));
            clock = minArribo;
            if (minArribo == arriboPieza1) {
                double tiempoAtencion = exponencial(mu_1);
                cola.add(new TipoPieza(tiempoAtencion, minArribo, 1));
            } else if (minArribo == arriboPieza2) {
                double tiempoAtencion = exponencial(mu_2);
                cola.add(new TipoPieza(tiempoAtencion, minArribo, 2));
            } else {
                double tiempoAtencion = exponencial(mu_3);
                cola.add(new TipoPieza(tiempoAtencion, minArribo, 3));
            }

        }
        System.out.println("Piezas totales recibidas " + cola.size());
        double servicioAnterior = 0;
        int piezasRechazadas = 0;
        int piezasAcabadas = 0;
        double tiempoEspera = 0.0;
        double usoCentroMaquinado = 0.0;
        while (!cola.isEmpty()) {
            TipoPieza tipoPieza = cola.poll();
            if (tipoPieza != null) {
                double tiempoLlegada = tipoPieza.tiempoEntrada;
                double tInicioServicio;
                double tiempoServicio = tipoPieza.tiempoMaquinado;
                if (servicioAnterior > tiempoLlegada) {
                    tInicioServicio = servicioAnterior;
                } else {
                    tInicioServicio = tiempoLlegada;
                }
                tiempoEspera += tInicioServicio - tiempoLlegada;
                servicioAnterior = tInicioServicio + tiempoServicio;
                if (servicioAnterior < horasSimulacionTotal) {
                    piezasAcabadas++;
                    usoCentroMaquinado += tiempoServicio;
                } else {
                    piezasRechazadas++;
                }

            }

        }
        int piezasTotales = (piezasAcabadas + piezasRechazadas);
        double piezasPromedio = (double) piezasAcabadas / piezasTotales;
        String[] headers = {"Inciso", "Respuesta"};
        List<List<String>> data = new ArrayList<>();

        data.add(Arrays.asList("Piezas Total Piezas Producidas ", Integer.toString(piezasAcabadas)));
        data.add(Arrays.asList("Piezas Rechazadas por limite del tiempo de simulacion ", Integer.toString(piezasRechazadas)));
        data.add(Arrays.asList("Tiempo de espera promedio ", String.format("%,.4f", tiempoEspera / piezasAcabadas)));
        data.add(Arrays.asList("Promedio de piezas en el almacen ", String.format("%,.1f", (100 * piezasPromedio)) + " %"));
        data.add(Arrays.asList("Uso del centro de maquinado ", String.format("%,.4f", (100 * (usoCentroMaquinado / horasSimulacionTotal))) + " %"));

        TablaSimulacion tb = new TablaSimulacion();
        tb.crearTablaSimulacion(headers, data);

    }

    public double exponencial(double lambda) {
        return -Math.log(1 - new Random().nextDouble()) * lambda;
    }

    public static void main(String[] args) {
        Problema3 simulacion = new Problema3();
        int horasSimulacionTotal = 6000;
        // Tasas de llegada (piezas por minuto)
        double lambda_1 = 30.0; // Una pieza cada 30 minutos en promedio
        double lambda_2 = 15.0; // Una pieza cada 15 minutos en promedio
        double lambda_3 = 30.0; // Una pieza cada 30 minutos en promedio

        // Tiempos de atención (minutos por pieza)
        double mu_1 = 3.0; // Tiempo de atención promedio para la pieza 1
        double mu_2 = 5.0; // Tiempo de atención promedio para la pieza 2
        double mu_3 = 10.0; // Tiempo de atención promedio para la pieza 3
        simulacion.simulacion(horasSimulacionTotal, lambda_1, lambda_2, lambda_3, mu_1, mu_2, mu_3);
    }
}
