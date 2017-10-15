package ipc;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.text.TableView;


public class JuegoVida extends JFrame  implements MouseListener,ActionListener,Runnable{
 
JFrame frame=new JFrame("JUEGO_DE_LA_VIDA");
 static int tamaño;
public static int velocidad;
boolean [][] tablero=new boolean[tamaño][tamaño];

Panel lamina=new Panel(tablero);
JButton salir=new JButton("SALIR");
JButton start=new JButton("START");
JButton pausa=new JButton("PAUSA");
Container panel_sur =new Container();
Container panel_norte=new Container();
boolean iniciar=false;
boolean terminar=false;
JScrollBar scroll = new JScrollBar(Adjustable.HORIZONTAL); 

public JuegoVida(){
    
    
   
    
    Dimension pantalla = Toolkit. getDefaultToolkit(). getScreenSize();
    int alto = pantalla. height;
    int ancho = pantalla. width;
    frame.setSize(ancho/2, alto/2);
    frame.setLocationRelativeTo(null);
    scroll.setValue(5);
    velocidad=1;
    frame.setSize((tamaño*20),(tamaño*20));
    frame.setLayout(new BorderLayout());
    frame.add(lamina,BorderLayout.CENTER);
    
    panel_sur.setLayout(new GridLayout(1,3));
    panel_sur.add(salir);
    salir.addActionListener(this);
    salir.setForeground(Color.BLUE);
    start.setForeground(Color.BLUE);
    pausa.setForeground(Color.BLUE);
    panel_sur.add(start);
    start.addActionListener(this);
    panel_sur.add(pausa);
    pausa.addActionListener(this);
    panel_sur.add(scroll);
    frame.add(panel_sur, BorderLayout.SOUTH);
    lamina.addMouseListener(this);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
    frame.setVisible(true);
     //scroll.setBounds(new Rectangle(30,30,100,200)); 
     
     
    
    


scroll.addAdjustmentListener(new AdjustmentListener() {
public void adjustmentValueChanged(AdjustmentEvent ae) {
        if (scroll.getValueIsAdjusting())
            return;
        //System.out.println("VELOCIDAD : " + ae.getValue()*0.1 + " SEGUNDOS_DE_INTERVALO");
        velocidad =ae.getValue();
      }
});

}   
@Override
public void mouseClicked(MouseEvent e) {
           
            
        }

        @Override
        public void mousePressed(MouseEvent e) {
            
        }

        @Override
public void mouseReleased(MouseEvent e) {


double ancho=(double)lamina.getWidth()/tablero[0].length;
double alto=(double)lamina.getHeight()/tablero.length;
int columna=Math.min(tablero[0].length-1,(int)(e.getX()/ancho));
int fila=Math.min(tablero.length-1,(int)(e.getY()/alto));

tablero[fila][columna] = !tablero[fila][columna];
frame.repaint();
        



        }

        @Override
        public void mouseEntered(MouseEvent e) {
            
        }

        @Override
        public void mouseExited(MouseEvent e) {
            
        }
    



 


    
  

    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource().equals(salir)){
           
           //proceso();
           System.exit(0);
           
       }
       if(e.getSource().equals(start)){
           if(iniciar == false){
           iniciar=true;
           Thread t=new Thread (this);
           t.start();
              
           } 
       }
       if(e.getSource().equals(pausa)){
           iniciar=false;
       }
    }
    public void run(){
        while(iniciar == true){
              proceso();
               
     try{
               Thread.sleep(1000-10*velocidad); //Controla la Velocidad juego
     }catch(Exception ex){
         ex.printStackTrace();
         
         
     }
               
           }
    }
    

   
    public void proceso(){
    
    boolean [][] Tablero_Nuevo=new boolean [tablero.length][tablero[0].length];
    for(int x=0; x < tablero.length; x++){
     for(int y=0; y < tablero[0].length; y++){
         int contador = 0;
      if(x > 0 && y > 0 && tablero[x-1][y-1] == true){
          contador++;
         } 
      if(x > 0 && tablero[x-1][y] == true){
          contador++;
      }
      
      if(x > 0 && y < tablero[0].length-1 && tablero[x-1][y+1] == true){
          contador++;   
      }
      if(y > 0 && tablero[x][y-1] == true){
          contador++;
      }
      if(y<tablero[0].length-1 && tablero[x][y+1] == true){
          contador++;
      }
      if(x < tablero.length-1 && y>0 && tablero[x+1][y-1] == true ){
          contador++;
      }
      if(x < tablero.length-1 && tablero[x+1][y] == true){
          contador++;
      }
      if(x < tablero.length-1 && y < tablero[0].length-1 && tablero[x+1][y+1]== true ){
          contador++;
      }
      
      
     if(tablero[x][y] == true){
         if(contador == 2 || contador == 3){
           Tablero_Nuevo[x][y]=true;  
         }
         else{
             Tablero_Nuevo[x][y]=false;
         }
     
     }
     else{
         if(contador == 3){
             Tablero_Nuevo[x][y]=true;
         }
         else{
             Tablero_Nuevo[x][y]=false; 
        }
     }
     }
     }
    tablero=Tablero_Nuevo;
    lamina.setTablero(Tablero_Nuevo);
    frame.repaint();
    }

    public static  class Dimension_t{
     
     public Dimension_t(){
     String ingresarT=JOptionPane.showInputDialog("Ingrese Tamaño del Tablero");
     int x=Integer.parseInt(ingresarT);
     tamaño=x;
     
         
     }
     }
    public static void main(String[] args){
        
    Dimension_t a = new Dimension_t();
    
    
    new Dimension_t();

    if(tamaño > 0){
         new JuegoVida();
    }
    else if(tamaño < 0){
        String mensaje = JOptionPane.showInputDialog("Valor no aceptado");
        
    }
    
        
    }
    
    
}

