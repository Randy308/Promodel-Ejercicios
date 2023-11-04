/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.promodel;

import java.util.LinkedList;
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
    int tiempoOcioPintorUno = 0;
    int tiempoOcioPintorDos = 0;
    int tEsperaPiezaUnoPintura = 0;
    int tEsperaPiezaDosPintura = 0;
    int tTotalPieza = 0;
    int tTotalPintura = 0;
    int tiempoOcupadoPintorUnoAnterior = 0;
    int tiempoOcupadoPintorDosAnterior = 0;
    int tiempoOcupadoHornoAnterior = 0;
    int tEsperaPiezaPintura = 0;
    int tTotalHorno = 0;

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
            if (llegadaPiezaUno > tiempoOcupadoPintorUnoAnterior) {
                tiempoOcioPintorUno += (llegadaPiezaUno - tiempoOcupadoPintorUnoAnterior);
                
            } else {
                tEsperaPiezaUnoActual = tiempoOcupadoPintorUnoAnterior - llegadaPiezaUno;
            }
            queueAnterior.add(llegadaPiezaUno);
            tEsperaPiezaUnoPintura += tEsperaPiezaUnoActual;
            tTotalPiezaUno += tEsperaPiezaUnoActual + TIEMPO_PINTURA_SEGUNDOS;
            tiempoOcupadoPintorUnoAnterior = tTotalPiezaUno + llegadaPiezaUno;
            queueHorno.add(tTotalPiezaUno + llegadaPiezaUno + TIEMPO_ENTRE_PROCESO_SEGUNDOS);
            tTotalPintura += tiempoOcupadoPintorUnoAnterior;
            //trabajo segundo pintor
            var llegadaPiezaDos = queue.poll();

            var tEsperaPiezaDosActual = 0;
            if (llegadaPiezaDos > tiempoOcupadoPintorDosAnterior) {
                tiempoOcioPintorDos += (llegadaPiezaDos - tiempoOcupadoPintorDosAnterior);
               
            } else {
                tEsperaPiezaDosActual = tiempoOcupadoPintorDosAnterior - llegadaPiezaDos;
            }
            queueAnterior.add(llegadaPiezaDos);
            tEsperaPiezaDosPintura += tEsperaPiezaDosActual;
            tTotalPiezaDos += tEsperaPiezaDosActual + TIEMPO_PINTURA_SEGUNDOS;
            tiempoOcupadoPintorDosAnterior = tTotalPiezaDos + llegadaPiezaDos;
            queueHorno.add(tTotalPiezaDos + llegadaPiezaDos + TIEMPO_ENTRE_PROCESO_SEGUNDOS);
            tTotalPintura += tiempoOcupadoPintorDosAnterior;
        }
        int tiempoOcioHorno=0;
        while (!queueHorno.isEmpty()) {
            var llegadaPieza = queueHorno.poll();
            var tEsperaPiezaActual = 0;
            if (llegadaPieza >= tiempoOcupadoHornoAnterior) {
               
                tiempoOcioHorno += (llegadaPieza - tiempoOcupadoHornoAnterior);
                
            } else {
                tEsperaPiezaActual = tiempoOcupadoHornoAnterior - llegadaPieza;
            }
            tEsperaPiezaPintura += tEsperaPiezaActual;
            tTotalHorno += tEsperaPiezaActual + TIEMPO_HORNEADO_SEGUNDOS;
            tiempoOcupadoHornoAnterior = tTotalHorno + llegadaPieza;
        }

        piezasTerminadas = cantidadPiezaTipoUno + cantidadPiezaTipoDos;
        cantidadTotalPiezas = piezasTerminadas;
    }

    public void mostrarResultados() {
    double utilizacionPintorUno = (double) tTotalPintura / tiempoSimulacionSegundos;
    double utilizacionPintorDos = (double) tTotalPintura / tiempoSimulacionSegundos;
    double utilizacionHorno = (double) tiempoOcupadoHornoAnterior / tiempoSimulacionSegundos;

    double tiempoPromedioPermanencia = (double) tTotalPieza / piezasTerminadas;
    double tiempoPromedioEsperaPintura = (double) tEsperaPiezaPintura / piezasTerminadas;
    double tiempoPromedioEsperaHorno = (double) tEsperaPiezaPintura / piezasTerminadas;

    System.out.println("Utilización del primer pintor: " + utilizacionPintorUno);
    System.out.println("Utilización del segundo pintor: " + utilizacionPintorDos);
    System.out.println("Utilización del horno: " + utilizacionHorno);
    System.out.println("Tiempo promedio de permanencia de las piezas: " + tiempoPromedioPermanencia);
    System.out.println("Tiempo promedio de espera antes del pintado: " + tiempoPromedioEsperaPintura);
    System.out.println("Tiempo promedio de espera antes del horneado: " + tiempoPromedioEsperaHorno);
}


    public static void main(String[] args) {
        ProcesoPintura p = new ProcesoPintura();
        p.simular();
        p.mostrarResultados();
    }

}
