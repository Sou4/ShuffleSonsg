package services;

import java.util.List;
import java.util.Random;
import pojos.Song;

public class ShuffleSongs {
	public static void shuffle(List<Song> songList) {
        int n = songList.size();
        Random random = new Random();
        random.nextInt();
        for (int i = 0; i < n; i++) {
            int swapTo = i + random.nextInt(n - i);
            swap(songList, i, swapTo);
        }
    }

    private static void swap(List<Song> songList, int i, int swapTo) {
        Song otherSongRef = songList.get(i);
        songList.set(i, songList.get(swapTo));
        songList.set(swapTo, otherSongRef);
    }
}
