package pi;

import java.sql.*;
import java.util.ArrayList;

public class AlunoDAO {
	private static Connection connection;
	
	private final String FALHA_CONEXAO = "Falha na conexão com o banco de dados";
	private final String FALHA_OPERACAO = "Falha na operação do banco de dados";
	private final String CADASTRO_SUCESSO = "Cadastro realizado com sucesso";
	private final String CADASTRO_FALHA = "O cadastro não foi realizado";
	private final String CONSULTA_VAZIA = "A consulta não retornou resultados";
	private final String ATUALIZACAO_SUCESSO = "Atualização realizada com sucesso";
	private final String ATUALIZACAO_FALHA = "A atualização não foi realizada";
	private final String EXCLUSAO_SUCESSO = "Exclusão realizada com sucesso";
	private final String EXCLUSAO_FALHA = "A exclusão não foi realizada";
	
	public boolean inserir(Aluno aluno){
		String query = null;
		PreparedStatement ps = null;
		int inserted = 0;
		boolean status = true;
		connection = ConnectionFactory.getConnection();
		if(connection == null) {
			System.out.println(FALHA_CONEXAO);
			return false;
		}
		query = "INSERT INTO Estudante (idEstudante, idDepartamentoDisciplinaEspecial, idProfessorTutor, nome, cpf, dataNasc) "+
				"VALUES (?,?,?,?,?,?);";
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, aluno.getID());
			ps.setInt(2, aluno.getDepartamento().getID());
			ps.setInt(3, aluno.getTutor().getID());
			ps.setString(4, aluno.getNome());
			ps.setString(5, aluno.getCPF());
			ps.setDate(6, Date.valueOf(aluno.getDataNascimento()));
			inserted = ps.executeUpdate();
			if(!(inserted > 0)) {
				System.out.println(EXCLUSAO_FALHA);
				return false;
			}
			ps.close();
			ps = null;
			query = "INSERT INTO Estudante_Possue_Disciplina VALUES (?,?);";
			ps = connection.prepareStatement(query);
			
