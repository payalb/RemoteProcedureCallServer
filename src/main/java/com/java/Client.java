package com.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Client {

	public static void main(String[] args) throws IOException {
		// Socket: client code: url, port
		// ServerSocket:port
		ServerSocket ss = new ServerSocket(1564);
		Socket s = ss.accept();// connection has been established
		BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
		int x = Integer.parseInt(reader.readLine());
		int y = Integer.parseInt(reader.readLine());
		String method = reader.readLine();
		long result = 0;
		switch (method.toLowerCase()) {
		case "add":
			// case "Add":
			result = CalculatorService.add(x, y);
			break;
		case "sub":
			result = CalculatorService.sub(x, y);
			break;
		case "mult":
			result = CalculatorService.mul(x, y);
			break;
		default:
			result = CalculatorService.div(x, y);
		}
		PrintWriter writer= new PrintWriter(s.getOutputStream());
		writer.print(result);
		writer.flush();
		s.close();
		ss.close();
	}

}
