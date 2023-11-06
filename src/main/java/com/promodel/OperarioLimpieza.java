/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.promodel;

/**
 *
 * @author User
 */
import java.util.Random;

public class OperarioLimpieza {

    public static void main(String[] args) {
        int horasSimulacion = 500;
        Random random = new Random();
        Distribucion distribucion = new Distribucion();

        double tiempoTotalLimpieza = 0.0;
        double tiempoTotalEspera = 0.0;
        double tiempoTotalEsperaAnt = 0.0;
        double tiempoPermanencia = 0.0;
        double tOcioMinutos = 0.0;
        double tLLegadaPiezasMinutos = 60.0;
        int inventarioPiezas = 60;
        int piezasLimpiadas = 0;
        for (int hora = 0; hora < horasSimulacion; hora++) {
            for (int minuto = 0; minuto < 60; minuto++) {
                while (inventarioPiezas > 0) {
                    double aleatorio = random.nextDouble();
                    double tiempoLimpieza = distribucion.uniforme(40.0, 60.0, aleatorio) / 60.0;
                    tiempoTotalLimpieza += tiempoLimpieza;
                    tiempoPermanencia += tiempoLimpieza + (tiempoTotalEsperaAnt);
                    tiempoTotalEspera += tiempoLimpieza * (60 - inventarioPiezas);
                    tiempoTotalEsperaAnt = tiempoLimpieza * (60 - inventarioPiezas);
                    inventarioPiezas--;
                    piezasLimpiadas += 1;
                    if (inventarioPiezas == 0) {
                        tOcioMinutos += 1; // Incrementar el tiempo de ocio
                    }

                }

                if (minuto % tLLegadaPiezasMinutos == 0) {
                    inventarioPiezas += 60;
                }
            }
        }

        double utilizacionOperario = (tiempoTotalLimpieza / (horasSimulacion * 60)) * 100.0; // En porcentaje
        double ocioOperario = (tOcioMinutos);
        double tiempoPromedioPermanenciaPiezas = tiempoPermanencia / piezasLimpiadas;
        double tiempoPromedioEsperaPiezas = tiempoTotalEspera / piezasLimpiadas;

        System.out.println("Utilización del operario: " + utilizacionOperario + "%");
        System.out.println("Tiempo de ocio del operario: " + ocioOperario + " minutos");
        System.out.println("Tiempo promedio de permanencia de las piezas: " + tiempoPromedioPermanenciaPiezas + " minutos");
        System.out.println("Tiempo promedio de espera de las piezas antes de ser limpiadas: " + tiempoPromedioEsperaPiezas + " minutos");
    }
}
