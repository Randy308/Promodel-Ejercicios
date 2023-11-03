/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.promodel;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class SistemaPintura {

    private static final int TIEMPO_PINTURA_MINUTOS = 10;
    private static final int TIEMPO_HORNEADO_MINUTOS = 6;
    private static final int CANTIDAD_PINTORES = 2;
    private static final int CANTIDAD_HORNO = 1;
    private static final double TIEMPO_ENTRE_PROCESO_MINUTOS = 0.5;
    private static final double TIEMPO_ENTRE_PROCESO_SEGUNDOS = 30.0;
    private static final int SIMULACION_DIAS = 5;
    private static final int SIMULACION_HORAS = SIMULACION_DIAS * 24;

    private int cantidadPiezaTipo1 = 0;
    private int cantidadPiezaTipo2 = 0;
    private int totalPiezasProcesadas = 0;

    private int tiempoTotalPintura = 0;
    private int tiempoTotalHorneado = 0;
    private int tiempoTotalEsperaPintura = 0;
    private int tiempoTotalEsperaHorneado = 0;
    private int tiempoTotalSistema = 0;

    private Queue<Integer> colaPintura = new LinkedList<>();
    private Queue<Integer> colaHorneado = new LinkedList<>();
    private Random random = new Random();

    public void simular() {
        int minutosSimulacion = SIMULACION_HORAS * 60;

        for (int minutos = 0; minutos < minutosSimulacion; minutos++) {
            // Llegada de nuevas piezas al sistema
            if (minutos % (60 / 7) == 0) {
                cantidadPiezaTipo1++;
                colaPintura.add(minutos);
            }
            if (minutos % (60 / 3) == 0) {
                cantidadPiezaTipo2++;
                colaPintura.add(minutos);
            }

            // Proceso de pintura
            if (!colaPintura.isEmpty()) {
                int llegadaPieza = colaPintura.poll();
                int tiempoEsperaPintura = minutos - llegadaPieza;
                tiempoTotalEsperaPintura += tiempoEsperaPintura;
                tiempoTotalPintura += TIEMPO_PINTURA_MINUTOS;
                colaHorneado.add(minutos + TIEMPO_PINTURA_MINUTOS);
            }

            // Proceso de horneado
            if (!colaHorneado.isEmpty()) {
                
                int llegadaPieza = colaHorneado.poll();
                int tiempoEsperaHorneado = minutos - llegadaPieza;
                tiempoTotalEsperaHorneado += tiempoEsperaHorneado;
                tiempoTotalHorneado += TIEMPO_HORNEADO_MINUTOS;
            }
        }

        totalPiezasProcesadas = cantidadPiezaTipo1 + cantidadPiezaTipo2;
        tiempoTotalSistema = tiempoTotalPintura + tiempoTotalHorneado;
    }

    public void mostrarResultados() {
        double utilizacionPintura = (double) tiempoTotalPintura / (SIMULACION_HORAS * 60) / CANTIDAD_PINTORES;
        double utilizacionHorneado = (double) tiempoTotalHorneado / (SIMULACION_HORAS * 60) / CANTIDAD_HORNO;
        double tiempoPromedioPermanencia = (double) tiempoTotalSistema / totalPiezasProcesadas;
        double tiempoPromedioEsperaPintura = (double) tiempoTotalEsperaPintura / totalPiezasProcesadas;
        double tiempoPromedioEsperaHorneado = (double) tiempoTotalEsperaHorneado / totalPiezasProcesadas;

        System.out.println("a) Utilización del proceso de pintura: " + utilizacionPintura);
        System.out.println("   Utilización del proceso de horneado: " + utilizacionHorneado);
        System.out.println("b) Tiempo promedio de permanencia de las piezas en el sistema: " + tiempoPromedioPermanencia);
        System.out.println("c) Tiempo promedio de espera antes del pintado: " + tiempoPromedioEsperaPintura);
        System.out.println("   Tiempo promedio de espera antes del horneado: " + tiempoPromedioEsperaHorneado);
    }

    public static void main(String[] args) {
        SistemaPintura sistema = new SistemaPintura();
        sistema.simular();
        sistema.mostrarResultados();
    }
}
