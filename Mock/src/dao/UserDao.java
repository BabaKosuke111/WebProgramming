package dao;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import model.User;

/**
 * ユーザテーブル用のDao
 * @author takano
 *
 */
public class UserDao {

    /**
     * ログインIDとパスワードに紐づくユーザ情報を返す
     * @param loginId
     * @param password
     * @return
     */
    public User findByLoginInfo(String loginId) {
        Connection conn = null;
        try {
            // データベースへ接続
            conn = DBManager.getConnection();

            // SELECT文を準備
            String sql = "SELECT * FROM user WHERE login_id = ? ";

             // SELECTを実行し、結果表を取得
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, loginId);
            ResultSet rs = pStmt.executeQuery();

             // 主キーに紐づくレコードは1件のみなので、rs.next()は1回だけ行う
            if (!rs.next()) {
                return null;
            }

            String loginIdData = rs.getString("login_id");
            String nameData = rs.getString("name");
            return new User(loginIdData, nameData);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            // データベース切断
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
    }
    public User findByLoginInfo(String loginId, String password)  {
        Connection conn = null;
        try {
            // データベースへ接続
            conn = DBManager.getConnection();

            // SELECT文を準備
            String sql = "SELECT * FROM user WHERE login_id = ? and password = ?";

            String result1=CodeInfo(password);

             // SELECTを実行し、結果表を取得
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, loginId);
            pStmt.setString(2, result1);
            ResultSet rs = pStmt.executeQuery();

             // 主キーに紐づくレコードは1件のみなので、rs.next()は1回だけ行う
            if (!rs.next()) {
                return null;
            }

            String loginIdData = rs.getString("login_id");
            String nameData = rs.getString("name");
            return new User(loginIdData, nameData);

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            // データベース切断
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
    }


    /**
     * 全てのユーザ情報を取得する
     * @return
     * @throws NoSuchAlgorithmException
     */
    public List<User> findAll()  {
        Connection conn = null;
        List<User> userList = new ArrayList<User>();

        try {
            // データベースへ接続
            conn = DBManager.getConnection();

            // SELECT文を準備
            // TODO: 未実装：管理者以外を取得するようSQLを変更する
            String sql = "SELECT * FROM user  ";


             // SELECTを実行し、結果表を取得
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // 結果表に格納されたレコードの内容を
            // Userインスタンスに設定し、ArrayListインスタンスに追加
            while (rs.next()) {
                int id = rs.getInt("id");
                String loginId = rs.getString("login_id");
                String name = rs.getString("name");
                Date birthDate = rs.getDate("birth_date");
                String password = rs.getString("password");
                String createDate = rs.getString("create_date");
                String updateDate = rs.getString("update_date");
                User user = new User(id, loginId, name, birthDate, password, createDate, updateDate);

                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            // データベース切断
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
        return userList;
    }
    public List<User> findSearch(String login_id, String na_me,String startDate,String endDate) {
        Connection conn = null;
        List<User> userList = new ArrayList<User>();

        try {
            // データベースへ接続
            conn = DBManager.getConnection();

            // SELECT文を準備
            // TODO: 未実装：管理者以外を取得するようSQLを変更する
            String sql = "SELECT * FROM user where login_id not in ('admin')";

            if(!login_id.equals("")){
            	sql += " AND login_id ='" + login_id + "'";
            }
            if(!na_me.equals("")) {
            	sql +=" AND name LIKE '%"+na_me+"%'";
            }
            if(!startDate.equals("")) {
            	sql +=" AND birth_date>='"+startDate+"'";
            }
            if(!endDate.equals("")) {
            	sql +=" AND birth_date<='"+endDate+"'";
            }

            // SELECTを実行し、結果表を取得
           Statement stmt = conn.createStatement();
           ResultSet rs = stmt.executeQuery(sql);

            // 結果表に格納されたレコードの内容を
            // Userインスタンスに設定し、ArrayListインスタンスに追加
            while (rs.next()) {
                int id = rs.getInt("id");
                String loginId = rs.getString("login_id");
                String name = rs.getString("name");
                Date birthDate = rs.getDate("birth_date");
                String password = rs.getString("password");
                String createDate = rs.getString("create_date");
                String updateDate = rs.getString("update_date");
                User user = new User(id, loginId, name, birthDate, password, createDate, updateDate);

                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            // データベース切断
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
        return userList;
    }


    public void NewLoginInfo(String loginId,String password,String name,String birthDate)  {
        Connection conn = null;
        try {

            conn = DBManager.getConnection();

            String sql = "INSERT INTO user(login_id,name,birth_date,password,create_date,update_date) VALUES(?,?,?,?,now(),now());";

            String result1=CodeInfo(password);

            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1,loginId);
            pStmt.setString(2,name);
            pStmt.setString(3,birthDate);
            pStmt.setString(4,result1);
            int result =pStmt.executeUpdate();

            pStmt.close();


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }


    }
    public User findById(String targetId)  {
        Connection conn = null;

        try {
            // データベースへ接続
            conn = DBManager.getConnection();

            // SELECT文を準備
            // TODO: 未実装：管理者以外を取得するようSQLを変更する
            String sql = "SELECT * FROM user WHERE id = ?";

             // SELECTを実行し、結果表を取得
            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, targetId);
            ResultSet rs = pStmt.executeQuery();


            if (!rs.next()) {
            	return null;
            }
                int id = rs.getInt("id");
                String loginId = rs.getString("login_id");
                String name = rs.getString("name");
                Date birthDate = rs.getDate("birth_date");
                String password = rs.getString("password");
                String createDate = rs.getString("create_date");
                String updateDate = rs.getString("update_date");
                return new User(id, loginId, name, birthDate, password, createDate, updateDate);


        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            // データベース切断
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }

    }
    public void UpdateInfo(String password,String name,String birthDate,String targetId)  {
        Connection conn = null;
        try {

            conn = DBManager.getConnection();

            String sql = "UPDATE user SET name =? ,birth_date=?,password=?,update_date=now() WHERE id=?;";

            String result1=CodeInfo(password);

            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1,name);
            pStmt.setString(2,birthDate);
            pStmt.setString(3,result1);
            pStmt.setString(4,targetId);
            int result =pStmt.executeUpdate();

            pStmt.close();


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void UpdateInfo(String name,String birthDate,String targetId)  {
        Connection conn = null;
        try {

            conn = DBManager.getConnection();

            String sql = "UPDATE user SET name =? ,birth_date=?,update_date=now() WHERE id=?;";



            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1,name);
            pStmt.setString(2,birthDate);
            pStmt.setString(3,targetId);
            int result =pStmt.executeUpdate();

            pStmt.close();


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void DeleteInfo(String targetId) {
        Connection conn = null;
        try {

            conn = DBManager.getConnection();

            String sql = " DELETE FROM user WHERE id = ?;" ;




            PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1,targetId);
            int result =pStmt.executeUpdate();

            pStmt.close();


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public String CodeInfo(String password) {
    String source = password;
    //ハッシュ生成前にバイト配列に置き換える際のCharset
    Charset charset = StandardCharsets.UTF_8;
    //ハッシュアルゴリズム
    String algorithm = "MD5";

    //ハッシュ生成処理
    byte[] bytes = null;
	try {
		bytes = MessageDigest.getInstance(algorithm).digest(source.getBytes(charset));
	} catch (NoSuchAlgorithmException e) {
		// TODO 自動生成された catch ブロック
		e.printStackTrace();
	}
    String result = DatatypeConverter.printHexBinary(bytes);

    return result;
    }

}
