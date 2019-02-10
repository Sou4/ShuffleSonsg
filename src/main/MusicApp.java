package main;

import services.MusicService;

public class MusicApp {

	static public void startApp(MusicService service) {
		try {
			service.startMusic(System.in);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		MusicService service = new MusicService();
		startApp(service);
	}

}
