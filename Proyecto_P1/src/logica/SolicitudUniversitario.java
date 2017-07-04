package logica;

public class SolicitudUniversitario extends Solicitud {
	boolean PostGrado;
	String Carrera;

	public SolicitudUniversitario(int cantVacantes, int annosExperiencia,
			int edadMax, int edadMin, String tipoContrato,
			boolean vehiculoPropio, String localidad, Empresa empresa,
			boolean mudarse, boolean PostGrado, String Carrera) {
		super(cantVacantes, annosExperiencia, edadMax, edadMin, tipoContrato,
				vehiculoPropio, localidad, empresa, mudarse);
		this.PostGrado=PostGrado;
		this.Carrera=Carrera;
	}

	public boolean isPostGrado() {
		return PostGrado;
	}

	public void setPostGrado(boolean postGrado) {
		PostGrado = postGrado;
	}

	public String getCarrera() {
		return Carrera;
	}

	public void setCarrera(String carrera) {
		Carrera = carrera;
	}

}
