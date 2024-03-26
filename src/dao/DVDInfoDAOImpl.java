package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.DVDInfoModel;
import utils.State;

public class DVDInfoDAOImpl implements DVDInfoDAO {
	public DVDInfoDAOImpl() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/brightstar1213?characterEncoding=UTF-8", "root",
				"xiao1998yuSIXI");
	}

	public void add(String name) throws SQLException {
		String sql = "insert into dvd_info values (null, '0', ?, 0, '0', ?, ?)";
		try(Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);){
			SimpleDateFormat sdf1 =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss" );
			String nowDate1 = sdf1.format(new Date());
			ps.setString(1, name);
			ps.setString(2, nowDate1);
			ps.setString(3, nowDate1);
			ps.execute();
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()) {
				int id = rs.getInt(1);
				DVDInfoModel infoModel = new DVDInfoModel();
				infoModel.setId(id);
			}
		}catch (SQLException e) {
			throw e;
		}
	}

	public void delete(int id) throws SQLException {
		try (Connection c = getConnection(); Statement s = c.createStatement();){
			String sql = "delete from dvd_info where id = " + id;
			s.execute(sql);
			
		}catch(SQLException e) {
			throw e;
		}
	}

	public List<DVDInfoModel> list() throws SQLException {
		List<DVDInfoModel> data = new ArrayList<>();
		try (Connection c = getConnection(); Statement s = c.createStatement();) {

			String sql = "SELECT i.ID , i.STATE ,i.NAME, MAX(l.LEND_DATE) , i.COUNT from dvd_info i left join dvd_lend_info l on i.ID = l.DVD_ID and l.RETURN_DATE is null GROUP BY i.ID";
			ResultSet rs = s.executeQuery(sql);

			while (rs.next()) {
				DVDInfoModel infoModel = new DVDInfoModel();
				infoModel.setId(rs.getInt("id"));
				State state = State.getFromCode(rs.getString(2));
				infoModel.setState(state);
				infoModel.setName(rs.getString(3));
				infoModel.setLastLendDate(rs.getDate(4));
				infoModel.setCount(rs.getInt(5));
				data.add(infoModel);
				
			}


		} catch (SQLException e) {
			throw e;
		}
		return data;
	}
	
	public State getDVDState(int id) throws Exception {
		try (Connection c = getConnection(); Statement s = c.createStatement();){
			String sql = "select state from dvd_info where id = " + id;
			ResultSet rs = s.executeQuery(sql);
			if(rs.next()) {
				return State.getFromCode(rs.getString(1));
				
			}else {
				throw new Exception();
			}
		}catch(SQLException e) {
			throw e;
		}
	}
}
