/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.promodel;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 *
 * @author User
 */
public class ProcesoPintura {

    private static final double TIEMPO_LLEGADA_PIEZAS_TIPO_1_POR_MINUTO = 8.6;
    private static final double TIEMPO_LLEGADA_PIEZAS_TIPO_2_POR_MINUTO = 20.0;
    private static final int TIEMPO_PINTURA_MINUTO = 10; // 50 - 10
    private static final int TIEMPO_HORNEADO_MINUTO = 6;
    private static final int CANTIDAD_PINTORES = 2; // 50 - 10
    private static final int CANTIDAD_HORNO = 1;
    private static final double TIEMPO_ENTRE_PROCESO_MINUTOS = 0.5; // 50 + 10
    private static final int HORAS_SIMULACION = 120;

    private int piezasTerminadas = 0;
    private int minutosSimulacion = HORAS_SIMULACION * 60;
    private int tiempoTotalPintura = 0;
    private int tiempoTotalHorneado = 0;
    private int tiempoTotalEspera = 0;
    private int cantidadPiezaTipoUno = 0;
    private int cantidadPiezaTipoDos = 0;
    private int cantidadTotalPiezas = 0;

    public int getCantidadPiezaTipoUno() {
        return cantidadPiezaTipoUno;
    }

    public void setCantidadPiezaTipoUno(int cantidadPiezaTipoUno) {
        this.cantidadPiezaTipoUno = cantidadPiezaTipoUno;
    }

    public int getCantidadPiezaTipoDos() {
        return cantidadPiezaTipoDos;
    }

    public void setCantidadPiezaTipoDos(int cantidadPiezaTipoDos) {
        this.cantidadPiezaTipoDos = cantidadPiezaTipoDos;
    }

    public int getCantidadTotalPiezas() {
        return cantidadTotalPiezas;
    }

    public void setCantidadTotalPiezas(int cantidadTotalPiezas) {
        this.cantidadTotalPiezas = cantidadTotalPiezas;
    }

    public Queue<Double> getQueue() {
        return queue;
    }

    public void setQueue(Queue<Double> queue) {
        this.queue = queue;
    }
    private boolean isPintorUnoOcupado = false;
    private boolean isPintorDosOcupado = false;
    Queue<Double> queue = new LinkedList<>();

    public void simular() {
        Random rand = new Random();

        for (var minutos = 0.0; minutos < minutosSimulacion; minutos = minutos + 0.1) {

            if (minutos % TIEMPO_LLEGADA_PIEZAS_TIPO_2_POR_MINUTO == 0) {
                cantidadPiezaTipoDos += 1;
                queue.add(minutos);
            }
            if (minutos % TIEMPO_LLEGADA_PIEZAS_TIPO_1_POR_MINUTO == 0) {
                cantidadPiezaTipoUno += 1;
                queue.add(minutos);
            }

            cantidadTotalPiezas += cantidadPiezaTipoDos + cantidadPiezaTipoDos;

            if (isPintorUnoOcupado && isPintorDosOcupado) {

            } else if (isPintorUnoOcupado || !isPintorDosOcupado) {

            } else if (!isPintorUnoOcupado || isPintorDosOcupado) {

            } else {

            }

        }
    }

    public static void main(String[] args) {
        ProcesoPintura proceso = new ProcesoPintura();
        proceso.simular();
        System.out.println(proceso.getCantidadPiezaTipoDos());
        System.out.println(proceso.getCantidadPiezaTipoUno());
        System.out.println(proceso.getCantidadTotalPiezas());
        System.out.println(proceso.getQueue());
    }
}
