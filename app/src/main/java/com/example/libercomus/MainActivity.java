package com.example.libercomus;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //iniciamos el array casillas que identifica cada casilla y la almacena en el array
        CASILLAS=new int[9];
        CASILLAS [0]=R.id.a1;
        CASILLAS [1]=R.id.a2;
        CASILLAS [2]=R.id.a3;
        CASILLAS [3]=R.id.b1;
        CASILLAS [4]=R.id.b2;
        CASILLAS [5]=R.id.b3;
        CASILLAS [6]=R.id.c1;
        CASILLAS [7]=R.id.c2;
        CASILLAS [8]=R.id.c3;



    }

     public void aJugar(View vista){
         ImageView imagen;

         for(int cadaCasilla:CASILLAS){
             imagen=(ImageView)findViewById(cadaCasilla);

         imagen.setImageResource(R.drawable.blank);
         }

        jugadores=1;

         if(vista.getId()==R.id.dosjug){
             jugadores=2;
         }

         RadioGroup configDificultad=(RadioGroup)findViewById(R.id.configD);

                 int id=configDificultad.getCheckedRadioButtonId();

                int dificultad=0;

         if(id==R.id.normal){

             dificultad=1;
         }else if(id==R.id.imposible){

             dificultad=2;
         }


         partida=new Partida(dificultad);

         ((Button)findViewById(R.id.unjugador)).setEnabled(false);
         ((RadioGroup)findViewById(R.id.configD)).setAlpha(0.30f);
         ((Button)findViewById(R.id.dosjug)).setEnabled(false);



     }

     public void toque(View mivista){

        if(partida==null){//si no se selecciono dificultad o jugador no cambian las casillas

            return;//salir del metodo sin hacer nada
        }



         int casilla=0;
         for(int i=0; i<9;i++){

             if(CASILLAS[i]==mivista.getId()){
                 casilla=i;
                 break;
             }
         }


         if (partida.compruebaCasilla(casilla)==false){
             return;
         }

         marca(casilla);

         int resultado=partida.turno();

         if(resultado>0){

             termina(resultado);
             return;
         }


            if (jugadores==2)return;

            casilla = partida.ia();

            while (partida.compruebaCasilla(casilla) != true) {
                casilla = partida.ia();

        }
         marca(casilla);

        resultado= partida.turno();

        if(resultado>0){termina(resultado);}

        /* Toast toast=Toast.makeText(this,"Has pulsao la casilla "+casilla,Toast.LENGTH_LONG);
         toast.setGravity(Gravity.CENTER,0,0);
         toast.show();*/
     }

     private void termina(int resultado){

        String mensaje;
        if(resultado==1)mensaje="Ganan los Libertarios";
        else if(resultado==2)mensaje="Ganan los Comunistas";
        else mensaje="Empate!";
         Toast toast=Toast.makeText(this,mensaje,Toast.LENGTH_LONG);
         toast.setGravity(Gravity.CENTER,0,0);
         toast.show();
         partida=null;
         ((Button)findViewById(R.id.unjugador)).setEnabled(true);
         ((RadioGroup)findViewById(R.id.configD)).setAlpha(1);
         ((Button)findViewById(R.id.dosjug)).setEnabled(true);

     }

     private void marca(int casilla){

            ImageView imagen;
            imagen=(ImageView)findViewById(CASILLAS[casilla]);
            if(partida.jugador==1){
                imagen.setImageResource(R.drawable.liber);
            }else{

                imagen.setImageResource(R.drawable.comu);
            }


     }

    private int jugadores;
    private int[ ] CASILLAS;
    private Partida partida;

}
