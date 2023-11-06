/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.promodel;

/**
 *
 * @author M S I
 */

class TipoPieza {
    double tiempoMaquinado; 
    double tiempoEntrada;
    int tipoPieza;
    double tiempoInspeccion;

    public TipoPieza(double tiempoMaquinado, double tiempoEntrada, int tipoPieza) {
        this.tiempoMaquinado = tiempoMaquinado;
        this.tiempoEntrada = tiempoEntrada;
         this.tipoPieza = tipoPieza;
    }

    public TipoPieza(double tiempoMaquinado, double tiempoEntrada, int tipoPieza, double tiempoInspeccion) {
        this.tiempoMaquinado = tiempoMaquinado;
        this.tiempoEntrada = tiempoEntrada;
        this.tipoPieza = tipoPieza;
        this.tiempoInspeccion = tiempoInspeccion;
    }

    public double getTiempoInspeccion() {
        return tiempoInspeccion;
    }

    public void setTiempoInspeccion(double tiempoInspeccion) {
        this.tiempoInspeccion = tiempoInspeccion;
    }
    

    
}




