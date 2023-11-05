/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.promodel;

/**
 *
 * @author User
 */
class Maquinado {
    private double tiempoRestante;  // Tiempo restante para terminar el maquinado
    private boolean ocupada;     // Indica si la máquina está ocupada

    public Maquinado() {
        this.tiempoRestante = 0;
        this.ocupada = false;
    }

    public void procesarPieza(TipoPieza tipoPieza) {
        tiempoRestante = tipoPieza.tiempoMaquinado;
        ocupada = true;
    }

    public void avanzarTiempo() {
        if (ocupada) {
            tiempoRestante--;
            if (tiempoRestante == 0) {
                ocupada = false;
            }
        }
    }

    public boolean estaOcupada() {
        return ocupada;
    }
}