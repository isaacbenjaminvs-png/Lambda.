import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Comparator;

public class PruebaLambda {

    public static void main(String[] args) {

   
        ArrayList<Productos> productos = new ArrayList<>();

        String sql = "SELECT * FROM producto";

        try {

           
            Connection con = Conexion.conectar();

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

      
            while (rs.next()) {

                Productos producto = new Productos(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("categoria"),
                    rs.getDouble("precio"),
                    rs.getInt("stock")
                );

                productos.add(producto);
            }

            rs.close();
            ps.close();
            con.close();

            // ========================================
            // PARTE 4: BÚSQUEDA POR CATEGORÍA
            // ========================================

            System.out.println("========================================");
            System.out.println("PRODUCTOS DE COMPUTACIÓN");
            System.out.println("========================================");

            productos.forEach(p -> {

                if (p.getCategoria().equalsIgnoreCase("Computación")) {
                    System.out.println(p);
                }
            });

            // ========================================
            //   PRECIO MENOR A $300
            // ========================================

            System.out.println();
            System.out.println("========================================");
            System.out.println("PRODUCTOS CON PRECIO MENOR A $300");
            System.out.println("========================================");

            productos.forEach(p -> {

                if (p.getPrecio() < 300) {
                    System.out.println(p);
                }
            });

            // ========================================
            // REMOVE IF
            // ========================================

            System.out.println();
            System.out.println("========================================");
            System.out.println("ELIMINAR PRODUCTOS CON STOCK 0");
            System.out.println("========================================");

            productos.removeIf(p -> p.getStock() == 0);

            System.out.println("Productos con stock 0 eliminados del ArrayList.");

            // ========================================
            //ORDENAR POR PRECIO
            // ========================================

            System.out.println();
            System.out.println("========================================");
            System.out.println("PRODUCTOS ORDENADOS POR PRECIO");
            System.out.println("========================================");

            productos.sort(
                Comparator.comparing(Productos::getPrecio)
            );

            productos.forEach(p -> System.out.println(p));

            // ========================================
            //   ORDENAR POR NOMBRE
            // ========================================

            System.out.println();
            System.out.println("========================================");
            System.out.println("PRODUCTOS ORDENADOS POR NOMBRE");
            System.out.println("========================================");

            productos.sort(
                Comparator.comparing(Productos::getNombre)
            );

            productos.forEach(p -> System.out.println(p));

        } catch (Exception e) {

            System.out.println("Error: " + e.getMessage());
        }
    }
}
