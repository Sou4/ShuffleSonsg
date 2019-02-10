package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.Test;
import services.MusicService;

class MusicServiceTest {
	MusicService serv;
	ByteArrayOutputStream outContent;
	InputStream stream;

	@Test
	void testStartMusic() throws InterruptedException {
		outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
		serv = new MusicService();
		String str = "Y\n";
		stream = new ByteArrayInputStream(str.getBytes());
		serv.startMusic(stream);
		assertEquals(100, serv.songList.size());
	}

	@Test
	void testStartPlayingSongsWhenPlayAllIsTrue() {
		outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
		serv = new MusicService();
		serv.loadSongs(5);
		serv.startPlayingSongs(0, true, System.in);
		String output = new String(outContent.toByteArray());
		assertTrue(output.contains("That was last song of the play list hence stopping music"));
	}

	@Test
	void testStartPlayingSongsWhenPlayAllIsFalse() {
		outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
		serv = new MusicService();
		serv.loadSongs(1);
		String str = "N\n";
		stream = new ByteArrayInputStream(str.getBytes());
		serv.startPlayingSongs(0, false, stream);  
		String output = new String(outContent.toByteArray());
		assertTrue(output.contains("That was last song of the play list hence stopping music"));
	}
	
	@Test
	void testLoadSongs() {
		serv = new MusicService();
		serv.loadSongs(5);
		assertEquals(5, serv.songList.size());
	}

	@Test
	void testDisplaySongList() {
		outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    serv = new MusicService();
	    serv.loadSongs(5);
	    serv.playSong(0);
	    String output = new String(outContent.toByteArray());
	    assertTrue(output.contains("Music Play List...."));
	    assertTrue(output.contains("Song 1"));
	    assertTrue(output.contains("================================"));
	}

	@Test
	void testPlaySong() {
		outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    serv = new MusicService();
	    serv.loadSongs(5);
	    serv.playSong(0);
	    String output = new String(outContent.toByteArray());
	    assertTrue(output.contains("Playing => Song 1"));
	}

}
