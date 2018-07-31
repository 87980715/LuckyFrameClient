package springboot;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;

/**
 * =================================================================
 * ����һ�������Ƶ�������������������κ�δ�������ǰ���¶Գ����������޸ĺ�������ҵ��;��Ҳ������Գ�������޸ĺ����κ���ʽ�κ�Ŀ�ĵ��ٷ�����
 * Ϊ���������ߵ��Ͷ��ɹ���LuckyFrame�ؼ���Ȩ��Ϣ�Ͻ��۸�
 * ���κ����ʻ�ӭ��ϵ�������ۡ� QQ:1573584944  seagull1985
 * =================================================================
 * @author seagull
 * @date 2018��7��27�� ����10:28:32
 */
@RestController
public class HttpImpl {

	/**
	 * �����Զ�������
	 * @param req
	 * @param res
	 * @return
	 * @throws RemoteException
	 */
	@PostMapping("/runtask")
	public String runtask(HttpServletRequest req) throws RemoteException {
		StringBuilder sb = new StringBuilder();
		try (BufferedReader reader = req.getReader();) {
			char[] buff = new char[1024];
			int len;
			while ((len = reader.read(buff)) != -1) {
				sb.append(buff, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		JSONObject jsonObject = JSONObject.parseObject(sb.toString());
		String projectname = jsonObject.getString("projectname");
		String taskid = jsonObject.getString("taskid");
		String loadpath = jsonObject.getString("loadpath");
		System.out.println("��������ģʽ���Գ���...������Ŀ��"+projectname+"  ����ID��"+taskid);
		try{
			File file =new File(System.getProperty("user.dir")+loadpath); 	   
			if  (!file .isDirectory())      
			{       
				System.out.println("�ͻ��˲�������׮·�������ڣ����顾"+file.getPath()+"��");
				return "�ͻ��˲�������׮·�������ڣ����顾"+file.getPath()+"��";
			}
			Runtime run = Runtime.getRuntime();
			StringBuffer sbf=new StringBuffer();
			sbf.append(taskid).append(" ");
			sbf.append(loadpath);
			run.exec("cmd.exe /k start " + "task.cmd" +" "+ sbf.toString(), null,new File(System.getProperty("user.dir")+"\\"));
			
		} catch (Exception e) {		
			e.printStackTrace();
			return "��������ģʽ���Գ����쳣������";
		}
		return "��������ģʽ���Գ�������";
	}
	
	/**
	 * ���е�������
	 * @param req
	 * @param res
	 * @return
	 * @throws RemoteException
	 */
	@PostMapping("/runcase")
	public String runcase(HttpServletRequest req) throws RemoteException {
		StringBuilder sbd = new StringBuilder();
		try (BufferedReader reader = req.getReader();) {
			char[] buff = new char[1024];
			int len;
			while ((len = reader.read(buff)) != -1) {
				sbd.append(buff, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		JSONObject jsonObject = JSONObject.parseObject(sbd.toString());
		String projectname = jsonObject.getString("projectname");
		String taskid = jsonObject.getString("taskid");
		String loadpath = jsonObject.getString("loadpath");
		String testCaseExternalId = jsonObject.getString("testCaseExternalId");
		String version = jsonObject.getString("version");
		System.out.println("����������ģʽ���Գ���...������Ŀ��"+projectname+"  ����ID��"+taskid);
		System.out.println("����������ţ�"+testCaseExternalId+"  �����汾��"+version);
		try{
			File file =new File(System.getProperty("user.dir")+loadpath); 	   
			if  (!file .isDirectory())      
			{   
				System.out.println("�ͻ��˲�������׮·�������ڣ����顾"+file.getPath()+"��");
				return "�ͻ��˲�������׮·�������ڣ����顾"+file.getPath()+"��";
			}
			Runtime run = Runtime.getRuntime();
			StringBuffer sb=new StringBuffer();
			sb.append(taskid).append(" ");
			sb.append(testCaseExternalId).append(" ");
			sb.append(version).append(" ");
			sb.append(loadpath);
			run.exec("cmd.exe /k start " + "task_onecase.cmd" + " " +sb.toString(), null,new File(System.getProperty("user.dir")+"\\"));			
		} catch (Exception e) {		
			e.printStackTrace();
			return "����������ģʽ���Գ����쳣������";
		} 
		return "����������ģʽ���Գ�������";
	}
	
	/**
	 * ������������
	 * @param req
	 * @return
	 * @throws RemoteException
	 */
	@PostMapping("/runbatchcase")
	public String runbatchcase(HttpServletRequest req) throws RemoteException {
		StringBuilder sbd = new StringBuilder();
		try (BufferedReader reader = req.getReader();) {
			char[] buff = new char[1024];
			int len;
			while ((len = reader.read(buff)) != -1) {
				sbd.append(buff, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		JSONObject jsonObject = JSONObject.parseObject(sbd.toString());
		String projectname = jsonObject.getString("projectname");
		String taskid = jsonObject.getString("taskid");
		String loadpath = jsonObject.getString("loadpath");
		String batchcase = jsonObject.getString("batchcase");
		System.out.println("������������ģʽ���Գ���...������Ŀ��"+projectname+"  ����ID��"+taskid);
		System.out.println("��������������"+batchcase);
		try{
			File file =new File(System.getProperty("user.dir")+loadpath); 	   
			if  (!file .isDirectory())      
			{    
				System.out.println("�ͻ��˲�������׮·�������ڣ����顾"+file.getPath()+"��");
				return "�ͻ��˲�������׮·�������ڣ����顾"+file.getPath()+"��";
			}
			Runtime run = Runtime.getRuntime();
			StringBuffer sb=new StringBuffer();
			sb.append(taskid).append(" ");
			sb.append(batchcase).append(" ");
			sb.append(loadpath);
			System.out.println(sb.toString());
			run.exec("cmd.exe /k start " + "task_batch.cmd" + " " +sb.toString(), null,new File(System.getProperty("user.dir")+"\\"));		
		} catch (Exception e) {		
			e.printStackTrace();
			return "������������ģʽ���Գ����쳣������";
		} 
		return "������������ģʽ���Գ�������";
	}
	
	/**
	 * web������Ƚӿ�
	 * @param req
	 * @return
	 * @throws RemoteException
	 */
	@PostMapping("/webdebugcase")
	public String webdebugcase(HttpServletRequest req) throws RemoteException {
		StringBuilder sbd = new StringBuilder();
		try (BufferedReader reader = req.getReader();) {
			char[] buff = new char[1024];
			int len;
			while ((len = reader.read(buff)) != -1) {
				sbd.append(buff, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		JSONObject jsonObject = JSONObject.parseObject(sbd.toString());
		String sign = jsonObject.getString("sign");
		String executor = jsonObject.getString("executor");
		String loadpath = jsonObject.getString("loadpath");
		System.out.println("Web�˵���������"+sign+" �����ˣ�"+executor);
		try{
			File file =new File(System.getProperty("user.dir")+loadpath); 	   
			if  (!file .isDirectory())      
			{    
				System.out.println("�ͻ��˲�������׮·�������ڣ����顾"+file.getPath()+"��");
				return "�ͻ��˲�������׮·�������ڣ����顾"+file.getPath()+"��";
			}
			Runtime run = Runtime.getRuntime();
			StringBuffer sb=new StringBuffer();
			sb.append(sign).append(" ");
			sb.append(executor).append(" ");
			sb.append(loadpath);
			run.exec("cmd.exe /k start " + "web_debugcase.cmd" + " " +sb.toString(), null,new File(System.getProperty("user.dir")+"\\"));			
		} catch (Exception e) {		
			e.printStackTrace();
			return "����Web����ģʽ���Գ����쳣������";
		} 
		return "����Web����ģʽ���Գ�������";
	}
	
	/**
	 * ��ȡ�ͻ��˱�����־
	 * @param req
	 * @return
	 * @throws RemoteException
	 */
	@GetMapping("/getlogdetail")
	public String getlogdetail(HttpServletRequest req) throws RemoteException{
		String fileName=req.getParameter("filename");
		String ctxPath = System.getProperty("user.dir")+"\\log\\";
		String downLoadPath = ctxPath + fileName;

		String str = "";
		InputStreamReader isr=null;
		try {
			isr = new InputStreamReader(new FileInputStream(downLoadPath), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "��ȡ��־·����������ͻ�����־·���Ƿ����!downLoadPath: "+downLoadPath;
		}
		BufferedReader bos = new BufferedReader(isr);
		StringBuffer sb = new StringBuffer();
		try {
			while ((str = bos.readLine()) != null)
			{
				sb.append(str).append("##n##");
			}
			bos.close();
			System.out.println("����˶�ȡ������־�ɹ�!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "�ͻ���תBufferedReaderʧ�ܣ�����ԭ��";
		}
		return sb.toString();
	}
	
	/**
	 * ��ȡ�����ͼ
	 * @param req
	 * @return
	 * @throws RemoteException
	 */
	@GetMapping("/getlogimg")
	public byte[] getlogimg(HttpServletRequest req,HttpServletResponse res) throws RemoteException{
		String imgName=req.getParameter("imgName");
		String ctxPath = System.getProperty("user.dir")+"\\log\\ScreenShot\\";
		String downLoadPath = ctxPath+imgName;
        byte[] b = null;
        try {
            File file = new File(downLoadPath);
            b = new byte[(int) file.length()];
            BufferedInputStream is = new BufferedInputStream(new FileInputStream(file));
            is.read(b);
            is.close();
            System.out.println("����˻�ȡ����ͼƬ��"+downLoadPath);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return b;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return b;
        }     
        return b;
	}
	
	@PostMapping("/uploadjar")
	public String uploadjar(HttpServletRequest req,HttpServletResponse res, HttpSession session,@RequestParam("jarfile") MultipartFile jarfile) throws IOException, ServletException{
		if (!jarfile.isEmpty()){
            if (!FilenameUtils.getExtension(jarfile.getOriginalFilename())
                    .equalsIgnoreCase("jar")) {
                return "�ļ���ʽ��������.jar���ϴ�ʧ��";
            }
		}else{
            return "�ϴ��ļ�Ϊ�գ����飡";
		}

		String name = jarfile.getOriginalFilename();
		String loadpath = req.getParameter("loadpath");
		String path = System.getProperty("user.dir")+loadpath;
		if  (!new File(path) .isDirectory())      
		{    
			System.out.println("�ͻ��˲�������׮·�������ڣ����顾"+path+"��");
			return "�ͻ��˲�������׮·�������ڣ����顾"+path+"��";
		}	
		String pathName = path +"\\"+ name;
		File file = new File(pathName);
        try { 
            if (file.exists()){
            	file.deleteOnExit();
            }
            file.createNewFile();
            BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(file));
            byte[] jarfileByte = jarfile.getBytes();
            os.write(jarfileByte);
            os.flush();
            os.close();
            System.out.println("�ϴ�JAR����"+name+"�����ͻ�������Ŀ¼��"+path+"���ɹ�!");
            return "�ϴ�JAR����"+name+"�����ͻ�������Ŀ¼��"+path+"���ɹ�!";
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "�ͻ���δ�ҵ���ȷ·�����ļ����ϴ�ʧ�ܣ�";
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "�ͻ���IOException";
        }
	}
	
	/**
	 * ���ͻ�������
	 * @param req
	 * @return
	 * @throws RemoteException
	 */
	@GetMapping("/getclientstatus")
	public String getClientStatus(HttpServletRequest req) throws RemoteException{
		return "success";
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
