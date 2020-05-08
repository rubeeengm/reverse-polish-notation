package notacionPolaca;

import java.util.Scanner;
import java.util.Stack;

public class TesterNotacionPolaca {
    static Pila PilaConstantes = new Pila();
    static Pila PilaOperadores = new Pila();
    static String operadores = "()+-*/^";
    static String constantes = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    
    public static void main(String[] args){
        TesterNotacionPolaca p = new TesterNotacionPolaca();
        Scanner teclado = new Scanner(System.in);
        String expresion;
        Boolean salir = false;
        
        do{
            System.out.println("Conversion de expresiones INFIJAS a POSFIJAS\n");
            System.out.println("Ingresa la expresión que deseas convertir: ");
            expresion = teclado.next();
            
            if(p.validarExpresion(expresion) == false){
                System.out.println("Expresión invalida\n\n\n\n\n");
            }
            
            else{
            	if(p.verificaParentesis(expresion) == false){
            		System.out.println("Expresión inválida\n\n\n\n\n");
            	}
            
            	else{
            		salir =  true;
            	}
            }
        }while(salir == false);
        
        p.conversor(expresion);
        
        String nuevaexpresion="";
        
        do{
            nuevaexpresion += PilaConstantes.sacar();
        }while(PilaConstantes.vacia() == false);
        
        StringBuilder cadenafinal = new StringBuilder(nuevaexpresion);
        String scadenafinal = cadenafinal.reverse().toString();
        System.out.println("\nCadena Final: "+scadenafinal);
    }
    
    public void conversor(String expresion){
        int siguiente;
        int parentesis[] = new int[10];//SE GUARDA LA POSICION EN DONDE SE INSERTO EL PRIMER PARENTESIS
        int aParentesis = -1; //INDICE DEL ARREGLO parentesis[]
        int contadorO = -1;
        int operadorAnterior[] = new int[10];
        int aAnterior = -1;
        int anterior = -1;
        Boolean salir = false;
        
        for(int i = 0; i<expresion.length(); i++){
            for(int j = 0; j<operadores.length(); j++){
                if(expresion.charAt(i) == operadores.charAt(j)){
                    salir = false;
                    switch(expresion.charAt(i)){
                        case '(':
                            siguiente = 0;
                            contadorO++;
                        break;
                            
                        case '+':
                        case '-':
                            siguiente = 1;
                            contadorO++;
                        break;
                            
                        case '*':
                        case '/':
                            siguiente = 2;
                            contadorO++;
                        break;
                        
                        case '^':
                            siguiente = 3;
                            contadorO++;
                        break;
                            
                        default:
                            siguiente = 4;
                        break;
                    }
                    
                    do{
                        if(siguiente == 0){
                            anterior = siguiente;
                            PilaOperadores.meter(expresion.charAt(i));
                            aAnterior++;
                            aParentesis++;
                            operadorAnterior[aAnterior] = siguiente;
                            parentesis[aParentesis] = contadorO;
                            salir = true;
                        }
                    
                        else{
                            if(siguiente == 4){
                                while(parentesis[aParentesis]<contadorO){
                                    PilaConstantes.meter(PilaOperadores.sacar());
                                    contadorO--;
                                    aAnterior--;
                                }
                                
                                PilaOperadores.sacar();
                                aParentesis--;
                                aAnterior--; 
                                salir = true;
                                
                                if(aAnterior == -1)
                                    anterior = -1;
                                
                                else
                                    anterior = operadorAnterior[aAnterior];
                            }
                            
                            else{
                                if(anterior >= siguiente){                                    
                                    PilaConstantes.meter(PilaOperadores.sacar());
                                    contadorO--;
                                    aAnterior--;
                                    if(aAnterior == -1)
                                        anterior = -1;
                                    else
                                        anterior = operadorAnterior[aAnterior];
                                }
                                
                                else{
                                    if(anterior < siguiente){
                                        aAnterior++;
                                        PilaOperadores.meter(expresion.charAt(i));
                                        operadorAnterior[aAnterior] = siguiente;
                                        salir = true;
                                        anterior = siguiente;
                                    }
                                }
                            }
                        }
                    }while(salir == false);
                }
            }   

            for(int k = 0; k<constantes.length(); k++){
                if(expresion.charAt(i) == constantes.charAt(k)){
                    PilaConstantes.meter(expresion.charAt(i));
                    break;
                }
            }
        }
        while(PilaOperadores.vacia() == false){
            PilaConstantes.meter(PilaOperadores.sacar());
        }
    }
    
    public Boolean validarExpresion(String expresion){
        Boolean salir = false;
        
        for(int i = 0; i<expresion.length(); i++){
            salir = false;
            
            for(int j = 0; j<operadores.length(); j++){
                if(expresion.charAt(i) == operadores.charAt(j)){
                    salir = true;
                    break;
                }
            }   
            
            for(int k = 0; k<constantes.length(); k++){
                if(expresion.charAt(i) == constantes.charAt(k)){
                    salir = true;
                    break;
                }
            }
            
            if(salir == false){
                break;
            }
        }
        
        return salir;
    }
    
    public Boolean verificaParentesis(String cadena) {
        Stack<String> pila = new Stack<>(); 
        int i = 0;

        while (i<cadena.length()) { // Recorremos la expresion caracter a caracter
            if(cadena.charAt(i)=='(') {
                pila.push("(");
            } // Si el parentesis es de apertura apilamos siempre
        
            else 
                if (cadena.charAt(i)==')') { // Si el parentesis es de cierre actuamos segun el caso
                    if (!pila.empty()){
                        pila.pop(); 
                    } // Si la pila no esta vacia desapilamos
                
                    else { 
                        pila.push(")"); 
                        break; 
                    } // La pila no puede empezar con un cierre, apilamos y salimos
                }
            
            i++;
        }
    
        if(pila.empty()){ 
            return true; 
        } 
    
        else { 
            return false; 
        }
    }
}