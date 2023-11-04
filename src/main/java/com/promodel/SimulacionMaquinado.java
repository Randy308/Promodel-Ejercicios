/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.promodel;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author M S I
 */
public class SimulacionMaquinado {
    public static void main(String[] args) {
        TipoPieza tipo1 = new TipoPieza(3, 2);
        TipoPieza tipo2 = new TipoPieza(5, 4);
        TipoPieza tipo3 = new TipoPieza(10, 2);

        Maquinado maquina = new Maquinado();

        int tiempoSimulacion = 100;  // Duración de la simulación en horas
        int tiempoTotalPiezas = 0;
        int piezasProducidas = 0;
        int tiempoEsperaTotal = 0;
        int piezasEnAlmacen = 0;

        Queue<TipoPieza> cola = new LinkedList<>();

        for (int tiempo = 0; tiempo < tiempoSimulacion * 60; tiempo++) {
            // Generar eventos de llegada de piezas
            if (tiempo % 60 == 0) {
                cola.add(tipo1); // Ejemplo: Tipo 1 llega cada hora
            }

            // Procesar piezas en la máquina
            if (!maquina.estaOcupada() && !cola.isEmpty()) {
                TipoPieza tipoActual = cola.poll();
                maquina.procesarPieza(tipoActual);
                tiempoTotalPiezas += tipoActual.tiempoMaquinado;
                piezasProducidas++;
            }

            // Avanzar el tiempo de la máquina
            maquina.avanzarTiempo();

            // Actualizar estadísticas
            if (!cola.isEmpty()) {
                tiempoEsperaTotal += cola.size();
            }
            piezasEnAlmacen += cola.size();
        }

        double utilizacionMaquinado = (double) tiempoTotalPiezas / (tiempoSimulacion * 60);
        double tiempoPromedioEspera = (double) tiempoEsperaTotal / piezasProducidas;
        double promedioPiezasEnAlmacen = (double) piezasEnAlmacen / (tiempoSimulacion * 60);

        System.out.println("Utilización del centro de maquinado: " + utilizacionMaquinado);
        System.out.println("Número total de piezas producidas: " + piezasProducidas);
        System.out.println("Tiempo promedio de espera de las piezas en el almacén: " + tiempoPromedioEspera);
        System.out.println("Número promedio de piezas en el almacén: " + promedioPiezasEnAlmacen);
    }
}
