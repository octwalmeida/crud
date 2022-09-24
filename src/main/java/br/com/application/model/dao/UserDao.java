package br.com.application.model.dao;

import br.com.application.factory.ConnectionFactory;
import br.com.application.model.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    public void save (User user) {
        String sql = "INSERT INTO user (name, email, born_date, city, password)" + "VALUES(?,?,?,?,?)";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);

            pstm.setString(1,user.getName());
            pstm.setString(2, user.getEmail());
            pstm.setDate(3,  user.getBornDate());
            pstm.setString(4,user.getCity());
            pstm.setString(5, user.getPassword());


            pstm.execute();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {

            try {
                if (pstm != null) {
                    pstm.close();
                }

                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    public void removeById (int id) {
        String sql = "DELETE FROM user where id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);

            pstm.setInt(1,id);


            pstm.execute();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {

            try {
                if (pstm != null) {
                    pstm.close();
                }

                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void update (User user) {
        String sql = "UPDATE user set name = ?, city = ?, born_date = ?, gender = ?, email = ?" + "WHERE id = ?";

        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);

            pstm.setString(1,user.getName());
            pstm.setString(2, user.getCity());
            pstm.setDate(3, user.getBornDate());
            pstm.setString(4, user.getGender());
            pstm.setString(5, user.getEmail());


            pstm.setInt(6, user.getId());

            pstm.execute();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {

            try {
                if (pstm != null) {
                    pstm.close();
                }

                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    public List<User> findById(Integer id){

        String sql = "SELECT * FROM user WHERE id = ?";

        List<User> users = new ArrayList<User>();

        Connection conn = null;
        PreparedStatement pstm = null;

        ResultSet rset = null;

        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);

            pstm.setInt(1,id);

            rset = pstm.executeQuery();

            while(rset.next()) {
                User user = new User();

                user.setId(rset.getInt("id"));

                user.setName(rset.getString("name"));

                user.setEmail(rset.getString("email"));

                user.setBornDate(rset.getDate("born_date"));

                user.setCity(rset.getString("city"));

                users.add(user);
            }


        }catch (Exception e) {
            e.printStackTrace();
        }finally {

            try {

                if (rset != null) {
                    rset.close();
                }

                if (pstm != null) {
                    pstm.close();
                }

                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return users;
    }

    public List<User> findAll(){

        String sql = "SELECT * FROM user";

        List<User> users = new ArrayList<User>();

        Connection conn = null;
        PreparedStatement pstm = null;

        ResultSet rset = null;

        try {
            conn = ConnectionFactory.createConnectionToMySQL();
            pstm = conn.prepareStatement(sql);
            rset = pstm.executeQuery();

            while(rset.next()) {
                User user = new User();

                user.setId(rset.getInt("id"));

                user.setName(rset.getString("name"));

                user.setEmail(rset.getString("email"));

                user.setBornDate(rset.getDate("born_date"));

                user.setCity(rset.getString("city"));

                users.add(user);
            }


        }catch (Exception e) {
            e.printStackTrace();
        }finally {

            try {

                if (rset != null) {
                    rset.close();
                }

                if (pstm != null) {
                    pstm.close();
                }

                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return users;
    }


}
