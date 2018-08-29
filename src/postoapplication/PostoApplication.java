package postoapplication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import postoapplication.DAO.CombustivelDAO;
import postoapplication.jdbc.ConnectionFactory;
import postoapplication.model.Combustivel;

/**
 *
 * @author fag
 */
public class PostoApplication {

    public static void main(String[] args) throws SQLException {
        /*Combustivel combustivel = new Combustivel();
        combustivel.setCodigo(2);
        combustivel.setDescricao("Gasolina Aditivada - Alterado");
        combustivel.setTipoCombustivel("GAa");
        combustivel.setDataAtualizacao(new Date());
        combustivel.setDataCadastro(new Date());
        combustivel.setUsuario(2);*/
        
        //System.out.println("Dados Combustivel ----> " + combustivel.toString());

        CombustivelDAO combustivelDAO = new CombustivelDAO();
        //combustivelDAO.save(combustivel);
        
        //combustivelDAO.delete(2);
        
        List<Combustivel> combustivelList = combustivelDAO.getAll();
        
        for (Combustivel c : combustivelList) {
            System.out.println("Combustivel consulta --> " + c.toString());
        }
        
        //Combustivel combustivel = combustivelDAO.getByName("Gasolina Aditivada");
        
        //System.out.println("Combustivel consulta --> " + combustivel.toString());
        
        //combustivelDAO.update(combustivel);
        
        /*combustivel = new Combustivel();
        combustivel.setCodigo(2);
        combustivel.setDescricao("Gasolina Aditivada");
        combustivel.setTipoCombustivel("GA");
        combustivel.setDataAtualizacao(new Date());
        combustivel.setDataCadastro(new Date());
        combustivel.setUsuario(1);
        //Salvar
        combustivelDAO.save(combustivel);*/
    }
    
}
