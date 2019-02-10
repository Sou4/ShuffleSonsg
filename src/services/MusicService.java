package services;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import pojos.Song;

public class MusicService {
	public ArrayList<Song> songList = new ArrayList<Song>();
	public Scanner sc;
	public void startMusic(InputStream in) throws InterruptedException {
		this.loadSongs(100);
		// Collections.shuffle(this.songList); // In build method which shuffle songs
		ShuffleSongs.shuffle(this.songList);   // Custom method to shuffle songs
		
		System.out.println("Do you want to play all songs ? (Y/N)");
		sc = new Scanner(in);
		String response = sc.nextLine();
		System.out.println("Music starts");
		if ( response.equalsIgnoreCase("Y")) {
			this.startPlayingSongs(0, true, System.in);
		} else {
			this.startPlayingSongs(0, false, System.in);
		}
	}
	
	public boolean startPlayingSongs(int songIndex, boolean playAll, InputStream in) {
		if (songIndex == this.songList.size() - 1) {
			this.playSong(songIndex);
			if (sc != null) {
				sc.close();
			}
			System.out.println("That was last song of the play list hence stopping music");
			return false;
		} else if (songIndex < 0) {
			System.out.println("Sorry their is no previous song available");
			return startPlayingSongs(songIndex + 1, playAll, in);
		}
		this.playSong(songIndex);
		
		if (playAll) {
			return startPlayingSongs(songIndex + 1, playAll, in);
		} else {
			System.out.println("Press (P) for Previous song / Press (N) for Next song");
			sc = new Scanner(in);
			String response = sc.nextLine();
			if (response.equalsIgnoreCase("P")) {
				return startPlayingSongs(songIndex - 1, playAll, in);
			} else  {
				return startPlayingSongs(songIndex + 1, playAll, in);
			}	
		}
	}
	
	public void loadSongs(int noOfSongs) {
		while (true) {
			Song s = new Song();
			s.setSongId(this.songList.size() + 1);
			s.setSongName("Song " + (this.songList.size() + 1));
			this.songList.add(s);
			if (this.songList.size() == noOfSongs) {
				this.displaySongList();
				break;
			}
		}		
	}

	public void displaySongList() {
		System.out.println("Music Play List....");
		this.songList.forEach(song -> {
			System.out.println(song.getSongName());
		});
		System.out.println("================================");
	}
	
	public void playSong(int songIndex) {
		System.out.println("Playing => " + this.songList.get(songIndex).getSongName());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
