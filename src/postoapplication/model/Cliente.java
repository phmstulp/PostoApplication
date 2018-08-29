package postoapplication.model;

/**
 *
 * @author fag
 */
public class Cliente extends Record {
    
    private int codigo;
    private String nome;
    private String cpfCnpj;
    private String telefone;
    private String endereco;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return nome;
    }

    public void setDescricao(String descricao) {
        this.nome = descricao;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return codigo + " - " + nome;
    }
    
    
    
}
