package com.mturk;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ReadFromFile
 */

public class ReadFromFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		FileInputStream in = null;
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
