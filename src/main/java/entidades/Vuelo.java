package entidades;

public class Vuelo {
    private int id;
    private String origen;
    private String destino;
    private double precio;
    private int asientos;
    private String tipo;

    // 1. Constructor vacío (Obligatorio para que funcione 'new Vuelo()')
    public Vuelo() {}

    // 2. Getters y Setters (Obligatorios para asignar datos desde la DB)
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getOrigen() { return origen; }
    public void setOrigen(String origen) { this.origen = origen; }

    public String getDestino() { return destino; }
    public void setDestino(String destino) { this.destino = destino; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public int getAsientos() { return asientos; }
    public void setAsientos(int asientos) { this.asientos = asientos; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
}