/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.promodel;

import java.util.Random;

/**
 *
 * @author User
 */
public class Prueba {

    public void operarioLimpieza(int horasSimulacion) {
        Random random = new Random();
        var aleatorio = random.nextDouble();
        Distribucion distribucion = new Distribucion();

        var tiempoTotalLimpieza = 0.0;
        var tOcioMinutos = 0.0;
        var tLLegadaPiezasMinutos = 60.0;
        var inventarioPiezas = 60;
        double tiempoTotalEspera = 0.0;
        for (int j = 0; j < (horasSimulacion * 60); j++) {
            while (inventarioPiezas > 0) {
                var tiempoLimpieza = distribucion.uniforme(40.0, 60.0, aleatorio)/60.0;
                tiempoTotalLimpieza += tiempoLimpieza;
                tiempoTotalEspera += tiempoLimpieza * (60-inventarioPiezas);
                inventarioPiezas--;
            }

            if (j % tLLegadaPiezasMinutos == 0) {
                inventarioPiezas = inventarioPiezas + 60;
            } else if (inventarioPiezas == 0) {
                tOcioMinutos = tOcioMinutos + 1;
            }

        }

    }
}
