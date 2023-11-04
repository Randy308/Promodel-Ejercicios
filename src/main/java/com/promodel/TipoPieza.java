/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.promodel;

/**
 *
 * @author M S I
 */
import java.util.LinkedList;
import java.util.Queue;

class TipoPieza {
    int tiempoMaquinado; // Tiempo de maquinado en minutos
    int tasaEntrada;    // Tasa de entrada en piezas por hora

    public TipoPieza(int tiempoMaquinado, int tasaEntrada) {
        this.tiempoMaquinado = tiempoMaquinado;
        this.tasaEntrada = tasaEntrada;
    }
}

class Maquinado {
    private int tiempoRestante;  // Tiempo restante para terminar el maquinado
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


