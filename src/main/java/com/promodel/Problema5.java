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
import java.util.Random;

/**
 *
 * @author User
 */
public class Problema5 {

    // Tasas de llegada (piezas por minuto)
    double lambda_1 = 15.0; // Una pieza cada 30 minutos en promedio
    double lambda_2 = 7.5; // Una pieza cada 15 minutos en promedio
    double lambda_3 = 3.75; // Una pieza cada 30 minutos en promedio

    // Tiempos de atención (minutos por pieza)
    double mu_1 = 12.0; // Tiempo de atención promedio para la pieza 1
    double mu_2 = 15.0; // Tiempo de atención promedio para la pieza 2
    double mu_3 = 1.0; // Tiempo de atención promedio para la pieza 3

    public void simulacion(int horasSimulacionTotal, int cantidadCopiadoras) {
        double clock = 0.0;
        int cantPieza1 = 0;
        int cantPieza2 = 0;
        int cantPieza3 = 0;

        Queue<TipoPieza> cola = new LinkedList<>();
        while (horasSimulacionTotal > clock) {
            // Calcular el tiempo de llegada de las tres piezas

            double arriboPieza1 = clock + exponencial(lambda_1);
            double arriboPieza2 = clock + exponencial(lambda_2);
            double arriboPieza3 = clock + exponencial(lambda_3);

            // Determinar cuál pieza llega primero
            double maxArribo = Math.min(arriboPieza1, Math.min(arriboPieza2, arriboPieza3));

            var tiempoEntrada = maxArribo;
            if (tiempoEntrada == arriboPieza1) {
                double tiempoAtencion = exponencial(mu_1);
                //System.out.println("Pieza 1 llega en " + tiempoEntrada + " minutos");
                cola.add(new TipoPieza(tiempoAtencion, tiempoEntrada, 1));
                cantPieza1++;
            } else if (tiempoEntrada == arriboPieza2) {
                double tiempoAtencion = exponencial(mu_2);
                //System.out.println("Pieza 2 llega en " + tiempoEntrada + " minutos");
                cola.add(new TipoPieza(tiempoAtencion, tiempoEntrada, 2));
                cantPieza2++;
            } else {
                double tiempoAtencion = exponencial(mu_3);
                //System.out.println("Pieza 3 llega en " + tiempoEntrada + " minutos");
                cola.add(new TipoPieza(tiempoAtencion, tiempoEntrada, 3));
                cantPieza3++;
            }
            clock = maxArribo;

        }
        int cantidaPiezas = cola.size();

        int piezasRechazadas = 0;
        int piezasAcabadas = 0;
        double tiempoEspera = 0.0;
        //double tiempoFinalServicioAnterior = 0.0;
        double usoCentroMaquinado = 0.0;
        double usoInspector = 0.0;
        double tServicioTotal = 0.0;
        List<Double> copiadorasServicioAnterior = new ArrayList<>();
        List<Double> usoCopiadoras = new ArrayList<>();
        for (int i = 0; i < cantidadCopiadoras; i++) {
            copiadorasServicioAnterior.add(0.0);
            usoCopiadoras.add(0.0);
        }
        while (!cola.isEmpty()) {
            for (int i = 0; i < cantidadCopiadoras; i++) {
                TipoPieza tipoPieza = cola.poll();
                if (tipoPieza != null) {
                    double tiempoLlegada = tipoPieza.tiempoEntrada;
                    double tiempoAtencion = tipoPieza.tiempoMaquinado;
                    int tipo = tipoPieza.tipoPieza;
                    double tiempoFinalServicioAnterior = copiadorasServicioAnterior.get(i);
                    if (tiempoFinalServicioAnterior < horasSimulacionTotal) {
                        double inicioServicio;
                        if (tiempoLlegada > tiempoFinalServicioAnterior) {
                            inicioServicio = tiempoLlegada;
                        } else {
                            inicioServicio = tiempoFinalServicioAnterior;
                        }
                        tiempoEspera += inicioServicio - tiempoLlegada;

                        tiempoFinalServicioAnterior = tiempoLlegada + tiempoAtencion;
                        copiadorasServicioAnterior.set(i, tiempoFinalServicioAnterior);
                        //tiempoFinalServicioAnterior = ;
                        if (tServicioTotal < horasSimulacionTotal) {
                            /**
                             * System.out.println("Pieza tipo " +
                             * tipoPieza.tipoPieza); System.out.println(" llega
                             * a la cola en " + tiempoLlegada);
                             * System.out.println(" espera " + (inicioServicio -
                             * tiempoLlegada)); System.out.println(" entra al
                             * servicio en " + inicioServicio);
                             * System.out.println("Es atendido por la copiadora
                             * " + (i + 1)); System.out.println(" el servicio
                             * dura " + (tiempoAtencion)); System.out.println("
                             * sale de la atencion en " +
                             * tiempoFinalServicioAnterior + " min");
                             */
                            switch (tipo) {
                                case 1: {
                                    double tInspeccion = exponencial(3);
                                    tServicioTotal = tInspeccion + tiempoFinalServicioAnterior;
                                    usoInspector += tInspeccion;
                                    //System.out.println(" sale de centro de copiado en " + tServicioTotal + " min");
                                    break;
                                }
                                case 2: {
                                    double tInspeccion = exponencial(6);
                                    tServicioTotal = tInspeccion + tiempoFinalServicioAnterior;
                                    usoInspector += tInspeccion;
                                    //System.out.println(" sale de centro de copiado en " + tServicioTotal + " min");
                                    break;
                                }
                                case 3: {
                                    double tInspeccion = exponencial(10);
                                    tServicioTotal = tInspeccion + tiempoFinalServicioAnterior;
                                    usoInspector += tInspeccion;
                                    //System.out.println(" sale de centro de copiado en " + tServicioTotal + " min");
                                    break;
                                }
                                default:
                                    break;
                            }

                            var usoCopiadora = usoCopiadoras.get(i);
                            usoCopiadoras.set(i, usoCopiadora + tiempoAtencion);
                            usoCentroMaquinado += tiempoAtencion;
                            piezasAcabadas++;
                        } else {
                            piezasRechazadas++;
                        }
                    }
                }
            }

        }
        int piezasTotales = (piezasAcabadas + piezasRechazadas);
        double piezasPromedio = (double) piezasAcabadas / piezasTotales;

        String[] headers = {"Inciso", "Respuesta"};
        List<List<String>> data = new ArrayList<>();
        data.add(Arrays.asList("Trabajos totales tipo 1 recibidas   ", Integer.toString(cantPieza1)));
        data.add(Arrays.asList("Trabajos totales tipo 2 recibidas   ", Integer.toString(cantPieza2)));
        data.add(Arrays.asList("Trabajos totales tipo 3 recibidas   ", Integer.toString(cantPieza3)));
        data.add(Arrays.asList("Trabajos totales recibidas  ", Integer.toString(cantidaPiezas)));
        data.add(Arrays.asList("Trabajos Rechazadas por limite del tiempo de simulacion  ", Integer.toString(piezasRechazadas)));
        data.add(Arrays.asList("Total trabajos realizados ", Integer.toString(piezasAcabadas)));
        data.add(Arrays.asList("Tiempo de espera promedio ", String.format("%,.1f", (tiempoEspera / piezasAcabadas)) + " minutos"));
        data.add(Arrays.asList("Promedio de trabajos realizados  ", String.format("%,.4f", (100 * piezasPromedio)) + " %"));
        //data.add(Arrays.asList("Uso total de las copiadoras ", String.format("%,.4f", (usoCentroMaquinado/ horasSimulacionTotal)) + " minutos"));
        for (int i = 0; i < usoCopiadoras.size(); i++) {
            data.add(Arrays.asList("Uso de la copiadora " + (i + 1), String.format("%,.4f", (100 * usoCopiadoras.get(i) / horasSimulacionTotal)) + " %"));
        }
        data.add(Arrays.asList("Uso del inspector ", String.format("%,.4f", (100 * (1 - usoInspector / (horasSimulacionTotal * piezasAcabadas)))) + " %"));

        TablaSimulacion tb = new TablaSimulacion();
        tb.crearTablaSimulacion(headers, data);
    }

    public double exponencial(double lambda) {
        return -Math.log(1 - new Random().nextDouble()) * lambda;
    }

    public static void main(String[] args) {
        Problema5 simulacion = new Problema5();
        int horasSimulacionTotal = 3000;
        int cantidadCopiadoras = 5;
        simulacion.simulacion(horasSimulacionTotal, cantidadCopiadoras);

    }
}
