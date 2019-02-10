package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pojos.Song;
import services.MusicService;
import services.ShuffleSongs;

class ShuffleSongsTest {
	MusicService service;
	
	@BeforeEach
	void setUp() throws Exception {
		service = new MusicService();
		service.loadSongs(5);
	}

	@Test
	void testShuffle() {
		@SuppressWarnings("unchecked")
		ArrayList<Song> oldSongList = (ArrayList<Song>) service.songList.clone();
		ShuffleSongs.shuffle(service.songList);
		assertNotSame(oldSongList.get(0), service.songList.get(0));
	}

}
