package br.edu.uepb.sistemarestaurante.models;

public class Bebida extends ItemCardapio{
    private TipoBebida tipo;
    private VolumeBebida volume;

    public Bebida(String nome, double preco, TipoBebida tipo, VolumeBebida volume){
        super(nome, preco);
        this.tipo = tipo;
        this.volume = volume;
        itensCardapio.add(this);
    }

    public TipoBebida getTipo(){
        return tipo;
    }

    public VolumeBebida getVolume(){
        return volume;
    }

    public String getStringVolume(){
        return volume.getVolume();
    }
}
