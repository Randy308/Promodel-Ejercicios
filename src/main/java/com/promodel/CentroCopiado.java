/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.promodel;

/**
 *
 * @author M S I
 */
public class CentroCopiado {

    int lambda = 12;// 12;
    int mu = 10;//10;
    int N = 3;//3;
    int tiempoSimulacionHoras = 40; 
    public void simulacion() {
        double rho = (double) lambda / mu;
        double p0 = (1 - rho) / (1 - Math.pow(rho, N + 1));
        double pN = Math.pow(rho, N) * p0;

        double clientesEntran = lambda * (1 - pN);
        double clientesNoEntran = lambda - clientesEntran;
        double clientesEnElSistema = (rho /( 1 - rho)) - (((N + 1) * Math.pow(rho, N + 1)) /( 1 - Math.pow(rho, N + 1)));

        double clientesEnLaCola = clientesEnElSistema - (1 - p0);
        double lambdaBarra = mu * (clientesEnElSistema - clientesEnLaCola);
        double promedioTiempoCola = clientesEnLaCola / lambdaBarra;
        double promedioTiempoSistema = clientesEnElSistema / lambdaBarra;

        System.out.println("clientes que no entran " + tiempoSimulacionHoras*clientesNoEntran);
        System.out.println("clientes que entran " + tiempoSimulacionHoras*clientesEntran);
        System.out.println("Longitud Promedio de clientes en la cola " +  tiempoSimulacionHoras*clientesEnLaCola);
        System.out.println("Longitud Promedio de clientes en el Sistema " +  tiempoSimulacionHoras*clientesEnElSistema);
        System.out.println("promedio Tiempo Cola " +  promedioTiempoCola*60 +" minutos");
        System.out.println("promedio Tiempo en el Centro de Copiado " +  (promedioTiempoSistema-promedioTiempoCola)*60 +" minutos");

//double promedioLlegadas =mu*()
    }

    public static void main(String[] args) {
        CentroCopiado centro = new CentroCopiado();
        centro.simulacion();

    }
}
