package com.example.libercomus;

import java.util.Random;

public class Partida {


    public  Partida (int dificultad){

        this.dificultad=dificultad;
        jugador=1;


        casillas=new int[9];
        for(int i=0; i<9; i++){
            casillas[i]=0;
        }

    }

    public boolean compruebaCasilla(int casilla){
        if(casillas[casilla]!=0){
            return false;
        }else{
            casillas[casilla]=jugador;
        }
        return true;
    }


   public int turno(){

       boolean empate=true;

       boolean ultimomov=true;

        for (int i=0;i<COMBINACIONES.length;i++){


            for (int pos:COMBINACIONES[i]){

              //  System.out.println("valor posicion "+ pos + " "+ casillas[pos]);

                if(casillas[pos]!=jugador)ultimomov=false;

                if(casillas[pos]==0){

                    empate=false;

                }
            }//cierre for anidado

            //System.out.println("----------------------------------------------------------- ");

            if(ultimomov)return jugador;

            ultimomov=true;

        }//cierre for principal

        //es importante donde poner el return porque lo have salir del metodo
        if(empate){
            return 3;
        }


        jugador++;

        if(jugador>2){
            jugador=1;
        }

        return 0;
   }

   public int dosenraya(int jugador_en_turno){

        int casilla=-1; //casilla que no exista

        int cuantasLleva=0;

       for (int i=0;i<COMBINACIONES.length;i++){


           for (int pos:COMBINACIONES[i]) {

               if(casillas[pos]==jugador_en_turno) cuantasLleva++;
               if(casillas[pos]==0) casilla=pos;


           }

           if (cuantasLleva==2 && casilla!=-1) return casilla;

           casilla=-1;
           cuantasLleva=0;
       }

       return -1;

           }



    public int ia(){
        int casilla;

        casilla=dosenraya(2);

        if(casilla!=-1) return casilla;





        if(dificultad>0){

            casilla=dosenraya(1);

            if (casilla!=-1) return casilla;
        }

        if(dificultad==2){


            if(casillas [4]==0)return 4;
            if(casillas [0]==1 && casillas[7] ==1 && casillas[6]==0) return 6;
            if(casillas [0]==1 && casillas[5] ==1&& casillas [2]==0) return 2;

            if(casillas [2]==1 && casillas[3] ==1 && casillas[0]==0) return 0;
            if(casillas [2]==1 && casillas[7] ==1 && casillas [8]==0) return 8;

            if(casillas [6]==1 && casillas[1] ==1 && casillas[0]==0) return 0;
            if(casillas [6]==1 && casillas[5] == 1&& casillas [8]==0) return 8;

            if(casillas [8]==1 && casillas[1] ==1 && casillas[2]==0) return 2;
            if(casillas [8]==1 && casillas[3] ==1 && casillas [6]==0) return 6;

            if(casillas [2]==1 && casillas[6] ==1 && casillas [3]==0) return 3;
            if(casillas [2]==1 && casillas[6] ==1 && casillas [5]==0) return 5;

            if(casillas [0]==1 && casillas[7] ==1 && casillas [1]==0) return 1;
            if(casillas [0]==1 && casillas[7] ==1 && casillas [3]==0) return 3;


            if(casillas[0]==1 && casillas[1]==0 )return 1;
            if(casillas[2]==1 && casillas[6]==0)return 6;
            if(casillas[6]==1 && casillas[2]==0)return 2;
            if(casillas[8]==1 && casillas[0]==0)return 0;

            if(casillas[0]==0  )return 0;
            if(casillas[2]==0 )return 2;
            if(casillas[6]==0 )return 6;
            if(casillas[8]==0 )return 8;


        }


        Random casilla_azar=new Random();

        casilla=casilla_azar.nextInt(9);

        return casilla;

    }


    public final int dificultad;
    public int jugador;
    private int []casillas;
    private final int [][] COMBINACIONES={{0,1,2}, {3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

}
