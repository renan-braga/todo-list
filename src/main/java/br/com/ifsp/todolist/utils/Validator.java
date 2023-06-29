package br.com.ifsp.todolist.utils;

public class Validator {

    public static boolean validarEmail(String email){
        return email.matches("^[a-zA-Z0-9._]+@[a-zA-Z0-9]+\\.[a-zA-Z]{2,}$");
    }

    public static boolean validarSenha(String senha){
        return senha.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=_-])(?=\\S+$).{8,}$");
    }

    public static boolean validarNome(String nome){
        return nome.matches("^[a-zA-Z\\s]+");
    }

    public static boolean validarLogin(String login){
        return login.matches("^[a-zA-Z0-9._]+");
    }
}
