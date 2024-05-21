import java.util.*;

class P240520 {
    public List<Integer> solution(String[] genres, int[] plays) {

        // 1. 많이 재생된 장르 먼저 수록
        // 2. 장르별로 최대 2개, 많이 재생된 순서로 수록
        // 3. 장르 내에서 재생수가 같으면 고유번호 순서로 수록

        // 장르별로 노래 및 총 재생수를 Map에 저장
        Map<String, List<Song>> songInGenres = new HashMap<>();
        Map<String, Integer> playInGenres = new HashMap<>();

        for(int i=0; i < genres.length; i++) {
            String genre = genres[i];
            // 장르별 노래 저장
            songInGenres.putIfAbsent(genre, new ArrayList<>());
            Song newSong = new Song(i, plays[i]);
            songInGenres.get(genre).add(newSong);

            // 장르별 총 재생수 갱신
            playInGenres.putIfAbsent(genre, 0);
            playInGenres.put(genre, playInGenres.get(genre) + plays[i]);
        }

        // 장르명을 총 재생수가 많은 순으로 정렬
        List<String> genreNames = new ArrayList<>(playInGenres.keySet());
        genreNames.sort((o1, o2) -> playInGenres.get(o2).compareTo(playInGenres.get(o1)));

        // 각 장르의 노래들을 정렬 후 최대 2곡 선택하기
        List<Integer> answerList = new ArrayList<>();
        for(String genre: genreNames) {
            List<Song> songs = songInGenres.get(genre);
            Collections.sort(songs);
            for(int i=0; i < Integer.min(2, songs.size()); i++) {
                answerList.add(songs.get(i).songNo);
            }
        }

        return answerList;
    }

    static class Song implements Comparable<Song> {
        int songNo;
        int playCnt;

        public Song(int songNo, int playCnt) {
            this.songNo = songNo;
            this.playCnt = playCnt;
        }

        @Override
        public int compareTo(Song other) {
            if (this.playCnt == other.playCnt) {
                return this.songNo - other.songNo;
            }
            return other.playCnt - this.playCnt;
        }
    }
}