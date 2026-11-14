package semana3;

public class Ejecutor {

    public static String ejecutar(Entorno entorno) {
        StringBuilder salida = new StringBuilder();

        for (Ejecucion ejecucion : entorno.getEjecuciones()) {
            String nombrePartida = ejecucion.getPartida();
            int seed = ejecucion.getSeed();

            Partida partida = entorno.getPartidas().get(nombrePartida);

            if (partida == null) {
                salida.append("ERROR: No existe la partida ").append(nombrePartida).append("\n");
                continue;
            }

            salida.append(ejecutarPartida(partida, seed, entorno));
        }

        return salida.toString();
    }

    private static String ejecutarPartida(Partida partida, int seed, Entorno entorno) {
        StringBuilder salida = new StringBuilder();

        salida.append("\n============================\n");
        salida.append("PARTIDA: ").append(partida.getNombre()).append("\n");
        salida.append("============================\n");

        Object p1 = obtenerCombatiente(partida.getJugador1(), entorno);
        Object p2 = obtenerCombatiente(partida.getJugador2(), entorno);

        if (p1 == null || p2 == null) {
            return "ERROR: Uno o ambos combatientes no existen.\n";
        }

        resetCombatiente(p1);
        resetCombatiente(p2);

        salida.append("Jugador 1: ").append(getNombre(p1)).append("\n");
        salida.append("Jugador 2: ").append(getNombre(p2)).append("\n");
        salida.append("Rondas programadas: ").append(partida.getRondas()).append("\n\n");

        for (int r = 0; r < partida.getRondas(); r++) {
            if (getVidaActual(p1) <= 0 || getVidaActual(p2) <= 0) {
                salida.append("!Combate terminado antes de tiempo por K.O.!\n\n");
                break;
            }

            String act1 = seleccionarAccion(p1, r);
            String act2 = seleccionarAccion(p2, r);

            salida.append("--- Ronda ").append(r).append(" ---\n");
            salida.append("  ").append(getNombre(p1)).append(" usa: ").append(act1).append("\n");
            salida.append("  ").append(getNombre(p2)).append(" usa: ").append(act2).append("\n");

            // Determinar orden según prioridad y velocidad
            int prio1 = getPrioridad(act1);
            int prio2 = getPrioridad(act2);

            boolean p1Primero = true;
            if (prio1 < prio2) {
                p1Primero = false;
            } else if (prio1 == prio2) {
                p1Primero = getVelocidad(p1) >= getVelocidad(p2);
            }

            if (p1Primero) {
                aplicarAccion(p1, p2, act1);
                if (getVidaActual(p2) > 0) {
                    aplicarAccion(p2, p1, act2);
                }
            } else {
                aplicarAccion(p2, p1, act2);
                if (getVidaActual(p1) > 0) {
                    aplicarAccion(p1, p2, act1);
                }
            }

            salida.append("  Estado: ").append(getNombre(p1)).append(" HP=").append(getVidaActual(p1))
                  .append(" | ").append(getNombre(p2)).append(" HP=").append(getVidaActual(p2)).append("\n\n");
        }

        salida.append("============================\n");
        salida.append("RESULTADO FINAL DE LA PARTIDA\n");
        salida.append("============================\n");
        salida.append(getNombre(p1)).append(" -> Vida Final: ").append(getVidaActual(p1)).append("\n");
        salida.append(getNombre(p2)).append(" -> Vida Final: ").append(getVidaActual(p2)).append("\n");

        return salida.toString();
    }

    private static Object obtenerCombatiente(String nombre, Entorno entorno) {
        if (entorno.getMagos().containsKey(nombre)) return entorno.getMagos().get(nombre);
        if (entorno.getGuerreros().containsKey(nombre)) return entorno.getGuerreros().get(nombre);
        return null;
    }

    private static void resetCombatiente(Object p) {
        if (p instanceof Mago) ((Mago) p).resetState();
        if (p instanceof Guerrero) ((Guerrero) p).resetState();
    }

    private static String getNombre(Object p) {
        if (p instanceof Mago) return ((Mago) p).getNombre();
        if (p instanceof Guerrero) return ((Guerrero) p).getNombre();
        return "";
    }

    private static int getVidaActual(Object p) {
        if (p instanceof Mago) return ((Mago) p).getVidaActual();
        if (p instanceof Guerrero) return ((Guerrero) p).getVidaActual();
        return 0;
    }

    private static int getVelocidad(Object p) {
        if (p instanceof Mago) return ((Mago) p).getVelocidad();
        if (p instanceof Guerrero) return ((Guerrero) p).getVelocidad();
        return 0;
    }

    private static String seleccionarAccion(Object p, int numRonda) {
        if (p instanceof Mago) return ((Mago) p).seleccionarAccion(numRonda);
        if (p instanceof Guerrero) return ((Guerrero) p).seleccionarAccion(numRonda);
        return "";
    }

    private static int getPrioridad(String accion) {
        if (accion.equalsIgnoreCase("ARCANE_BOLT") || accion.equalsIgnoreCase("SLASH")) return 4;
        if (accion.equalsIgnoreCase("MEDITATE") || accion.equalsIgnoreCase("REST")) return 1;
        return 1;
    }

    private static void aplicarAccion(Object atacante, Object defensor, String accion) {
        if (accion.equalsIgnoreCase("ARCANE_BOLT")) {
            // Ataque Mágico: Costo 10 Maná, Poder 12
            if (atacante instanceof Mago) {
                Mago m = (Mago) atacante;
                if (m.getManaActual() >= 10) {
                    m.setManaActual(m.getManaActual() - 10);
                    int poderMagicoAtacante = m.getPoderMagico();
                    int resMagicaDefensor = (defensor instanceof Mago) ? ((Mago) defensor).getResistenciaMagica() : ((Guerrero) defensor).getResistenciaMagica();
                    int danio = Math.max(1, 12 + poderMagicoAtacante - resMagicaDefensor);
                    hacerDanio(defensor, danio);
                }
            }
        } else if (accion.equalsIgnoreCase("SLASH")) {
            // Ataque Físico: Costo 10 Energía, Poder 12
            if (atacante instanceof Guerrero) {
                Guerrero g = (Guerrero) atacante;
                if (g.getEnergiaActual() >= 10) {
                    g.setEnergiaActual(g.getEnergiaActual() - 10);
                    int ataFisicoAtacante = g.getAtaqueFisico();
                    int armaduraDefensor = (defensor instanceof Mago) ? ((Mago) defensor).getArmadura() : ((Guerrero) defensor).getArmadura();
                    int danio = Math.max(1, 12 + ataFisicoAtacante - armaduraDefensor);
                    hacerDanio(defensor, danio);
                }
            }
        } else if (accion.equalsIgnoreCase("MEDITATE")) {
            // Recuperación Mago: Recupera 25 Maná
            if (atacante instanceof Mago) {
                Mago m = (Mago) atacante;
                m.setManaActual(m.getManaActual() + 25);
            }
        } else if (accion.equalsIgnoreCase("REST")) {
            // Recuperación Guerrero: Recupera 25 Energía
            if (atacante instanceof Guerrero) {
                Guerrero g = (Guerrero) atacante;
                g.setEnergiaActual(g.getEnergiaActual() + 25);
            }
        }
    }

    private static void hacerDanio(Object defensor, int danio) {
        if (defensor instanceof Mago) {
            Mago m = (Mago) defensor;
            m.setVidaActual(m.getVidaActual() - danio);
        } else if (defensor instanceof Guerrero) {
            Guerrero g = (Guerrero) defensor;
            g.setVidaActual(g.getVidaActual() - danio);
        }
    }
}