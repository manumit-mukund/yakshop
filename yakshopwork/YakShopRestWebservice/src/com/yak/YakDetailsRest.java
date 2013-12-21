package com.yak;

import java.io.File;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;


@Path("/yak-shop")

public class YakDetailsRest {

	@GET

	@Path("/{param1}/{param2}")

	@Produces("text/plain")

	public String getYakDetails(@PathParam("param1") String yak, @PathParam("param2") int T) {

		String yakDetails = "";
		YakUtil yu=new YakUtil();	
		yu.readXML(new File("resources/herd.xml"),T);

		if (yak.equals("stock")) {
									
			yakDetails+="milk: ";		
			yakDetails+=String.format("%.2f",yu.getTotalMilkProduced())+",\n";	
			yakDetails+="skins: "+T%10;
			

		} else if (yak.equals("herd")) {

			yakDetails = "";
			yakDetails+="herd:\n"+yu.getHerdDeatils();						

		}  else {

			yakDetails = "Data not found";
		}

		return yakDetails;
	}
	
	
}