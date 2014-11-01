package com.daxia.core.util.dev;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.common.collect.Lists;

public class CodeDeleteUtils {
	static List<String> deleteList = new ArrayList<String>();
	static {
		deleteList.add("src/main/java/com/daxia/core/model/{model}.java");
		deleteList.add("src/main/java/com/daxia/core/dao/{model}DAO.java");
		deleteList.add("src/main/java/com/daxia/core/service/{model}Service.java");
		deleteList.add("src/main/java/com/daxia/core/web/controller/admin/Admin{model}Controller.java");
		deleteList.add("src/main/java/com/daxia/core/web/controller/m/M{model}Controller.java");
		deleteList.add("src/main/java/com/daxia/core/dto/{model}DTO.java");
       deleteList.add("src/main/java/com/daxia/wy/dto/api/{model}APIDTO.java");
       deleteList.add("src/main/java/com/daxia/wy/dto/api/info/{model}InfoAPIDTO.java");
		deleteList.add("src/main/webapp/WEB-INF/jsp/admin/{model}/{model}_list.jsp");
		
		deleteList.add("src/main/webapp/WEB-INF/jsp/admin/{model}/{model}_detail.jsp");
	    deleteList.add("src/main/webapp/WEB-INF/jsp/admin/{model}/{model}_search.jsp");
	    deleteList.add("src/main/webapp/WEB-INF/jsp/admin/{model}");

	}
			
	public static void main(String[] args) throws Exception {
//		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入要删除的model: ");
		 //String model = scanner.nextLine();
//		String model = "Kevin";
		List<String> models = Lists.newArrayList();
		models.add("Order");
		
		models.add("Account");
		models.add("Brand");
		models.add("Dept");
		models.add("Exchange");
		models.add("FeedBack");
		models.add("GatherPrice");
		models.add("Groups");
		models.add("HomeImage");
		models.add("Merchant");
		models.add("Message");
		models.add("MessageSend");
		models.add("Notice");
		models.add("OrderItem");
		models.add("Orders");
		models.add("Product");
		models.add("QuestionItem");
		models.add("QuestionItem2");
		models.add("QuestItem");
		models.add("Sign");
		models.add("Topic");
		models.add("User");
		models.add("UserGroups");
		models.add("Visit");
		models.add("VisitDetail");
		models.add("Vote");
		models.add("VoteQuestion");
		models.add("Work");
		models.add("WorkSheet");
		models.add("UserRole");
		
		
		for (String model : models) {
			for (String path : deleteList) {
				File file = new File(path.replace("{model}", model));
				if (file.exists()) {
					file.delete();
				}
			}
		}
		
    }
}
