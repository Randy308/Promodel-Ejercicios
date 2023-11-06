/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.promodel;

/**
 *
 * @author User
 */
public class CentroCopiado {

    int lambda = 12;// 12;
    int mu = 10;//10;
    int N = 3;//3;

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

        System.out.println("clientes que no entran por hora " + clientesNoEntran);
        System.out.println("clientes que entran por hora " + clientesEntran);
        System.out.println("Longitud Promedio de clientes en la cola " + clientesEnLaCola);
        System.out.println("Longitud Promedio de clientes en el Sistema " + clientesEnElSistema);
        System.out.println("promedio Tiempo Cola " + promedioTiempoCola +" horas");
        System.out.println("promedio Tiempo Sistema " + promedioTiempoSistema +" horas");

//double promedioLlegadas =mu*()
    }

    public static void main(String[] args) {
        CentroCopiado centro = new CentroCopiado();
        centro.simulacion();

    }
}