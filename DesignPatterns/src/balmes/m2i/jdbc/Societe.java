package balmes.m2i.jdbc;

import java.util.List;

public class Societe {
	private int _ID_Societe;
	private String _Nom;
	private float _ChiffreDAffaire;
	private String _Activite;
	private List<Personne> lstEmployes;

	public Societe(int _ID_Societe, String _Nom, float _ChiffreDAffaire, String _Activite, List<Personne> lstEmployes) {
		super();
		this._ID_Societe = _ID_Societe;
		this._Nom = _Nom;
		this._ChiffreDAffaire = _ChiffreDAffaire;
		this._Activite = _Activite;
		this.lstEmployes = lstEmployes;
	}

	public int get_ID_Societe() {
		return _ID_Societe;
	}

	public void set_ID_Societe(int _ID_Societe) {
		this._ID_Societe = _ID_Societe;
	}

	public String get_Nom() {
		return _Nom;
	}

	public void set_Nom(String _Nom) {
		this._Nom = _Nom;
	}

	public float get_ChiffreDAffaire() {
		return _ChiffreDAffaire;
	}

	public void set_ChiffreDAffaire(float _ChiffreDAffaire) {
		this._ChiffreDAffaire = _ChiffreDAffaire;
	}

	public String get_Activite() {
		return _Activite;
	}

	public void set_Activite(String _Activite) {
		this._Activite = _Activite;
	}

	public List<Personne> getLstEmployes() {
		return lstEmployes;
	}

	public void setLstEmployes(List<Personne> lstEmployes) {
		this.lstEmployes = lstEmployes;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Societe [_ID_Societe=");
		builder.append(_ID_Societe);
		builder.append(", _Nom=");
		builder.append(_Nom);
		builder.append(", _ChiffreDAffaire=");
		builder.append(_ChiffreDAffaire);
		builder.append(", _Activite=");
		builder.append(_Activite);
		builder.append(", lstEmployes=");
		builder.append(lstEmployes);
		builder.append("]");
		return builder.toString();
	}
	
	public String lstEmployesToString() {
		StringBuilder builder = new StringBuilder();
		return builder.toString();
	}

	public void addEmploye(Personne p) {
		p.set_ID_Societe(this._ID_Societe);
		this.lstEmployes.add(p);
	}

}
