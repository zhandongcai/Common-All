package com.balance;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**�����ľ����㷨test����ѯ�������hash����Ȩ��ѯ����Ȩ�����
 * @author zhongyi
 *
 */
public class Balance {
	public static Map<String,Integer> serverWeigthMap=new HashMap<String,Integer>();
	public static Integer pos=0;
	
	static{
		serverWeigthMap.put("192.168.0.1", 1);
		serverWeigthMap.put("192.168.0.2", 1);
		serverWeigthMap.put("192.168.0.3", 3);
		serverWeigthMap.put("192.168.0.4",5);
		serverWeigthMap.put("192.168.0.5", 5);
		serverWeigthMap.put("192.168.0.6", 1);
		serverWeigthMap.put("192.168.0.7", 2);
	}
	
	public static String testRoundRobin(){
		
		//���½�һ��map������Ӧ�������ߵ����±�Խ��
		Map<String,Integer> serverMap=new HashMap<String,Integer>();
		serverMap.putAll(serverWeigthMap);
		
		Set<String> keySet=serverMap.keySet();
		ArrayList<String> keyList=new ArrayList<>();
		keyList.addAll(keySet);
		
		String server=null;
		synchronized(pos){
			pos++;
			if(pos>keyList.size())
				pos=0;
			server=keyList.get(pos);
		}
		return server;
	}
	
	
	public static String testRandom(){
		//���½�һ��map������Ӧ�������ߵ����±�Խ��
		Map<String,Integer> serverMap=new HashMap<String,Integer>();
		serverMap.putAll(serverWeigthMap);
		
		Set<String> keySet=serverMap.keySet();
		ArrayList<String> keyList=new ArrayList<>();
		keyList.addAll(keySet);
		
		Random r= new Random();
		return keyList.get(r.nextInt(keyList.size()));
	}
	
	public static String testConsumerHash(String remoteIp){
		//���½�һ��map������Ӧ�������ߵ����±�Խ��
		Map<String,Integer> serverMap=new HashMap<String,Integer>();
		serverMap.putAll(serverWeigthMap);
		
		Set<String> keySet=serverMap.keySet();
		ArrayList<String> keyList=new ArrayList<>();
		keyList.addAll(keySet);
		int serverPos=remoteIp.hashCode()&Integer.MAX_VALUE%keyList.size();
		return keyList.get(serverPos);
	}
	
	
	
	/**��Ȩ��ѯ
	 * @return
	 */
	public static String testWeightRoundRobin(){
		
		//���½�һ��map������Ӧ�������ߵ����±�Խ��
		Map<String,Integer> serverMap=new HashMap<String,Integer>();
		serverMap.putAll(serverWeigthMap);
		
		Set<String> keySet=serverMap.keySet();
		Iterator<String> it=keySet.iterator();
		
		ArrayList<String> serverList=new ArrayList<>();
		while(it.hasNext()){
			String server=it.next();
			Integer weight=serverMap.get(server);
			for (int i = 0; i < weight; i++) {
				serverList.add(server);
			}
			
		}
			
		
		String server=null;
		synchronized(pos){
			pos++;
			if(pos>serverList.size())
				pos=0;
			server=serverList.get(pos);
		}
		return server;
	}
	
	
	public static String testWeightRandom(){
		//���½�һ��map������Ӧ�������ߵ����±�Խ��
		Map<String,Integer> serverMap=new HashMap<String,Integer>();
		serverMap.putAll(serverWeigthMap);
		
		Set<String> keySet=serverMap.keySet();
		Iterator<String> it=keySet.iterator();
		
		ArrayList<String> serverList=new ArrayList<>();
		while(it.hasNext()){
			String server=it.next();
			Integer weight=serverMap.get(server);
			for (int i = 0; i < weight; i++) {
				serverList.add(server);
			}
			
		}
		
		Random r= new Random();
		return serverList.get(r.nextInt(serverList.size()));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
