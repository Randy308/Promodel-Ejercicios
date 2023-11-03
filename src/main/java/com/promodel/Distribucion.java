package com.promodel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.distribution.RealDistribution;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author User
 */
public class Distribucion {

    double parametroUno;
    double parametroDos;
    double parametroTres;
    double random;
    ArrayList<Double> aleatorios;
    double[][] limites;

    public double[][] getLimites() {
        return limites;
    }

    public void setLimites(double[][] limites) {
        this.limites = limites;
    }
    public Distribucion() {
    }

    public Distribucion(double parametroUno, double parametroDos) {
        this.parametroUno = parametroUno;
        this.parametroDos = parametroDos;
    }

    public Distribucion(double parametroUno, double parametroDos, double parametroTres) {
        this.parametroUno = parametroUno;
        this.parametroDos = parametroDos;
        this.parametroTres = parametroTres;
    }

    public double getParametroTres() {
        return parametroTres;
    }

    public void setParametroTres(double parametroTres) {
        this.parametroTres = parametroTres;
    }

    public Distribucion(double parametroUno, double parametroDos, double random, ArrayList<Double> aleatorios) {
        this.parametroUno = parametroUno;
        this.parametroDos = parametroDos;
        this.random = random;
        this.aleatorios = aleatorios;
    }

    public double getParametroUno() {
        return parametroUno;
    }

    public void setParametroUno(double parametroUno) {
        this.parametroUno = parametroUno;
    }

    public double getParametroDos() {
        return parametroDos;
    }

    public void setParametroDos(double parametroDos) {
        this.parametroDos = parametroDos;
    }

    public double getRandom() {
        return random;
    }

    public void setRandom(double random) {
        this.random = random;
    }

    public ArrayList<Double> getAleatorios() {
        return aleatorios;
    }

    public void setAleatorios(ArrayList<Double> aleatorios) {
        this.aleatorios = aleatorios;
    }

    public double resultado(String texto, double random) {
        var resultado = 0.0;
        switch (texto) {
            case "Exponencial":
                resultado = exponencial(parametroUno, random);
                break;
            case "Normal":
                resultado = normal(parametroUno, parametroDos, random);

                break;
            case "Uniforme":
                resultado = uniforme(parametroUno, parametroDos, random);
                break;
            case "Erlang":

                resultado = erlang(parametroUno, parametroDos, random);
                break;
            case "Triangular":

                resultado = triangular(parametroUno, parametroDos, parametroTres, random);
                break;

            case "logNormal":

                resultado = logNormal(parametroDos, parametroTres, random);
                break;
            default:
            // code block
        }
        return resultado;
    }

    public double exponencial(double media, double r) {
        double resultado = -media * Math.log(1.0 - r);
        return resultado;
    }

    public double normal(double media, double desviacionEstandar, double r) {
        RealDistribution distribucionNormal = new NormalDistribution(media, desviacionEstandar);
        double resultado = distribucionNormal.inverseCumulativeProbability(r);
        return resultado;
    }

    public double erlang(double valorEsperado, double parametroForma, ArrayList<Double> aleatorios) {
        var multiplicacion = 1.0;
        for (Double aleatorio : aleatorios) {
            multiplicacion = multiplicacion * (1 - aleatorio);
        }
        double resultado = -(valorEsperado / parametroForma) * Math.log(multiplicacion);
        return resultado;
    }

    public double erlang(double parametroForma, double valorEsperado, double multiplicacion) {

        double resultado = -(valorEsperado / parametroForma) * Math.log(multiplicacion);
        return resultado;
    }

    public double uniforme(double minimo, double maximo, double r) {
        double resultado = minimo + ((maximo - minimo) * r);
        return resultado;
    }

    public double triangular(double min, double likeliest, double max, double r) {

        if (r <= (likeliest - min) / (max - min)) {
            double x = min + Math.sqrt(r * (max - min) * (likeliest - min));
            return x;
        } else {
            double x = max - Math.sqrt((1 - r) * (max - min) * (max - likeliest));
            return x;
        }
    }

    public double logNormal(double media, double desviacionEstandar, double z) {

        return Math.exp((media + (desviacionEstandar * z))) - 1;
    }

    public double[][] encontrarLimites(List<Double> lista) {
        double minValue = Collections.min(lista);
        double maxValue = Collections.max(lista);        
        int e = (int) Math.sqrt(lista.size());
        double c = (maxValue - minValue) / e;

        double[][] array = new double[e][2];
        for (int i = 0; i < e; i++) {
            maxValue = minValue + c;
            array[i][0] = minValue;
            array[i][1] = maxValue;
            minValue = maxValue;
        }

        return array;

    }

    public LinkedHashMap<Double, Integer> devolverFrecuencias(List<Double> lista) {
        lista.sort(Comparator.naturalOrder());
        var map = new LinkedHashMap<Double, Integer>();
        double[][] array = encontrarLimites(lista);
        setLimites(array);
        for (int k = 0; k < array.length; k++) {
            var limiteInferior = array[k][0];
            var limiteSuperior = array[k][1];
            var x = (limiteInferior + limiteSuperior) / 2;
            var frecuencia = buscarCoincidencias(limiteInferior, limiteSuperior, lista);
            map.put(x, frecuencia);
        }

        return map;
    }

    private int buscarCoincidencias(double limiteInferior, double limiteSuperior, List<Double> lista) {
        int i = 0;
        int res = 0;
        while (lista.size() > i) {
            var compardo = lista.get(i);
            if (compardo >= limiteInferior && compardo <= limiteSuperior) {
                lista.remove(i);
                i = 0;
                res++;

            } else {
                i++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Distribucion distribucion = new Distribucion();
        //51.367
        Random random = new Random();
        distribucion.setParametroUno(80);
        distribucion.setParametroDos(110);
        distribucion.setParametroTres(120);
        var lista = new ArrayList<Double>();

        for (int i = 0; i < 50; i++) {
            //System.out.println("###############################################");
            double Body_Weight = distribucion.normal(70.0, 10.0, random.nextDouble());
            //System.out.println("Body Weight " + Body_Weight);
            double Volume = distribucion.normal(2.0, 1.0, random.nextDouble());
            //System.out.println("Volume of water per day " + Volume);
            double Concentration = distribucion.resultado("Triangular", random.nextDouble());
            //System.out.println("Concentration of Contaminant " + Concentration);
            double CPF = distribucion.logNormal(0.03, 0.01, random.nextGaussian());
            // System.out.println("Cancer Potency Factor " + CPF);
            double Conv_Factor = 1.0e+03;
            double resultado = Concentration * Volume * CPF / (Body_Weight * Conv_Factor);
            resultado = Math.abs(resultado);
            String result = String.format("%.6f", resultado);
            result = result.replace(',', '.');
            lista.add(Double.valueOf(result));
            //System.out.println("Resultado " + resultado);
        }
        var map = distribucion.devolverFrecuencias(lista);
        List keys = new ArrayList(map.keySet());
        for (int i = 0; i < keys.size(); i++) {
            Double obj = (Double) keys.get(i);
            var valor = map.get(obj);
            System.out.print("X" + (i + 1) + " " + String.format("%.12f", obj));
            System.out.print(" ");
            System.out.print("Fe " + valor);
            System.out.println("");
            // do stuff here
        }

    }

}
