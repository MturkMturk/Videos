package com.mturk;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Query;

/**
 * Servlet implementation class ReadFromFile
 */

public class ReadFromFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DatastoreService datastore;
	StringBuilder strLine = null;
	
	@Override
	public void init() throws ServletException {
		// setup datastore service
		datastore = DatastoreServiceFactory.getDatastoreService();
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReadFromFile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*FileInputStream in = null;
		BufferedReader br = null;
		String strLine = "";
		try {
			File file = new File("VideoTask.txt");
			if (!file.exists()) {
				file.createNewFile();
				file.setWritable(true, false);
			}
			in = new FileInputStream(file);
			br = new BufferedReader(new InputStreamReader(in));
			String tmp;
			while ((tmp = br.readLine()) != null) {
				strLine = strLine+tmp+"\n";
			}
		} finally {
			br.close();
			in.close();
		}
		response.getWriter().append(strLine);*/
		
		Query query = new Query("Blogpost").addSort("timestamp");
		List<Entity> blogposts = datastore.prepare(query).asList(FetchOptions.Builder.withLimit(50));
		strLine = new StringBuilder();
		blogposts.forEach(
			    (result) -> {
			    	strLine.append(result.getProperty("body"));
			    	strLine.append("\n");
			    });
		response.setContentType("text/plain");
		response.getWriter().append(strLine);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
