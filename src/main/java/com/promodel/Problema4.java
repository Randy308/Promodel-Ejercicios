/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.promodel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author User
 */
public class Problema4 {

    public void simulacion(int tiempoSimulacion, int lambda, int mu, int N,double costo) {
        double rho = (double) lambda / mu;
        double p0 = (1 - rho) / (1 - Math.pow(rho, N + 1));
        double pN = Math.pow(rho, N) * p0;

        double clientesEntran = lambda * (1 - pN);
        double clientesNoEntran = lambda - clientesEntran;
        double clientesEnElSistema = (rho / (1 - rho)) - (((N + 1) * Math.pow(rho, N + 1)) / (1 - Math.pow(rho, N + 1)));

        double clientesEnLaCola = clientesEnElSistema - (1 - p0);
        double lambdaBarra = mu * (clientesEnElSistema - clientesEnLaCola);
        double promedioTiempoCola = clientesEnLaCola / lambdaBarra;
        double promedioTiempoSistema = clientesEnElSistema / lambdaBarra;

        String[] headers = {"Inciso", "Respuesta"};
        List<List<String>> data = new ArrayList<>();

        data.add(Arrays.asList("Cantidad de clientes que no entran  ", String.format("%,.4f", clientesNoEntran * tiempoSimulacion)));
        data.add(Arrays.asList("Pérdida esperada por clientes que no entran  ", String.format("%,.4f", costo *clientesNoEntran * tiempoSimulacion)+"$"));
        data.add(Arrays.asList("Cantidad de clientes que entran ", String.format("%,.4f", clientesEntran * tiempoSimulacion)));
        data.add(Arrays.asList("Longitud Promedio de clientes en la cola ", String.format("%,.4f", clientesEnLaCola * tiempoSimulacion) ));
        data.add(Arrays.asList("Longitud Promedio de clientes en el sistema ", String.format("%,.1f", (clientesEnElSistema * tiempoSimulacion))));
        data.add(Arrays.asList("promedio Tiempo Cola ", String.format("%,.4f", (promedioTiempoCola * 60)) + " minutos"));
        data.add(Arrays.asList("promedio Tiempo Sistema ", String.format("%,.4f", (promedioTiempoSistema * 60)) + " minutos"));

        TablaSimulacion tb = new TablaSimulacion();
        tb.crearTablaSimulacion(headers, data);
//double promedioLlegadas =mu*()
    }

    public static void main(String[] args) {
        Problema4 centro = new Problema4();
        int lambda = 12;// 12;
        int mu = 10;//10;
        int N = 3;//3;
        int tiempoSimulacion = 40;
        double costo = 5.0;
        centro.simulacion(tiempoSimulacion, lambda, mu, N, costo);

    }
}
