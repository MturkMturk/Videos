package com.mturk;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class ClearFile
 */
public class ClearFile extends HttpServlet {
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
    public ClearFile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*File file = new File("VideoTask.txt");
		if (!file.exists()) {
			file.createNewFile();
			file.setWritable(true, false);
		} else {
			PrintWriter writer = new PrintWriter(file);
			writer.print("");
			writer.close();
		}*/
		Query query = new Query("Blogpost");
		List<Entity> blogposts = datastore.prepare(query).asList(FetchOptions.Builder.withLimit(50));
		blogposts.forEach(
			    (result) -> {
			    	datastore.delete(result.getKey()); //delete it
			    });
		response.getWriter().append("File Content cleared");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
