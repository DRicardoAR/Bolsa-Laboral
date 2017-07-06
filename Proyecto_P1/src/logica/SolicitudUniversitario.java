package logica;

import java.util.ArrayList;

public class SolicitudUniversitario extends Solicitud {
	private boolean postGrado;
	private String carrera;
	
	public SolicitudUniversitario(int cantVacantes, int annosExperiencia, int edadMax, int edadMin, String tipoContrato,
			boolean vehiculoPropio, String localidad, Empresa empresa, boolean mudarse, ArrayList<String> idiomas,
			int categoriaLicencia,boolean postGrado,String carrera) {
		super(cantVacantes, annosExperiencia, edadMax, edadMin, tipoContrato, vehiculoPropio, localidad, empresa, mudarse,
				idiomas, categoriaLicencia);
		this.postGrado= postGrado;
		this.carrera = carrera;
	}


	public boolean isPostGrado() {
		return postGrado;
	}

	public void setPostGrado(boolean postGrado) {
		this.postGrado = postGrado;
	}

	public String getCarrera() {
		return carrera;
	}

	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}

}
