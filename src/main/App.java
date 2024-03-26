package main;

import java.util.Scanner;

import services.DVDService;


public class App {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		DVDService service = new DVDService();
		String[] options = { "新增", "查看", "删除", "借出", "归还", "退出", };

		while (true) {
			System.out.println("欢迎使用迷你DVD管理器");
			System.out.println("-------------------------");
			for (int i = 0; i < options.length; i++) {
				System.out.println((i + 1) + "." + options[i] + "DVD");
			}
			System.out.print("请选择：");
			int selectedOption = new Scanner(System.in).nextInt();
			boolean isContinue = true;
			switch (selectedOption) {
				case 1:
					service.add();
					break;
				case 2:
					service.query();
					break;
				case 3:
					service.delete();
					break;
				case 4:
					service.lend();
					break;
				case 5:
					service.returnBack();
					break;
				case 6:
					System.out.println("--->退出DVD");
					isContinue = false;
					break;
				default:
					break;
				
			}
			if(!isContinue) {
				break;
			}
			System.out.println("-------------------------");
			System.out.print("输入0返回：");
			int back = new Scanner(System.in).nextInt();
			System.out.println();
			if(back != 0) {
				break;
			}

		}

	}
}
