package model.bean;

public class Suporte {
    private int codAtendimento;
    private String tipoReclamacao;
    private String motivoReclamacao;
    private String nomeCliente;
    private String cpfFuncionario;
    private int codProjeto;
    private String cpfCNPJ;

    public int getCodAtendimento() {
        return codAtendimento;
    }

    public String getTipoReclamacao() {
        return tipoReclamacao;
    }

    public String getMotivoReclamacao() {
        return motivoReclamacao;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public String getCpfFuncionario() {
        return cpfFuncionario;
    }

    public int getCodProjeto() {
        return codProjeto;
    }
    
    public String getCpfCNPJ(){
        return cpfCNPJ;
    }

    public void setCodAtendimento(int codAtendimento) {
        this.codAtendimento = codAtendimento;
    }

    public void setTipoReclamacao(String tipoReclamacao) {
        this.tipoReclamacao = tipoReclamacao;
    }

    public void setMotivoReclamacao(String motivoReclamacao) {
        this.motivoReclamacao = motivoReclamacao;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public void setCpfFuncionario(String cpfFuncionario) {
        this.cpfFuncionario = cpfFuncionario;
    }

    public void setCodProjeto(int codProjeto) {
        this.codProjeto = codProjeto;
    }
    
    public void setCpfCNPJ(String cpfCNPJ){
        this.cpfCNPJ = cpfCNPJ;
    }
}
