/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.promodel;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author M S I
 */
public class EjercicioPintura {

    private static final int CANTIDAD_PIEZAS_HORA = 10;
    private static final int CANTIDAD_PIEZAS_TIPO_1_HORA = 7;
    private static final int CANTIDAD_PIEZAS_TIPO_2_HORA = 3;
    private static final int CANTIDAD_PIEZAS_PINTADAS_HORA = 6;
    private static final int CANTIDAD_PIEZAS_EN_EL_HORNO_HORA = 10;
    private static final double TIEMPO_ENTRE_PROCESO_MINUTOS = 0.5;
    private static final int CANTIDAD_PINTORES = 2;
    private static final int CANTIDAD_HORNOS = 1;
    private static final int DIAS_SIMULACION = 5;
    private static final int HORAS_SIMULACION = 24 * DIAS_SIMULACION;

    private Queue<Integer> queuePrimerPintor = new LinkedList<>();
     private Queue<Integer> queueSegundoPintor = new LinkedList<>();
    private Queue<Integer> queueAnterior = new LinkedList<>();
    private Queue<Integer> queueHorno = new LinkedList<>();
    private List<Integer> lista = new ArrayList<Integer>();
    private int cantidadPieza1 = 0;
    private int cantidadPieza2 = 0;
    private int cantidadFinalPiezas = 0;

    double tiempoTotalLimpieza = 0.0;
    double tiempoTotalEspera = 0.0;
    double tiempoTotalEsperaAnt = 0.0;
    double tiempoPermanencia = 0.0;
    double tOcioMinutos = 0.0;
    double tLLegadaPiezasMinutos = 60.0;
    int inventarioPiezas = 60;
    int piezasLimpiadas = 0;
    double tiempoTotalLimpiezaAnterior = 0.0;

    public void simular() {
        for (int horas = 0; horas < HORAS_SIMULACION; horas++) {

            for (int minutos = 0; minutos < 60; minutos++) {
                if (minutos != 0) {
                    if (minutos % (60 / CANTIDAD_PIEZAS_TIPO_1_HORA) == 0) {
                        lista.add(horas * 60 + minutos);
                        cantidadPieza1 += 1;
                    }
                    if (minutos % (60 / CANTIDAD_PIEZAS_TIPO_2_HORA) == 0) {
                        lista.add(horas * 60 + minutos);
                        cantidadPieza2 += 1;
                    }

                }

            }

        }
        for (int i = 0; i < lista.size()-1; i+=2) {
            queuePrimerPintor.add(lista.get(i));
            queueSegundoPintor.add(lista.get(i+1));
        }
        
        cantidadFinalPiezas = cantidadPieza1 + cantidadPieza2;
    }

    public int getCantidadPieza1() {
        return cantidadPieza1;
    }

    public int getCantidadPieza2() {
        return cantidadPieza2;
    }

    public int getCantidadFinalPiezas() {
        return cantidadFinalPiezas;
    }


    public Queue<Integer> getQueuePrimerPintor() {
        return queuePrimerPintor;
    }

    public Queue<Integer> getQueueSegundoPintor() {
        return queueSegundoPintor;
    }

    public Queue<Integer> getQueueAnterior() {
        return queueAnterior;
    }

    public Queue<Integer> getQueueHorno() {
        return queueHorno;
    }

    public static void main(String[] args) {
        EjercicioPintura ejp = new EjercicioPintura();
        ejp.simular();
        System.out.println(ejp.getCantidadPieza1());
        System.out.println(ejp.getCantidadPieza2());
        System.out.println(ejp.getCantidadFinalPiezas());
        System.out.println(ejp.getQueuePrimerPintor());
        System.out.println(ejp.getQueueSegundoPintor());
    }
}
