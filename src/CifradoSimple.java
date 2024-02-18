
/*
Se pide inventar un método de cifrado que nos permita cifrar un texto y descifrarlo
posteriormente. Este método inventado debe ser distinto César. Implementar
una aplicación Java que nos proporcione estos dos metodos:
public String cifrar(String mensaje);
public String descifrar(Sring mensaje);
 */

import java.util.Random;

public class CifradoSimple {
    //Patrón más largo y complejo

    private static final Random RANDOM = new Random();

    public static String cifrar(String mensaje, String clave){
        StringBuilder mensajeCifrado = new StringBuilder();

        for (int i = 0; i < mensaje.length(); i++){
            char caracter = mensaje.charAt(i);
            char caracterClave = clave.charAt(i % clave.length());

            if (Character.isLetter(caracter)){
                char caracterCifrado = cifrarCaracter (caracter, caracterClave);
                mensajeCifrado.append(caracterCifrado);
            }else if (Character.isWhitespace(caracter)){
                mensajeCifrado.append(RANDOM.nextInt(10)); //Agrega un número aleatorio en lugar de espacio
            }else {
                mensajeCifrado.append(caracter);
            }
        }
        return mensajeCifrado.toString();
    }

    private static char cifrarCaracter(char caracter, char clave) {
        int desplazamiento = clave - 'A';
        char caracterCifrado = (char) (caracter + desplazamiento);

        if (Character.isUpperCase(caracter) && caracterCifrado > 'Z'){
            caracterCifrado = (char) (caracterCifrado - 26);
        } else if (Character.isLowerCase(caracter) && caracterCifrado > 'z') {
            caracterCifrado = (char) (caracterCifrado - 26);
        }
        return caracterCifrado;
    }

    public static String descifrar(String mensajeCifrado, String clave){
        StringBuilder mensajeDescifrado = new StringBuilder();

        for (int i = 0; i < mensajeCifrado.length(); i++){
            char caracterCifrado = mensajeCifrado.charAt(i);
            char caracterClave = clave.charAt(i % clave.length());

            if (Character.isLetter(caracterCifrado)){
                char caracterDescifrado = descifrarCaracter(caracterCifrado, caracterClave);
                mensajeDescifrado.append(caracterDescifrado);
            } else if (Character.isDigit(caracterCifrado)) {
                mensajeDescifrado.append(' '); //Reemplaza los números con espacios
            }else {
                mensajeDescifrado.append(caracterCifrado);
            }
        }

        return mensajeDescifrado.toString();
    }

    private static char descifrarCaracter(char caracterCifrado, char clave) {
        int desplazamiento = clave - 'A';
        char caraterDescifrado = (char) (caracterCifrado - desplazamiento);

        if (Character.isUpperCase(caracterCifrado) && caraterDescifrado < 'A'){
            caraterDescifrado = (char) (caraterDescifrado + 26);
        } else if (Character.isLowerCase(caracterCifrado) && caraterDescifrado < 'a') {
            caraterDescifrado = (char) (caraterDescifrado + 26);
        }
        return caraterDescifrado;
    }

    public static void main(String[] args) {
        String mensajeOriginal = "Hola, esto es un mensaje de prueba!";
        System.out.println("Mensaje Original: " + mensajeOriginal);

        String clave = "SECRETA"; //Clave secreta para el cifrado
        String mensajeCifrado = cifrar(mensajeOriginal, clave);
        System.out.println("Mensaje Cifrado: " + mensajeCifrado);

        String mensajeDescifrado = descifrar(mensajeCifrado, clave);
        System.out.println("Mensaje Descifrado: " + mensajeDescifrado);
    }
}
