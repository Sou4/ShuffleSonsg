package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pojos.Song;

class SongTest {

	@Test
	void test() {
		Song s =  new Song();
		s.setSongId(1);
		s.setSongName("Song 1");
		assertEquals(s.getSongName(), "Song 1");
		assertEquals(s.getSongId(), 1);
	}

}
