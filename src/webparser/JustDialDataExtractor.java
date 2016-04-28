package webparser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class JustDialDataExtractor {	
	    public static void main( String[] args ) throws IOException{  
	    	RestaurantsModel model=null;
	    	List<RestaurantsModel> restList = new ArrayList<RestaurantsModel>();
	    	RestaurantsModel model2 = new RestaurantsModel();
	    	model2.setName("Name of Restaurants");
	    	model2.setPhone("Phone Numbers");
	    	model2.setAddress("Full Address");
	    	model2.setRating("Restaurants Rating");
	    	model2.setYear("Estd.in Year");
	    	restList.add(model2);
	    	RestaurantsModel model1 = new RestaurantsModel();
	    	model1.setName("");
	    	model1.setPhone("");
	    	model1.setAddress("");
	    	model1.setRating("");
	    	model1.setYear("");
	    	restList.add(model1);
	    	 Document doc=null;
	    	 String excelFilePath = "src//JustDialInfo.xls";
	    	 int i=1;
	    	 Elements name=null;
	    	do{	    		
	    			doc  = Jsoup.connect("http://www.justdial.com/Bangalore/insurance-agents/ct-3750/page-"+i).userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/535.21"
                            + " (KHTML, like Gecko) Chrome/19.0.1042.0 Safari/535.21").timeout(1000000000).get();
	                System.out.println("pageno."+i);	            
	                Element  aTag= doc.getElementById("tab_block");	            
	            	  name=aTag.getElementsByClass("store-name");
	            	 Elements phones = aTag.getElementsByClass("contact-info");	
	            	 Elements add = aTag.getElementsByClass("mrehover");       
	            	 Elements ratings = aTag.getElementsByClass("rt_count"); 
	            	 Elements years = aTag.getElementsByClass("year"); 
	            	
	             for (Element a : name) {  
	                	if(!a.getElementsByAttribute("title").text().isEmpty()){
	                		model = new RestaurantsModel();
	                		model.setName(a.getElementsByAttribute("title").text());
	               //	System.out.println("name : " + a.getElementsByAttribute("title").text());  	
	      	 
	                	}
	             for (Element phone : phones) {  
	            	 model.setPhone(phone.getElementsByTag("b").text());
		              //  System.out.println("phone <b>: " + phone.getElementsByTag("b").text());
		                if(phone.getElementsByTag("b").text().isEmpty()){
		                	model.setPhone(phone.getElementsByTag("a").text());
		             //  	System.out.println("phone <a> : " + phone.getElementsByTag("a").text());
		                }
		                phones.remove(phone);
		                break;
          
		                	 }	    
	             for (Element address : add) {
	            	 model.setAddress(address.getElementsByTag("span").text());
            		// System.out.println("address : " + address.getElementsByTag("span").text());
            		 add.remove(address);
            		 break;
            	 }
	             for (Element rating : ratings) {
	            	 model.setRating(rating.getElementsByClass("rt_count").text());
            		 //System.out.println("rating : " + rating.getElementsByClass("rt_count").text());
            		 ratings.remove(rating);
            		 break;
            	 }
	             for (Element year : years) {
	            	 model.setYear(year.getElementsByClass("year").text());
            		// System.out.println("year : " + year.getElementsByClass("year").text());
            		 years.remove(year);
            		 break;
            	 }
	             restList.add(model);	  
	           }  
	        //     System.out.println("*************************************************");
	             i++;	                   
	    	}while(name.size()!=0);
	    
	    	ExcelWriter.writeExcel(restList,excelFilePath);
	    }  
	    }
	 

