/*

* Copyright (c) Joan-Manuel Marques 2013. All rights reserved.
* DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
*
* This file is part of the practical assignment of Distributed Systems course.
*
* This code is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* This code is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this code.  If not, see <http://www.gnu.org/licenses/>.
*/

package tcp.client;



import java.io.*;
import java.net.Socket;

import edu.uoc.dpcs.lsim.logger.LoggerManager.Level;
import lsim.library.api.LSimLogger;


/**
 * @author Joan-Manuel Marques
 *
 */

public class HTTPclient {

	public HTTPclient() {
	}
			
	public HTTPrequestResponse get(String http_server_address, int http_server_port){
		LSimLogger.log(Level.INFO, "inici HTTPclient.get ");
		LSimLogger.log(Level.INFO, "HTTP server_address: " + http_server_address);
		LSimLogger.log(Level.INFO, "HTTP server_port: " + http_server_port);

		/* ENVIAR LA PETICIÓ I REBRE LA RESPOSTA / SEND REQUEST AND RECEIVE THE ANSWER / ENVIAR LA PETICIÓN Y RECIBIR LA RESPUESTA */
		String request = "GET /xai/xai.html HTTP/1.0\r\n" +
                "Host: labxarxes.techlab.uoc.edu\r\n" +
                "Accept: text/html\r\n\r\n"; /* petició HTTP / petición HTTP / HTTP request */
		String response =""; /* resposta HTTP / respuesta HTTP / HTTP response */
		
		try {
            //Connect to the server
            Socket socket = new Socket(http_server_address, http_server_port);


            // Send the request to the server
            OutputStream out = socket.getOutputStream();
            out.write(request.getBytes());
            out.flush();

            //Receive and display the response
            BufferedReader in = new BufferedReader(
            		new InputStreamReader(socket.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
                response += line;
                response += "\r\n";
            }

            //Close the socket
            socket.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
	    
		return new HTTPrequestResponse(request, response);
	}
}
