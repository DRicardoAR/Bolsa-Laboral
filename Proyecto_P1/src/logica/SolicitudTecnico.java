package logica;

import java.util.ArrayList;

public class SolicitudTecnico extends Solicitud {
	private String area;

	public SolicitudTecnico(int cantVacantes, int annosExperiencia, int edadMax, int edadMin, String tipoContrato,
			boolean vehiculoPropio, String localidad, Empresa empresa, boolean mudarse, ArrayList<String> idiomas,
			int categoriaLicencia,String area) {
		super(cantVacantes, annosExperiencia, edadMax, edadMin, tipoContrato, vehiculoPropio, localidad, empresa,
				mudarse, idiomas, categoriaLicencia);
		this.area = area;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

}
