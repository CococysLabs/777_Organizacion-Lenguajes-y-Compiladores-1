package semana3;

import java.util.ArrayList;

public class Mago {

    private String nombre;
    private String accionInicial;
    private ArrayList<Regla> reglas;
    private String accionDefecto;

    // Estadísticas fijas de la clase Mago
    private int vidaMaxima = 100;
    private int manaMaximo = 120;
    private int poderMagico = 25;
    private int resistenciaMagica = 18;
    private int armadura = 8;
    private int velocidad = 14;
    private int ataqueFisico = 5;

    // Estado en combate
    private int vidaActual;
    private int manaActual;

    public Mago(String nombre, String accionInicial, ArrayList<Regla> reglas, String accionDefecto) {
        this.nombre = nombre;
        this.accionInicial = accionInicial;
        this.reglas = reglas;
        this.accionDefecto = accionDefecto;
        resetState();
    }

    public void resetState() {
        this.vidaActual = vidaMaxima;
        this.manaActual = manaMaximo;
    }

    public String seleccionarAccion(int numRonda) {
        if (numRonda == 0) {
            return accionInicial;
        }
        if (reglas != null) {
            for (Regla r : reglas) {
                if (r.evaluar(this.manaActual)) {
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

    public int getManaActual() {
        return manaActual;
    }

    public void setManaActual(int manaActual) {
        this.manaActual = Math.min(manaMaximo, Math.max(0, manaActual));
    }

    public int getPoderMagico() {
        return poderMagico;
    }

    public int getResistenciaMagica() {
        return resistenciaMagica;
    }

    public int getArmadura() {
        return armadura;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public int getAtaqueFisico() {
        return ataqueFisico;
    }

    public String getEstadisticas() {
        return "Vida: " + vidaActual + "/" + vidaMaxima + "\nMana: " + manaActual + "/" + manaMaximo + "\n";
    }
}