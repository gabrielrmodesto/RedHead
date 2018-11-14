package model.bean;

public class Projeto {
    private int codProjeto;
    private String nomeProjeto;
    private String prazo;
    private int equipe;
    private float valor;
    private String cpfFuncionario;
    private int codDepartamento;
    private String cpfCliente;
    private String nomeCliente;


    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }
    public void setCodProjeto(int codProjeto) {
        this.codProjeto = codProjeto;
    }

    public void setNomeProjeto(String nomeProjeto) {
        this.nomeProjeto = nomeProjeto;
    }

    public void setPrazo(String prazo) {
        this.prazo = prazo;
    }

    public void setEquipe(int equipe) {
        this.equipe = equipe;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public void setCpfFuncionario(String cpfFuncionario) {
        this.cpfFuncionario = cpfFuncionario;
    }

    public void setCodDepartamento(int codDepartamento) {
        this.codDepartamento = codDepartamento;
    }
    public void setNomeCliente(String nomeCliente){
        this.nomeCliente = nomeCliente;
    }
    
    
    public String getCpfCliente() {
        return cpfCliente;
    }
    public int getCodProjeto() {
        return codProjeto;
    }

    public String getNomeProjeto() {
        return nomeProjeto;
    }

    public String getPrazo() {
        return prazo;
    }

    public int getEquipe() {
        return equipe;
    }

    public float getValor() {
        return valor;
    }

    public String getCpfFuncionario() {
        return cpfFuncionario;
    }

    public int getCodDepartamento() {
        return codDepartamento;
    }  
    
    public String getNomeCliente(){
        return nomeCliente;
    }
    
}