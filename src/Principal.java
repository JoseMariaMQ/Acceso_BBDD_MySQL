import java.sql.SQLException;

public class Principal {
    public static void main(String[] args) throws SQLException {
        Acceso a = new Acceso();
        a.Conectar();
//        System.out.println("\n");
//        a.MostrarInformacionBBDD("SELECT * FROM alumnos;");
//        a.RealizarConsulta("SELECT * FROM alumnos;");

//        a.modificarDatosAlumno(1, "UPDATE alumnos SET Apellidos = 'Cesur' WHERE ID = ?");
        a.insertarDato("Alberto", "Ruiz Miguel", 34);
        a.RealizarConsulta("SELECT * FROM alumnos;");
    }
}
