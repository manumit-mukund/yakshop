package yak.com.businness;

import java.io.File;
import java.util.LinkedHashSet;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class YakUtil {

	private double totalMilkProduced = 0.0;	
	private LinkedHashSet<String> herdDeatils = new LinkedHashSet<String>();

	

	public void readXML(File file, int T) {
		try {

			DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();

			Document doc = dBuilder.parse(file);
			
			if (doc.hasChildNodes()) {

				printNote(doc.getChildNodes(), T);

			}
			System.out.println("\n");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void printNote(NodeList nodeList, int T) {

		for (int count = 0; count < nodeList.getLength(); count++) {

			Node tempNode = nodeList.item(count);

			// Ensure it's element node.
			if (tempNode.getNodeType() == Node.ELEMENT_NODE) {

				// get node name and value
				
				String name="";
				double age=0.0;

				if (tempNode.hasAttributes()) {

					// get attributes names and values
					NamedNodeMap nodeMap = tempNode.getAttributes();

					for (int i = 0; i < nodeMap.getLength(); i++) {

						Node node = nodeMap.item(i);
						
						if (node.getNodeName().equals("name"))
							name=node.getNodeValue();
						if (node.getNodeName().equals("age"))
						{
							age=Double.parseDouble(node.getNodeValue())+(double)T/100;						
							totalMilkProduced += calcMilkProduced(age,T);
						}
						
					}
					
					herdDeatils.add(name+" "+age +" years old");				

				}

				if (tempNode.hasChildNodes()) {

					// loop again if has child nodes
					printNote(tempNode.getChildNodes(), T);

				}				
			}

		}

	}

	public double calcMilkProduced(double ageInYears, int T) {
		return (50 - (ageInYears * 100 * 0.03))*(T-1);
	}
	
	public double getTotalMilkProduced() {
		return totalMilkProduced;
	}

	public LinkedHashSet<String> getHerdDeatils() {
		return herdDeatils;
	}
	

}
