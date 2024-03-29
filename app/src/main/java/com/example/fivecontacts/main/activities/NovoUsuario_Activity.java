package com.example.fivecontacts.main.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import com.example.fivecontacts.R;
import com.example.fivecontacts.main.model.User;

public class NovoUsuario_Activity extends AppCompatActivity {

    boolean primeiraVezUser=true;
    boolean primeiraVezNome=true;
    boolean primeiraVezEmail=true;
    boolean primeiraVezSenha=true;
    EditText edUser;
    EditText edPass;
    EditText edNome;
    EditText edEmail;
    Switch swLogado;

    Switch swTema;
    Button btCriar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_usuario);

        btCriar=findViewById(R.id.btCriar);
        edUser=findViewById(R.id.edT_Login2);
        edPass=findViewById(R.id.edt_Pass2);
        edNome=findViewById(R.id.edtNome);
        edEmail=findViewById(R.id.edEmail);
        swLogado=findViewById(R.id.swLogado);

        swTema = findViewById(R.id.swTema);
        setTitle("Novo Usuário");

        btCriar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String nome, login, senha;
                nome = edNome.getText().toString();
                login = edUser.getText().toString();
                senha = edPass.getText().toString();

                //Novos componentes
                String email;
                email = edEmail.getText().toString();
                boolean manterLogado;
                manterLogado= swLogado.isChecked();


                boolean temaEscuro=swTema.isChecked();

                if(temaEscuro){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    setTheme(R.style.AppDarkTheme);
                }else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    setTheme(R.style.AppLighTheme);
                }

                SharedPreferences salvaUser= getSharedPreferences("usuarioPadrao", Activity.MODE_PRIVATE);
                SharedPreferences.Editor escritor= salvaUser.edit();

                escritor.putString("nome",nome);
                escritor.putString("senha",senha);
                escritor.putString("login",login);

                //Escrever no SharedPreferences
                //Falta Salvar o E-mail
                escritor.putString("email",email);
                escritor.putBoolean("manterLogado",manterLogado);

                escritor.putBoolean("tema",temaEscuro);

                escritor.commit(); //Salva em Disco

                //Salvando o user

                User user =new User(nome,login,senha,email,manterLogado);


                Intent intent=new Intent(NovoUsuario_Activity.this, AlterarContatos_Activity.class);
                intent.putExtra("usuario",user);
                startActivity(intent);

                //Mesmo após a chamar de um startActivity o método continuará execuntando
                //Por exemplo, aqui mataremos a Activity atual porém AlterarContatos será exibida
                finish();
            }
        });
    }
}