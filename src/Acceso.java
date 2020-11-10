import java.sql.*;

public class Acceso {
    private Connection conexion;
    private Statement St;
    private ResultSet rs1;
    private ResultSet rs2;
    private PreparedStatement ps;
    private int cont = 0;

    public Acceso(){
    }

    public void Conectar(){
        try{
            // Se carga el driver mysql de la siguiente forma:
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Datos para la conexión:
            String url="jdbc:mysql://localhost/aad";
            String usuario="root";
            String contraseña="";
            // Crear un objeto conexión:
            conexion = DriverManager.getConnection (url, usuario, contraseña);
            // Se crea un objeto de tipo Statement, para realizar la consulta:
            St = conexion.createStatement();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void RealizarConsulta(String consulta) throws SQLException {
        try{
            // Se realiza la consulta. Los resultados se guardan en el ResultSet rs
            rs1 = St.executeQuery(consulta);
            // se recuperan los datos recorriendo el ResultSet, mostrando por pantalla los resultados:
            while(rs1.next()){
                System.out.println (rs1.getInt (1) + " " +
                        rs1.getString (2) + " " +
                        rs1.getString(3) + " " +
                        rs1.getInt (4) );
            }
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            //Se cierra conexión
            St.close();
            rs1.close();
            conexion.close();
        }
    }

    public void MostrarInformacionBBDD(String consulta) {
        try {
            // Se realiza la consulta. Los resultados se guardan en el ResultSet rs
            rs2 = St.executeQuery (consulta);

            ResultSetMetaData rsMetaData = rs2.getMetaData();

            System.out.println("número de columnas: " + rsMetaData.getColumnCount());
            try {
                while (rs2.next()){
                    cont++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                rs2.close();
            }
            System.out.println("número de filas: " + cont);
            System.out.println("nombre de la tabla: " + rsMetaData.getTableName(1));
            System.out.println("nombre del esquema o catálogo: " + rsMetaData.getCatalogName(1));
            System.out.println("\n");
            System.out.println("Datos de la tabla alumnos con columnas");
            System.out.println("--------------------------------------");
            System.out.println(rsMetaData.getColumnName(1) + " " + rsMetaData.getColumnName(2) + " " + rsMetaData.getColumnName(3) + " " + rsMetaData.getColumnName(4));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void modificarDatosAlumno(int id, String consulta) {
        try {
            //Se crea un objeto de tipo PreparedStatement para enviar sentencias SQL
            ps = conexion.prepareStatement(consulta);
            //Establece el parámetro designado en el valor int del String consulta
            ps.setInt(1, id);
            //Ejecuta la instrucción SQL en este objeto
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                ps.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void insertarDato(String nombre, String apellidos, int edad) {
//        String sql = "INSERT INTO alumnos (Nombre, Apellidos, Edad) VALUES (" + "'" + nombre + "'" + "," + "'" + apellidos + "'" + "," + edad + ");";
        String sql = "INSERT INTO alumnos (Nombre, Apellidos, Edad) VALUES (? , ?, ?);";
        try {
            //Se crea un objeto de tipo PreparedStatement para enviar sentencias SQL
            ps = conexion.prepareStatement(sql);
            //Establece el parámetro designado en el valor String del String consulta
            ps.setString(1, nombre);
            //Establece el parámetro designado en el valor String del String consulta
            ps.setString(2, apellidos);
            //Establece el parámetro designado en el valor int del String consulta
            ps.setInt(3, edad);
            //Ejecuta la instrucción SQL en este objeto
            ps.executeUpdate();

//            St.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                ps.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}

