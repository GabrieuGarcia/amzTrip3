package amztrip.cursoandroid.com.amztrip.model;

/**
 * Created by Gabriel on 24/09/17.
 */

public class GastoModel {

    private String viagem_id = "viagem_id";
    private String categoria = "categoria";
    private String data = "data";
    private String descricao = "descricao";
    private String valor = "valor";
    private String local = "local";

    public GastoModel(String viagem_id, String categoria, String data, String descricao, String valor, String local) {
        this.viagem_id = viagem_id;
        this.categoria = categoria;
        this.data = data;
        this.descricao = descricao;
        this.valor = valor;
        this.local = local;
    }

    public GastoModel() {
    }

    public String getViagem_id() {
        return viagem_id;
    }

    public void setViagem_id(String viagem_id) {
        this.viagem_id = viagem_id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }
}
