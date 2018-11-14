package model.bean;

public class Usuario {
    private int idUsuario;
    private String login;
    private String senha;
    
    public int getIdUsuario(){
        return idUsuario;
    }
    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public void setIdUsuario(int idUsuario){
        this.idUsuario = idUsuario;
    }
    
    
}
