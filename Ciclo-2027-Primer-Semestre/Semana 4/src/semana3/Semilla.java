/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package semana3;

import java.util.Random;

public class Semilla {
    
    private int seedGeneral;
    
    private Random randomJugador1;
    private Random randomJugador2;

    public Semilla(int seedGeneral) {
        this.seedGeneral = seedGeneral;
        this.randomJugador1=new Random(seedGeneral);
        this.randomJugador2=new Random(seedGeneral+1);
    }
        public int getSeedGeneral() {
        return seedGeneral;
    }

    public int getSeedJugador1() {
        return seedGeneral;
    }

    public int getSeedJugador2() {
        return seedGeneral + 1;
    }

    public double generarRandomJugador1() {
        return randomJugador1.nextDouble();
    }

    public double generarRandomJugador2() {
        return randomJugador2.nextDouble();
    }
    
    
}
