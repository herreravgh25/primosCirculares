/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package primosCirculares;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static primosCirculares.PrimosCirculares.listaPC;
/**
 *
 * @author ALFA3
 */
class Hilo extends Thread{
    
    int idHilo;
    long numeroActual;

    Hilo(int idNucleo) {
        idHilo = idNucleo;
        numeroActual = PrimosCirculares.numeroSiguiente();
    }
    
    
    private static boolean esCircular(String numero) {
        int j =0, cant=0;
        //Se crea el arreglo que va a contener todas las rotaciones del numero
        String rotaciones[] = new String[numero.length()];
        rotaciones = obtenerRotaciones(numero);
        
        //Se pregunta si todas las rotaciones del numero son numeros primos
        while(j < rotaciones.length)
        {
            if(esPrimo(Integer.parseInt(rotaciones[j]))){
                //cant lleva la cuenta de cuantos numeros son primos
                cant++;
            }
            j++;
        }
        // Si cant es igual a la longitud del numero entonces si es un numero circular
        return (cant == numero.length());
    }

    private static String[] obtenerRotaciones(String numero) {
        int i = 1;
        String resultado[] = new String[numero.length()];
        resultado[0] = numero;
       
        //Se rota el numero de acuerdo a su longitud y se guarda en un arreglo
        while (i < numero.length()){
            resultado[i] = rotarUna(resultado[i-1]);
          
            i++;
        }
        return resultado;
    }
    
    private static String rotarUna(String a){
        String b = "";
        b = a.substring(1) + a.charAt(0);
        return b;
    }

    private static boolean esPrimo(long numero){
       
        //Expresion regular
        Pattern pat = Pattern.compile("/[024568]/");
        Matcher mat = pat.matcher(String.valueOf(numero));
        
        if (numero == 2 || numero == 3 || numero == 5){
            return true;
        }else if (mat.find()){
            return false;
        }else{
           double raiz = Math.sqrt(numero);
           for(int cont=3;cont<Math.sqrt(numero);cont+=2){			
			if(numero%cont==0)
			{				
				 return false;
			}               
		}
		return true;
        }
        
    }

    public void run(){
      
        while (numeroActual < 1000000)
        {
            if(esCircular(""+numeroActual)){
               listaPC.add(numeroActual);
            }
             numeroActual= PrimosCirculares.numeroSiguiente();
            
        }
    }
}
