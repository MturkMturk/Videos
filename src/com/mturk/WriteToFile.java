package com.mturk;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreFailureException;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;


/**
 * Servlet implementation class WriteToFile
 */
public class WriteToFile extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	DatastoreService datastore;
	
	@Override
	public void init() throws ServletException {
		// setup datastore service
		datastore = DatastoreServiceFactory.getDatastoreService();
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WriteToFile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		String value = req.getParameter("params")+" "+dateFormat.format(date)+"\n";
		//writeToFile(value);

		//Entity post = new Entity("Blogpost", new Date().getTime()); // create a new entity
		Entity post = new Entity("Blogpost"); // create a new entity
	
		post.setProperty("body", value);
		if(value != null && !value.equals("")) {
			String videoNumber = value.split(" ",2)[0];
			post.setProperty("videoNumber", videoNumber);
		}
		post.setProperty("timestamp", new Date().getTime());
	
		try {
			datastore.put(post); // store the entity
		  } catch (DatastoreFailureException e) {
		    throw new ServletException("Datastore error", e);
		  }
	}

/*	private void writeToFile(String value) throws IOException {
		FileWriter fw = null;
		File file;
		BufferedWriter bw = null;
		
		try {
			file = new File("VideoTask.txt");
			if (!file.exists()) {
				file.createNewFile();
				file.setWritable(true, false);
				System.out.println("File Created");
			} else {
				file.setWritable(true, false);
				System.out.println("File already present");
			}
			fw = new FileWriter(file.getAbsoluteFile(), true);
			bw = new BufferedWriter(fw);
			if(value != null && value.contains("loaded")) {
				bw.write(value + "\n");
				bw.write("*************************************************************\n");
				bw.write(value + "\n");
			}
			bw.write(value + "\n");
		} finally {
			bw.close();
			fw.close();
		}
		
	}*/

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
