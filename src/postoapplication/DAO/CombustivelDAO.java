package postoapplication.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import postoapplication.jdbc.ConnectionFactory;
import postoapplication.model.Combustivel;

/**
 *
 * @author fag
 */
public class CombustivelDAO implements GenericDAO<Combustivel> {

    private Connection connection;

    @Override
    public void save(Combustivel entity) throws SQLException {
        try {
            this.connection = new ConnectionFactory().getConnection();
            //Mais performatico que concatenar com +
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO COMBUSTIVEL(CD_COMBUSTIVEL, DS_COMBUSTIVEL, TIPO,")
                    .append("UNIDADEMEDIDA, DT_RECORD, CD_USUARIO) values (?, ?, ?, ?, ?, ?)");

            //Preparar a execução do comando
            PreparedStatement pstm = connection.prepareStatement(sql.toString());

            pstm.setInt(1, entity.getCodigo());
            pstm.setString(2, entity.getDescricao());
            pstm.setString(3, entity.getTipoCombustivel());
            pstm.setString(4, "1");
            pstm.setDate(5, new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
            pstm.setInt(6, entity.getUsuario());

            pstm.execute(); //Executa o comando no banco
            pstm.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao inserir Combustivel");
            ex.printStackTrace();
        } finally {
            connection.close(); //Fecha a conexão
        }
    }

    @Override
    public void update(Combustivel entity) throws SQLException {
        try {
            this.connection = new ConnectionFactory().getConnection();
            StringBuilder sql = new StringBuilder();
            sql.append("update combustivel set ds_combustivel = ?, ")
                    .append("tipo = ?, unidademedida = ?, ")
                    .append("dt_update = ?, cd_usuario = ? ")
                    .append("where cd_combustivel = ?");

            PreparedStatement pstm = connection.prepareStatement(sql.toString());

            pstm.setString(1, entity.getDescricao());
            pstm.setString(2, entity.getTipoCombustivel());
            pstm.setInt(3, 1);
            pstm.setDate(4, new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
            pstm.setInt(5, entity.getUsuario());
            pstm.setInt(6, entity.getCodigo());
            pstm.execute(); //Executa o comando no banco
            pstm.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao Atualizar Combustivel");
            ex.printStackTrace();
        } finally {
            connection.close();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        try {
            this.connection = new ConnectionFactory().getConnection();
            String sql = "DELETE FROM COMBUSTIVEL WHERE CD_COMBUSTIVEL = " + id;
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.execute();
            pstm.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao Deletar Combustivel");
            ex.printStackTrace();
        } finally {
            this.connection.close();
        }
    }

    @Override
    public Combustivel getById(int id) throws SQLException {
        Combustivel combustivel = null;
        try {
            this.connection = new ConnectionFactory().getConnection();
            String sql = "SELECT * FROM COMBUSTIVEL WHERE CD_COMBUSTIVEL = " + id;
            PreparedStatement pstm = connection.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            combustivel = new Combustivel();
            while (rs.next()) {
                combustivel.setCodigo(rs.getInt("CD_COMBUSTIVEL"));
                combustivel.setDescricao(rs.getString("DS_COMBUSTIVEL"));
                combustivel.setTipoCombustivel(rs.getString("TIPO"));
                combustivel.setUsuario(rs.getInt("CD_USUARIO"));
            }

            pstm.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao Buscar Combustivel Por ID");
            ex.printStackTrace();
        } finally {
            this.connection.close();
        }
        return combustivel;
    }

    @Override
    public List<Combustivel> getByName(String name) throws SQLException {
        Combustivel combustivel = null;
        List<Combustivel> combustivelList = null;
        try {
            this.connection = new ConnectionFactory().getConnection();
            String sql = "SELECT * FROM COMBUSTIVEL WHERE UPPER(DS_COMBUSTIVEL) LIKE UPPER('%" + name + "%') ";
            PreparedStatement pstm = connection.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            combustivelList = new ArrayList<>();
            while (rs.next()) {
                combustivel = new Combustivel();
                combustivel.setCodigo(rs.getInt("CD_COMBUSTIVEL"));
                combustivel.setDescricao(rs.getString("DS_COMBUSTIVEL"));
                combustivel.setTipoCombustivel(rs.getString("TIPO"));
                combustivel.setUsuario(rs.getInt("CD_USUARIO"));
                combustivelList.add(combustivel);
            }

            pstm.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao Buscar Combustivel Por Nome");
            ex.printStackTrace();
        } finally {
            this.connection.close();
        }
        return combustivelList;
    }

    @Override
    public List<Combustivel> getAll() throws SQLException {
        List<Combustivel> combustivelList = null;
        Combustivel combustivel = null;
        try {
            this.connection = new ConnectionFactory().getConnection();
            String sql = "SELECT * FROM COMBUSTIVEL ORDER BY CD_COMBUSTIVEL";
            PreparedStatement pstm = connection.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            combustivelList = new ArrayList<>();
            while (rs.next()) {
                combustivel = new Combustivel();
                combustivel.setCodigo(rs.getInt("CD_COMBUSTIVEL"));
                combustivel.setDescricao(rs.getString("DS_COMBUSTIVEL"));
                combustivel.setTipoCombustivel(rs.getString("TIPO"));
                combustivel.setUsuario(rs.getInt("CD_USUARIO"));
                combustivelList.add(combustivel);
            }

            pstm.close();
        } catch (SQLException ex) {
            System.out.println("Erro ao Buscar Todos Combustivel");
            ex.printStackTrace();
        } finally {
            this.connection.close();
        }
        return combustivelList;
    }

    @Override
    public int getLastId() throws SQLException {
        PreparedStatement pstm = null;
        try {
            this.connection = new ConnectionFactory().getConnection();
            String sql = "SELECT COALESCE(MAX(CD_COMBUSTIVEL),0) + 1 AS MAIOR FROM COMBUSTIVEL";
            pstm = connection.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return rs.getInt("MAIOR");
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao buscar maior ID Combustivel");
            ex.printStackTrace();
        } finally {
            this.connection.close();
            pstm.close();
        }
        return 1;
    }
}
