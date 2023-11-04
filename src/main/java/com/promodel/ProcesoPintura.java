/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.promodel;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public class ProcesoPintura {

    private static final int TIEMPO_LLEGADA_PIEZA_TIPO_1_EN_SEGUNDOS = 516;
    private static final int TIEMPO_LLEGADA_PIEZA_TIPO_2_EN_SEGUNDOS = 1200;
    private static final int TIEMPO_PINTURA_SEGUNDOS = 600;
    private static final int TIEMPO_HORNEADO_SEGUNDOS = 360;
    private static final int CANTIDAD_PINTORES = 2;
    private static final int CANTIDAD_HORNO = 1;
    private static final int TIEMPO_ENTRE_PROCESO_SEGUNDOS = 30;
    private static final int HORAS_SIMULACION = 120;

    private int piezasTerminadas = 0;
    private int tiempoSimulacionSegundos = HORAS_SIMULACION * 60 * 60;
    private int tiempoTotalPintura = 0;
    private int tiempoTotalHorneado = 0;
    private int tiempoTotalEspera = 0;
    private int cantidadPiezaTipoUno = 0;
    private int cantidadPiezaTipoDos = 0;
    private int cantidadTotalPiezas = 0;

    private Queue<Integer> queue = new LinkedList<>();
    private Queue<Integer> queueAnterior = new LinkedList<>();
    private Queue<Integer> queueHorno = new LinkedList<>();
    private Random rand = new Random();
    int tiempoOcupadoPintorUno = 0;
    int tiempoOcupadoPintorDos = 0;
    int tEsperaPiezaUnoPintura = 0;
    int tEsperaPiezaDosPintura = 0;
    int tTotalPieza = 0;
    int tTotalPinturaDos = 0;
    int tTotalPinturaUno = 0;
    int tiempoOcupadoPintorUnoAnterior = 0;
    int tiempoOcupadoPintorDosAnterior = 0;
    int tiempoOcupadoHornoAnterior = 0;
    int tEsperaPiezaPintura = 0;
    int tTotalHorno = 0;
    private List<Integer> lista = new ArrayList<>();
    int tiempoTotalPintadoUnoAnterior = 0;
    int tiempoTotalPintadoDosAnterior = 0;
    int piezasFinalizadas = 0;
    int tHornoOcupadoAnterior = 0;
    int usoHornoTotal = 0;
    int promedioEsperaHorno = 0;
    int tHornoOcupadoTotal =0 ;
    int esperaTotalHorno = 0;

    public void simular() {
        for (int segundos = 0; segundos < tiempoSimulacionSegundos; segundos++) {
            if (segundos != 0) {
                if ((segundos % TIEMPO_LLEGADA_PIEZA_TIPO_2_EN_SEGUNDOS) == 0) {
                    cantidadPiezaTipoDos += 1;
                    queue.add(segundos);
                }
                if ((segundos % TIEMPO_LLEGADA_PIEZA_TIPO_1_EN_SEGUNDOS) == 0) {
                    cantidadPiezaTipoUno += 1;
                    queue.add(segundos);
                }
            }
        }

        //proceso de pintado
        while (!queue.isEmpty()) {
            //trabajo primer pintor
            int tTotalPiezaUno = 0;
            int tTotalPiezaDos = 0;
            var llegadaPiezaUno = queue.poll();
            var tEsperaPiezaUnoActual = 0;
            if (llegadaPiezaUno < tiempoOcupadoPintorUnoAnterior) {
                tEsperaPiezaUnoActual = tiempoOcupadoPintorUnoAnterior - llegadaPiezaUno;
            }
            //queueAnterior.add(llegadaPiezaUno);
            tEsperaPiezaUnoPintura += tEsperaPiezaUnoActual;
            tTotalPiezaUno += tEsperaPiezaUnoActual + TIEMPO_PINTURA_SEGUNDOS;

            var tiempoTotalPinturaPiezaUno = tTotalPiezaUno + llegadaPiezaUno + TIEMPO_ENTRE_PROCESO_SEGUNDOS;
            tiempoOcupadoPintorUnoAnterior = tiempoTotalPinturaPiezaUno;
            if (tiempoTotalPinturaPiezaUno < tiempoSimulacionSegundos) {
                queueHorno.add(tiempoTotalPinturaPiezaUno);
            }
            tTotalPinturaUno += tiempoOcupadoPintorUnoAnterior - llegadaPiezaUno;
            //trabajo segundo pintor
            var llegadaPiezaDos = queue.poll();

            var tEsperaPiezaDosActual = 0;
            if (llegadaPiezaDos < tiempoOcupadoPintorDosAnterior) {
                tEsperaPiezaDosActual = tiempoOcupadoPintorDosAnterior - llegadaPiezaDos;
            }

            //queueAnterior.add(llegadaPiezaDos);
            tEsperaPiezaDosPintura += tEsperaPiezaDosActual;
            tTotalPiezaDos += tEsperaPiezaDosActual + TIEMPO_PINTURA_SEGUNDOS;

            var tiempoTotalPinturaPiezaDos = tTotalPiezaDos + llegadaPiezaDos + TIEMPO_ENTRE_PROCESO_SEGUNDOS;
            tiempoOcupadoPintorDosAnterior = tiempoTotalPinturaPiezaDos;
            if (tiempoTotalPinturaPiezaDos < tiempoSimulacionSegundos) {
                queueHorno.add(tiempoTotalPinturaPiezaDos);
            }
            tTotalPinturaDos += tiempoOcupadoPintorDosAnterior - llegadaPiezaDos;
        }

        while (!queueHorno.isEmpty()) {
            int piezaHornoLLegada = queueHorno.poll();
            if (piezaHornoLLegada < tiempoSimulacionSegundos) {
                piezasFinalizadas++;
                int esperaHornoActual = 0;
                if (piezaHornoLLegada < tHornoOcupadoAnterior) {
                    esperaHornoActual = tHornoOcupadoAnterior - piezaHornoLLegada;
                }
                esperaTotalHorno += esperaHornoActual;
                promedioEsperaHorno += esperaHornoActual;
                usoHornoTotal += TIEMPO_HORNEADO_SEGUNDOS;
                tHornoOcupadoAnterior = esperaHornoActual + TIEMPO_HORNEADO_SEGUNDOS +piezaHornoLLegada;
                tHornoOcupadoTotal += TIEMPO_HORNEADO_SEGUNDOS;
            }
        }
        piezasTerminadas = cantidadPiezaTipoUno + cantidadPiezaTipoDos;
        cantidadTotalPiezas = piezasTerminadas;
    }

    public void mostrarResultados() {
        double utilizacionPintorUno = (double) 100 * tTotalPinturaUno / tiempoSimulacionSegundos;
        double utilizacionPintorDos = (double) 100 * tTotalPinturaDos / tiempoSimulacionSegundos;
        int tiempoSimulacion = tiempoSimulacionSegundos;
        double utilizacionHorno = (double) 100* tHornoOcupadoTotal/ tiempoSimulacionSegundos;

        double tiempoPromedioPermanencia = (double) tTotalPieza / piezasTerminadas;
        double tiempoPromedioEsperaPintura = (double) piezasFinalizadas;
        int tiempoPromedioEsperaHorno = esperaTotalHorno/piezasFinalizadas;

        System.out.println("Utilización del primer pintor: " + utilizacionPintorUno + "%");
        System.out.println("Utilización del segundo pintor: " + utilizacionPintorDos + "%");

        System.out.println("Tiempo de simulacion: " + tiempoSimulacion/3600 + " horas");
        System.out.println("Utilización del horno: " + utilizacionHorno + "%");
        System.out.println("Tiempo promedio de permanencia de las piezas: " + tiempoPromedioPermanencia);
        System.out.println("Tiempo promedio de espera antes del pintado: " + tiempoPromedioEsperaPintura);
        System.out.println("Tiempo promedio de espera antes del horneado: " + tiempoPromedioEsperaHorno);
    }

    public Queue<Integer> getQueue() {
        return queue;
    }

    public Queue<Integer> getQueueAnterior() {
        return queueAnterior;
    }

    public Queue<Integer> getQueueHorno() {
        return queueHorno;
    }

    public static void main(String[] args) {
        ProcesoPintura p = new ProcesoPintura();
        p.simular();
        p.mostrarResultados();
        System.out.println(p.getQueueHorno());
        System.out.println(p.getQueueHorno().size());
        System.out.println(p.getQueueAnterior().size());
        System.out.println(p.getQueueAnterior());
    }

}
