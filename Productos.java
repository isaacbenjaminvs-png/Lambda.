public class Productos {

    private int id;
    private String nombre;
    private String categoria;
    private double precio;
    private int stock;

    // Constructor
    public Productos(int id, String nombre, String categoria,
                     double precio, int stock) {

        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.stock = stock;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public double getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    // toString
    @Override
    public String toString() {

        return "ID: " + id
                + " | Nombre: " + nombre
                + " | Categoría: " + categoria
                + " | Precio: $" + precio
                + " | Stock: " + stock;
    }
}
