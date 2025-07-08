package com.vitor.taskly.controller;

import android.content.Context;
import android.content.SharedPreferences;
import com.vitor.taskly.model.Login;
import com.vitor.taskly.model.Register;


public class RegisterController {

    private static final String prefs = "prefs";
    private static final String ITENS_KEY = "login";
    private SharedPreferences sp;

    public RegisterController(){
    }
    public RegisterController (Context context){
        sp = context.getSharedPreferences(prefs, Context.MODE_PRIVATE);
    }

    public void salvarLogin(String name, Login login) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("name", name);
        editor.putString("email", login.getEmail());
        editor.putString("password", login.getPassword());
        editor.apply();
    }

    public int confirmRegister (Register register) {
        String[] registerInfos = new String[] {
                register.getName(), register.getPhone(), register.getEmail(), register.getPassword()
        };

        for (int i = 0; i < registerInfos.length; i++) {
            if (registerInfos[i] == null || registerInfos[i].trim().isEmpty()){
                return i;
            }
        }
        return -1;
    }

    public String getSavedName() {
        return sp.getString("name", null);
    }
}

