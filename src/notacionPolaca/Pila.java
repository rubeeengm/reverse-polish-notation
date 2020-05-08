package notacionPolaca;

public class Pila {
    private char pila[];
    private int apuntador;

    public Pila() {
        apuntador = -1;
        pila = new char[20];
    }
    
    public boolean vacia(){
        if(apuntador == -1)
            return true;
        
        else
            return false;
    }
    
    public boolean llena(){
        if(apuntador == pila.length-1)
            return true;
        
        else
            return false;
    }
    
    public void limpiar(){
        apuntador = -1;
    }
    
    public void meter(char dato){
        if(vacia()||llena() == false){
            apuntador++;
            pila[apuntador] = dato;
        }
        
        else{
            System.out.println("La Pila esta llena, no se pueden añadir elementos");
        }
    }
    
    public char sacar(){
        if(vacia() == false){
            return pila[apuntador--];
        }
        
        else
            return 'E';
    }
    
    public void imprimir(){
        if(vacia())
            System.out.println("Pila  se encuentra vacía, no hay elementos para imprimir");
        
        else
            for(int i = 0; i <= apuntador; i++){
                System.out.println("p["+i+"]"+pila[i]);
            }
    }
}