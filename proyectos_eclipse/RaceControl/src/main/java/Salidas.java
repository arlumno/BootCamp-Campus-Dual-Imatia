
/**
 *  Mensajes de la libreria con salida directa por consola.
 * @author Ar
 */
public class Salidas {
  
    /**
     *  Mensaje de error para cuando el valor est� fuera del rango requerido, aunque coincida el tipo de dato
     */
    public static void errorFueraRango() {
        System.out.println("Valor introducido fuera del rango requerido. ");
    }
    
    /**
     *  Mensaje de error para cuando el valor es null.
     */
    public static void errorVacio() {
        System.out.println("Valor introducido no puede est�r vacio.");
    }
    /**
     *  Mensaje de error para cuando el menu no tiene opciones.
     */
    public static void errorMenuVacio() {
        System.out.println("Valor introducido no puede est�r vacio.");
    }
    
    /**
     *  Mensaje de error para cuando se ha alcanzado un limite m�ximo.
     */
    public static void errorLimite() {
        System.out.println("Limite m�ximo alcanzado.");
    }
    
    /**
     * Mensaje de error para cuando el tipo no coincide.
     */
    public static void errorTipo() {
        System.out.println("Datos introducidos NO V�lidos. El tipo no coincide.");
    }
    
    /**
     * Mensaje para indicar que el proceso se vuelve a repetir debido a un error previo.
     */
    public static void errorReintentarIntroducir() {
        System.out.println("Vuelva a introducirlo.");
    }
    
    /**
     * Mensaje con instrucciones para volver al men� anterior.
     */
    public static void repetirMenu(){
        System.out.println("\n Pulsa Enter para volver al men�.");
    }    
    public static void saltoLinea(){
        System.out.println("\b");
    }
}
