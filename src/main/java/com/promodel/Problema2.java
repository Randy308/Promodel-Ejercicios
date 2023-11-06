/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.promodel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Problema2 {

    private static final int TIEMPO_LLEGADA_PIEZA_TIPO_1_EN_SEGUNDOS = 516;
    private static final int TIEMPO_LLEGADA_PIEZA_TIPO_2_EN_SEGUNDOS = 1200;
    private static final int TIEMPO_PINTURA_SEGUNDOS = 600;
    private static final int TIEMPO_HORNEADO_SEGUNDOS = 360;
    private static final int TIEMPO_ENTRE_PROCESO_SEGUNDOS = 30;
    //private int horasSimulacionTotal = 120;

    private int piezasTerminadas = 0;
    private int cantidadPiezaTipoUno = 0;
    private int cantidadPiezaTipoDos = 0;

    private Queue<Integer> queue = new LinkedList<>();
    private Queue<Integer> queueAnterior = new LinkedList<>();
    private Queue<Integer> queueHorno = new LinkedList<>();
    private int tEsperaPiezaUnoPintura = 0;
    private int tEsperaPiezaDosPintura = 0;
    private final int tTotalPieza = 0;
    private int tTotalPinturaDos = 0;
    private int tTotalPinturaUno = 0;
    private int tiempoOcupadoPintorUnoAnterior = 0;
    private int tiempoOcupadoPintorDosAnterior = 0;

    private int piezasFinalizadas = 0;
    private int tHornoOcupadoAnterior = 0;
    private int usoHornoTotal = 0;
    private int promedioEsperaHorno = 0;
    private int tHornoOcupadoTotal = 0;
    private int esperaTotalHorno = 0;

    public void simular(int horasSimulacionTotal) {
        int tiempoSimulacionSegundos = horasSimulacionTotal * 3600;
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
                tHornoOcupadoAnterior = esperaHornoActual + TIEMPO_HORNEADO_SEGUNDOS + piezaHornoLLegada;
                tHornoOcupadoTotal += TIEMPO_HORNEADO_SEGUNDOS;
            }
        }
        piezasTerminadas = cantidadPiezaTipoUno + cantidadPiezaTipoDos;
        double utilizacionPintorUno = (double) 100 * tTotalPinturaUno / tiempoSimulacionSegundos;
        double utilizacionPintorDos = (double) 100 * tTotalPinturaDos / tiempoSimulacionSegundos;
        int tiempoSimulacion = tiempoSimulacionSegundos;
        double utilizacionHorno = (double) 100 * tHornoOcupadoTotal / tiempoSimulacionSegundos;

        double tiempoPromedioPermanencia = (double) (tTotalPinturaDos + tTotalPinturaDos + tHornoOcupadoTotal) / piezasTerminadas;
        double tiempoPromedioEsperaPintura = (double) ((tEsperaPiezaDosPintura + tEsperaPiezaUnoPintura)) / piezasFinalizadas;
        double tiempoPromedioEsperaHorno = (double) esperaTotalHorno / piezasFinalizadas;
        
        var promedioPintura = (utilizacionPintorDos + utilizacionPintorUno) / 2;
        String[] headers = {"Inciso", "Respuesta"};
        List<List<String>> data = new ArrayList<>();

        data.add(Arrays.asList("Utilización del primer pintor: ", String.format("%,.4f", utilizacionPintorUno) + " %"));
        data.add(Arrays.asList("Utilización del segundo pintor: ", String.format("%,.4f", utilizacionPintorDos) + " %"));
        data.add(Arrays.asList("Utilización del proceso de pintura: ", String.format("%,.4f", promedioPintura) + " %"));
        data.add(Arrays.asList("Tiempo de simulación: ", String.format("%,.1f", (tiempoSimulacion / 3600.0)) + " horas"));
        data.add(Arrays.asList("Utilización del horno: ", String.format("%,.4f", utilizacionHorno) + " %"));
        data.add(Arrays.asList("Tiempo promedio de permanencia de las piezas:", String.format("%,.4f", (tiempoPromedioPermanencia / 60.0)) + " minutos"));
        data.add(Arrays.asList("Tiempo promedio de espera antes del pintado: ", String.format("%,.4f", tiempoPromedioEsperaPintura) + " minutos"));
        data.add(Arrays.asList("Tiempo promedio de espera antes del horneado: ", String.format("%,.4f", tiempoPromedioEsperaHorno) + " minutos"));
        TablaSimulacion tb = new TablaSimulacion();
        tb.crearTablaSimulacion(headers, data);
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
        Problema2 p = new Problema2();
        int horasSimulacionTotal = 120;
        p.simular(horasSimulacionTotal);
    }

}
