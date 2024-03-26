package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.DVDLendInfoModel;

public class DVDLendInfoDAOImpl implements DVDLendInfoDAO{

	public DVDLendInfoDAOImpl() {
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
	
	public void lend(int dvdId) throws SQLException {
		String sql1 = "insert into dvd_lend_info values (null, ?, ?, null, 1, null, ?, ?)";
		try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);Statement s = c.createStatement();){
			SimpleDateFormat sdf1 =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss" );
			String nowDate = sdf1.format(new Date());
			ps.setInt(1, dvdId);
			ps.setString(2, nowDate);
			ps.setString(3, nowDate);
			ps.setString(4, nowDate);
			ps.execute();
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()) {
				int id = rs.getInt(1);
				DVDLendInfoModel lendInfoModel = new DVDLendInfoModel();
				lendInfoModel.setId(id);
			}
			String sql2 = "update dvd_info set STATE = '1', COUNT = COUNT + 1, LAST_UPDATE_TIME = CURRENT_TIMESTAMP where id = " + dvdId;
			s.execute(sql2);
			
		}catch(SQLException e) {
			throw e;
		}
	}

	@Override
	public void returnBack(int dvdId) throws SQLException {
		try (Connection c = getConnection(); Statement s = c.createStatement();){
			String sql1 = "select LEND_DATE from dvd_lend_info where DVD_ID = " + dvdId;
			ResultSet rs = s.executeQuery(sql1);
			double nowDate=new Date().getTime();
			int lendDays = 0;
			while (rs.next()) {
				DVDLendInfoModel lendInfoModel = new DVDLendInfoModel();
				double lendDate = rs.getDate(1).getTime();
				lendDays = (int)Math.ceil((nowDate - lendDate)/24/60/60/1000);
				lendInfoModel.setCost(lendDays);
			}
			String sql2 = "update dvd_lend_info set RETURN_DATE = CURRENT_TIMESTAMP, COST = " + lendDays + " where DVD_ID = " + dvdId + " and RETURN_DATE is null";
			s.execute(sql2);
			String sql3 = "update dvd_info set STATE = '0', LAST_UPDATE_TIME = CURRENT_TIMESTAMP where id = " + dvdId;
			s.execute(sql3);
		}catch(SQLException e) {
			throw e;
		}
		
	}

}
