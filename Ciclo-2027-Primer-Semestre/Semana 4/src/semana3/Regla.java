/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package semana3;

public class Regla {
    private String variable;
    private String operador;
    private int valor;
    private String accion;

    public Regla(String variable, String operador, int valor, String accion) {
        this.variable = variable;
        this.operador = operador;
        this.valor = valor;
        this.accion = accion;
    }

    public boolean evaluar(int valorVariable) {
        if (operador.equals("<=")) return valorVariable <= valor;
        if (operador.equals("<")) return valorVariable < valor;

        return false;
    }

    public String getAccion() {
        return accion;
    }

    public String getVariable() {
        return variable;
    }
}
