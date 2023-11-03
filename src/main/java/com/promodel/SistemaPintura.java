/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.promodel;

/**
 *
 * @author User
 */
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

class SistemaPintura {
    private static final int HORAS_SIMULACION = 5 * 24; // 5 días
    private static final int MINUTOS_POR_HORA = 60;
    private static final int MINUTOS_PINTURA_PIEZA1 = 10;
    private static final int MINUTOS_PINTURA_PIEZA2 = 10;
    private static final int MINUTOS_HORNEADO = 6;
    private static final double TASA_ENTRADA_PIEZA1 = 7.0 / 60.0; // Piezas por minuto
    private static final double TASA_ENTRADA_PIEZA2 = 3.0 / 60.0; // Piezas por minuto
    private static final int TIEMPO_MOVIMIENTO = 30; // Segundos

    private Queue<Integer> pinturaPiezas1;
    private Queue<Integer> pinturaPiezas2;
    private Queue<Integer> horneadoPiezas;
    private int tiempoTotalPinturaPieza1;
    private int tiempoTotalPinturaPieza2;
    private int tiempoTotalHorneado;
    private int tiempoHorneado;
    private int tiempoEsperaPinturaPieza1;
    private int tiempoEsperaPinturaPieza2;
    private int tiempoEsperaHorneado;

    public SistemaPintura() {
        pinturaPiezas1 = new LinkedList<>();
        pinturaPiezas2 = new LinkedList<>();
        horneadoPiezas = new LinkedList<>();
        tiempoTotalPinturaPieza1 = 0;
        tiempoTotalPinturaPieza2 = 0;
        tiempoTotalHorneado = 0;
        tiempoHorneado = 0;
        tiempoEsperaPinturaPieza1 = 0;
        tiempoEsperaPinturaPieza2 = 0;
        tiempoEsperaHorneado = 0;
    }

    public void simular() {
        Random random = new Random();
        int tiempoPinturaPieza1 = 0;
        int tiempoPinturaPieza2 = 0;
        int tiempoMovimiento = 0;

        for (int minuto = 0; minuto < HORAS_SIMULACION * MINUTOS_POR_HORA; minuto++) {
            if (random.nextDouble() < TASA_ENTRADA_PIEZA1) {
                pinturaPiezas1.add(minuto);
            }
            if (random.nextDouble() < TASA_ENTRADA_PIEZA2) {
                pinturaPiezas2.add(minuto);
            }

            if (tiempoPinturaPieza1 == 0 && !pinturaPiezas1.isEmpty()) {
                int llegadaPieza1 = pinturaPiezas1.poll();
                tiempoEsperaPinturaPieza1 += minuto - llegadaPieza1;
                tiempoPinturaPieza1 = MINUTOS_PINTURA_PIEZA1;
            }

            if (tiempoPinturaPieza2 == 0 && !pinturaPiezas2.isEmpty()) {
                int llegadaPieza2 = pinturaPiezas2.poll();
                tiempoEsperaPinturaPieza2 += minuto - llegadaPieza2;
                tiempoPinturaPieza2 = MINUTOS_PINTURA_PIEZA2;
            }

            if (tiempoMovimiento == 0) {
                if (tiempoPinturaPieza1 > 0) {
                    horneadoPiezas.add(minuto);
                    tiempoPinturaPieza1 = 0;
                } else if (tiempoPinturaPieza2 > 0) {
                    horneadoPiezas.add(minuto);
                    tiempoPinturaPieza2 = 0;
                }
                tiempoMovimiento = TIEMPO_MOVIMIENTO;
            }

            if (tiempoHorneado == 0 && !horneadoPiezas.isEmpty()) {
                int llegadaHorneado = horneadoPiezas.poll();
                tiempoEsperaHorneado += minuto - llegadaHorneado;
                tiempoHorneado = MINUTOS_HORNEADO;
            }

            tiempoPinturaPieza1 = Math.max(0, tiempoPinturaPieza1 - 1);
            tiempoPinturaPieza2 = Math.max(0, tiempoPinturaPieza2 - 1);
            tiempoHorneado = Math.max(0, tiempoHorneado - 1);
            tiempoMovimiento = Math.max(0, tiempoMovimiento - 1);
        }
    }

    public double utilizacionPinturaPieza1() {
        return (1.0 - (double) tiempoEsperaPinturaPieza1 / (HORAS_SIMULACION * MINUTOS_POR_HORA)) * 100;
    }

    public double utilizacionPinturaPieza2() {
        return (1.0 - (double) tiempoEsperaPinturaPieza2 / (HORAS_SIMULACION * MINUTOS_POR_HORA)) * 100;
    }

    public double utilizacionHorneado() {
        return (1.0 - (double) tiempoEsperaHorneado / (HORAS_SIMULACION * MINUTOS_POR_HORA)) * 100;
    }

    public double tiempoPromedioPermanenciaPiezas() {
        return (double) (tiempoTotalPinturaPieza1 + tiempoTotalPinturaPieza2 + tiempoTotalHorneado) / (HORAS_SIMULACION * MINUTOS_POR_HORA);
    }

    public double tiempoPromedioEsperaPinturaPieza1() {
        return (double) tiempoEsperaPinturaPieza1 / (pinturaPiezas1.size() + 1);
    }

    public double tiempoPromedioEsperaPinturaPieza2() {
        return (double) tiempoEsperaPinturaPieza2 / (pinturaPiezas2.size() + 1);
    }
    public static void main(String[] args) {
        SistemaPintura sistema = new SistemaPintura();
        sistema.simular();

        System.out.println("a) Utilización de pintura para pieza tipo 1: " + sistema.utilizacionPinturaPieza1() + "%");
        System.out.println("a) Utilización de pintura para pieza tipo 2: " + sistema.utilizacionPinturaPieza2() + "%");
        System.out.println("a) Utilización del horno: " + sistema.utilizacionHorneado() + "%");
        System.out.println("b) Tiempo promedio de permanencia de las piezas en el proceso: " + sistema.tiempoPromedioPermanenciaPiezas() + " minutos");
        System.out.println("c) Tiempo promedio de espera de las piezas antes del pintado (pieza tipo 1): " + sistema.tiempoPromedioEsperaPinturaPieza1() + " minutos");
        System.out.println("c) Tiempo promedio de espera de las piezas antes del pintado (pieza tipo 2): " + sistema.tiempoPromedioEsperaPinturaPieza2() + " minutos");
    }
}
