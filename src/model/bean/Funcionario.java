package model.bean;

public class Funcionario {
    private String nomeFuncionario;
    private String cpf;
    private String endereco;
    private int registro;
    private String dataNascimento;
    private float salario;
    private String cargo;
    private int codDepartamento;
    private String estado;
    private String cep;
    private String fotoPerfil;
    private String cidade;
    private int idFuncionario;

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public void setCep(String cep) {
        this.cep = cep;
    }
    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public void setRegistro(int registro) {
        this.registro = registro;
    }
    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    public void setSalario(float salario) {
        this.salario = salario;
    }
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    public void setCodDepartamento(int codDepartamento) {
        this.codDepartamento = codDepartamento;
    }
    public void setIdFuncionario(int idFuncionario){
        this.idFuncionario = idFuncionario;
    }
    public int getIdFuncionario(){
        return idFuncionario;
    }
    public String getCidade() {
        return cidade;
    }
    public String getFotoPerfil() {
        return fotoPerfil;
    }
    public String getEstado() {
        return estado;
    }
    public String getCep() {
        return cep;
    }
    public String getNomeFuncionario() {
        return nomeFuncionario;
    }
    public String getCpf() {
        return cpf;
    }
    public String getEndereco() {
        return endereco;
    }
    public int getRegistro() {
        return registro;
    }
    public String getDataNascimento() {
        return dataNascimento;
    }
    public float getSalario() {
        return salario;
    }
    public String getCargo() {
        return cargo;
    }
    public int getCodDepartamento() {
        return codDepartamento;
    }
}
