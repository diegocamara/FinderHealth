package university.pds.util;

import java.io.IOException;
import java.sql.SQLException;

import org.h2.tools.Server;

public class StartDatabase {
	public static void main(String[] args) throws SQLException, IOException {
		Server server = Server.createTcpServer(args);
		server.start();
		System.in.read();
		server.stop();
	}
}