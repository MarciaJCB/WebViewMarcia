package com.example.ppt18webview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //obtenemos la referencia del Id definido en el XML
        webView = findViewById(R.id.webView);

        //obtiene las configuraciones del WebView pra modificar sus propiedades
        WebSettings webSettings = webView.getSettings();

        //habilita el Javascript
        webSettings.setJavaScriptEnabled(true);

        //agrega una interfaz de JavaScript, permite comunicar el código de la aplicación
        webView.addJavascriptInterface(new JavaScriptInterface(), "appInterface");
    }

    //el método loadURL se llama cuando el usuario hace clic en el botón
    public void loadURL(View view){
        //obtiene las referencia del editText definido en el XML
        EditText urlEditText = findViewById(R.id.urlEditText);

        //Obtiene el texto ingresado como una cadena
        String url = urlEditText.getText().toString();

        //Comprueba si la cadena está vacía
        if(url.isEmpty()){
            //Muestra el toast indicndo que se debe ingresar una URL válida
            Toast.makeText(this, "Ingresa una URL valida", Toast.LENGTH_SHORT).show();
        }else {
            //Si la cadena no está vacía cara la URL ingresada
            webView.loadUrl(url);
        }

    }

    //Define a clase interna que se utiliza para comunicar entre JavaScript y Java
    private class JavaScriptInterface {
        //Clase interna que contiene un método llamado showtoast
        @android.webkit.JavascriptInterface //Permite a Javascript llamarlo desde una página web cargada
        public void showToast(String message){

        }
    }
}