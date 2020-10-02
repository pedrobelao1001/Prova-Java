package entity ;

import java.sql.Date;

public class Funcionario {
private int id;
private String nome;
private String cargo;
private String rg;
private Date dataemissao;



public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getNome() {
	return nome;
}
public void setNome(String nome) {
	this.nome = nome;
}
public String getCargo() {
	return cargo;
}
public void setCargo(String cargo) {
	this.cargo = cargo;
}
public String getRg() {
	return rg;
}
public void setRg(String rg) {
	this.rg = rg;
}

public Date getDataemissao() {
	return dataemissao;
}
public void setDataemissao(Date dataemissao) {
	this.dataemissao = dataemissao;
}






@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((cargo == null) ? 0 : cargo.hashCode());
	result = prime * result + ((dataemissao == null) ? 0 : dataemissao.hashCode());
	result = prime * result + id;
	result = prime * result + ((nome == null) ? 0 : nome.hashCode());
	result = prime * result + ((rg == null) ? 0 : rg.hashCode());
	return result;
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Funcionario other = (Funcionario) obj;
	if (cargo == null) {
		if (other.cargo != null)
			return false;
	} else if (!cargo.equals(other.cargo))
		return false;
	if (dataemissao == null) {
		if (other.dataemissao != null)
			return false;
	} else if (!dataemissao.equals(other.dataemissao))
		return false;
	if (id != other.id)
		return false;
	if (nome == null) {
		if (other.nome != null)
			return false;
	} else if (!nome.equals(other.nome))
		return false;
	if (rg == null) {
		if (other.rg != null)
			return false;
	} else if (!rg.equals(other.rg))
		return false;
	return true;
}




@Override
public String toString() {
	return "Funcionario [id=" + id + ", nome=" + nome + ", cargo=" + cargo + ", rg=" + rg + ", dataemissao="
			+ dataemissao + "]";
}
public Funcionario() {
	super();
	// TODO Auto-generated constructor stub
} 


	public Funcionario(int id, String nome, String cargo, String rg, Date dataemissao) {
		super();
		this.id = id;
		this.nome = nome;
		this.cargo = cargo;
		this.rg = rg;
		this.dataemissao = dataemissao;
	
	}
	
	
	public Funcionario(String nome, String cargo, String rg, Date dataemissao) {
		super();
		this.nome = nome;
		this.cargo = cargo;
		this.rg = rg;
		this.dataemissao = dataemissao;
	
	}




}
