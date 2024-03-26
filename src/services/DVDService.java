package services;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import dao.DVDInfoDAOImpl;
import dao.DVDLendInfoDAOImpl;
import model.DVDInfoModel;
import utils.State;

public class DVDService {
	private DVDInfoDAOImpl infoDAO;
	private DVDLendInfoDAOImpl lendInfoDAO;
	
	public DVDService() {
		this.infoDAO = new DVDInfoDAOImpl();
		this.lendInfoDAO = new DVDLendInfoDAOImpl();
	}
	
	public void add() {
		try {
			System.out.println("--->新增DVD");
			System.out.print("请输入需要新增DVD名字:");
			String name = new Scanner(System.in).nextLine();
			infoDAO.add(name);
			System.out.println("成功新增DVD 《" + name + "》");
		}catch(Exception e) {
			System.out.println("新增失败");
		}
		
	}
	
	public void query() {
		try {
			System.out.println("--->查看DVD");
			System.out.println();
			List<DVDInfoModel> infoList = infoDAO.list();
			System.out.println("序号\t状态\t名称\t借出日期\t借出次数");
			
			for (DVDInfoModel infoModel : infoList) {
				String lendDays;
				double nowDate=new Date().getTime();
				if(infoModel.getLastLendDate() != null) {
					double lendDate = infoModel.getLastLendDate().getTime();
					lendDays = (int)Math.ceil((nowDate - lendDate)/24/60/60/1000) + "日";
				}else {
					lendDays = "";
				}
				
				System.out.printf("%d\t%s\t%s\t%s\t%d%n", infoModel.getId(), infoModel.getState().label,
						"《" + infoModel.getName() + "》",lendDays, infoModel.getCount());
			}
		}catch(Exception e) {
			System.out.println("查找失败");
		}
		
	}
	
	public void delete() {
		
		try {
			System.out.println("--->删除DVD");
			System.out.print("请输入需要删除的DVD序号:");
			int deleteID = new Scanner(System.in).nextInt();
			infoDAO.delete(deleteID);
			System.out.println("删除成功");
		} catch (Exception e) {
			System.out.println("删除失败");
		}
		
	}
	
	public void lend() {
		try {
			System.out.println("--->借出DVD");
			System.out.print("请输入需要借出DVD序号:");
			int id = new Scanner(System.in).nextInt();
			if(infoDAO.getDVDState(id) == State.RENTABLE) {
				lendInfoDAO.lend(id);
				System.out.println("成功借出DVD");
			}else {
				System.out.println("此DVD已借出，不可重复借出");
			}
			
		}catch(Exception e) {
			System.out.println("借出失败，请输入正确序号");
		}
		
	}
	
	public void returnBack() {
		try {
			System.out.println("--->归还DVD");
			System.out.print("请输入需要归还DVD序号:");
			int id = new Scanner(System.in).nextInt();
			if(infoDAO.getDVDState(id) == State.RENTED) {
				lendInfoDAO.returnBack(id);
				System.out.println("成功归还DVD");
			}else {
				System.out.println("此DVD已归还，不可重复归还");
			}
			
		}catch(Exception e) {
			System.out.println("归还失败，请输入正确序号");
		}
		
	}
}
