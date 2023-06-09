package com.web.cyneuro.reports;

import java.util.HashMap;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;


@Controller
@RequestMapping("/reports")
@ComponentScan(basePackages = {"com.web.cyneuro"})
public class reportsController{

	@Autowired
	private Environment env;
	RestTemplate restTemplate = new RestTemplate();
  
	
	@RequestMapping("/getGeneDict")
	@ResponseBody
    public Map getGeneDict() throws Exception {
		String url = env.getProperty("python.service.url");
		url += "/get_gene_dict";
		System.out.println(url);
		
		Map result = null;
    	try {
    		result = restTemplate.getForObject(url, Map.class);
//    		System.out.print(result);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    	return result;
		
	}
	
	@RequestMapping("/getGeneLenDict")
	@ResponseBody
    public Map getGeneLenDict() throws Exception {
		String url = env.getProperty("python.service.url");
		url += "/get_gene_len_dict";
		
		Map result = null;
    	try {
    		result = restTemplate.getForObject(url, Map.class);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    	return result;
		
	}
	
	@RequestMapping("/getDrugDict")
	@ResponseBody
    public Map getDrugDict() throws Exception {
		String url = env.getProperty("python.service.url");
		url += "/get_drug_dict";
//		String url2 = url + "/get_gene_len_dict";
		
		Map result = null;
    	try {
    		result = restTemplate.getForObject(url, Map.class);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    	return result;
		
	}
	
	@RequestMapping("/getDrugLenDict")
	@ResponseBody
    public Map getDrugLenDict() throws Exception {
		String url = env.getProperty("python.service.url");
		url += "/get_drug_len_dict";
		
		Map result = null;
    	try {
    		result = restTemplate.getForObject(url, Map.class);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    	return result;
		
	}
	
	@RequestMapping("/getGeneAllDict")
	@ResponseBody
    public Map getGeneAllDict() throws Exception {
		String url = env.getProperty("python.service.url");
		url += "/get_gene_all_dict";
		
		Map result = null;
    	try {
    		result = restTemplate.getForObject(url, Map.class);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    	return result;
		
	}
	
	@RequestMapping("/getDrugAllDict")
	@ResponseBody
    public Map getDrugAllDict() throws Exception {
		String url = env.getProperty("python.service.url");
		url += "/get_drug_all_dict";
		
		Map result = null;
    	try {
    		result = restTemplate.getForObject(url, Map.class);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    	return result;
		
	}
	
	@RequestMapping("/getInfoJson")
	@ResponseBody
    public Map getInfoJson() throws Exception {
		String url = env.getProperty("python.service.url");
		url += "/get_info_json";
		
		Map result = null;
    	try {
    		result = restTemplate.getForObject(url, Map.class);
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    	return result;
		
	}
	
// 	@PostMapping("/doAnalysisGenes")
// 	@ResponseBody
// 	public Map<String, List<articles>> genesAnalysis() {
// //		String title = request.getParameter("title");
// //		String abstracts = request.getParameter("abstracts");
// //		String full_paper = request.getParameter("full_paper");
// //		String genes_name = request.getParameter("genes");
		
// 		Map<String, List<articles>> paperDict = new HashMap<String, List<articles>>();
		
// 		List<genes> genes_list = genesService.findAll();
		
// 		if(genes_list.size()>0) {
// 			for (genes gene : genes_list) {
// 				String gene_name = gene.getName();
// 				List<articles> all_gene_paper_list = new ArrayList<articles>();
// 				List<articles> gene_paper_list_title = publicationService.findByTitleContaining(gene_name);
// 				List<articles> gene_paper_list_abstract = publicationService.findByAbstractsContaining(gene_name);
				
// 				all_gene_paper_list.addAll(gene_paper_list_title);
// 				all_gene_paper_list.removeAll(gene_paper_list_abstract);
// 				all_gene_paper_list.addAll(gene_paper_list_abstract);

				
// 				if(!paperDict.containsKey(gene_name)) {
// 					paperDict.put(gene_name, all_gene_paper_list);
// 				}else {
// 					all_gene_paper_list.removeAll(paperDict.get(gene_name));
// 					all_gene_paper_list.addAll(paperDict.get(gene_name));
// 					paperDict.put(gene_name, all_gene_paper_list);
// 				}
// 			}
// 		}
		
// 		System.out.print(paperDict.size());
// 		//Get gene numbers top 10
// 		Map<String, Integer> gene_top = new HashMap<String, Integer>();
// 		for(var entry : paperDict.entrySet()) {
// //		    System.out.println(entry.getKey() + "/" + entry.getValue());
// 			String key = entry.getKey();
// 			List<articles> papers_num = entry.getValue();
// 			Integer length = papers_num.size();
// 			gene_top.put(key, length);
// 		}
// 		LinkedHashMap<String, Integer> reverseSortedMap = new LinkedHashMap<>();
// 		gene_top.entrySet()
// 	    .stream()
// 	    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())) 
// 	    .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));
// 		Map<String, List<articles>> dataFinal = new HashMap<String, List<articles>>();
// 		int i = 1;
// 		for(var entry:reverseSortedMap.entrySet()) {
// 			String gene = entry.getKey();
// 			List<articles> paper_list = paperDict.get(gene);
// 			if (i <= 10) {
// 				dataFinal.put(entry.getKey(), paper_list);
// 			}else {
// 				break;
// 			}
// 		}
// 		System.out.print(dataFinal);
// 		return dataFinal;
// 	}
	
@RequestMapping("/test")
@ResponseBody
public Map test(Model m) {
	boolean testBoolean = false;
	Map result = new HashMap();
	result.put("testBoolean", false);
	m.addAttribute("testBoolean", testBoolean);
	System.out.println("Test success!");
	return result;
}

}
