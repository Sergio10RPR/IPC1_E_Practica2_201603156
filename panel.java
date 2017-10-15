package ipc;
import java.awt.*;
import javax.swing.JPanel;

public class Panel extends JPanel {
 boolean [][] tablero;

public Panel(boolean [][] Tablero_Nuevo){
    tablero = Tablero_Nuevo;
}
public void setTablero(boolean[][] newTablero){
    tablero=newTablero;
}
public void paintComponent(Graphics p){
super.paintComponent(p);
double anchoCaja=(double)(this.getWidth()) / tablero[0].length;
double altoCaja=(double)(this.getHeight()) / tablero.length;
p.setColor(Color.BLACK);
for(int filas=0;filas<tablero.length;filas++){
 for(int columnas=0;columnas<tablero[0].length;columnas++  ){
 
 if(tablero[filas][columnas]==true){
  p.fillRect((int)Math.round(columnas*anchoCaja), (int)Math.round(filas*altoCaja), (int)(anchoCaja+1),(int)(altoCaja+1));
 }
 }
}
p.setColor(Color.RED);
for(int i=0;i<tablero[0].length;i++){
    p.drawLine((int)Math.round(i*anchoCaja),0,(int)Math.round(i*anchoCaja),this.getHeight());
}
   for(int j=0;j<tablero.length;j++){
    p.drawLine(0,(int)Math.round(j*altoCaja),this.getWidth(),(int)Math.round(j*altoCaja));
      
            
     }
   
    
   
    
    
    
      
    }
 }
    

    
    
   

