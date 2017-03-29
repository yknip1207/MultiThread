package DriverSynchronization;

public class Company {

	private int sum = 0;

	//當某個Thread在call run()的時候，其他thread就無法call run()
	public synchronized void add(int a, String name){
		int tmp = sum;
		System.out.println("目前合計金額是：" + tmp + " 元");
		System.out.println(name + " 賺了 "+ a +" 元");
		tmp += a;
		System.out.println("合計金額變： " + tmp + " 元");
		this.sum = tmp;

	}

	//	public static Object lock = new Object();
	//	方法一：多宣告一個static Object lock
	//		public void add(int a, String name){
	//		synchronized(lock){
	//			int tmp = sum;
	//			System.out.println("目前合計金額是：" + tmp + " 元");
	//			System.out.println(name + " 賺了 "+ a +" 元");
	//			tmp += a;
	//			System.out.println("合計金額變： " + tmp + " 元");
	//			this.sum = tmp;
	//		}
	//	}





}
