
package com.mycompany.test;



import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.mycompany.service.UrlService;
import com.mycompany.utils.CsvUtil;
import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Test05 {


	public static void main(String[] args) throws IOException {
		
		
	
			  //读取json文件，保存到String json中
			  String fileName="json/ioc_list";
			  File file=new File(fileName);
			  StringBuffer sb = new StringBuffer() ;
			  String sJson;
			  String line;
			  BufferedReader br=null;
			  try {
			   br=new BufferedReader(new FileReader(file));
			  } catch (FileNotFoundException e) {
			   e.printStackTrace();
			  }
			  try {
			   while((line=br.readLine())!=null){
			    sb.append(line);
			   }
			  } catch (IOException e) {
			   e.printStackTrace();
			  }
			  String json=sb.toString();
			//  System.out.println("json:"+json);

			  //利用com.google.gson 包将json转换成List

			  List persons=new ArrayList();
			  Gson gson = new Gson();
			  List<JsonElement> list=new ArrayList();
			  JsonParser jsonParser=new JsonParser();
			  JsonElement jsonElement=jsonParser.parse(json);  //将json字符串转换成JsonElement
			  JsonArray jsonArray=jsonElement.getAsJsonArray();  //将JsonElement转换成JsonArray
			  Iterator it=jsonArray.iterator();  //Iterator处理
			  while(it.hasNext()){  //循环
			   jsonElement=(JsonElement) it.next(); //提取JsonElement
			   json=jsonElement.toString();  //JsonElement转换成String
			   System.out.println(json);
//			   Person person=gson.fromJson(json, Person.class); //String转化成JavaBean
//			   persons.add(person);  //加入List
			  }

			  System.out.println("ok");

			 }
		
		
		
		
		
		
		
		
		
		
		
//	
//		File file=new File("json/json");
//		InputStreamReader read=new InputStreamReader(new FileInputStream(file));
//		BufferedReader	bufferedReader = new BufferedReader(read);
//		String lineTxt=null;
//		String str=null;
//		while (( lineTxt= bufferedReader.readLine()) != null) {
//			str=str+lineTxt;
//			
//		}
//	bufferedReader.close();
//		
//	
//		Gson gson=new Gson();
//	List<Person> ps = gson.fromJson(str, new TypeToken<List<Person>>(){}.getType());   
//		for(int i = 0; i < ps.size() ; i++)   
//		{   
//	     Person p = ps.get(i);   
//	     System.out.println(p.toString());   
//		}  
//		
//		

		
		
		
		
		
		
		
		
		
		
//		
//		Gson gson = new Gson();
//		List<Person> persons = new ArrayList<Person>();
//		for (int i = 0; i < 10; i++) {
//		     Person p = new Person();
//		     p.setName("name" + i);
//		     p.setAge(i * 5);
//		     persons.add(p);
//		}
//		String str = gson.toJson(persons);
//		System.out.println(str);

		
		
}

	
	

		
		
		
		

