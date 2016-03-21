
package primosCirculares;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class PrimosCirculares {

   
    private static long numSiguente = 2;
    public static final List <Long> listaPC= new ArrayList();
    private static boolean algunosCasos = true;
    
    
     private static boolean esCandidato(String numero){ 
    // _Los candidatos a ser primos circualres tiene que contener el numero 1, 3, 7,9
        boolean esPosible = true;
        for (int i=0 ; i<numero.length() ; i++){
            char c = numero.charAt(i);
            if ((c!='1') && (c!='3') && (c!='7') && (c!='9')){
                esPosible = false;
                break;
            }
        }
        return esPosible;
    }
    
    //La iteracion de busqueda de numeros primos circulares no es secuencial, se realizan saltos 
    public synchronized static long numeroSiguiente(){
        long result = numSiguente;
         if (!algunosCasos){
            boolean esCandidato = false;
            while (!esCandidato){
                int num = (int)(numSiguente % 30);
                switch (num){
                    case 1:
                    case 23:{ numSiguente += 6; break; }
                    case 7:
                    case 13:
                    case 19:{ numSiguente += 4; break; }
                    case 11:
                    case 17:
                    case 29:{ numSiguente += 2; break; }
                }
                esCandidato = esCandidato(""+numSiguente);
            }
        }else{
            switch ((int)numSiguente){
                case 2:{ numSiguente=3; break; }
                case 3:{ numSiguente=5; break; }
                case 5:{ numSiguente=7; algunosCasos=false; break; }
            }
        }
        return result;
    }
    
   
    
    public static void main(String[] args) {
        //Se obtiene el numero de nucleos que tiene el procesador para crear esa cantidad de hilos
        int numerodeNucleos = Runtime.getRuntime().availableProcessors();
        Hilo[] hilos = new Hilo[numerodeNucleos];
        
        
        //Se crean los hilos
        for (int idNucleo=0 ; idNucleo<numerodeNucleos ; idNucleo++){
            hilos[idNucleo] = new Hilo(idNucleo);
            hilos[idNucleo].start();
        }
        
        //Espero a que terminen los hilos
        try {
            Thread.currentThread().sleep( 1000 );
        }catch( InterruptedException e ){}
        
        //Ordeno la lista
        Collections.sort(listaPC);
        
        // Se muestran los resultados
        System.out.println("************Primos Circulares**************");
        System.out.println(" Total: "+listaPC.size()+" numeros");
        System.out.print(" Números: ");
        Iterator it = listaPC.iterator(); 
            while(it.hasNext())                    
        System.out.print(it.next()+",");
        System.out.println("");    
        System.out.println("*******************************************");
        
         for (int idNucleo=0 ; idNucleo<numerodeNucleos ; idNucleo++){
           
            hilos[idNucleo].stop();
        }
}
        
}


    



