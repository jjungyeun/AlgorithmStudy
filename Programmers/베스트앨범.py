def solution(genres, plays):
    answer = []
    song_dict = dict()
    
    for idx in range(len(genres)):
        genre, play = genres[idx], plays[idx]
        
        # create default dict
        if genre not in song_dict:
            song_dict[genre] = {"play": 0, "first": (-1, -1), "second": (-1, -1)}
        # add play of genre
        song_dict[genre]["play"] += play
        
        # update most play song of genre
        if song_dict[genre]["second"][0] < play:
            if song_dict[genre]["first"][0] < play:
                song_dict[genre]["first"], song_dict[genre]["second"] = (play, idx), song_dict[genre]["first"]
            else:
                song_dict[genre]["second"] = (play, idx)
    
    # sort genre by play
    populars = sorted(song_dict.items(), key=lambda item: -item[1]["play"])
    for genre, info in populars:
        if info["first"][0] != -1:
            answer.append(info["first"][1])
        if info["second"][0] != -1:
            answer.append(info["second"][1])
    
    return answer
