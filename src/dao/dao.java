/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import Exception.NumeroJugadorException;
import data.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
/**
 *
 * @author dieguischa
 */
public class dao {

    public Equipo cargarEquipo(String archivo) throws FileNotFoundException{
        
        Scanner sc = new Scanner(new File(archivo));
            sc.useDelimiter(",");
            String nombre= sc.next().trim();
            Equipo e1= new Equipo(nombre); 
            int PJ= sc.nextInt();
            int PG= sc.nextInt();
            e1.setPartidosGanados(PG);
            e1.setPartidosJugados(PJ);
            while(sc.hasNext()){
            String type = sc.next().trim();
           
            if (type.equals("Jugador")){
               String nombreJ=sc.next();
               String apellidoJ= sc.next();
               Jugador j1= cargarJugador(nombreJ+apellidoJ+".txt");
               e1.anadirJugador(j1);
            }
            
            }
        return e1;
    }
     public Jugador cargarJugador(String Archivo) throws FileNotFoundException{
        Scanner sc = new Scanner(new File(Archivo));
        sc.useDelimiter(","); 
        String nombre = sc.next().trim();
        String apellido = sc.next().trim();
        int numero= sc.nextInt();
        String rutaFoto=sc.next();
        int puntos= sc.nextInt();
        int asistencias= sc.nextInt();
        int rebotes= sc.nextInt();
        int rebotesOfensivos= sc.nextInt();
        int tapones= sc.nextInt();
        int robos= sc.nextInt();
        int faltas= sc.nextInt();
        int doblesAnotados= sc.nextInt();
        int intentosDoble= sc.nextInt();
        int triplesAnotados= sc.nextInt();
        int intentosTriple= sc.nextInt();
        int libresAnotados= sc.nextInt();
        int intentosLibre= sc.nextInt();
        int partidosJugados= sc.nextInt();
        Jugador j1= new Jugador(rutaFoto, puntos, asistencias, rebotes, rebotesOfensivos, rebotesOfensivos, tapones, robos, faltas, intentosDoble, intentosTriple, intentosLibre, doblesAnotados, triplesAnotados, libresAnotados, nombre, apellido, numero, partidosJugados);
        return j1;
    }
    
    public Torneo cargarTorneo(String archivo) throws FileNotFoundException{
            Scanner sc = new Scanner(new File(archivo));
            sc.useDelimiter(",");
            String nombre= sc.next().trim();
            Torneo t1= new Torneo(nombre);   
            while(sc.hasNext()){
            String type = sc.next().trim();
            if (type.equals("Partido")){
                String nombreEquipoA=sc.next();
                String nombreEquipoB=sc.next();
                Partido p1= cargarPartido(nombreEquipoA, nombreEquipoB, t1);
                try{
                t1.anadirPartido(p1,nombreEquipoA,nombreEquipoB);
                }catch(NullPointerException ex){
                    
                }
            }
            if (type.equals("Equipo")){
               String nombreE= sc.next().trim(); 
               Equipo e1= cargarEquipo(nombreE+".txt");
               t1.anadirEquipo(e1);
               
            
            }
            }
            
        return t1;
    } 

    public Partido cargarPartido(String nombreA, String nombreB, Torneo t1) throws FileNotFoundException {
            Scanner sc;     
            try{
            sc = new Scanner(new File(nombreA+nombreB+".txt"));
            } catch (FileNotFoundException ex){
                return new Partido(nombreA, nombreB, t1);
            }
            sc.useDelimiter(","); 
            String type= sc.next();
            String nombreEA= sc.next();
            String nombreEB= sc.next();
            int marcadorA=sc.nextInt();
            int marcadorB=sc.nextInt();
            String stats="";
            while (sc.hasNext()){
                stats=stats+sc.nextLine()+"\n";
            }
            
            Partido p1= new Partido(nombreA, nombreB, t1);
            p1.setMarcador(marcadorA, marcadorB);
            p1.asignarStats(stats);
            return p1;
            
    }
    
     
}
