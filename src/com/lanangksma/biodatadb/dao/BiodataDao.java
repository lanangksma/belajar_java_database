package com.lanangksma.biodatadb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import com.lanangksma.biodatadb.biodata.Biodata;
import com.lanangksma.biodatadb.db.MySqlConnection;
public class BiodataDao {

    public int insert(Biodata biodata){
        String query = "INSERT INTO biodata (id, nama, alamat, no_hp, jenis_kelamin, wna) VALUES (?, ?, ?, ?, ?, ?)";
        return executeUpdate(query, biodata);
    }

    public int update(Biodata biodata){
        String query = "UPDATE biodata SET nama = ?, no_hp = ?, jenis_kelamin = ?, wna = ?, alamat = ? WHERE id = ?";
        return executeUpdate(query, biodata);
    }

    public int delete(String id) {
        int result = -1;
        try(Connection connection = MySqlConnection.getInstance().getConnection();) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM biodata WHERE id = ?");
            statement.setString(1, id);

            result = statement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private int executeUpdate(String query, Biodata biodata){
        int result = -1;
        try(Connection connection = MySqlConnection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, biodata.getId());
            statement.setString(2, biodata.getNama());
            statement.setString(3, biodata.getAlamat());
            statement.setString(4, biodata.getNoHp());
            statement.setString(5, biodata.getJenisKelamin());
            statement.setString(6, biodata.getStatus());
            result = statement.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    public List<Biodata> getAll(){
        List<Biodata> list = new ArrayList<>();
        String query = "SELECT * FROM biodata";
        try(Connection connection = MySqlConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query)){
            while(resultSet.next()){
                Biodata biodata = new Biodata();
                biodata.setId(resultSet.getString("id"));
                biodata.setNama(resultSet.getString("nama"));
                biodata.setAlamat(resultSet.getString("alamat"));
                biodata.setNoHp(resultSet.getString("no_hp"));
                biodata.setJenisKelamin(resultSet.getString("jenis_kelamin"));
                biodata.setStatus(resultSet.getString("wna"));
                list.add(biodata);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return list;
    }
}
