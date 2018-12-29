package JavaNio;

public class TimeServer {
	public static void main(String[] args) {
		int port = 9090;
		if(args !=null && args.length>0){
			try {
				port = Integer.valueOf(args[0]);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		 MultiplexerTimerServer timeServer=new MultiplexerTimerServer(port);
	     new Thread(timeServer, "NIO-MultiplexerTimeServer-001").start();
	}
}
