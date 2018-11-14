package model.bean;

public class Departamento {
    private int codDepartamento;
    private String nomeDepartamento;
    private int numeroFuncionario;
    private String responsavelDepartamento;
    private String inicioAtividades;

    public String getResponsavelDepartamento() {
        return responsavelDepartamento;
    }

    public void setResponsavelDepartamento(String responsavelDepartamento) {
        this.responsavelDepartamento = responsavelDepartamento;
    }

    public String getInicioAtividades() {
        return inicioAtividades;
    }

    public void setInicioAtividades(String inicioAtividades) {
        this.inicioAtividades = inicioAtividades;
    }
    
    
    public int getCodDepartamento() {
        return codDepartamento;
    }

    public String getNomeDepartamento() {
        return nomeDepartamento;
    }

    public int getNumeroFuncionario() {
        return numeroFuncionario;
    }

    public void setCodDepartamento(int codDepartamento) {
        this.codDepartamento = codDepartamento;
    }

    public void setNomeDepartamento(String nomeDepartamento) {
        this.nomeDepartamento = nomeDepartamento;
    }

    public void setNumeroFuncionario(int numeroFuncionario) {
        this.numeroFuncionario = numeroFuncionario;
    }
    
    
}