			for(int i = 0; i<aluno.getDisciplina().size() ; i++) {
				ps.setInt(1, aluno.getDisciplina().get(i).getID());
				ps.setInt(2, aluno.getID());
				inserted = ps.executeUpdate();
			}
			System.out.println((inserted > 0) ? CADASTRO_SUCESSO.toString() : CADASTRO_FALHA);
		}catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
			System.out.println(FALHA_OPERACAO);
			status = false;
		}finally {
			try { if (ps != null) ps.close(); } 
			catch (Exception ex) {
				System.err.println("SQLException: " + ex.getMessage());
			}
		}
		ConnectionFactory.closeConnection();
		return status;
	}
	
	public boolean atualizar(int id, Aluno aluno) {
		String query = null;
		PreparedStatement ps = null;
		int updated = 0;
		boolean status = true;
		connection = ConnectionFactory.getConnection();
		if(connection == null) {
			System.out.println(FALHA_CONEXAO);
			return false;
		}
		query = "UPDATE Estudante SET idDepartamentoDisciplinaEspecial = ?, "
				+ "idProfessorTutor = ?, "
				+ "nome = ?, "
				+ "cpf = ?, "
				+ "dataNasc = ? "
				+ "WHERE idEstudante = ?;";
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, aluno.getDepartamento().getID());
			ps.setInt(2, aluno.getTutor().getID());
			ps.setString(3, aluno.getNome());
			ps.setString(4, aluno.getCPF());
			ps.setDate(5, Date.valueOf(aluno.getDataNascimento()));
			ps.setInt(6, id);
			updated = ps.executeUpdate();
			System.out.println((updated > 0) ? ATUALIZACAO_SUCESSO : ATUALIZACAO_FALHA);
		}catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
			System.out.println(FALHA_OPERACAO);
			status = false;
		}finally {
			try { if (ps != null) ps.close(); } 
			catch (Exception ex) {
				System.err.println("SQLException: " + ex.getMessage());
			}
		}
		ConnectionFactory.closeConnection();
		return status;
	}
	
	public boolean remover(int id) {
		String query = null;
		PreparedStatement ps = null;
		int deleted = 0;
		boolean status = true;
		connection = ConnectionFactory.getConnection();
		if(connection == null) {
			System.out.println(FALHA_CONEXAO);
			return false;
		}
		try {
			query = "DELETE FROM Estudante_Possue_Disciplina WHERE Estudante_idEstudante = ?;";
			ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			deleted = ps.executeUpdate();
			ps.close();
			ps = null;
			if(!(deleted > 0)) {
				System.out.println(EXCLUSAO_FALHA);
				return false;
			}
			query = "DELETE FROM Estudante WHERE idEstudante = ?;";
			ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			deleted = ps.executeUpdate();
			System.out.println((deleted > 0) ? EXCLUSAO_SUCESSO : EXCLUSAO_FALHA);
		}catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
			System.out.println(FALHA_OPERACAO);
			status = false;
		}finally {
			try { if (ps != null) ps.close(); } 
			catch (Exception ex) {
				System.err.println("SQLException: " + ex.getMessage());
			}
		}
		ConnectionFactory.closeConnection();
		return status;
	}
	
	public ArrayList<Aluno> listar(){
		ArrayList<Aluno> res = new ArrayList<Aluno>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = null;
		connection = ConnectionFactory.getConnection();
		if(connection == null) {
			System.out.println(FALHA_CONEXAO);
			return null;
		}
		
		query = "SELECT * FROM Estudante;";
		
		try {
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				Aluno aluno = new Aluno();
				
				ps = connection.prepareStatement(query);
				
				aluno.setID(rs.getInt(1));
				aluno.setDepartamento(null);
				aluno.setTutor(null);
				aluno.setNome(rs.getString(5));
				aluno.setCpf(rs.getString(6));
				aluno.setDataNascimento(String.valueOf(rs.getDate(7)));

				res.add(aluno);
			}
			if(res.isEmpty()) {
				System.out.println(CONSULTA_VAZIA);
			}
		}catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
			System.out.println(FALHA_OPERACAO);
		}finally {
			try { if (ps != null) ps.close(); } 
			catch (Exception ex) {
				System.err.println("SQLException: " + ex.getMessage());
			}
		}
		ConnectionFactory.closeConnection();
		
		for(int i=0; i<res.size(); i++) {
			res.get(i).setTutor(getProfessor(res.get(i).getID()));
			res.get(i).setDepartamento(getDepartamento(res.get(i).getID()));
			res.get(i).setDisciplina(getDisciplinas(res.get(i).getID()));
		}
		return res;
	}
	
	private ArrayList<Disciplina> getDisciplinas(int id) {
		String query;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Disciplina> disciplina = new ArrayList<Disciplina>();
		connection = ConnectionFactory.getConnection();
		query = "SELECT idDisciplina, nomeDisciplina FROM Estudante_Possue_Disciplina as eps, Disciplina "
				+ "WHERE eps.Estudante_idEstudante = ? "
				+ "AND eps.Disciplina_idDisciplina = Disciplina.idDisciplina;";
		
		if(connection == null) {
			System.out.println(FALHA_CONEXAO);
			return null;
		}try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				disciplina.add(new Disciplina(rs.getInt(1), rs.getString(2)));
			}
			if(disciplina.isEmpty())
				System.out.println(CONSULTA_VAZIA);
			
		}catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
			System.out.println(FALHA_OPERACAO);
		}finally {
			try { if (ps != null) ps.close(); } 
			catch (Exception ex) {
				System.err.println("SQLException: " + ex.getMessage());
			}
		}
		ConnectionFactory.closeConnection();
		return disciplina;
	}
	
	private Professor getProfessor(int id) {
		Professor professor = null;
		String query;
		PreparedStatement ps = null;
		ResultSet rs = null;
		connection = ConnectionFactory.getConnection();
		query = "SELECT idProfessor, Professor.nome FROM Professor, Estudante "
				+ "WHERE Estudante.idEstudante = ? AND Estudante.idProfessorTutor = Professor.idProfessor;";
		
		if(connection == null) {
			System.out.println(FALHA_CONEXAO);
			return null;
		}try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				professor = new Professor(rs.getInt(1), rs.getString(2));
			}
		}catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
			System.out.println(FALHA_OPERACAO);
		}finally {
			try { if (ps != null) ps.close(); } 
			catch (Exception ex) {
				System.err.println("SQLException: " + ex.getMessage());
			}
		}
		ConnectionFactory.closeConnection();
		
		return professor;
	}
	
	private Departamento getDepartamento(int id) {
		Departamento departamento = null;
		String query;
		PreparedStatement ps = null;
		ResultSet rs = null;
		connection = ConnectionFactory.getConnection();
		query = "SELECT idDepartamento, nomeDepartamento FROM Departamento, Estudante "
				+ "WHERE Estudante.idEstudante = ? AND Estudante.idDepartamentoDisciplinaEspecial = Departamento.idDepartamento;";
		
		if(connection == null) {
			System.out.println(FALHA_CONEXAO);
			return null;
		}try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				departamento = new Departamento(rs.getInt(1), rs.getString(2));
			}
		}catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
			System.out.println(FALHA_OPERACAO);
		}finally {
			try { if (ps != null) ps.close(); } 
			catch (Exception ex) {
				System.err.println("SQLException: " + ex.getMessage());
			}
		}
		ConnectionFactory.closeConnection();
		
		return departamento;
	}
}
