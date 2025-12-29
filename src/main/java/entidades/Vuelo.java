package entidades;

public class Vuelo {
    private int id;
    private String origen;
    private String destino;
    private double precio;
    private int asientos;

    public Vuelo(int id, String origen, String destino, double precio, int asientos) {
        this.id = id;
        this.origen = origen;
        this.destino = destino;
        this.precio = precio;
        this.asientos = asientos;
    }

    public int getId() { return id; }
    public String getOrigen() { return origen; }
    public String getDestino() { return destino; }
    public double getPrecio() { return precio; }
    public int getAsientos() { return asientos; }
}
