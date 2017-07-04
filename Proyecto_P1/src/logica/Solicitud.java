package logica;

public abstract class Solicitud {
	protected String codigo;
	protected int cantVacantes;
	protected int annosExperiencia;
	protected int edadMax;
	protected int edadMin;
	protected String tipoContrato;
	protected boolean vehiculoPropio;
	protected String localidad;
	protected Empresa empresa;
	protected boolean mudarse;

	public Solicitud(int cantVacantes, int annosExperiencia, int edadMax, int edadMin, String tipoContrato,
			boolean vehiculoPropio, String localidad, Empresa empresa, boolean mudarse) {
		super();
		this.cantVacantes = cantVacantes;
		this.annosExperiencia = annosExperiencia;
		this.edadMax = edadMax;
		this.edadMin = edadMin;
		tipoContrato = tipoContrato;
		this.vehiculoPropio = vehiculoPropio;
		this.localidad = localidad;
		this.empresa = empresa;
		this.mudarse = mudarse;
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public int getCantVacantes() {
		return cantVacantes;
	}

	public void setCantVacantes(int cantVacantes) {
		this.cantVacantes = cantVacantes;
	}

	public int getAnnosExperiencia() {
		return annosExperiencia;
	}

	public void setAnnosExperiencia(int annosExperiencia) {
		this.annosExperiencia = annosExperiencia;
	}

	public int getEdadMax() {
		return edadMax;
	}

	public void setEdadMax(int edadMax) {
		this.edadMax = edadMax;
	}

	public int getEdadMin() {
		return edadMin;
	}

	public void setEdadMin(int edadMin) {
		this.edadMin = edadMin;
	}

	public String getTipoContrato() {
		return tipoContrato;
	}

	public void setTipoContrato(String tipoContrato) {
		tipoContrato = tipoContrato;
	}

	public boolean isVehiculoPropio() {
		return vehiculoPropio;
	}

	public void setVehiculoPropio(boolean vehiculoPropio) {
		this.vehiculoPropio = vehiculoPropio;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public boolean isMudarse() {
		return mudarse;
	}

	public void setMudarse(boolean mudarse) {
		this.mudarse = mudarse;
	}

}
