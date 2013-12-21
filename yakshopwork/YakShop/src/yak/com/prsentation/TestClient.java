package yak.com.prsentation;

import java.io.File;
import java.util.Iterator;

import yak.com.businness.YakUtil;

public class TestClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		YakUtil yu=new YakUtil();			
		yu.readXML(new File("resources/herd.xml"), 14);	
		
		System.out.println("In Stock:");
		System.out.print("\t");
		
		System.out.println(String.format("%.2f",yu.getTotalMilkProduced())+" liters of milk\n");
		System.out.println("Herd:");
		
		Iterator<String> iterator = yu.getHerdDeatils().iterator();
		while (iterator.hasNext())
		{
			System.out.print("\t");
			System.out.println(iterator.next());
		}
		//System.out.println(yu.getHerdDeatils());
		
	}

}
