/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.promodel;

/**
 *
 * @author User
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Problema1 {

    public void simular(int horasSimulacion, double min, double max, int tiempoArribo, int cantidadPiezas) {
        Random random = new Random();
        Distribucion distribucion = new Distribucion();

        double tiempoTotalLimpieza = 0.0;
        double tiempoTotalEspera = 0.0;
        double tiempoTotalEsperaAnt = 0.0;
        double tiempoPermanencia = 0.0;
        double tOcioMinutos = 0.0;
        double inventarioPiezas = cantidadPiezas;
        int piezasLimpiadas = 0;

        for (int minuto = 0; minuto < 60 * horasSimulacion; minuto++) {
            while (inventarioPiezas > 0) {
                double aleatorio = random.nextDouble();
                double tiempoLimpieza = distribucion.uniforme(min, max, aleatorio) / 60.0;

                tiempoTotalLimpieza += tiempoLimpieza;
                tiempoPermanencia += tiempoLimpieza + (tiempoTotalEsperaAnt);
                tiempoTotalEspera += tiempoLimpieza * (60 - inventarioPiezas);
                tiempoTotalEsperaAnt = tiempoLimpieza * (60 - inventarioPiezas);

                piezasLimpiadas++;
                if (inventarioPiezas == 0) {
                    tOcioMinutos += 1; // Incrementar el tiempo de ocio
                }

                inventarioPiezas--;
            }

            if (minuto % tiempoArribo == 0) {
                inventarioPiezas += cantidadPiezas;
            }
        }

        double utilizacionOperario = 100.0 * (tiempoTotalLimpieza / (horasSimulacion * 60)); // En porcentaje
        //double ocioOperario = (tOcioMinutos);
        double tiempoPromedioPermanenciaPiezas = tiempoPermanencia / piezasLimpiadas;
        double tiempoPromedioEsperaPiezas = tiempoTotalEspera / piezasLimpiadas;
        String[] headers = {"Inciso", "Respuesta"};
        List<List<String>> data = new ArrayList<>();

        data.add(Arrays.asList("Utilización del operario: ", String.format("%,.4f", utilizacionOperario) + "%"));
        data.add(Arrays.asList("Tiempo promedio de permanencia de las piezas: ", String.format("%,.4f", tiempoPromedioPermanenciaPiezas) + " minutos"));
        data.add(Arrays.asList("Tiempo promedio de espera de las piezas antes de ser limpiadas:", String.format("%,.4f", tiempoPromedioEsperaPiezas) + " minutos"));

        TablaSimulacion tb = new TablaSimulacion();
        tb.crearTablaSimulacion(headers, data);

    }

    public static void main(String[] args) {
        int horasSimulacion = 800;
        int tiempoArribo = 60;
        int cantidadPiezas = 60;
        double min = 50.0;
        double max = 60.0;
        Problema1 p1 = new Problema1();
        p1.simular(horasSimulacion, min, max, tiempoArribo, cantidadPiezas);
    }
}
