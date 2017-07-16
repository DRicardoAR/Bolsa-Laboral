package logica;

import java.util.ArrayList;
import java.io.Serializable;

public abstract class Solicitud implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String codigo;
	protected int cantVacantes;
	protected int annosExperiencia;
	protected int edadMax;
	protected int edadMin;
	protected String tipoContrato;
	protected boolean vehiculoPropio;
	protected int categoriaLicencia;
	protected String localidad;
	protected Empresa empresa;
	protected boolean mudarse;
	protected ArrayList<String> idiomas;

	public Solicitud(int cantVacantes, int annosExperiencia, int edadMax, int edadMin, String tipoContrato,
			boolean vehiculoPropio, String localidad, Empresa empresa, boolean mudarse, ArrayList<String> idiomas,int categoriaLicencia) {
		super();
		this.cantVacantes = cantVacantes;
		this.annosExperiencia = annosExperiencia;
		this.edadMax = edadMax;
		this.edadMin = edadMin;
		this.tipoContrato = tipoContrato;
		this.vehiculoPropio = vehiculoPropio;
		this.localidad = localidad;
		this.empresa = empresa;
		this.mudarse = mudarse;
		this.codigo = codigo;
		this.categoriaLicencia = categoriaLicencia;
		this.idiomas = idiomas;

	}

	public int getCategoriaLicencia() {
		return categoriaLicencia;
	}

	public void setCategoriaLicencia(int categoriaLicencia) {
		this.categoriaLicencia = categoriaLicencia;
	}

	public ArrayList<String> getIdiomas() {
		return idiomas;
	}

	public void setIdiomas(ArrayList<String> idiomas) {
		this.idiomas = idiomas;
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
		this.tipoContrato = tipoContrato;
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
