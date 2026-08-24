import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class MenuProductos {

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        ArrayList<Productos> productos = new ArrayList<>();

        // Cargar productos desde MySQL
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

        } catch (Exception e) {

            System.out.println("Error al cargar productos: "
                    + e.getMessage());

            return;
        }

        int opcion;

        do {

            System.out.println();
            System.out.println("======================================");
            System.out.println("        MENU DE PRODUCTOS");
            System.out.println("======================================");
            System.out.println("1. Mostrar todos los productos");
            System.out.println("2. Buscar por categoría");
            System.out.println("3. Buscar precio menor a $300");
            System.out.println("4. Eliminar productos con stock 0");
            System.out.println("5. Ordenar por precio");
            System.out.println("6. Ordenar por nombre");
            System.out.println("7. Salir");
            System.out.println("======================================");
            System.out.print("Seleccione una opción: ");

            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {

                case 1:

                    System.out.println();
                    System.out.println("TODOS LOS PRODUCTOS");
                    System.out.println("--------------------------------------");

                    productos.forEach(p -> System.out.println(p));

                    break;

                case 2:

                    System.out.println();
                    System.out.println("PRODUCTOS DE COMPUTACIÓN");
                    System.out.println("--------------------------------------");

                    productos.forEach(p -> {

                        if (p.getCategoria()
                                .equalsIgnoreCase("Computación")) {

                            System.out.println(p);
                        }
                    });

                    break;

                case 3:

                    System.out.println();
                    System.out.println("PRODUCTOS CON PRECIO MENOR A $300");
                    System.out.println("--------------------------------------");

                    productos.forEach(p -> {

                        if (p.getPrecio() < 300) {

                            System.out.println(p);
                        }
                    });

                    break;

                case 4:

                    productos.removeIf(p -> p.getStock() == 0);

                    System.out.println();
                    System.out.println(
                        "Productos con stock 0 eliminados del ArrayList."
                    );

                    break;

                case 5:

                    productos.sort(
                        Comparator.comparing(Productos::getPrecio)
                    );

                    System.out.println();
                    System.out.println("PRODUCTOS ORDENADOS POR PRECIO");
                    System.out.println("--------------------------------------");

                    productos.forEach(p -> System.out.println(p));

                    break;

                case 6:

                    productos.sort(
                        Comparator.comparing(Productos::getNombre)
                    );

                    System.out.println();
                    System.out.println("PRODUCTOS ORDENADOS POR NOMBRE");
                    System.out.println("--------------------------------------");

                    productos.forEach(p -> System.out.println(p));

                    break;

                case 7:

                    System.out.println();
                    System.out.println("Programa finalizado.");

                    break;

                default:

                    System.out.println();
                    System.out.println("Opción no válida.");
            }

        } while (opcion != 7);

        teclado.close();
    }
}
