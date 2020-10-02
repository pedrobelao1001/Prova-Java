package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import db.ConexaoHSQLDB;
import entity.Funcionario;

public class FuncionarioDao extends ConexaoHSQLDB{
	final String SQL_INSERT_FUNCIONARIO = "INSERT INTO FUNCIONARIO(NOME, CARGO, RG, DATAEMISSAO) VALUES (?,?,?,?)";
	final String SQL_SELECT_FUNCIONARIO = "SELECT * FROM FUNCIONARIO";
	final String SQL_SELECT_FUNCIONARIO_ID = "SELECT * FROM FUNCIONARIO WHERE ID = ?";
	final String SQL_ALTERA_FUNCIONARIO = "UPDATE FUNCIONARIO SET NOME=?, CARGO=? , RG=?, DATAEMISSAO=? WHERE ID = ?";
	final String SQL_DELETA_FUNCIONARIO = "DELETE FROM FUNCIONARIO WHERE ID = ?";

public int inserir(Funcionario funcionario) {
	int quantidade = 0;

	//INSERIR
	try (Connection connection = this.conectar();
			PreparedStatement pst = connection.prepareStatement(SQL_INSERT_FUNCIONARIO);) {
		pst.setString(1, funcionario.getNome());
		pst.setString(2, funcionario.getCargo());
		pst.setString(3, funcionario.getRg());
		pst.setDate(4,java.sql.Date.valueOf(funcionario.getDataemissao().toString()));
		quantidade = pst.executeUpdate();
		

	} catch (SQLException e) {
		e.printStackTrace();
	}

	return quantidade;
	}

	//LISTAR
public List<Funcionario> listAll(){
	List<Funcionario> listaFuncionario = new ArrayList<>();
	
	try (Connection connection = this.conectar();
			PreparedStatement pst = connection.prepareStatement(SQL_SELECT_FUNCIONARIO);){

		ResultSet rs = pst.executeQuery();
		
		while(rs.next()) {
			Funcionario funcionario = new Funcionario();
			
			funcionario.setId(rs.getInt("ID"));
			funcionario.setNome(rs.getString("NOME"));
			funcionario.setCargo(rs.getString("CARGO"));
			funcionario.setRg(rs.getString("RG"));
			funcionario.setDataemissao(java.sql.Date.valueOf((rs.getString("DATAEMISSAO"))));
			
			listaFuncionario.add(funcionario);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	return listaFuncionario;
	}
	
	//BUSCAR PELO ID
public Funcionario findByID(int idFuncionario) {
	Funcionario funcionario = null;
	try (Connection connection = this.conectar();
			PreparedStatement pst = connection.prepareStatement(SQL_SELECT_FUNCIONARIO_ID);){

		pst.setInt(1, idFuncionario);
		ResultSet rs = pst.executeQuery();
		
		while(rs.next()) {
			funcionario = new Funcionario();
			
			funcionario.setId(rs.getInt("ID"));
			funcionario.setNome(rs.getString("NOME"));
			funcionario.setCargo(rs.getString("CARGO"));
			funcionario.setRg(rs.getString("RG"));
			funcionario.setDataemissao(rs.getDate("DATAEMISSAO"));
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	return funcionario;
	}

	//ALTERAR 
public int alterar(Funcionario funcionario) {
	int quantidade = 0;

	try (Connection connection = this.conectar();
			PreparedStatement pst = connection.prepareStatement(SQL_ALTERA_FUNCIONARIO);) {
		pst.setString(1, funcionario.getNome());
		pst.setString(2, funcionario.getCargo());
		pst.setString(3, funcionario.getRg());
		pst.setDate(4, java.sql.Date.valueOf(funcionario.getDataemissao().toString()));
		pst.setInt(5, funcionario.getId());

		quantidade = pst.executeUpdate();

	} catch (SQLException e) {
		e.printStackTrace();
	}

	return quantidade;
	}

	//DELETAR 
public int deletar(int idFuncionario) {
        int quantidade = 0;
        try (Connection connection = this.conectar();
                PreparedStatement pst = connection.prepareStatement(SQL_DELETA_FUNCIONARIO);) {
            pst.setInt(1, idFuncionario);
            
            quantidade = pst.executeUpdate();
            
        } catch (Exception e) {
            System.out.println(e);
        }
        return quantidade;
    }

}
