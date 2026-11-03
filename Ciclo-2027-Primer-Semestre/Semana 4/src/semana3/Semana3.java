package semana3;

import Analizadores.parser;
import Analizadores.scanner;

import java.io.BufferedReader;
import java.io.StringReader;

public class Semana3 {

    public static void main(String[] args) {

        try {

            String entrada =
                """
                mage Merlin {
                }

                warrior Ragnar {
                }

                mage Gandalf {
                }

                warrior Thor {
                }


                match DueloInicial {
                    players: [Merlin, Ragnar]
                    rounds: 5
                }


                match DueloFinal {
                    players: [Gandalf, Thor]
                    rounds: 10
                }


                match DueloEspecial {
                    players: [Merlin, Thor]
                    rounds: 7
                }


                main {

                    run [DueloInicial] with {
                        seed: 42
                    }

                    run [DueloEspecial] with {
                        seed: 99
                    }

                }
                """;


            // ==============================
            // ANALISIS LEXICO
            // ==============================

            scanner scanner =
                new scanner(
                    new BufferedReader(
                        new StringReader(
                            entrada
                        )
                    )
                );


            // ==============================
            // ANALISIS SINTACTICO
            // ==============================

            parser parser =
                new parser(scanner);


            var resultado =
                parser.parse();


            // ==============================
            // OBTENER ENTORNO
            // ==============================

            Entorno entorno =
                (Entorno) resultado.value;


            // ==============================
            // EJECUTAR MAIN
            // ==============================

            String salida =
                Ejecutor.ejecutar(
                    entorno
                );


            // ==============================
            // MOSTRAR RESULTADO
            // ==============================

            System.out.println(salida);


        } catch (Exception ex) {

            System.out.println(
                "Algo salio mal."
            );

            ex.printStackTrace();
        }
    }
}