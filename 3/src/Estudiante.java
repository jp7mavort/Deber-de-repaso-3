public class Estudiante {
    private String nombre;
    private String cedula;
    private int edad;
    private String carrera;
    private boolean activo;

    public Estudiante(String nombre, String cedula, int edad, String carrera, boolean activo) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.edad = edad;
        this.carrera = carrera;
        this.activo = activo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public int getEdad() {
        return edad;
    }

    public String getCarrera() {
        return carrera;
    }

    public boolean isActivo() {
        return activo;
    }
}
