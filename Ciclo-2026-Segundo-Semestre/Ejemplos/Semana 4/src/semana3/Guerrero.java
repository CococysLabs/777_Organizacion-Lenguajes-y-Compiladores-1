package semana3;

import java.util.ArrayList;

public class Guerrero {

    private String nombre;
    private String accionInicial;
    private ArrayList<Regla> reglas;
    private String accionDefecto;

    // Estadísticas fijas de la clase Guerrero
    private int vidaMaxima = 140;
    private int energiaMaxima = 100;
    private int ataqueFisico = 22;
    private int armadura = 20;
    private int resistenciaMagica = 8;
    private int velocidad = 10;
    private int poderMagico = 0;

    // Estado en combate
    private int vidaActual;
    private int energiaActual;

    public Guerrero(String nombre, String accionInicial, ArrayList<Regla> reglas, String accionDefecto) {
        this.nombre = nombre;
        this.accionInicial = accionInicial;
        this.reglas = reglas;
        this.accionDefecto = accionDefecto;
        resetState();
    }

    public void resetState() {
        this.vidaActual = vidaMaxima;
        this.energiaActual = energiaMaxima;
    }

    public String seleccionarAccion(int numRonda) {
        if (numRonda == 0) {
            return accionInicial;
        }
        if (reglas != null) {
            for (Regla r : reglas) {
                if (r.evaluar(this.energiaActual)) {
                    return r.getAccion();
                }
            }
        }
        return accionDefecto;
    }

    public String getNombre() {
        return nombre;
    }

    public int getVidaActual() {
        return vidaActual;
    }

    public void setVidaActual(int vidaActual) {
        this.vidaActual = Math.max(0, vidaActual);
    }

    public int getEnergiaActual() {
        return energiaActual;
    }

    public void setEnergiaActual(int energiaActual) {
        this.energiaActual = Math.min(energiaMaxima, Math.max(0, energiaActual));
    }

    public int getAtaqueFisico() {
        return ataqueFisico;
    }

    public int getArmadura() {
        return armadura;
    }

    public int getResistenciaMagica() {
        return resistenciaMagica;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public int getPoderMagico() {
        return poderMagico;
    }

    public String getEstadisticas() {
        return "Vida: " + vidaActual + "/" + vidaMaxima + "\nEnergia: " + energiaActual + "/" + energiaMaxima + "\n";
    }
}
