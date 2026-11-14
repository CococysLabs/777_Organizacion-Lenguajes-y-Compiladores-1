/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package semana3;

import java.util.ArrayList;
import java.util.HashMap;

public class Entorno {

    private HashMap<String, Mago> magos;
    private HashMap<String, Guerrero> guerreros;
    private HashMap<String, Partida> partidas;

    private ArrayList<Ejecucion> ejecuciones;

    public Entorno() {

        magos = new HashMap<>();
        guerreros = new HashMap<>();
        partidas = new HashMap<>();

        ejecuciones = new ArrayList<>();
    }

    // =========================
    // AGREGAR
    // =========================

    public void agregarMago(Mago mago) {

        magos.put(
                mago.getNombre(),
                mago
        );
    }

    public void agregarGuerrero(Guerrero guerrero) {

        guerreros.put(
                guerrero.getNombre(),
                guerrero
        );
    }

    public void agregarPartida(Partida partida) {

        partidas.put(
                partida.getNombre(),
                partida
        );
    }

    public void agregarEjecucion(Ejecucion ejecucion) {

        ejecuciones.add(ejecucion);
    }


    // =========================
    // OBTENER
    // =========================

    public HashMap<String, Mago> getMagos() {
        return magos;
    }

    public HashMap<String, Guerrero> getGuerreros() {
        return guerreros;
    }

    public HashMap<String, Partida> getPartidas() {
        return partidas;
    }

    public ArrayList<Ejecucion> getEjecuciones() {
        return ejecuciones;
    }
}