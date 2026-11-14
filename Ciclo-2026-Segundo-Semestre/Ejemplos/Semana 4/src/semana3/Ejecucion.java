/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package semana3;

import java.util.ArrayList;

public class Ejecucion {

    private String partida;
    private int seed;

    public Ejecucion(String partida, int seed) {
        this.partida = partida;
        this.seed = seed;
    }

    public String getPartida() {
        return partida;
    }

    public int getSeed() {
        return seed;
    }
}